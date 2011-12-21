package ua.lw0000.navigame.render;

import org.newdawn.slick.Image;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;

public class AnchorMouseOverArea extends MouseOverArea {

	private int anchorN, anchorW;
	
	public AnchorMouseOverArea(GUIContext container, Image image, int x, int y,
			int width, int height) {
		super(container, image, x, y, width, height);
	}

	public int getAnchorN() {
		return anchorN;
	}

	public void setAnchorN(int anchorN) {
		this.anchorN = anchorN;
	}

	public int getAnchorW() {
		return anchorW;
	}

	public void setAnchorW(int anchorW) {
		this.anchorW = anchorW;
	}
	
	@Override
	public void mouseMoved(int oldx, int oldy, int newx,
			int newy) {
		super.mouseMoved(oldx-anchorW, oldy-anchorN, newx-anchorW, newy-anchorN);
	}

	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		super.mouseDragged(oldx-anchorW, oldy-anchorN, newx-anchorW, newy-anchorN);
	}

	@Override
	public void mousePressed(int button, int mx, int my) {
		super.mousePressed(button, mx-anchorW, my-anchorN);
	}

	@Override
	public void mouseReleased(int button, int mx, int my) {
		super.mouseReleased(button, mx-anchorW, my-anchorN);
	}	
}
