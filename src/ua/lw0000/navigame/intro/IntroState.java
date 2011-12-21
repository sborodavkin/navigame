package ua.lw0000.navigame.intro;

import org.newdawn.slick.Image;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import ua.lw0000.navigame.main.NaviGame;


public class IntroState extends BasicGameState {
	
	private static final int FADE_OUT_TIME = 2000;
	
	private int stateId;
	private Image image;
	boolean shouldStart;
	boolean fade;
	private int time = 0;
	private int timeSinceLastFadeOut = 0;
	
	
	public IntroState(int stateId) {
		this.stateId = stateId;
		fade = false;
	}
	
	@Override
	public int getID() {
		return stateId;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		image = new Image("image/intro1.png");
		shouldStart = true;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		renderIntroImage();		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		time += delta;		
		Input input = container.getInput();
		if (fade) {
			if (time - timeSinceLastFadeOut >= FADE_OUT_TIME) {		
				fade = false;
				timeSinceLastFadeOut = time;
				if (shouldStart) {
					((NaviGame)game).reset();
					game.enterState(NaviGame.MAINMENU_STATE);							
				} else {
					shouldStart = true;
					image = new Image("image/intro1.png");
				}
			}		
		} else {
			if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				fade = true;
				timeSinceLastFadeOut = time;
			}
		}		
	}
	
	private void renderIntroImage() {
		if (fade) {
			image.setAlpha(1-((float)(time-timeSinceLastFadeOut) / FADE_OUT_TIME));
		}
		image.draw(0,0);
	}
	
	public void reset() {
		image.setAlpha(1.0f);
		fade = false;
		timeSinceLastFadeOut = 0;		
	}

}
