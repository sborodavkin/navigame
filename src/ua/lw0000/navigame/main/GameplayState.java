package ua.lw0000.navigame.main;

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
import ua.lw0000.navigame.model.Developer;

public class GameplayState extends BasicGameState {
	
	private Context context;
	private Controller controller;
	
	int stateId = -1;
	
	public GameplayState(int stateId) {
		this.stateId = stateId;	
	}
	
	@Override
    public int getID() {
        return stateId;
    }
	
	public Controller getController() {
		return controller;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// Slick seems to have a defect leading to double initialization
		if (context == null || controller == null) {
			context = new Context();
			controller = new Controller(context);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		if (controller.processTimeEvent(delta)) {		
			Input input = gc.getInput();
			boolean mbLeft = input.isMousePressed(Input.MOUSE_LEFT_BUTTON);
			boolean mbRight = input.isMousePressed(Input.MOUSE_RIGHT_BUTTON);
			
			if (mbLeft || mbRight) {
				int mouseX = input.getMouseX();
				int mouseY = input.getMouseY();			
				controller.processOfficeClick(mouseX, mouseY, mbRight);
				controller.processMapClick(mouseX, mouseY, mbRight);				
			}
		} else {
			((GameOverState)sbg.getState(NaviGame.GAMEOVER_STATE)).reset();
			sbg.enterState(NaviGame.GAMEOVER_STATE, new FadeOutTransition(Color.black, 5000), new FadeInTransition(Color.black, 3000));
		}
		
	}
	

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		controller.renderBackground(g);
		controller.renderOffice();
		controller.renderAvailableMaps();
		controller.renderGameStatePanel();
		controller.renderNPCs();
		controller.renderShouts();
		
		
	}	
	
	public void reset(GameContainer gc, StateBasedGame sbg) throws SlickException {
		context = null;
		controller = null;
		Developer.reset();
		init(gc, sbg);
	}
	
	
}
