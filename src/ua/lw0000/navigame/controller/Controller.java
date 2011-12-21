package ua.lw0000.navigame.controller;

import java.util.Iterator;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ua.lw0000.navigame.controller.ShoutController.ShoutPosition;
import ua.lw0000.navigame.main.Composition;
import ua.lw0000.navigame.main.Context;
import ua.lw0000.navigame.model.Developer;
import ua.lw0000.navigame.model.GameState;
import ua.lw0000.navigame.model.Map;
import ua.lw0000.navigame.model.Office;
import ua.lw0000.navigame.model.Position;
import ua.lw0000.navigame.model.Production;
import ua.lw0000.navigame.model.ProductionPlan;
import ua.lw0000.navigame.model.ProductionPlan.MapState;
import ua.lw0000.navigame.model.Shout;
import ua.lw0000.navigame.render.GameStateRenderer;
import ua.lw0000.navigame.render.MapRenderer;
import ua.lw0000.navigame.render.NPCRenderer;
import ua.lw0000.navigame.render.Renderer;
import ua.lw0000.navigame.render.RendererFactory;
import ua.lw0000.navigame.render.ResourceRenderer;
import ua.lw0000.navigame.render.ShoutRenderer;

/**
 * The overall game controller.
 * 
 * @author SBorodavkin
 * 
 */
public class Controller {

	/**
	 * Initial % of compiler feature-completeness
	 */
	private static final int INIT_COMPILER_COMPLETE = 40;

	private Context context;
	private Office office;
	private GameState gameState;
	private RendererFactory rendererFactory;
	private TimeController timeController;
	private MapController mapController;
	private MoneyController moneyController;
	private MoodController moodController;
	private ShoutController shoutController;
	private NPCController npcController;
	private HWController hwController;
	private GameStateRenderer gameStateRenderer;
	private MapRenderer mapRenderer;
	private ShoutRenderer shoutRenderer;
	private NPCRenderer npcRenderer;

	/**
	 * The constructor
	 * 
	 * @param context
	 *            game context
	 */
	public Controller(Context context) {
		this.context = context;
		initOffice();
		initGameState();
		this.rendererFactory = new RendererFactory(context, mapController);
	}

	public Context getContext() {
		return context;
	}

	public ShoutController getShoutController() {
		return shoutController;
	}

	public void setShoutController(ShoutController shoutController) {
		this.shoutController = shoutController;
	}

	private void initGameState() {
		gameState = new GameState();
		gameState.setController(this);
		gameState.setMoney(2000);
		gameState.setTime(0);
		gameState.setCompilerComplete(INIT_COMPILER_COMPLETE);
		moneyController = new MoneyController(gameState, office);
		hwController = new HWController(moneyController);
		moodController = new MoodController(office);
		mapController = new MapController(gameState, moneyController,
				hwController, office);
		shoutController = new ShoutController(office);
		npcController = new NPCController(context, office);
		this.timeController = new TimeController(gameState, moneyController,
				new CompilerController(gameState, office, moodController,
						hwController), mapController, moodController,
				shoutController, npcController);
		gameStateRenderer = new GameStateRenderer(moneyController,
				timeController, moodController, hwController, context);
		mapRenderer = new MapRenderer(mapController, context);
		shoutRenderer = new ShoutRenderer(shoutController, context);
		npcRenderer = new NPCRenderer(npcController, context);
	}

	private void initOffice() {
		office = new Office(Composition.ROOM_ROWS, Composition.ROOM_COLS);
		office.setPosition(2, 3, new Developer());
	}

	/**
	 * Render global background
	 */
	public void renderBackground(Graphics g) {
		context.getBackground().draw(0,0);
	}

