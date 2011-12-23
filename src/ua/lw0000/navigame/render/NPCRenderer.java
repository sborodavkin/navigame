package ua.lw0000.navigame.render;

import java.util.Iterator;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ua.lw0000.navigame.controller.NPCController;
import ua.lw0000.navigame.main.Context;
import ua.lw0000.navigame.npc.NPC;
import ua.lw0000.navigame.npc.NPCLocation;

public class NPCRenderer implements Renderer {

	private NPCController npcController;
	private Context context;

	public NPCRenderer(NPCController npcCtrl, Context ctx) {
		this.npcController = npcCtrl;
		this.context = ctx;
	}

	@Override
	public Image render() throws SlickException {
		Graphics imgGraphics = context.getGenImageGraphics();
		imgGraphics.clear();
		// Color prevColor = imgGraphics.getColor();
		Iterator<NPC> it = npcController.getNPCs().iterator();
		while (it.hasNext()) {
			NPC npc = it.next();
			NPCLocation loc = npcController.getLocation(npc);
			if (!loc.isSpeaking()) {
				// walking
				Animation anim = null;
				switch (loc.getWalkType()) {
				case NPCLocation.WALK_EW:
					anim = npc.getAnimationWalkEW();
					break;
				case NPCLocation.WALK_WE:
					anim = npc.getAnimationWalkWE();
					break;
				case NPCLocation.WALK_NS:
					anim = npc.getAnimationWalkNS();
					break;
				case NPCLocation.WALK_SN:
					anim = npc.getAnimationWalkSN();
					break;
				}
				imgGraphics.drawAnimation(anim, loc.getLocation().getX(), loc
						.getLocation().getY());
			} else {
				// Speaking
				Image image = null;
				switch (loc.getWalkType()) {
				case NPCLocation.WALK_EW:
					image = npc.getStandImageEW();
					break;
				case NPCLocation.WALK_WE:
					image = npc.getStandImageWE();
					break;
				case NPCLocation.WALK_NS:
					image = npc.getStandImageNS();
					break;
				case NPCLocation.WALK_SN:
					image = npc.getStandImageSN();
					break;
				}
				imgGraphics.drawImage(image, loc.getLocation().getX(), loc
						.getLocation().getY());
				renderCallout(imgGraphics, npc, loc);
			}
			imgGraphics.setFont(context.getSmallFont());
			imgGraphics.drawString(npc.getTitle(),
					Math.round(loc.getLocation().getX() - 10), loc.getLocation().getY()
							+ npc.getHeight());
		}
		imgGraphics.flush();
		return context.getGenImage().copy();

	}

	private void renderCallout(Graphics g, NPC npc, NPCLocation loc) {
		float xScale = npc.getText().length() <= 8 ? 1 : (float) npc.getText()
				.length() / 12;
		GL11.glPushMatrix();
		GL11.glScalef(xScale, 1, 1);
		g.drawImage(context.getCalloutImg(), loc.getLocation().getX() / xScale
				- context.getCalloutImg().getWidth(),
				loc.getLocation().getY() - 30);
		GL11.glPopMatrix();
		g.setColor(Color.black);
		g.setFont(context.getSmallBlackFont());
		g.drawString(npc.getText(), Math.round(loc.getLocation().getX()
				- context.getCalloutImg().getWidth()*xScale + 5), loc.getLocation()
				.getY() - 30);
	}

	@Override
	public void updateMouseAreas(float x, float y) {
		// noop
	}

}
