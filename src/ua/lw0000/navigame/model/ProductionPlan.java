package ua.lw0000.navigame.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.newdawn.slick.util.Log;

public class ProductionPlan {

	public static final String HARSUM = "HARSUM";
	public static final String MUNICH = "Munich";
	public static final String HAMBURG = "Hamburg";
	public static final String SWISS = "Swiss";
	public static final String DACHI = "DACHI";
	public static final String ECE = "ECE";
	
	public static final Map<String, Integer> mapNumProductions = new HashMap<String, Integer>() {{
		put(HARSUM, 10);
		put(MUNICH, 5);
		put(HAMBURG, 4);
		put(SWISS, 3);
		put(DACHI, 2);
		put(ECE, 1);
	}};
	
	private java.util.Map<String, Integer> toBeCompiled; // map title -> number of times
	private java.util.Map<String, Integer> alreadyCompiled;
	
	public ProductionPlan() {
		toBeCompiled = new HashMap<String, Integer>();
		alreadyCompiled = new HashMap<String, Integer>();
		
		toBeCompiled.put(HARSUM, mapNumProductions.get(HARSUM));
		toBeCompiled.put(MUNICH, mapNumProductions.get(MUNICH));
		toBeCompiled.put(HAMBURG, mapNumProductions.get(HAMBURG));
		toBeCompiled.put(SWISS, mapNumProductions.get(SWISS));
		toBeCompiled.put(DACHI, mapNumProductions.get(DACHI));
		toBeCompiled.put(ECE, mapNumProductions.get(ECE));		
	}
	
	public Integer getNumToCompile(String mapTitle) {
		return toBeCompiled.get(mapTitle);
	}
	
	public void markMapCompiled(String mapTitle) {
		// remove from "to be compiled"
		Integer numToCompile = toBeCompiled.get(mapTitle);
		if (numToCompile == null || numToCompile < 0) {
			throw new IllegalStateException("compiled map " + mapTitle + " which" +
					" was not required by plan");			
		}
		if (numToCompile > 0)
		{
			toBeCompiled.put(mapTitle, --numToCompile);		
		
			// add to "already compiled"
			Integer numCompiled = alreadyCompiled.get(mapTitle);
			numCompiled = numCompiled == null ? 1 : ++numCompiled;
			alreadyCompiled.put(mapTitle, numCompiled);
			Log.info("Finished compiling map " + mapTitle);
		}
	}
	
	public boolean isPlanFulfilled() {
		Iterator<String> it = toBeCompiled.keySet().iterator();
		while (it.hasNext()) {
			String title = it.next();
			Integer toCompile = toBeCompiled.get(title);
			if (toCompile > 0) {
				return false;
			}
		}
		return true;
	}
	
	public java.util.Map<String, MapState> getStatus() {
		HashMap<String, MapState> res = new HashMap<String, ProductionPlan.MapState>();
		Iterator<String> it = toBeCompiled.keySet().iterator();
		while (it.hasNext()) {
			String title = it.next();
			Integer numCompiled = alreadyCompiled.get(title);			
			numCompiled = (numCompiled == null ? 0 : numCompiled);
			int toBeCompiledTotal = toBeCompiled.get(title) + numCompiled;
			res.put(title, new MapState(toBeCompiledTotal, numCompiled));
		}
		return res;
	}
	
	
	public static class MapState {
		private int toBeCompiledTotal;
		private int numCompiled;
		
		public MapState(int toBeCompiledTotal, int numCompiled) {
			super();
			this.toBeCompiledTotal = toBeCompiledTotal;
			this.numCompiled = numCompiled;
		}

		public int getToBeCompiledTotal() {
			return toBeCompiledTotal;
		}

		public int getNumCompiled() {
			return numCompiled;
		}		
	}
}


