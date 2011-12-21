package ua.lw0000.navigame.controller;

import ua.lw0000.navigame.model.GameState;

public class TimeController {
	
	public static final int SALARY_INTERVAL = 20 * 1000; // msec
	public static final int COMPILER_INTERVAL = 100; // msec
	public static final int PRODUCTION_INTERVAL = COMPILER_INTERVAL;
	public static final int MOOD_INTERVAL = COMPILER_INTERVAL * 10;
	public static final int SHOUT_INTERVAL = 10 * 1000;
	public static final int NPC_INTERVAL = 60 * 1000;
	
	private GameState gameState;
	private MoneyController moneyController;
	private CompilerController compilerController;
	private MapController mapController;
	private MoodController moodController;
	private ShoutController shoutController;
	private NPCController npcController;
	private int timeSinceLastSalary;
	private int timeSinceLastCompilerIncrement;
	private int timeSinceLastProduction;
	private int timeSinceLastMoodUpdate;
	private int timeSinceLastRandomShout;
	private int timeSinceLastNPC;
	
	
	public TimeController(GameState gameState, MoneyController moneyController,
			CompilerController compilerController, MapController mapController,
			MoodController moodController, ShoutController shoutController,
			NPCController npcController) {
		this.gameState = gameState;
		this.moneyController = moneyController;
		this.compilerController = compilerController;
		this.mapController = mapController;
		this.moodController = moodController;
		this.shoutController = shoutController;
		this.npcController = npcController;
		timeSinceLastSalary = 0;
		timeSinceLastCompilerIncrement = 0;
		timeSinceLastProduction = 0;
		timeSinceLastMoodUpdate = 0;
		timeSinceLastRandomShout = 0;
		timeSinceLastNPC = 0;
	}
	
	public boolean processTimeEvent(int delta) {
		gameState.setTime(gameState.getTime() + delta);
		//Log.debug("Time set to " + gameState.getTime());
		
		boolean res = true;
		res &= processSalary();
		res &= processMood();
		res &= processCompiler();
		res &= processMaps();
		processShouts(delta);
		processNPC(delta);
		return res;
	}
	
	public void processNPC(int delta) {
		if (gameState.getTime() - timeSinceLastNPC >= NPC_INTERVAL) {
			npcController.addRandomNPC();
			timeSinceLastNPC = gameState.getTime();
		}
		npcController.updateNPCs(delta);
	}
	
	public void processShouts(int delta) {
		if (gameState.getTime() - timeSinceLastRandomShout >= SHOUT_INTERVAL) {
			shoutController.addRandomShouts();	
			timeSinceLastRandomShout = gameState.getTime();
		}
		shoutController.updateShouts(delta);
	}
	
	private boolean processSalary() {
		if (gameState.getTime() - timeSinceLastSalary >= SALARY_INTERVAL) {
			if (!moneyController.paySalary()) {
				return false;
			}
			timeSinceLastSalary = gameState.getTime();
		}
		//Log.debug("Money = " + gameState.getMoney());
		return true;
	}
	
	public int getTimeSinceLastSalary() {
		return timeSinceLastSalary;
	}
	
	private boolean processMood() {
		if (gameState.getTime() - timeSinceLastMoodUpdate >= MOOD_INTERVAL) {
			moodController.updateDevelopersPerformance();
			moodController.updateProductionPerformance();
			timeSinceLastMoodUpdate = gameState.getTime();
		}
		return true;
	}

	private boolean processCompiler() {
		if (gameState.getTime() - timeSinceLastCompilerIncrement >= COMPILER_INTERVAL) {
			compilerController.developFeatures();
			timeSinceLastCompilerIncrement = gameState.getTime();
		}
		return true;		
	}
	
	private boolean processMaps() {
		if (gameState.getTime() - timeSinceLastProduction >= PRODUCTION_INTERVAL) {
			if (!mapController.updateProduction()) {
				return false;
			}
			mapController.updateBugfix();
			timeSinceLastProduction = gameState.getTime();
		}		
		return true;
	}		
}

