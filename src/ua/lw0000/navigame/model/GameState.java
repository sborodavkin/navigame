package ua.lw0000.navigame.model;

import ua.lw0000.navigame.controller.Controller;

public class GameState {

	private int money;
	private int time;
	private double compilerComplete;
	private Controller controller;
	
	private int effortsCompiler;
	private int effortsNDSLib;
	private int effortsDBTests;

	public GameState() {

	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public double getCompilerComplete() {
		return compilerComplete;
	}

	public void setCompilerComplete(double compilerComplete) {
		assert (compilerComplete >= 0 && compilerComplete <= 100);
		this.compilerComplete = compilerComplete;
	}

	public int getEffortsCompiler() {
		return effortsCompiler;
	}

	public void setEffortsCompiler(int effortsCompiler) {
		this.effortsCompiler = effortsCompiler;
	}

	public int getEffortsNDSLib() {
		return effortsNDSLib;
	}

	public void setEffortsNDSLib(int effortsNDSLib) {
		this.effortsNDSLib = effortsNDSLib;
	}

	public int getEffortsDBTests() {
		return effortsDBTests;
	}

	public void setEffortsDBTests(int effortsDBTests) {
		this.effortsDBTests = effortsDBTests;
	}
}
