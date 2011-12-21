package ua.lw0000.navigame.render;

import java.util.HashMap;
import java.util.Map;

import ua.lw0000.navigame.controller.MapController;
import ua.lw0000.navigame.main.Context;
import ua.lw0000.navigame.model.Developer;
import ua.lw0000.navigame.model.Position;
import ua.lw0000.navigame.model.Production;

public class RendererFactory {

	private Context context;
	private MapController mapController;
	
	private Map<Position, Renderer> rendererCache;
	
	public RendererFactory(Context context, MapController mapController) {
		this.context = context;
		this.mapController = mapController;
		rendererCache = new HashMap<Position, Renderer>();
	}
	
	
	/**
	 * Gets a renderer for a given position
	 * @param position
	 * @return
	 */
	public Renderer getRenderer(Position position, float x, float y) {
		Renderer renderer = rendererCache.get(position);
		if (renderer == null) {			
			if (position instanceof Developer) {			
				renderer = new DeveloperRenderer((Developer)position, context, mapController, context.getDeveloper(), x, y);
			} else if (position instanceof Production) {
				renderer = new ProductionRenderer((Production)position, context, mapController, context.getProduction(), x, y);
			} else {
				renderer = new EmptyRenderer(context);
			}
			rendererCache.put(position, renderer);
			// prevent overbloating
			if (rendererCache.size() > 1000) {
				rendererCache.clear();
			}
		}
		return renderer;
	}
}
