package ua.lw0000.navigame.npc;

import ua.lw0000.navigame.controller.ShoutController.ShoutPosition;
import ua.lw0000.navigame.model.Position;

public class NPCLocation {

	public static final int WALK_NS = 0;
	public static final int WALK_SN = 1;
	public static final int WALK_EW = 2;
	public static final int WALK_WE = 3;

	private ShoutPosition curLocation;
	private ShoutPosition targetLocation;
	private Position victim;
	private int walkType;
	private boolean speaking;
	private int speakTime;
	private boolean finished;
	private boolean returning;
	

	public NPCLocation(ShoutPosition location, ShoutPosition targetLocation,
			Position victim, int walkType) {
		super();
		this.curLocation = location;
		this.targetLocation = targetLocation;
		this.victim = victim;
		this.walkType = walkType;
		this.speaking = false;
		speakTime = 0;
		this.finished = false;
		this.returning = false;
	}

	public ShoutPosition getLocation() {
		return curLocation;
	}

	public void setLocation(ShoutPosition location) {
		this.curLocation = location;
	}

	public Position getVictim() {
		return victim;
	}

	public void setVictim(Position victim) {
		this.victim = victim;
	}

	public ShoutPosition getTargetLocation() {
		return targetLocation;
	}

	public void setTargetLocation(ShoutPosition targetLocation) {
		this.targetLocation = targetLocation;
	}

	public int getWalkType() {
		return walkType;
	}

	public void setWalkType(int walkType) {
		this.walkType = walkType;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public boolean isSpeaking() {
		return speaking;
	}

	public void setSpeaking(boolean speaking) {
		this.speaking = speaking;
	}

	public int getSpeakTime() {
		return speakTime;
	}

	public void setSpeakTime(int speakTime) {
		this.speakTime = speakTime;
	}

	public boolean isReturning() {
		return returning;
	}

	public void setReturning(boolean returning) {
		this.returning = returning;
	}
	
	
	
	
	

}