package ua.lw0000.navigame.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import ua.lw0000.navigame.model.Developer;
import ua.lw0000.navigame.model.Office;
import ua.lw0000.navigame.model.Position;
import ua.lw0000.navigame.model.Production;
import ua.lw0000.navigame.model.Shout;

public class ShoutController {

	private Map<Shout, ShoutPosition> shouts;
	private Random horRandomShift;
	private Office office;

	private float HOR_SHOUT_GENERAL_DEVIATION = 1f;
	private float HOR_SHOUT_BUG_DEVIATION = 0.5f;
	private float HOR_SHOUT_CURSE_DEVIATION = 0.8f;

	private Random rand;
	
	/** trigger: false = generate for developers, true = for production */
	private boolean triggerDevProd = false;

	public ShoutController(Office office) {
		this.shouts = new HashMap<Shout, ShoutController.ShoutPosition>();
		horRandomShift = new Random(System.currentTimeMillis());
		this.office = office;
		this.rand = new Random(System.currentTimeMillis());
	}

	public void addRandomShouts() {
		// developers
		List<Position> list = new ArrayList<Position>();
		for (int i = 0; i < office.getNumRows(); i++) {
			for (int j = 0; j < office.getNumColumns(); j++) {
				Position pos = office.getPosition(i, j);
				if (triggerDevProd == false) {
					if (pos instanceof Developer) {
						list.add((Developer)pos);					
					}
				} else {
					if (pos instanceof Production) {
						list.add((Production)pos);					
					}
				}
			}
		}
		if (list.size() > 0) {		
			int index = rand.nextInt(list.size());
			ShoutPosition shoutPos = office
					.getAnchorForPosition(list.get(index));
			if (triggerDevProd == false) {
				addShout(new Shout(
						ShoutGenerator.generateDeveloperCurse((Developer)list.get(index)),
						Shout.CURSE, shoutPos.getX(), shoutPos.getY(),
						100, 0.05f));
			} else {
				addShout(new Shout(
						ShoutGenerator.generateProductionCurse((Production)list.get(index)),
						Shout.CURSE, shoutPos.getX(), shoutPos.getY(),
						100, 0.05f));
			}
		}
		
		// invert trigger to make another class of people curse next time
		triggerDevProd = !triggerDevProd;
	}

	public void updateShouts(int deltaMs) {
		Iterator<Shout> it = shouts.keySet().iterator();
		while (it.hasNext()) {
			Shout shout = it.next();
			ShoutPosition pos = shouts.get(shout);
			if (shout.getStartN() - pos.getY() >= shout.getFlightLength()) {
				it.remove();
			} else {
				float deviation = 0;
				switch (shout.getType()) {
				case Shout.BUG_NAME:
					deviation = HOR_SHOUT_BUG_DEVIATION;
					break;
				case Shout.CURSE:
					deviation = HOR_SHOUT_CURSE_DEVIATION;
					break;
				default:
					deviation = HOR_SHOUT_GENERAL_DEVIATION;
					break;
				}
				float deltaY = deltaMs * shout.getSpeed();
				float deltaX = horRandomShift.nextFloat() * deviation;
				if (horRandomShift.nextFloat() > 0.5) {
					deltaX = 0 - deltaX;
				}
				pos.setY(pos.getY() - deltaY);
				pos.setX(pos.getX() + deltaX);
			}
		}
	}

	public void addShout(Shout shout) {
		shouts.put(shout,
				new ShoutPosition(shout.getStartW(), shout.getStartN()));
	}

	public Set<Shout> getShouts() {
		return shouts.keySet();
	}

	public ShoutPosition getPosition(Shout shout) {
		return shouts.get(shout);
	}

	public static class ShoutPosition {
		private float x;
		private float y;

		public ShoutPosition(float x, float y) {
			super();
			this.x = x;
			this.y = y;
		}

		public float getX() {
			return x;
		}

		public void setX(float x) {
			this.x = x;
		}

		public float getY() {
			return y;
		}

		public void setY(float y) {
			this.y = y;
		}
	}
}
