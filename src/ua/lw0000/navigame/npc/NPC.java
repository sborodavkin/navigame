package ua.lw0000.navigame.npc;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;

public interface NPC {

	public Animation getAnimationWalkNS();
	public Animation getAnimationWalkSN();
	public Animation getAnimationWalkWE();
	public Animation getAnimationWalkEW();
	
	public Image getStandImageNS();
	public Image getStandImageSN();
	public Image getStandImageWE();
	public Image getStandImageEW();
	
	public int getWidth();
	public int getHeight();
	
	public String getTitle();
	public String getText();
	public void setText(String s);
	
}
