package com.mycompany.a3;

import com.codename1.util.MathUtil;

/**
 * This class is for the Attack Strategy for the NPC. This strategy moves the 
 * NPC towards the player cyborg and causes damage to both the NPC and player.
 * 
 * @author kiryl
 *
 */

public class AttackStrategy implements IStrategy {
	private GameWorld gw;
	
	public AttackStrategy(GameWorld gw) {
		this.gw = gw;
	}

	/* applies the strategy when the invokeStrategy() method in NPC is invoked. It gets
	 * the location of the player and moves the heading accordinly to get to the player */
	@Override
	public void apply(NonPlayerCyborg npc) {
		PlayerCyborg pCyb = gw.getPlayerCyborg();
		double a = pCyb.getLocation().getX() - npc.getLocation().getX();
		double b = pCyb.getLocation().getY() - npc.getLocation().getY();
		double B = MathUtil.atan2(a,b);
		int steerDir = (int)Math.toDegrees(B);
		npc.setSteeringDirection(steerDir);
		npc.setHeading(npc.getSteeringDirection());
		npc.setSpeed(60);
	}

}
