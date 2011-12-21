package ua.lw0000.navigame.model;

public class Map implements Cloneable {
	
	private String title;
	private int totalMSecondsToCompile;
	private int totalMSecondsToBugfix;
	private int msecondsPassed;
	private int bugfixMSecondsPassed;
	private boolean isBlocked;
	
	
	public Map(String title, int totalMSecondsToCompile, int totalMSecondsToBugfix) {
		super();
		this.title = title;
		this.isBlocked = false;
		this.totalMSecondsToCompile = totalMSecondsToCompile;
		this.totalMSecondsToBugfix = totalMSecondsToBugfix;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getMSecondsPassed() {
		return msecondsPassed;
	}
	
	private void setMSecondsPassed(int n) {
		this.msecondsPassed = n;
	}

	public void msecPassed(int msec) {
		if (msecondsPassed < totalMSecondsToCompile) {
			msecondsPassed += msec;
		}
	}

	public int getTotalMSecToCompile() {
		return totalMSecondsToCompile;
	}
	
	public int getBugfixMSecondsPassed() {
		return bugfixMSecondsPassed;
	}
	
	public void resetBugfixIntervalsPassed() {
		this.bugfixMSecondsPassed = 0;
	}
	
	private void setBugfixMSecondsPassed(int n) {
		this.bugfixMSecondsPassed = n;
	}

	public void bugfixMSecPassed(int msec) {
		if (bugfixMSecondsPassed < totalMSecondsToBugfix) {
			bugfixMSecondsPassed += msec;
		}
	}

	public int getTotalMSecondsToBugfix() {
		return totalMSecondsToBugfix;
	}
	
	
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Map map = new Map(getTitle(), getTotalMSecToCompile(), getTotalMSecondsToBugfix());
		map.setMSecondsPassed(getMSecondsPassed());
		map.setBugfixMSecondsPassed(getBugfixMSecondsPassed());
		return map;		
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}
}
