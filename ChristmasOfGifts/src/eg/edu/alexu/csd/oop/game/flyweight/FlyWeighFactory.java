
package eg.edu.alexu.csd.oop.game.flyweight;

import java.util.HashMap;


import java.util.Random;

import eg.edu.alexu.csd.oop.game.log4j.CreateLogger;
import eg.edu.alexu.csd.oop.game.template.AbstractGiftObject;


;

public class FlyWeighFactory {

	private static final HashMap<String, AbstractGiftObject> rectsByImage = new HashMap<String, AbstractGiftObject>();
	private static AbstractGiftObject gift;
	private static boolean rightBeltFlag;
	

	public static AbstractGiftObject createGift(String path,Class<AbstractGiftObject> GiftClass) {
		
		gift = rectsByImage.get(path);
		
		CreateLogger.error(gift+"");
		
		if (gift == null) {
			
			// not used before , new one 
			try {
				gift = GiftClass.newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				CreateLogger.fatal(e.toString());
			}
			gift.setBufferedImagePath(path);
			
			// Add new gift to HashMap
			rectsByImage.put(path, (AbstractGiftObject) gift);
		}
		
		Random randomBelt = new Random();
		rightBeltFlag = randomBelt.nextBoolean();
		
		if(rightBeltFlag){
			//on right belt
			gift.setX(1400);
		}
		else {
			// on left belt
			gift.setX(-70);
		}
		// whatever belt , they the same initial height
		gift.setY(38);
		
		return gift;
	}

}
