package ua.lw0000.navigame.render;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public interface Renderer {
	
	public Image render() throws SlickException;
	public void updateMouseAreas(float x, float y);
}
