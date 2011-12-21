package ua.lw0000.navigame.controller;

import ua.lw0000.navigame.model.Developer;
import ua.lw0000.navigame.model.GameState;
import ua.lw0000.navigame.model.Office;

/**
 * Controller for the compiler feature development
 * 
 * @author SBorodavkin
 *
 */
public class CompilerController {
	private static final double INCREMENT_PER_DEV_PER_MSEC = 2; // in percent 0-100
	
	private GameState gameState;
	private Office office;
	private MoodController moodController;
	private HWController hwController;

	/**
	 * The constructor
	 * 
	 * @param gameState
	 * @param office
	 * @param moodCtrl
	 * @param hwCtrl
	 */
	public CompilerController(GameState gameState, Office office, MoodController moodCtrl, HWController hwCtrl) {
		this.gameState = gameState;
		this.office = office;
		this.moodController = moodCtrl;
		this.hwController = hwCtrl;
	}
	
	/**
	 * Increments the compiler feature complete percentage based on the amount of developers,
	 * their performance and the HW.
	 * 
	 */
	public void developFeatures() {
		int numDevelopers = office.getNumDevelopersOfFeatureInState(Developer.FEATURE_ANACONDA, Developer.FEATURE);
		double multiplier = numDevelopers;
		if (numDevelopers > 3) {
			multiplier = Math.log(numDevelopers);
		}			
		double totalIncrement = (INCREMENT_PER_DEV_PER_MSEC / TimeController.COMPILER_INTERVAL)
				* multiplier;
		gameState.setEffortsCompiler(gameState.getEffortsCompiler() + numDevelopers);
		// consider overall team performance
		totalIncrement = totalIncrement * ((float)moodController.getDevelopersPerformance() / 100);
		if (gameState.getController().getMoneyController().isOvertime()) {
			totalIncrement = totalIncrement * MoneyController.COMPILER_OVERTIME_BOOST;
		}
		
		// the higher the feature complete percentage, the lowest development speed is
		float speedDecrement = 1;
		if (gameState.getCompilerComplete() >= 90) {
			speedDecrement = 0.4f;
		} else if (gameState.getCompilerComplete() >= 80) {
			speedDecrement = 0.5f;
		} else if (gameState.getCompilerComplete() >= 70) {
			speedDecrement = 0.7f;
		} else if (gameState.getCompilerComplete() >= 60) {
			speedDecrement = 0.8f;
		}
		totalIncrement = totalIncrement * speedDecrement * hwController.getHwMultiplier();
		
		// If there's any active NDSLib developer - double the speed
		if (office.getNumDevelopersOfFeature(Developer.FEATURE_NDSLIB, true) > 0) {
			totalIncrement *= 2;
		}
				
		if (gameState.getCompilerComplete() < 100) {
			double newTotalComplete = gameState.getCompilerComplete()
					+ (gameState.getCompilerComplete() * totalIncrement) / 100;			
			gameState.setCompilerComplete(newTotalComplete < 100 ? newTotalComplete : 100);			
		}
		
		int numNdsLib = office.getNumDevelopersOfFeatureInState(Developer.FEATURE_NDSLIB, Developer.FEATURE);
		gameState.setEffortsNDSLib(gameState.getEffortsNDSLib() + numNdsLib);
		
		int numDBTests = office.getNumDevelopersOfFeatureInState(Developer.FEATURE_DBTEST, Developer.FEATURE);
		gameState.setEffortsDBTests(gameState.getEffortsDBTests() + numDBTests);
		
	}
}
