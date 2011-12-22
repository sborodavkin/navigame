package ua.lw0000.navigame.render;

import org.newdawn.slick.Image;

public class EmptyRenderer implements Renderer {

	public EmptyRenderer() {
		// noop
	}
	
	@Override
	public Image render() {
		return null;
	}

	@Override
	public void updateMouseAreas(float x, float y) {
		// noop		
	}
}