	/**
	 * Renders the office with resources
	 * 
	 * @throws SlickException
	 */
	public void renderOffice() throws SlickException {
		for (int i = 0; i < office.getNumRows(); i++) {
			for (int j = 0; j < office.getNumColumns(); j++) {
				Position position = office.getPosition(i, j);
				Renderer renderer = rendererFactory.getRenderer(position,
						Composition.ROOM_NW + j * Composition.ROOM_CELL_SIZE,
						Composition.ROOM_NW + i * Composition.ROOM_CELL_SIZE);
				renderer.updateMouseAreas(Composition.ROOM_NW + j
						* Composition.ROOM_CELL_SIZE, Composition.ROOM_NW + i
						* Composition.ROOM_CELL_SIZE);
				Image image = renderer.render();
				if (image != null) {
					image.draw(Composition.ROOM_NW + j
							* Composition.ROOM_CELL_SIZE, Composition.ROOM_NW
							+ i * Composition.ROOM_CELL_SIZE);
				}

			}
		}
	}

	/**
	 * Gets the global point of top-left corner of a given position
	 * 
	 * @param pos
	 *            position to process
	 * @return point or null if there's no such position in the office
	 */
	public ShoutPosition getAnchorForPosition(Position pos) {
		return office.getAnchorForPosition(pos);
	}

	/**
	 * Renders the shouts
	 * 
	 * @throws SlickException
	 */
	public void renderShouts() throws SlickException {
		Image img = shoutRenderer.render();
		img.draw(0, 0);
	}

	/**
	 * Renders non-playing characters
	 * 
	 * @throws SlickException
	 */
	public void renderNPCs() throws SlickException {
		Image img = npcRenderer.render();
		img.draw(0, 0);
	}

	/**
	 * Renders the map toolbar
	 * 
	 * @throws SlickException
	 */
	public void renderAvailableMaps() throws SlickException {
		Image img = mapRenderer.render();
		img.draw(Composition.getMapW(0), Composition.AVAIL_MAP_N);
	}

	/**
	 * Renders the game state panel
	 * 
	 * @throws SlickException
	 */
	public void renderGameStatePanel() throws SlickException {
		Image image = gameStateRenderer.render();
		image.draw(Composition.LEFT_PANEL_W, Composition.ROOM_NW);

	}

	/**
	 * Handles the time increment
	 * 
	 * @param delta
	 *            delta in ms passed since previous call
	 * 
	 * @return result of TimeController.processTimeEvent
	 */
	public boolean processTimeEvent(int delta) {
		return timeController.processTimeEvent(delta);
	}

	/**
	 * Handles the mouse click on the office
	 * 
	 * @param mouseX
	 *            x global mouse coordinate
	 * @param mouseY
	 *            y global mouse coordinate
	 * @param isRightMouseButton
	 *            is right button?
	 */
	public void processOfficeClick(int mouseX, int mouseY,
			boolean isRightMouseButton) {
		if ((mouseX >= Composition.ROOM_NW && mouseX < Composition.ROOM_NW
				+ Composition.ROOM_COLS * Composition.ROOM_CELL_SIZE)
				&& (mouseY >= Composition.ROOM_NW && mouseY < Composition.ROOM_NW
						+ Composition.ROOM_ROWS * Composition.ROOM_CELL_SIZE)) {
			int officeRow = (mouseY - Composition.ROOM_NW)
					/ Composition.ROOM_CELL_SIZE;
			int officeCol = (mouseX - Composition.ROOM_NW)
					/ Composition.ROOM_CELL_SIZE;

			if (officeRow >= 0 && officeRow < Composition.ROOM_ROWS
					&& officeCol >= 0 && officeCol < Composition.ROOM_COLS) {
				Position position = office.getPosition(officeRow, officeCol);
				handlePositionClick(officeRow, officeCol, position, mouseX,
						mouseY, isRightMouseButton);
			}
		}
	}

	/**
	 * Handles the click on the map toolbar
	 * 
	 * @param mouseX
	 *            x mouse global coord
	 * @param mouseY
	 *            y mouse global coord
	 * @param isRightMouseButton
	 *            is right button?
	 */
	public void processMapClick(int mouseX, int mouseY,
			boolean isRightMouseButton) {
		if (isRightMouseButton) {
			return; // not supported
		}
		List<Map> maps = mapController.getAvailableMaps();
		if ((mouseX >= Composition.getMapW(0) && mouseX < Composition
				.getMapW(maps.size() - 1) + Composition.MAP_WIDTH)
				&& (mouseY >= Composition.AVAIL_MAP_N && mouseY < Composition.AVAIL_MAP_N
						+ Composition.MAP_HEIGHT)) {
			int mapCol = (mouseX - Composition.getMapW(0))
					/ (Composition.MAP_WIDTH + Composition.AVAIL_MAP_STEP);
			if (mapCol >= 00 && mapCol < maps.size()) {
				handleMapClick(maps.get(mapCol));
			}
		}
	}

