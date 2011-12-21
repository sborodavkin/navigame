package ua.lw0000.navigame.model;

import ua.lw0000.navigame.controller.ShoutController.ShoutPosition;
import ua.lw0000.navigame.main.Composition;

/**
 * The office where the game takes place
 * 
 */
public class Office {

	private int numColumns;
	private int numRows;

	private Position[][] room;

	/**
	 * Creates an empty Office of given size
	 * 
	 * @param cols
	 * @param rows
	 */
	public Office(int rows, int cols) {
		numColumns = cols;
		numRows = rows;
		room = new Position[numRows][numColumns];
	}

	public void setPosition(int row, int col, Position position) {
		room[row][col] = position;
	}

	public Position getPosition(int row, int col) {
		return room[row][col];
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public int getTotalSalary() {
		int res = 0;
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				if (getPosition(i, j) instanceof Resource) {
					res += ((Resource) getPosition(i, j)).getSalary();
				}
			}
		}
		return res;
	}

	public int getNumDevelopers() {
		int res = 0;
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				if (getPosition(i, j) instanceof Developer) {
					res++;
				}
			}
		}
		return res;
	}

	public int getNumDevelopersInState(int state) {
		int res = 0;
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				if (getPosition(i, j) instanceof Developer) {
					Developer dev = (Developer) getPosition(i, j);
					if (dev.getState() == state) {
						res++;
					}
				}
			}
		}
		return res;
	}
	
	public int getNumDevelopersOfFeature(int feature, boolean active) {
		int res = 0;
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				if (getPosition(i, j) instanceof Developer) {
					Developer dev = (Developer) getPosition(i, j);
					if (dev.getFeature() == feature && (active && dev.getState() == Developer.FEATURE || !active)) {
						res++;
					}
				}
			}
		}
		return res;
	}
	
	

	public int getNumProduction() {
		int res = 0;
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				if (getPosition(i, j) instanceof Production) {
					res++;
				}
			}
		}
		return res;
	}

	public int getNumProductionInState(int state) {
		int res = 0;
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				if (getPosition(i, j) instanceof Production) {
					Production prod = (Production) getPosition(i, j);
					if (prod.getState() == state) {
						res++;
					}
				}
			}
		}
		return res;
	}

	public Production getFirstProductionInState(int state) {
		Production res = null;
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				if (getPosition(i, j) instanceof Production) {
					Production prod = (Production) getPosition(i, j);
					if (prod.getState() == state) {
						res = prod;
						break;
					}
				}
			}
		}
		return res;
	}

	public Developer getFirstDeveloperInState(int state) {
		Developer res = null;
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				if (getPosition(i, j) instanceof Developer) {
					Developer dev = (Developer) getPosition(i, j);
					if (dev.getState() == state) {
						res = dev;
						break;
					}
				}
			}
		}
		return res;
	}
	
	public Developer getFirstDeveloperOfFeatureInState(int feature, int state) {
		Developer res = null;
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				if (getPosition(i, j) instanceof Developer) {
					Developer dev = (Developer) getPosition(i, j);
					if (dev.getState() == state && dev.getFeature() == feature) {
						res = dev;
						break;
					}
				}
			}
		}
		return res;
	}
	
	public int getNumDevelopersOfFeatureInState(int feature, int state) {
		int res = 0;
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				if (getPosition(i, j) instanceof Developer) {
					Developer dev = (Developer) getPosition(i, j);
					if (dev.getState() == state && dev.getFeature() == feature) {
						res++;
					}
				}
			}
		}
		return res;
	}
	

	public ShoutPosition getAnchorForPosition(Position pos) {
		for (int i = 0; i < getNumRows(); i++) {
			for (int j = 0; j < getNumColumns(); j++) {
				Position position = getPosition(i, j);
				if (position != null && position.equals(pos)) {
					return new ShoutPosition(Composition.ROOM_NW + j
							* Composition.ROOM_CELL_SIZE, Composition.ROOM_NW
							+ i * Composition.ROOM_CELL_SIZE);
				}
			}
		}
		return null;
	}

	public boolean isInOffice(Position pos) {
		for (int i = 0; i < getNumRows(); i++) {
			for (int j = 0; j < getNumColumns(); j++) {
				Position position = getPosition(i, j);
				if (position != null && pos != null
						&& position.getTitle() == pos.getTitle()
						&& pos.getClass() == position.getClass()) {
					return true;
				}
			}
		}
		return false;
	}
}
