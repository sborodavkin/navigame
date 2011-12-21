package ua.lw0000.navigame.controller;

import java.util.HashMap;

import ua.lw0000.navigame.model.GameState;
import ua.lw0000.navigame.model.Office;
import ua.lw0000.navigame.model.ProductionPlan;

public class MoneyController {
	
	public static final int SALARY_INCREMENT = 50;
	public static final int OVERTIME_SCALE = 2;
	
	public static final float COMPILER_OVERTIME_BOOST = 1.5f;
	public static final float PRODUCTION_OVERTIME_BOOST = 2.0f;
	
	private boolean isOvertime;
	
	@SuppressWarnings("serial")
	private static final java.util.Map<String, Integer> PAYMENT_FOR_MAP = new HashMap<String, Integer>() {
		{
			put(ProductionPlan.HARSUM, 100);
			put(ProductionPlan.MUNICH, 300);
			put(ProductionPlan.HAMBURG, 600);
			put(ProductionPlan.SWISS, 2000);
			put(ProductionPlan.DACHI, 3000);
			put(ProductionPlan.ECE, 5000);
		}
	};

	private GameState gameState;
	private Office office;

	public MoneyController(GameState gameState, Office office) {
		this.gameState = gameState;
		this.office = office;
		isOvertime = false;
	}
	
	public int getTotalSalary() {
		return office.getTotalSalary() * (isOvertime() ? OVERTIME_SCALE : 1);
	}
	
	public boolean paySalary() {		
		int totalSalary = getTotalSalary();
		if (isOvertime) {
			isOvertime = false;
		}
		if (gameState.getMoney() < totalSalary) {
			return false;
		} else {
			gameState.setMoney(gameState.getMoney() - totalSalary);
			return true;
		}		
	}
	
	public boolean payForHW(int hwType) {
		if (gameState.getMoney() < HWController.getPaymentForHWType(hwType)) {
			return false;
		} else {
			gameState.setMoney(gameState.getMoney() - HWController.getPaymentForHWType(hwType));
			return true;
		}
	}
	
	public void makePaymentForMap(String mapTitle) {
		gameState.setMoney(gameState.getMoney() + getPaymentForMap(mapTitle));
	}
	
	public int getPaymentForMap(String mapTitle) {
		return PAYMENT_FOR_MAP.get(mapTitle);
	}

	public GameState getGameState() {
		return gameState;
	}

	public boolean isOvertime() {
		return isOvertime;
	}

	public void setOvertime(boolean isOvertime) {
		this.isOvertime = isOvertime;
	}
		
}
