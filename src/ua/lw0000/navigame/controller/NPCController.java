package ua.lw0000.navigame.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.newdawn.slick.util.Log;

import ua.lw0000.navigame.controller.ShoutController.ShoutPosition;
import ua.lw0000.navigame.main.Composition;
import ua.lw0000.navigame.main.Context;
import ua.lw0000.navigame.model.Office;
import ua.lw0000.navigame.model.Position;
import ua.lw0000.navigame.model.Resource;
import ua.lw0000.navigame.npc.NPC;
import ua.lw0000.navigame.npc.NPCLocation;
import ua.lw0000.navigame.npc.PKunath;

public class NPCController {

	private Map<NPC, NPCLocation> npcs;
	private Office office;
	private Random rand;
	private Context context;

	private float NPC_SPEED = 0.05f; // pixels per msec
	private int NPC_SPEAK_TIME = 5 * 1000;

	public NPCController(Context ctx, Office office) {
		this.office = office;
		this.context = ctx;
		this.npcs = new HashMap<NPC, NPCLocation>();
		this.rand = new Random(System.currentTimeMillis());
	}

	public void addRandomNPC() {
		List<Position> list = new ArrayList<Position>();
		for (int i = 0; i < office.getNumRows(); i++) {
			for (int j = 0; j < office.getNumColumns(); j++) {
				Position pos = office.getPosition(i, j);
				if (pos instanceof Resource) {
					list.add(pos);
				}
			}
		}
		if (list.size() > 0) {
			Position victim = list.get(rand.nextInt(list.size()));
			ShoutPosition targetPos = office.getAnchorForPosition(victim);
			targetPos.setX(targetPos.getX() + Composition.ROOM_SPRITE_SIZE);
			targetPos.setY(targetPos.getY() + Composition.ROOM_SPRITE_SIZE);
			int walkType = 0;
			ShoutPosition srcPos = null;
			if (rand.nextInt(2) == 0) {
				// horizontal walk
				if (targetPos.getX() > 100) {
					// walk WE
					walkType = NPCLocation.WALK_WE;
					srcPos = new ShoutPosition(0, targetPos.getY());
				} else if (targetPos.getX() < Composition.LEFT_PANEL_W - 100) {
					// walk EW
					walkType = NPCLocation.WALK_EW;
					srcPos = new ShoutPosition(800, targetPos.getY());
				}
			} else {
				// vertical walk
				if (targetPos.getY() > 100) {
					// walk NS
					walkType = NPCLocation.WALK_NS;
					srcPos = new ShoutPosition(targetPos.getX(), 0);
				} else {
					// walk SN
					walkType = NPCLocation.WALK_SN;
					srcPos = new ShoutPosition(targetPos.getX(), 600);
				}
			}
			NPCLocation npcLoc = new NPCLocation(srcPos, targetPos, victim,
					walkType);
			npcs.put(new PKunath(context), npcLoc);
		}
	}

	public Set<NPC> getNPCs() {
		return npcs.keySet();
	}

	public NPCLocation getLocation(NPC npc) {
		return npcs.get(npc);
	}

	public void updateNPCs(int deltaMs) {
		Iterator<NPC> it = npcs.keySet().iterator();
		while (it.hasNext()) {
			NPC npc = it.next();
			NPCLocation loc = npcs.get(npc);
			if (loc.isFinished()) {
				if (loc.isSpeaking()) {
					// finished speaking, return offscreen
					loc.setSpeaking(false);
					loc.setFinished(false);
					returnOffscreen(loc);
				}
			} else {
				ShoutPosition curLoc = loc.getLocation();
				ShoutPosition targetLoc = loc.getTargetLocation();
				if (Math.abs(curLoc.getX() - targetLoc.getX()) <= 1
						&& Math.abs(curLoc.getY() - targetLoc.getY()) <= 1) {
					if (!loc.isSpeaking()) {
						if (!loc.isReturning()) {
							// speech start
							loc.setSpeaking(true);
							loc.setSpeakTime(0);
						} else {
							it.remove();
						}
					} else {
						if (loc.getSpeakTime() + deltaMs >= NPC_SPEAK_TIME) {
							loc.setFinished(true);							
						} else {
							// person to whom NPC goes is fired
							if (!office.isInOffice(loc.getVictim())) {
								npc.setText("Hmm... strange...");
							}
							loc.setSpeakTime(loc.getSpeakTime() + deltaMs);
						}
					}
				} else {
					switch (loc.getWalkType()) {
					case NPCLocation.WALK_EW:
						curLoc.setX(curLoc.getX() - deltaMs * NPC_SPEED);
						break;
					case NPCLocation.WALK_WE:
						curLoc.setX(curLoc.getX() + deltaMs * NPC_SPEED);
						break;
					case NPCLocation.WALK_NS:
						curLoc.setY(curLoc.getY() + deltaMs * NPC_SPEED);
						break;
					case NPCLocation.WALK_SN:
						curLoc.setY(curLoc.getY() - deltaMs * NPC_SPEED);
						break;
					}
					// person to whom NPC goes is fired
					if (!office.isInOffice(loc.getVictim()) && !loc.isReturning()) {
						returnOffscreen(loc);
					}
				}
			}
		}
	}
	
	private void returnOffscreen(NPCLocation loc) {
		loc.setReturning(true);
		// just return offscreen
		switch (loc.getWalkType()) {
		case NPCLocation.WALK_EW:
			loc.getTargetLocation().setX(1000);
			loc.setWalkType(NPCLocation.WALK_WE);							
			break;
		case NPCLocation.WALK_WE:
			loc.getTargetLocation().setX(-30);
			loc.setWalkType(NPCLocation.WALK_EW);
			break;
		case NPCLocation.WALK_NS:
			loc.getTargetLocation().setY(-30);
			loc.setWalkType(NPCLocation.WALK_SN);
			break;
		case NPCLocation.WALK_SN:
			loc.getTargetLocation().setY(-40);
			loc.setWalkType(NPCLocation.WALK_NS);
			break;
		}
	}
}
