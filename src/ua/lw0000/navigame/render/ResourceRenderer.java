package ua.lw0000.navigame.render;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ua.lw0000.navigame.controller.MapController;
import ua.lw0000.navigame.controller.MoneyController;
import ua.lw0000.navigame.main.NaviGame;
import ua.lw0000.navigame.main.Composition;
import ua.lw0000.navigame.main.Context;
import ua.lw0000.navigame.model.Resource;

public abstract class ResourceRenderer implements Renderer {
	private Resource resource;
	private Context context;
	private MapController mapController;

	private AnchorMouseOverArea areaBtnSalary;
	private AnchorMouseOverArea areaBtnMotivation;

	public static final float MOOD_OK_BORDER = 0.6f;
	public static final float MOOD_AVG_BORDER = 0.3f;

	private static final int SALARY_W = 55;
	private static final int SALARY_N = 38;
	private static final int MOTIVATION_W = 73;
	private static final int MOTIVATION_N = 38;
	private static final int MOOD_W = 55;
	private static final int MOOD_N = 20;
	
	protected static final int STATE_N = 68;

	protected Image mainSprite;

	public ResourceRenderer(Resource dev, Context context,
			MapController mapController, Image mainImg, float w, float n) {
		this.resource = dev;
		this.context = context;
		this.mapController = mapController;
		this.mainSprite = mainImg;
		this.areaBtnSalary = new AnchorMouseOverArea(
				NaviGame.gameContainer, context.getBtnSalaryImg(),
				SALARY_W, SALARY_N, 16, 16) {
			@Override
			public void mousePressed(int button, int mx, int my) {
				super.mousePressed(button, mx, my);
				if (isMouseOver()) {
					resource.setSalary(resource.getSalary()
							+ MoneyController.SALARY_INCREMENT);
					resource.setSalarySatisfaction(100);
				}
			}
		};
		areaBtnSalary.setAnchorN((int) n);
		areaBtnSalary.setAnchorW((int) w);

		areaBtnSalary.setMouseDownImage(context.getBtnSalaryPressedImg());
		areaBtnSalary.setMouseOverImage(context.getBtnSalaryHoverImg());
		this.areaBtnMotivation = new AnchorMouseOverArea(
				NaviGame.gameContainer, context.getBtnMotivationImg(),
				MOTIVATION_W, MOTIVATION_N, 16, 16) {
			@Override
			public void mousePressed(int button, int mx, int my) {
				super.mousePressed(button, mx, my);
				if (isMouseOver()) {
					resource.setMood(100); // max %
				}
			}
		};
		areaBtnMotivation.setAnchorN((int) n);
		areaBtnMotivation.setAnchorW((int) w);
		areaBtnMotivation.setMouseDownImage(context
				.getBtnMotivationPressedImg());
		areaBtnMotivation.setMouseOverImage(context.getBtnMotivationHoverImg());
	}

	@Override
	public Image render() throws SlickException {
		Graphics imgGraphics = context.getGenImageGraphics();
		imgGraphics.clear();
		// Main sprite
		imgGraphics.drawImage(mainSprite, 0, 0);

		imgGraphics.setFont(context.getSmallFont());

		// Salary
		imgGraphics.drawString("$" + resource.getSalary(), MOOD_W, 1);

		// Mood
		renderMood(imgGraphics, MOOD_W, MOOD_N);

		// Salary/motivation buttons
		areaBtnSalary.render(NaviGame.gameContainer, imgGraphics);
		areaBtnMotivation.render(NaviGame.gameContainer, imgGraphics);

		// Title
		imgGraphics.drawString(resource.getTitle(), 1, 52);

		renderState(imgGraphics);

		imgGraphics.flush();
		return context.getGenImage().copy();
	}

	private void renderMood(Graphics g, float x, float y) {
		if (resource.getPerformanceMultiplier() >= MOOD_OK_BORDER) {
			// TODO hack as sun.png is 16px wide
			g.drawImage(context.getMoodOkImg(), x + 8, y);
		} else if (resource.getPerformanceMultiplier() >= MOOD_AVG_BORDER) {
			g.drawImage(context.getMoodAvgImg(), x, y);
		} else {
			g.drawImage(context.getMoodBadImg(), x, y);
		}
	}

	protected abstract void renderState(Graphics g) throws SlickException;

	public Resource getResource() {
		return resource;
	}

	public MapController getMapController() {
		return mapController;
	}

	@Override
	public void updateMouseAreas(float x, float y) {
		areaBtnSalary.setAnchorN((int) y);
		areaBtnSalary.setAnchorW((int) x);
		areaBtnMotivation.setAnchorN((int) y);
		areaBtnMotivation.setAnchorW((int) x);
	}

	public static boolean isWithinMouseAreas(int row, int col, int mouseX,
			int mouseY) {
		int anchorW = Composition.ROOM_NW + col * Composition.ROOM_CELL_SIZE;
		int anchorN = Composition.ROOM_NW + row * Composition.ROOM_CELL_SIZE;

		if ((mouseX > anchorW + SALARY_W && mouseX <= anchorW + SALARY_W + 16
				&& mouseY > anchorN + SALARY_N && mouseY <= anchorN + SALARY_N
				+ 16)
				|| (mouseX > anchorW + MOTIVATION_W
						&& mouseX <= anchorW + MOTIVATION_W + 16
						&& mouseY > anchorN + MOTIVATION_N && mouseY <= anchorN
						+ MOTIVATION_N + 16)) {
			return true;
		}
		return false;
	}

}