	private void handlePositionClick(int row, int col, Position position,
			int mouseX, int mouseY, boolean isRightMouseButton) {
		if (position instanceof Developer) {
			if (isRightMouseButton) {
				// fire if does not fix maps
				if (mapController.getBugfixMap((Developer) position) == null) {
					office.setPosition(row, col, new Position());
				}
			} else {
				if (!ResourceRenderer.isWithinMouseAreas(row, col, mouseX,
						mouseY)) {
					Developer developer = (Developer) position;
					if (developer.getState() == Developer.FREE) {
						developer.setState(Developer.FEATURE);
					} else if (developer.getState() == Developer.FEATURE) {
						developer.setState(Developer.FREE);
					}
				}
			}
		} else if (position instanceof Production) {
			if (isRightMouseButton) {
				// fire if does not build maps
				if (mapController.getProductionMap((Production) position) == null) {
					office.setPosition(row, col, new Position());
				}
			}
		} else {
			if (!isRightMouseButton) {
				// add developer
				office.setPosition(row, col, new Developer());
			} else {
				// add production
				office.setPosition(row, col, new Production());
			}
		}
	}

	private void handleMapClick(Map map) {
		Integer numToCompile = mapController.getProductionPlan()
				.getNumToCompile(map.getTitle());
		if (numToCompile != null && numToCompile > 0) {
			if (!map.isBlocked()) {
				// find production guy to build the map
				Production freeProduction = office
						.getFirstProductionInState(Production.FREE);
				if (freeProduction != null) {
					try {
						freeProduction.setState(Production.PRODUCTION);
						Map realMap = (Map) map.clone();
						mapController.addProductionMap(freeProduction, realMap);
						ShoutPosition shoutPos = gameState.getController()
								.getAnchorForPosition(freeProduction);
						gameState
								.getController()
								.getShoutController()
								.addShout(
										new Shout("Starting " + map.getTitle()
												+ "!", Shout.GENERAL, shoutPos
												.getX(), shoutPos.getY(), 100,
												0.05f));

					} catch (CloneNotSupportedException cnse) {
						// noop - can't happen
					}
				}
			} else {
				if (!mapController.isMapUnderBugfix(map)) {
					// find developer to fix the bug
					Developer freeDeveloper = office
							.getFirstDeveloperOfFeatureInState(
									Developer.FEATURE_ANACONDA, Developer.FREE);
					if (freeDeveloper != null) {
						freeDeveloper.setState(Developer.BUGFIX);
						// note that a global map instance is passed here
						mapController.addFixMap(freeDeveloper, map);
					}
				}
			}
		}
	}

	public MoneyController getMoneyController() {
		return moneyController;
	}

	/**
	 * Calculates the overall game score.
	 * 
	 * The result is a sum of: (a) money for all compiled maps; (b) cost of
	 * current HW; (c) money left; (d) (compiler % - 40) multiplied by 2,5 * 100
	 * (assumption that in average 2.5 developers work on a compiler and their
	 * salary is $100)
	 * 
	 * @return
	 */
	public int calculateScore() {
		int res = 0;

		ProductionPlan prodPlan = mapController.getProductionPlan();
		java.util.Map<String, MapState> planStatus = prodPlan.getStatus();
		Iterator<String> it = planStatus.keySet().iterator();
		while (it.hasNext()) {
			String mapTitle = it.next();
			MapState mapState = planStatus.get(mapTitle);
			res += moneyController.getPaymentForMap(mapTitle)
					* mapState.getNumCompiled();
		}

		res += HWController.getPaymentForHWType(hwController.getHW());

		res += gameState.getMoney();

		res += (gameState.getCompilerComplete() - INIT_COMPILER_COMPLETE) * 2500;

		return res;
	}

	public MapController getMapController() {
		return mapController;
	}

	public GameState getGameState() {
		return gameState;
	}
	
	
	
	

}
