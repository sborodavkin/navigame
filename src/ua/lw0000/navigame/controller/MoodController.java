package ua.lw0000.navigame.controller;

import java.util.Random;

import ua.lw0000.navigame.model.Developer;
import ua.lw0000.navigame.model.Office;
import ua.lw0000.navigame.model.Position;
import ua.lw0000.navigame.model.Production;

public class MoodController {

	private Office office;
	private Random moodRandomGenerator;

	public MoodController(Office office) {
		this.office = office;
		moodRandomGenerator = new Random(System.currentTimeMillis());
	}

	public void updateDevelopersPerformance() {		
		for (int i = 0; i < office.getNumRows(); i++) {
			for (int j = 0; j < office.getNumColumns(); j++) {
				Position pos = office.getPosition(i, j);
				if (pos instanceof Developer) {
					Developer dev = (Developer) pos;
					int lowestDecrease = 4;
					if (dev.getState() == Developer.BUGFIX) {
						// bugfixers unhappy
						lowestDecrease = 5;						
					} else if (dev.getState() == Developer.FEATURE) {
						lowestDecrease = 3;
					} else {
						lowestDecrease = 4;
					}					
					float fMood = (float)(moodRandomGenerator.nextInt(lowestDecrease) + (100-lowestDecrease+1)) / 100;					
					float fSalarySat = (float)(moodRandomGenerator.nextInt(2) + 99) / 100;					
					dev.setMood(Math
							.min(Math.round(dev.getMood() * fMood), 100));
					dev.setSalarySatisfaction(Math.min(Math.round(dev
							.getSalarySatisfaction()
							* fSalarySat), 100));
				}
			}
		}
	}

	public void updateProductionPerformance() {
		for (int i = 0; i < office.getNumRows(); i++) {
			for (int j = 0; j < office.getNumColumns(); j++) {
				Position pos = office.getPosition(i, j);
				if (pos instanceof Production) {
					float fMood = (float)(moodRandomGenerator.nextInt(3) + 98) / 100;
					float fSalarySat = (float)(moodRandomGenerator.nextInt(2) + 99) / 100;
					Production prod = (Production) pos;
					prod.setMood(Math.min(Math.round(prod.getMood() * fMood),
							100));
					prod.setSalarySatisfaction(Math.min(Math.round(prod
							.getSalarySatisfaction()
							* fSalarySat), 100));
				}
			}
		}
	}

	/**
	 * Returns the overall performance of developers
	 * 
	 * @return int 0-100
	 */
	public int getDevelopersPerformance() {
		float res = 0;
		int devCount = 0;
		for (int i = 0; i < office.getNumRows(); i++) {
			for (int j = 0; j < office.getNumColumns(); j++) {
				Position pos = office.getPosition(i, j);
				if (pos instanceof Developer) {
					devCount++;
					Developer dev = (Developer) pos;
					float f = dev.getPerformanceMultiplier();
					res += f;
				}
			}
		}
		return Math.round(res * 100 / devCount);
	}

	/**
	 * Returns the overall performance of production engineers
	 * 
	 * @return int 0-100
	 */
	public int getProductionPerformance() {
		float res = 0;
		int prodCount = 0;
		for (int i = 0; i < office.getNumRows(); i++) {
			for (int j = 0; j < office.getNumColumns(); j++) {
				Position pos = office.getPosition(i, j);
				if (pos instanceof Production) {
					prodCount++;
					Production prod = (Production) pos;
					float f = prod.getPerformanceMultiplier();
					res += f;
				}
			}
		}
		return Math.round(res * 100 / prodCount);
	}
}
