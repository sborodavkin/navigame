package ua.lw0000.navigame.main;

public class Composition {

	/*
	 * (0,0)
	 *   +----------------------------------------------+
	 *   |
	 *   |
	 *   |
	 *   |
	 *   |
	 *   |
	 *   |
	 *   |
	 *   |
	 *   |
	 *   |
	 *   +----------------------------------------------+
	 *                                               (800,600)
	 * 
	 * 
	 */
	
	public static final int WIDTH = 774;
	public static final int HEIGHT = 580;
	
	public static final int ROOM_NW = 10;	
	public static final int ROOM_COLS = 6;
	public static final int ROOM_ROWS = 5;
	public static final int ROOM_CELL_SIZE = 104;  // room cell
	public static final int ROOM_SPRITE_SIZE = 52; // actual resource sprite
	
	public static final int MAP_WIDTH = 86;
	public static final int MAP_HEIGHT = 56;	
	public static final int AVAIL_MAP_N = 544;
	public static final int AVAIL_MAP_W = 20;	
	public static final int AVAIL_MAP_STEP = 12;
	
	public static final int MONEY_N = 20;
	public static final int MONEY_W = 300;
	
	public static final int LEFT_PANEL_W = 644;	
	public static final int LEFT_PANEL_WIDTH = 100;
	
	
	public static final int getMapW(int index) {
		return AVAIL_MAP_W + index * (Composition.MAP_WIDTH
				+ Composition.AVAIL_MAP_STEP);
	}
	
	
	
}
