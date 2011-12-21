package ua.lw0000.navigame.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import ua.lw0000.navigame.controller.ShoutController.ShoutPosition;
import ua.lw0000.navigame.main.Composition;
import ua.lw0000.navigame.model.Developer;
import ua.lw0000.navigame.model.GameState;
import ua.lw0000.navigame.model.Map;
import ua.lw0000.navigame.model.Office;
import ua.lw0000.navigame.model.Production;
import ua.lw0000.navigame.model.ProductionPlan;
import ua.lw0000.navigame.model.Shout;

/**
 * Controls the maps: production and bugfixes
 * 
 * @author SBorodavkin
 * 
 */
public class MapController {

	public static final int BUG_PROBABILITY = 1; // in percent 0-100

	private HWController hwController;
	private GameState gameState;

	/**
	 * All maps
	 */
	private java.util.Map<String, Map> allMaps;

	private ProductionPlan productionPlan;

	private java.util.Map<Production, Map> productionToMap;
	private java.util.Map<Developer, Map> developerToMap;

	private Random bugRandomGenerator;

	private MoneyController moneyController;

	private Office office;

	@SuppressWarnings("serial")
	public MapController(GameState gameState, MoneyController moneyController,
			HWController hwCtrl, Office office) {
		this.gameState = gameState;
		this.moneyController = moneyController;
		this.hwController = hwCtrl;
		this.office = office;
		productionPlan = new ProductionPlan();
		productionToMap = new HashMap<Production, Map>();
		developerToMap = new HashMap<Developer, Map>();
		bugRandomGenerator = new Random(System.currentTimeMillis());
		allMaps = new HashMap<String, Map>() {
			{
				put(ProductionPlan.HARSUM, new Map(ProductionPlan.HARSUM,
						10000, 1500));
				put(ProductionPlan.MUNICH, new Map(ProductionPlan.MUNICH,
						20000, 3000));
				put(ProductionPlan.HAMBURG, new Map(ProductionPlan.HAMBURG,
						25000, 4000));
				put(ProductionPlan.SWISS, new Map(ProductionPlan.SWISS, 60000,
						6000));
				put(ProductionPlan.DACHI, new Map(ProductionPlan.DACHI, 100000,
						9000));
				put(ProductionPlan.ECE, new Map(ProductionPlan.ECE, 180000,
						12000));

			}
		};

	}

	public ProductionPlan getProductionPlan() {
		return productionPlan;
	}

	public List<Map> getAvailableMaps() {
		ArrayList<Map> maps = new ArrayList<Map>();

		if (gameState.getCompilerComplete() >= 40) {
			maps.add(allMaps.get(ProductionPlan.HARSUM));
		}
		if (gameState.getCompilerComplete() >= 50) {
			maps.add(allMaps.get(ProductionPlan.MUNICH));
		}
		if (gameState.getCompilerComplete() >= 60) {
			maps.add(allMaps.get(ProductionPlan.HAMBURG));
		}
		if (gameState.getCompilerComplete() >= 70) {
			maps.add(allMaps.get(ProductionPlan.SWISS));
		}
		if (gameState.getCompilerComplete() >= 80) {
			maps.add(allMaps.get(ProductionPlan.DACHI));
		}
		if (gameState.getCompilerComplete() >= 90) {
			maps.add(allMaps.get(ProductionPlan.ECE));
		}

		return maps;
	}

	public void addProductionMap(Production prod, Map map) {
		if (!productionToMap.keySet().contains(prod)) {
			productionToMap.put(prod, map);
		}
	}

	public void addFixMap(Developer dev, Map map) {
		if (!developerToMap.keySet().contains(dev)) {
			developerToMap.put(dev, map);
		}
	}

	public Map getProductionMap(Production prod) {
		return productionToMap.get(prod);
	}

	public Map getBugfixMap(Developer dev) {
		return developerToMap.get(dev);
	}

	public boolean isMapUnderBugfix(Map mapFix) {
		Iterator<Developer> it = developerToMap.keySet().iterator();
		while (it.hasNext()) {
			Developer dev = it.next();
			Map map = developerToMap.get(dev);
			if (mapFix.getTitle().equals(map.getTitle())) {
				return true;
			}
		}
		return false;
	}

	public int howManyUnderProduction(Map map) {
		int res = 0;
		Iterator<Production> it = productionToMap.keySet().iterator();
		while (it.hasNext()) {
			Map nextMap = productionToMap.get(it.next());
			if (nextMap.getTitle().equals(map.getTitle())) {
				res++;
			}
		}
		return res;
	}

	public int howManyUnderBugfix(Map map) {
		int res = 0;
		Iterator<Developer> it = developerToMap.keySet().iterator();
		while (it.hasNext()) {
			Map nextMap = developerToMap.get(it.next());
			if (nextMap.getTitle().equals(map.getTitle())) {
				res++;
			}
		}
		return res;
	}

