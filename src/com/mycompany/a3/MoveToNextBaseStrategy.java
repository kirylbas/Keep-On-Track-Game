package com.mycompany.a3;
import com.codename1.util.MathUtil;

/**
 * This class is for the Move to Next Base Strategy for the NPC. This strategy moves the 
 * NPC towards the base that has a sequence number that is one greated the NPC last base reached.
 * @author kiryl
 *
 */

public class MoveToNextBaseStrategy implements IStrategy {
	private GameWorld gw;
	
	public MoveToNextBaseStrategy(GameWorld gw) {
		this.gw = gw;
	}

	/* this method is executed when invokeStrategy() is called for a NPC that is
	 * using this strategy */
	@Override
	public void apply(NonPlayerCyborg npc) {
		GameObjectCollection gameObjColl = gw.getGameObjCollection();
		IIterator theGameObjs = gameObjColl.getIterator();
		while(theGameObjs.hasNext()) {
			GameObject gameObj = theGameObjs.getNext();
			if(gameObj instanceof Base) {
				if(npc.getLastBaseReached() + 1 == ((Base)gameObj).getSequenceNumber()) {
					Base base = (Base)gameObj;
					double a = (base.getLocation().getX() + base.getSize()/2) - npc.getLocation().getX();
					double b = (base.getLocation().getY() + base.getSize()/2) - npc.getLocation().getY();
					double B = MathUtil.atan2(a,b);
					int steerDir = (int)Math.toDegrees(B);
					npc.setSteeringDirection(steerDir);
					npc.setHeading(npc.getSteeringDirection());
					npc.setSpeed(60);
					break;
				}
			}
		}
	}
}
