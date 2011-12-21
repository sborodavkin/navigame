package ua.lw0000.navigame.render;

import java.util.Iterator;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ua.lw0000.navigame.controller.ShoutController;
import ua.lw0000.navigame.controller.ShoutController.ShoutPosition;
import ua.lw0000.navigame.main.Context;
import ua.lw0000.navigame.model.Shout;

public class ShoutRenderer implements Renderer {

	private ShoutController shoutController;
	private Context context;

	private static final Color COLOR_GENERAL = new Color(255, 200, 0, 255);
	private static final Color COLOR_BUG = new Color(255, 0, 0, 255);

	private static final Color COLOR_CURSE = new Color(255, 0, 0, 255);

	public ShoutRenderer(ShoutController ctrl, Context ctx) {
		this.shoutController = ctrl;
		this.context = ctx;
	}

	@Override
	public Image render() throws SlickException {
		Graphics imgGraphics = context.getGenImageGraphics();
		imgGraphics.clear();
		Color prevColor = imgGraphics.getColor();
		Iterator<Shout> it = shoutController.getShouts().iterator();
		while (it.hasNext()) {
			Shout shout = it.next();
			ShoutPosition pos = shoutController.getPosition(shout);
			Color c;
			switch (shout.getType()) {
			case Shout.BUG_NAME:
				imgGraphics.setFont(context.getMediumFont());
				c = new Color(COLOR_BUG);
				break;
			case Shout.CURSE:
				imgGraphics.setFont(context.getSmallBlackFont());
				c = new Color(COLOR_CURSE);
				break;
			default:
				imgGraphics.setFont(context.getMediumFont());
				c = new Color(COLOR_GENERAL);
				break;
			}
			float remainingY = pos.getY()
					- (shout.getStartN() - shout.getFlightLength());
			float fadeDist = (float)shout.getFlightLength() / 10;
			c.a = remainingY > fadeDist ? 1 : remainingY / fadeDist;
			if (shout.getType() == Shout.CURSE) {
				Color cloud = new Color(Color.white);
				cloud.a = c.a;
				// the current cloud + font => cloud is ok for 12 chars
				float xScale = shout.getText().length() <= 12 ? 1
						: (float) shout.getText().length() / 14;
				GL11.glPushMatrix();
				GL11.glScalef(xScale, 1, 1);
				imgGraphics.drawImage(context.getShoutCloudImg(),
						(pos.getX() - 15) / xScale, pos.getY() - 5, cloud);
				GL11.glPopMatrix();
			}
			imgGraphics.setColor(c);

			imgGraphics.drawString(shout.getText(), Math.round(pos.getX()), Math.round(pos.getY()));
		}
		imgGraphics.setColor(prevColor);
		imgGraphics.flush();
		return context.getGenImage().copy();
	}

	@Override
	public void updateMouseAreas(float x, float y) {
		// noop

	}

}
