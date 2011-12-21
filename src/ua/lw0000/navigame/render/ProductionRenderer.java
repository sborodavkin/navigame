package ua.lw0000.navigame.render;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ua.lw0000.navigame.controller.MapController;
import ua.lw0000.navigame.main.Composition;
import ua.lw0000.navigame.main.Context;
import ua.lw0000.navigame.model.Map;
import ua.lw0000.navigame.model.Production;
import ua.lw0000.navigame.model.Resource;

public class ProductionRenderer extends ResourceRenderer {
	
	public ProductionRenderer(Resource dev, Context context,
			MapController mapController, Image mainImg, float w, float n) {
		super(dev, context, mapController, mainImg, w, n);
	}

	@Override
	protected void renderState(Graphics g) throws SlickException {
		Map map = getMapController().getProductionMap((Production)getResource());		
		if (map != null) {
			g.drawString(map.getTitle(), 1, Composition.ROOM_CELL_SIZE-35);
			Color progressColor = map.isBlocked() ? Color.red : Color.green;
			if (getMapController().isMapUnderBugfix(map)) {
				progressColor = Color.orange;
			}			
			double mapPercent = (double)map.getMSecondsPassed() / (double)map.getTotalMSecToCompile();
			Color prevColor = g.getColor();
			Color prevBg = g.getBackground();
			g.setColor(progressColor);
			int progressBarW = 5;
			int progressBarWidth = Composition.ROOM_CELL_SIZE - progressBarW * 2;
			g.drawRect(progressBarW, Composition.ROOM_CELL_SIZE-15, progressBarWidth, 3);
			g.setColor(Color.black);
			g.drawRect(progressBarW+1, Composition.ROOM_CELL_SIZE-14, progressBarWidth-2, 1);
			g.setColor(progressColor);
			float progress = (float)((mapPercent * (double)progressBarWidth));
			g.drawRect(progressBarW+1, Composition.ROOM_CELL_SIZE-14, progress, 1);
			g.setColor(prevColor);
			g.setBackground(prevBg);
			
		}
	}

	@Override
	public void updateMouseAreas(float x, float y) {
		// noop
	}
	
}
