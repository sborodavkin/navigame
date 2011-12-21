package ua.lw0000.navigame.render;

import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ua.lw0000.navigame.controller.MapController;
import ua.lw0000.navigame.main.Composition;
import ua.lw0000.navigame.main.Context;
import ua.lw0000.navigame.model.Map;

public class MapRenderer implements Renderer {

	private MapController mapController;
	private Context context;

	public MapRenderer(MapController mapCtrl, Context ctx) {
		this.mapController = mapCtrl;
		this.context = ctx;
	}

	@Override
	public Image render() throws SlickException {
		Graphics imgGraphics = context.getGenImageGraphics();
		imgGraphics.clear();

		List<Map> maps = mapController.getAvailableMaps();
		for (int i = 0; i < maps.size(); i++) {
			Map map = maps.get(i);
			Integer numToCompile = mapController.getProductionPlan()
					.getNumToCompile(map.getTitle());
			if (numToCompile != null && numToCompile > 0) {
				Image img = context.getMapImage(i);
				int numProducing = mapController.howManyUnderProduction(map);
				imgGraphics.drawImage(img, Composition.getMapW(i)
						- Composition.AVAIL_MAP_W, 0);
				Image overlay = getMapOverlay(map);
				if (overlay != null) {
					imgGraphics.drawImage(overlay, Composition.getMapW(i)
							- Composition.AVAIL_MAP_W + 10, 0);
				}

				imgGraphics.drawString("" + numProducing, Composition
						.getMapW(i)
						- Composition.AVAIL_MAP_W + 30, 0);
				imgGraphics.flush();
			}
		}
		imgGraphics.flush();
		return context.getGenImage().copy();
	}

	/**
	 * Returns either a bug-image, a bugfix-image etc.
	 * 
	 * @param map
	 * @return
	 */
	private Image getMapOverlay(Map map) {
		if (map.isBlocked()) {
			return mapController.isMapUnderBugfix(map) ? context.getFixImage()
					: context.getBugImage();
		}
		return null;
	}

	@Override
	public void updateMouseAreas(float x, float y) {
		// noop		
	}
}
