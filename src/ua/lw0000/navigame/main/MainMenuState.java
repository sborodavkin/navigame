package ua.lw0000.navigame.main;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MainMenuState extends BasicGameState {

	private int stateID;

	private Image imgBackground, imgStart, imgInstructions, imgExit;

	private int startN = 160;
	private int startW = 280;
	private float scaleStep = 0.0002f;

	private float startScale = 1;
	private float instructionsScale = 1;
	private float exitScale = 1;

	public MainMenuState(int stateId) {
		this.stateID = stateId;
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return stateID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		imgBackground = new Image("image/menu_bg.png");
		imgStart = new Image("image/menu_start.png");
		imgInstructions = new Image("image/menu_instructions.png");
		imgExit = new Image("image/menu_exit.png");
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		imgBackground.draw(0, 0);
		imgStart.draw(
				startW
						- Math.abs((float) imgStart.getWidth()
								* (startScale - 1) / 2), startN, startScale);
		imgInstructions.draw(
				startW

						- Math.abs((float) imgInstructions.getWidth()
								* (instructionsScale - 1) / 2), startN + 90,
				instructionsScale);
		imgExit.draw(startW

		- Math.abs((float) imgExit.getWidth() * (exitScale - 1) / 2),
				startN + 180, exitScale);

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		Input input = container.getInput();

		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();

		boolean insideStart = false;
		boolean insideInstructions = false;
		boolean insideExit = false;

		if ((mouseX >= startW && mouseX <= startW + imgStart.getWidth())
				&& (mouseY >= startN && mouseY <= startN + imgStart.getHeight())) {
			insideStart = true;
		} else if ((mouseX >= startW && mouseX <= startW
				+ imgInstructions.getWidth())
				&& (mouseY >= startN && mouseY <= startN + 90
						+ imgInstructions.getHeight())) {
			insideInstructions = true;
		} else if ((mouseX >= startW && mouseX <= startW + imgExit.getWidth())
				&& (mouseY >= startN + 180 && mouseY <= startN + 180
						+ imgExit.getHeight())) {
			insideExit = true;
		}

		if (insideStart) {
			if (startScale < 1.1f) {
				startScale += scaleStep * delta;
			}
			if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
				input.clearMousePressedRecord();
				game.enterState(NaviGame.GAMEPLAY_STATE,
						new FadeOutTransition(), new FadeInTransition());
			}
		} else {
			if (startScale > 1.0f) {
				startScale -= scaleStep * delta;
			}
		}

		if (insideInstructions) {
			if (instructionsScale < 1.1f) {
				instructionsScale += scaleStep * delta;
			}
			if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
				input.clearMousePressedRecord();
				game.enterState(NaviGame.INSTRUCTIONS_STATE,
						new FadeOutTransition(), new FadeInTransition());
			}
		} else {
			if (instructionsScale > 1.0f) {
				instructionsScale -= scaleStep * delta;
			}
		}

		if (insideExit) {
			if (exitScale < 1.1f) {
				exitScale += scaleStep * delta;
			}
			if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
				container.exit();
			}
		} else {
			if (exitScale > 1.0f) {
				exitScale -= scaleStep * delta;
			}
		}
	}

}