	public boolean updateProduction() {
		Iterator<Production> it = productionToMap.keySet().iterator();
		List<Production> toFree = new ArrayList<Production>();
		while (it.hasNext()) {
			Production prod = it.next();
			Map map = productionToMap.get(prod);
			if (!map.isBlocked()) {
				map.msecPassed(Math.round(TimeController.PRODUCTION_INTERVAL
						* prod.getPerformanceMultiplier()
						* hwController.getHwMultiplier()));
				if (map.getMSecondsPassed() >= map.getTotalMSecToCompile()) {
					// map finished
					// update production plan
					ShoutPosition shoutPos = gameState.getController()
							.getAnchorForPosition(prod);
					gameState
							.getController()
							.getShoutController()
							.addShout(
									new Shout(map.getTitle() + " compiled!",
											Shout.GENERAL, shoutPos.getX(),
											shoutPos.getY(), 100, 0.05f));
					productionPlan.markMapCompiled(map.getTitle());
					moneyController.makePaymentForMap(map.getTitle());
					// stop this production and cancel those productions
					// which were building the same map in parallel
					if (productionPlan.getNumToCompile(map.getTitle()) != null
							&& productionPlan.getNumToCompile(map.getTitle()) == 0) {
						cancelProductions(map.getTitle(), toFree);
					} else {
						// only stop this production
						toFree.add(prod);
					}
				} else {
					// bug is possible
					double bug = bugRandomGenerator.nextDouble();

					// If there's any DBTests developer - decrease bug
					// probability
					float bugProbabilityDecrement = 1;
					if (office.getNumDevelopersOfFeature(
							Developer.FEATURE_DBTEST, true) > 0) {
						bugProbabilityDecrement = 1.5f;
					}

					if (Math.round(Math.abs(bug * 100)
							* bugProbabilityDecrement) < BUG_PROBABILITY) {
						// bug happened, sniff
						// block all productions of such map
						Iterator<Production> itBlock = productionToMap.keySet()
								.iterator();
						while (itBlock.hasNext()) {
							Production prodBlock = itBlock.next();
							Map mapBlock = productionToMap.get(prodBlock);
							if (mapBlock.getTitle().equals(map.getTitle())) {
								mapBlock.resetBugfixIntervalsPassed();
								mapBlock.setBlocked(true);
							}
						}
						// disable ability to start compilations of such map
						allMaps.get(map.getTitle()).setBlocked(true);
						allMaps.get(map.getTitle())
								.resetBugfixIntervalsPassed();
						shoutBug(map);
					}
				}
			}
		}

		// free production guys
		it = toFree.iterator();
		while (it.hasNext()) {
			Production prod = it.next();
			productionToMap.remove(prod);
			prod.setState(Production.FREE);
		}

		return true;
	}

	private void shoutBug(Map map) {
		int index = 0;
		if (map.getTitle().equals(ProductionPlan.HARSUM)) {
			index = 0;
		} else if (map.getTitle().equals(ProductionPlan.MUNICH)) {
			index = 1;
		} else if (map.getTitle().equals(ProductionPlan.HAMBURG)) {
			index = 2;
		} else if (map.getTitle().equals(ProductionPlan.SWISS)) {
			index = 3;
		} else if (map.getTitle().equals(ProductionPlan.DACHI)) {
			index = 4;
		} else if (map.getTitle().equals(ProductionPlan.ECE)) {
			index = 5;
		}

		ShoutPosition shoutPos = new ShoutPosition(Composition.getMapW(index),
				Composition.AVAIL_MAP_N);
		gameState
				.getController()
				.getShoutController()
				.addShout(
						new Shout(ShoutGenerator.generateBug(map.getTitle()),
								Shout.BUG_NAME, shoutPos.getX(), shoutPos
										.getY(), 70, 0.02f));
	}

	private void cancelProductions(String mapTitle, List<Production> toFree) {
		Iterator<Production> it = productionToMap.keySet().iterator();
		while (it.hasNext()) {
			Production prod = it.next();
			Map map = productionToMap.get(prod);
			if (map.getTitle().equals(mapTitle)) {
				toFree.add(prod);
			}
		}
	}

	public void updateBugfix() {
		Iterator<Developer> it = developerToMap.keySet().iterator();

		while (it.hasNext()) {
			Developer dev = it.next();
			Map map = developerToMap.get(dev);

			// overtimes
			float overtimeBoost = 1;
			if (gameState.getController().getMoneyController().isOvertime()) {
				overtimeBoost = MoneyController.COMPILER_OVERTIME_BOOST;
			}

			map.bugfixMSecPassed(Math.round(TimeController.PRODUCTION_INTERVAL
					* dev.getPerformanceMultiplier() * overtimeBoost));
			if (map.getBugfixMSecondsPassed() >= map.getTotalMSecondsToBugfix()) {
				// bugfix finished
				// unblock ability to build such maps
				map.setBlocked(false);
				map.resetBugfixIntervalsPassed();
				it.remove();
				dev.setState(Developer.FREE);
				// unblock all productions
				Iterator<Production> itBlock = productionToMap.keySet()
						.iterator();
				while (itBlock.hasNext()) {
					Production prodBlock = itBlock.next();
					Map mapBlock = productionToMap.get(prodBlock);
					mapBlock.setBlocked(false);
					mapBlock.resetBugfixIntervalsPassed();
				}
			}
		}
	}

	public int getPercentComplete() {
		int res = 0;
		int totalMSec = 0;
		Iterator<String> it = allMaps.keySet().iterator();
		while (it.hasNext()) {
			String mapTitle = it.next();
			Map map = allMaps.get(mapTitle);
			totalMSec += map.getTotalMSecToCompile() * ProductionPlan.mapNumProductions.get(mapTitle); 
			res += productionPlan.getNumToCompile(mapTitle) * map.getTotalMSecToCompile();
		}
		return Math.round(((float)(totalMSec - res) / totalMSec) * 100);
	}
}
