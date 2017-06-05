package eg.edu.alexu.csd.oop.game;

import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.singleton.GameEngineHandler;

public class StartEngine {
	public static void main(String args[]){
		GameEngineHandler engineHandler = GameEngineHandler.getGameHandlerInstance();
		
		GameEngine.start("Christmas Gifts Factory",engineHandler);
		
	}

}
