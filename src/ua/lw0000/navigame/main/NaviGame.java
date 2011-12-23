package ua.lw0000.navigame.main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import ua.lw0000.navigame.intro.IntroState;



public class NaviGame extends StateBasedGame {
	
	public static final int INTRO_STATE = 0;
	public static final int MAINMENU_STATE = 1;
	public static final int INSTRUCTIONS_STATE = 2;
	public static final int GAMEPLAY_STATE = 3;
	public static final int GAMEOVER_STATE = 4;
	
	public static GameContainer gameContainer;
	private GameplayState gameplayState;
	public static NaviGame game;
	
	public NaviGame() {
		super("THE NAVIGAME");
		IntroState introState = new IntroState(INTRO_STATE);
		this.addState(introState);
		MainMenuState menuState = new MainMenuState(MAINMENU_STATE); 
		this.addState(menuState);
		InstructionsState instrState = new InstructionsState(INSTRUCTIONS_STATE); 
		this.addState(instrState);
		gameplayState = new GameplayState(GAMEPLAY_STATE);
		this.addState(gameplayState);
		GameOverState gameoverState = new GameOverState(GAMEOVER_STATE);
		this.addState(gameoverState);
		
		
		this.enterState(INTRO_STATE);
	}
	
	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		this.getState(INTRO_STATE).init(container, this);
		this.getState(MAINMENU_STATE).init(container, this);
		this.getState(INSTRUCTIONS_STATE).init(container, this);
		this.getState(GAMEPLAY_STATE).init(container, this);
		this.getState(GAMEOVER_STATE).init(container, this);
		((GameOverState)this.getState(GAMEOVER_STATE)).setController(gameplayState.getController());
		
	}
	
	public void reset() throws SlickException {
		this.gameplayState.reset(gameContainer, this);
		((GameOverState)this.getState(GAMEOVER_STATE)).setController(gameplayState.getController());
	}
	
	public static void main(String[] args) throws SlickException {
		game = new NaviGame();
		AppGameContainer app = new AppGameContainer(game);
		app.setShowFPS(false);
		app.setVSync(true);
		gameContainer = app;

		app.setDisplayMode(800, 600, false);
		app.start();
	}

}
