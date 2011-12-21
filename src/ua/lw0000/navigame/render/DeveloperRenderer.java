package ua.lw0000.navigame.render;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ua.lw0000.navigame.controller.MapController;
import ua.lw0000.navigame.main.Composition;
import ua.lw0000.navigame.main.Context;
import ua.lw0000.navigame.model.Developer;
import ua.lw0000.navigame.model.Map;
import ua.lw0000.navigame.model.Resource;

public class DeveloperRenderer extends ResourceRenderer {


	public DeveloperRenderer(Resource dev, Context context,
			MapController mapController, Image mainImg, float w, float n) {
		super(dev, context, mapController, mainImg, w, n);
	}

	@Override
	protected void renderState(Graphics g) throws SlickException {
		if (getResource().getState() == Developer.FEATURE) {
			renderFeatureDevProgressIfNeeded(g, 1, STATE_N);
		} else if (getResource().getState() == Developer.BUGFIX) {			
			renderBugfixProgressIfNeeded(g);
		}		
	}
	
	private void renderFeatureDevProgressIfNeeded(Graphics g, float x, float y) throws SlickException {
		String activity = "";
		Developer dev = (Developer)getResource();
		switch (dev.getFeature()) {
		case Developer.FEATURE_ANACONDA:
			activity = "Compiler Dev.";
			break;
		case Developer.FEATURE_NDSLIB:
			activity = "NDSLib Dev.";
			break;
		case Developer.FEATURE_DBTEST:
			activity = "DB Tests Dev.";
			break;
		}
		g.drawString(activity, 1, STATE_N);
	}
	
	private void renderBugfixProgressIfNeeded(Graphics g) throws SlickException {
		Map map = getMapController().getBugfixMap((Developer)getResource());
		if (map != null) {
			g.drawString(map.getTitle(), 1, Composition.ROOM_CELL_SIZE-35);			
			double mapPercent = (double)map.getBugfixMSecondsPassed() / (double)map.getTotalMSecondsToBugfix();
			Color prevColor = g.getColor();
			Color prevBg = g.getBackground();
			g.setColor(Color.cyan);
			int progressBarW = 5;
			int progressBarWidth = Composition.ROOM_CELL_SIZE - progressBarW * 2;
			g.drawRect(progressBarW, Composition.ROOM_CELL_SIZE-15, progressBarWidth, 3);
			g.setColor(Color.black);
			g.drawRect(progressBarW+1, Composition.ROOM_CELL_SIZE-14, progressBarWidth-2, 1);
			g.setColor(Color.cyan);
			float progress = (float)((mapPercent * (double)progressBarWidth));
			g.drawRect(progressBarW+1, Composition.ROOM_CELL_SIZE-14, progress, 1);
			g.setColor(prevColor);
			g.setBackground(prevBg);			
		}
	}
}
