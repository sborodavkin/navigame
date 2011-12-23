package ua.lw0000.navigame.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import ua.lw0000.navigame.controller.Controller;
import ua.lw0000.navigame.intro.IntroState;
import ua.lw0000.navigame.model.Developer;
import ua.lw0000.navigame.model.Production;

public class GameOverState extends BasicGameState {

	private static final float TITLES_SPEED = 0.1f; // pixels per msec
	private static final int TITLE_ROW_HEIGHT = 20; // pixels per msec
	
	private int stateId;
	private boolean shouldLeave = false;
	private int firstStringIdx, lastStringIdx;
	private List<String> allStrings;
	private float firstStringN = 600;

	private Controller controller;

	public GameOverState(int stateId) {
		this.stateId = stateId;
		this.controller = null;
		initTitles();
	}
	
	private void initTitles() {
		allStrings = new ArrayList<String>();
		allStrings.add("Starring:");
		allStrings.add("\n");
		allStrings.add("Anaconda Development Team:");
		allStrings.add("\n");
		allStrings.addAll(Arrays.asList(Developer.ALL_NAMES[Developer.FEATURE_ANACONDA]));
		allStrings.add("\n");
		allStrings.add("NDSLib Development Team:");
		allStrings.add("\n");
		allStrings.addAll(Arrays.asList(Developer.ALL_NAMES[Developer.FEATURE_NDSLIB]));
		allStrings.add("\n");
		allStrings.add("DB Tests Team:");
		allStrings.add("\n");
		allStrings.addAll(Arrays.asList(Developer.ALL_NAMES[Developer.FEATURE_DBTEST]));
		allStrings.add("\n");
		allStrings.add("NDS DB Production Team:");
		allStrings.add("\n");
		allStrings.addAll(Arrays.asList(Production.ALL_NAMES));
		allStrings.add("\n");
		allStrings.add("Special Guest Star:");		
		allStrings.add("\n");
		allStrings.add("Dr. Peter Kunath");
		allStrings.add("\n");
		allStrings.add("\n");
		allStrings.add("(C) Sergey Borodavkin, 2011");
		allStrings.add("\n");
		
	}

	@Override
	public int getID() {
		return stateId;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		reset();
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		if (!shouldLeave) {
			int score = controller.calculateScore();		
			int productionPercent = controller.getMapController().getPercentComplete();
			int naviPercent = (int)Math.round(controller.getGameState().getCompilerComplete());
			g.setFont(controller.getContext().getBigWhiteLightFont());
			g.setColor(Color.lightGray);
			g.drawString("Total score for the game: ", 150, 250);
			g.drawString("Production plan complete: ", 150, 300);
			g.drawString("Navigation system complete: ", 150, 350);
			
			g.setFont(controller.getContext().getBigWhiteFont());
			g.setColor(Color.white);
			g.drawString("" + score, 600, 250);
			g.drawString(productionPercent + "%", 600, 300);
			g.drawString(naviPercent + "%", 600, 350);
		} else {
			renderTitles(g);
		}		
	}
	
	private void renderTitles(Graphics g) {
		if (firstStringIdx < 0) {
			firstStringIdx = 0;
		} else if (firstStringIdx >= allStrings.size()) {
			firstStringIdx = allStrings.size() - 1;
		}
		if (lastStringIdx < 0) {
			lastStringIdx = 0;
		} else if (lastStringIdx >= allStrings.size()) {
			lastStringIdx = allStrings.size() - 1;
		}
		g.setColor(Color.white);
		for (int i = firstStringIdx; i <= lastStringIdx; i++) {
			g.drawString(allStrings.get(i), 200, firstStringN + TITLE_ROW_HEIGHT * (i-firstStringIdx));
		}
	}
	

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		Input input = container.getInput();
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			if (shouldLeave) {
				leave(game);
			} else {
				shouldLeave = true;				
			}
		} else if (shouldLeave) {
			firstStringN -= delta * TITLES_SPEED;
			if (firstStringN < -TITLE_ROW_HEIGHT) {
				firstStringN = 0;
				firstStringIdx++;
				lastStringIdx++;					
			}				
			//Log.info("First string N = " + firstStringN + ", firstStringIdx = " + firstStringIdx + ", lastStringIdx = " + lastStringIdx);
			if (firstStringIdx == lastStringIdx) {
				leave(game);
			}
		}

	}
	
	private void leave(StateBasedGame game) {
		((IntroState) (game.getState(NaviGame.INTRO_STATE))).reset();
		game.enterState(NaviGame.INTRO_STATE, new FadeOutTransition(
				Color.black, 3000), new FadeInTransition(Color.black, 1000));
	}
	
	public void reset() {
		shouldLeave = false;
		firstStringIdx = 0;
		lastStringIdx = 600 / TITLE_ROW_HEIGHT;
		firstStringN = 600;
	}
}
