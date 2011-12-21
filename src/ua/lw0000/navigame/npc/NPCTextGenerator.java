package ua.lw0000.navigame.npc;

import java.util.Random;

public class NPCTextGenerator {

	private static String[] PREDEFINED = new String[] {
		"Faster, faster, faster!",
		"More steam, more power!",
		"What's the progress with BMD?",
		"What's the progress with Routing?",
		"You fucked up!",
		"I need your report!",
		"Your salary is delayed!",
		"You lazy bone!"
		
		
	};
	
	public static String generateNPCText() {
		Random rand = new Random(System.currentTimeMillis());
		return PREDEFINED[rand.nextInt(PREDEFINED.length)];
	}
}
