package ua.lw0000.navigame.npc;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;

import ua.lw0000.navigame.main.Context;

public class PKunath implements NPC {
	
	private Animation animNS, animSN, animWE, animEW;
	private Image standNS, standSN, standWE, standEW;
	private String text;
		
	public PKunath(Context ctx) {
		Image canvas = ctx.getpKunathImg();
		init(canvas);
	}
	
	
	
	@Override
	public String getText() {
		return text;
	}

	private void init(Image canvas) {
		standSN = canvas.getSubImage(0, 0, 21, 27);
		standNS = canvas.getSubImage(64, 32, 21, 27);
		standWE = canvas.getSubImage(33, 0, 21, 27);
		standEW = canvas.getSubImage(0, 64, 21, 27);
		
		animNS = new Animation(new Image[]{
				canvas.getSubImage(64, 64, 21, 27),
				standNS,
				canvas.getSubImage(64, 96, 21, 27)
		}, 100);
		animSN = new Animation(new Image[]{
				canvas.getSubImage(64, 0, 21, 27),
				standSN,
				canvas.getSubImage(32, 96, 21, 27)
		}, 100);
		animEW = new Animation(new Image[]{					
				canvas.getSubImage(0, 96, 21, 27),
				standEW,
				canvas.getSubImage(0, 32, 21, 27)
		}, 100);
		animWE = new Animation(new Image[]{
				canvas.getSubImage(33, 64, 21, 27),
				standWE,
				canvas.getSubImage(33, 32, 21, 27)				
		}, 100);
		text = NPCTextGenerator.generateNPCText();
		
	}
	
	

	@Override
	public int getWidth() {
		return 21;
	}

	@Override
	public int getHeight() {
		return 27;
	}

	@Override
	public Animation getAnimationWalkNS() {
		return animNS;
	}

	@Override
	public Animation getAnimationWalkSN() {
		return animSN;
	}

	@Override
	public Animation getAnimationWalkWE() {
		return animWE;
	}

	@Override
	public Animation getAnimationWalkEW() {
		return animEW;
	}

	@Override
	public Image getStandImageNS() {
		return standEW;
	}

	@Override
	public Image getStandImageSN() {
		return standEW;
	}

	@Override
	public Image getStandImageWE() {
		return standEW;
	}

	@Override
	public Image getStandImageEW() {
		return standEW;
	}

	@Override
	public String getTitle() {
		return "PKunath";
	}



	@Override
	public void setText(String s) {
		this.text = s;
	}
	
	

}
