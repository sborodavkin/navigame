package ua.lw0000.navigame.model;

public class Resource extends Position {
	
	private int state;
	
	/**
	 * The mood of the person (0-100).
	 * 0 - does not perform
	 * 100 - performs at his most
	 */
	private int mood; 
	
	/**
	 * How much is the person satisfied with his salary
	 * 0 - does not perform
	 * 100 - satisfied 100%
	 */
	private int salarySatisfaction;
	
	private int salary;
	
	public Resource() {
		super();
		mood = 100;
		salary = 100;
		salarySatisfaction = 100;
	}
			
	public void setState(int state) {
		this.state = state;
	}
	
	public int getState() {
		return state;
	}

	public int getMood() {
		return mood;
	}

	public void setMood(int mood) {
		this.mood = mood;
	}

	public int getSalarySatisfaction() {
		return salarySatisfaction;
	}

	public void setSalarySatisfaction(int salarySatisfaction) {
		this.salarySatisfaction = salarySatisfaction;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	public float getPerformanceMultiplier() {
		float res = (float)(0.3 * getMood() + 0.7 * getSalarySatisfaction()) / 100;
		return res;
	}
	
}
