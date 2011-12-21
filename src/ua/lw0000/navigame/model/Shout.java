package ua.lw0000.navigame.model;

/**
 * Represents a popping-up notification (developer's scream, bug name etc.)
 * 
 */
public class Shout {
	
	public static final int GENERAL = 0;
	public static final int BUG_NAME = 1;
	public static final int CURSE = 2;
	
	private float startW, startN;
	private int flightLength;
	private float speed;
	
	private String text;
	private int type;
	
	/**
	 * Creates the Shout instance
	 * 
	 * @param text text to shout
	 * @param type type of shout
	 * @param startW screen origin of shout X (in pixels) 
	 * @param startN screen origin of shout Y (in pixels)
	 * @param flightLength length (in pixels) of shout flight
	 * @param speed speed of shout flight (pixels per msec)
	 */
	public Shout(String text, int type, float startW, float startN, int flightLength, float speed) {
		this.text = text;
		this.type = type;
		this.startW = startW;
		this.startN = startN;
		this.flightLength = flightLength;
		this.speed = speed;
	}

	public float getStartW() {
		return startW;
	}

	public void setStartW(float startW) {
		this.startW = startW;
	}

	public float getStartN() {
		return startN;
	}

	public void setStartN(float startN) {
		this.startN = startN;
	}

	public int getFlightLength() {
		return flightLength;
	}

	public void setFlightLength(int flightLength) {
		this.flightLength = flightLength;
	}

	/**
	 * Gets the flight speed of shout
	 * 
	 * @return speed (pixels / msec)
	 */
	public float getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
	
	
	
	
}
