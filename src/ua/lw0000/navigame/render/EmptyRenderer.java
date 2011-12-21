package ua.lw0000.navigame.render;

import org.newdawn.slick.Image;

import ua.lw0000.navigame.main.Context;

public class EmptyRenderer implements Renderer {

	private Context context;
	
	public EmptyRenderer(Context context) {
		this.context = context;
	}
	
	@Override
	public Image render() {
		return null; //context.getEmpty();
	}

	@Override
	public void updateMouseAreas(float x, float y) {
		// noop		
	}
	
	

}
