package eg.edu.alexu.csd.oop.game.singleton;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import eg.edu.alexu.csd.oop.game.dynamicLinkage.ClassLodaerInterface;
import eg.edu.alexu.csd.oop.game.dynamicLinkage.ConcreteGameObjectsClassLoader;
import eg.edu.alexu.csd.oop.game.factory.Factory;
import eg.edu.alexu.csd.oop.game.flyweight.FlyWeighFactory;
import eg.edu.alexu.csd.oop.game.iterator.GameIteratable;
import eg.edu.alexu.csd.oop.game.iterator.ScoreIterator;
import eg.edu.alexu.csd.oop.game.log4j.CreateLogger;
import eg.edu.alexu.csd.oop.game.snapshot.Snapshot;
import eg.edu.alexu.csd.oop.game.snapshot.Snapshoter;
import eg.edu.alexu.csd.oop.game.state.LevelStateSelector;
import eg.edu.alexu.csd.oop.game.strategy.ScoreStrategy1;
import eg.edu.alexu.csd.oop.game.strategy.ScoreStrategy2;
import eg.edu.alexu.csd.oop.game.strategy.ScoreStrategyInterfcae;
import eg.edu.alexu.csd.oop.game.template.AbstractConstantObject;
import eg.edu.alexu.csd.oop.game.template.AbstractGiftObject;
import eg.edu.alexu.csd.oop.game.template.AbstractSantaObject;


public class GameEngineHandler implements World {
	private final int screenHeight = 690;
	private final int screenWidth = 1600;
	private LevelStateSelector currentLevel = new LevelStateSelector();
	private LinkedList<String> availableGifts = new LinkedList<String>();
	private List<GameObject> constants = new LinkedList<GameObject>();
	private List<GameObject> movables = new LinkedList<GameObject>();
	private LinkedList<GameObject> userConrolled = new LinkedList<GameObject>();
	private Class<?>[] loadedClass;
	private Class<AbstractGiftObject> GiftClass;
	private Class<AbstractSantaObject> SantaClass;
	private Class<AbstractConstantObject> ConstantClass;
	private AbstractGiftObject level;
	private Snapshot snapShoter = new Snapshoter();
	private boolean play = true;
	private int frameCounter = 0;
	private int height1 = 0;
	private ScoreStrategyInterfcae scoreStrategy;
	private LinkedList<GameObject> firstSantaLeftHand ;
	private LinkedList<GameObject> firstSantaRightHand ;
	private LinkedList<GameObject> secondSantaLeftHand ;
	private LinkedList<GameObject> secondSantaRightHand;

	private int levelStatus ;
	private long startTime = System.currentTimeMillis();
	private long currentTime = 12000;
	private long startOfStrategy = 0;
	// start of program
	private long gameStartTime = System.currentTimeMillis();

	private static GameEngineHandler singletonHandler = null;

	public static GameEngineHandler getGameHandlerInstance() {
		// first usage

		if (singletonHandler == null) {
			singletonHandler = new GameEngineHandler();
		}
		return singletonHandler;
	}

	protected GameEngineHandler() {
		// TODO Auto-generated constructor stub

		// dynamic loading
		classSetter();

		// initiating the constant object lists
		constantObjListInitiator();

		// initiating the user controlled object lists
		userControlledtObjListInitiator();

		// refresh will choose any gift
		allGiftsAvailable();

		// initiating level ,current level is one
		levelInitiator();
		initiateSantaGifs();
		// musicPlayer.play("1.wav");
		// System.out.println("get here?");
		scoreStrategy = new ScoreStrategy1(0);

	}
	private void initiateSantaGifs(){
		 firstSantaLeftHand =   new LinkedList<GameObject>();
		 firstSantaRightHand =  new LinkedList<GameObject>();
		 secondSantaLeftHand =  new LinkedList<GameObject>();
		 secondSantaRightHand = new LinkedList<GameObject>();
	}
	private void levelInitiator() {
		levelStatus=1;
		level= ((AbstractGiftObject)Factory.createObject(GiftClass));
		level.setBufferedImagePath("/Level.png");
		
		currentLevel.setLevelState(currentLevel.getLevel1());
	}

	private void constantObjListInitiator() {
		// adding the background
		AbstractConstantObject constantObj = ((AbstractConstantObject)Factory.createObject(ConstantClass));
		constantObj.setBufferedImagePath("/room2.png");
		constantObj.setX(0);
		constantObj.setY(0);
		constants.add(constantObj);

		// adding the right belt
		constantObj =  ((AbstractConstantObject)Factory.createObject(ConstantClass));
		constantObj.setBufferedImagePath("/rightBelt2.png");
		constantObj.setX(1100);
		constantObj.setY(70);
		constants.add(constantObj);

		// adding the left belt
		constantObj =  ((AbstractConstantObject)Factory.createObject(ConstantClass));
		constantObj.setBufferedImagePath("/leftBelt2.png");
		constantObj.setX(0);
		constantObj.setY(70);
		constants.add(constantObj);

	}

	private void userControlledtObjListInitiator() {
		// adding Santa Claus :D
		Random randomChooser = new Random();
		int num;
		
		AbstractSantaObject santa = ((AbstractSantaObject)Factory.createObject(SantaClass));
		santa.setX(screenWidth / 6);
		num = randomChooser.nextInt(3)+1;
		santa.setBufferedImagePath("/santaClaus"+num+".png");
		userConrolled.add(santa);

		santa = ((AbstractSantaObject)Factory.createObject(SantaClass));
		santa.setX(screenWidth / 2);
		num = randomChooser.nextInt(3)+1;
		santa.setBufferedImagePath("/santaClaus"+num+".png");
		userConrolled.add(santa);
	}

	private void allGiftsAvailable() {

		for (int i = 1; i < 8; i++) {

			String path = "/gift" + i + ".png";
			availableGifts.add(path);

		}
	}

	private void takeSnapshot() {
		LinkedList<LinkedList<GameObject>>santaGifts = new LinkedList<LinkedList<GameObject>>();
		CreateLogger.fatal("************************before save snapshot**********************");
		printStacks();
		santaGifts.add((LinkedList<GameObject>) firstSantaLeftHand.clone());
		santaGifts.add((LinkedList<GameObject>)firstSantaRightHand.clone());
		santaGifts.add((LinkedList<GameObject>)secondSantaLeftHand.clone());
		santaGifts.add((LinkedList<GameObject>)secondSantaRightHand.clone());
		
		
		
		snapShoter.takeSnapshot((LinkedList<GameObject>) userConrolled, levelStatus+"", "" + scoreStrategy.getScore(), santaGifts);
	}

	private void showSnapshot() {
		initiateSantaGifs();
		userConrolled = new LinkedList<GameObject>();
		userConrolled.addAll(snapShoter.getUserControlle());
		levelStatus = Integer.parseInt(snapShoter.getLvl());
		currentLevel.decreaseLevelSpeed();
		firstSantaLeftHand.addAll(snapShoter.getSantaGifts().get(0));
		firstSantaRightHand.addAll(snapShoter.getSantaGifts().get(1));
		secondSantaLeftHand.addAll(snapShoter.getSantaGifts().get(2));
		secondSantaRightHand.addAll(snapShoter.getSantaGifts().get(3));
		scoreStrategy= new ScoreStrategy1(Integer.parseInt(snapShoter.getScore()));
		CreateLogger.fatal("******************** stacks after snap shot *********************");
		printStacks();

	}
	
	
private void printStacks(){
		
		
		for(GameObject g : firstSantaLeftHand){
			CreateLogger.debug("firstSantaLeftHand"+g + "");
		}
		for(GameObject g : firstSantaRightHand){
			CreateLogger.debug("firstSantaRightHand"+g + "");
		}
		for(GameObject g : secondSantaLeftHand){
			CreateLogger.debug("secondSantaLeftHand"+g + "");
		}
		for(GameObject g : secondSantaRightHand){
			CreateLogger.debug("secondSantaRightHand"+g + "");
		}
		
		
		
	}
	
	private boolean gameOverCondition() {
		if(firstSantaLeftHand.size()==8||
			firstSantaRightHand.size()==8||
			secondSantaLeftHand.size()==8||
			secondSantaRightHand.size()==8){
				
			return true;
		}
		return false;
	}


	@Override
	public List<GameObject> getConstantObjects() {
		// TODO Auto-generated method stub
		return constants;
	}

	@Override
	public List<GameObject> getMovableObjects() {
		// TODO Auto-generated method stub
		return movables;
	}

	@Override
	public List<GameObject> getControlableObjects() {
		// TODO Auto-generated method stub
		return userConrolled;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return screenWidth;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return screenHeight;
	}

	@Override
	public boolean refresh() {
	
		if (gameOverCondition()) {
			
			return false;
		}
		// new object
		AbstractGiftObject gift;
		// what is time now
		currentTime = System.currentTimeMillis();

		if (currentTime - startTime >= currentLevel.getFactorySpeed()) {
			// TODO Auto-generated method stub

			Random randomIndex = new Random();
			frameCounter++;
			// how many available gift
			int size = availableGifts.size();
			if (frameCounter == 15) {
				gift = ((AbstractGiftObject)Factory.createObject(GiftClass));
				gift.setBufferedImagePath("/x2.png");
				gift.setX(1400);
				gift.setY(38);
				gift.observeSanta((AbstractSantaObject) userConrolled.get(0));
				gift.observeSanta((AbstractSantaObject) userConrolled.get(1));
				movables.add(gift);

			}

			else if (frameCounter == 50 || frameCounter == 40) {
				gift = ((AbstractGiftObject)Factory.createObject(GiftClass));
				gift.setBufferedImagePath("/reset.png");
				gift.setX(1400);
				gift.setY(38);
				gift.observeSanta((AbstractSantaObject) userConrolled.get(0));
				gift.observeSanta((AbstractSantaObject) userConrolled.get(1));
				movables.add(gift);
			} 
			else if (size != 0) {
				// pick an address to it randomly
				int randomGift = randomIndex.nextInt(size);
				// it is not available any more
				String path = availableGifts.remove(randomGift);
				// just create or reuse a gift of this address
				gift = FlyWeighFactory.createGift(path, GiftClass);
				// hey gift , please observe santa
				gift.observeSanta((AbstractSantaObject) userConrolled.get(0));
				gift.observeSanta((AbstractSantaObject) userConrolled.get(1));
				// to it to movable
				movables.add(gift);
			}
			// the start of a new frame
			startTime = currentTime;

			// after 30 sec from level 2 to 3

			if (currentTime - gameStartTime >= currentLevel.getPromotionTime() && levelStatus!=3) {
				
				CreateLogger.info("level :"+levelStatus);
				takeSnapshot();
				currentLevel.increaseLevel();
				levelStatus ++;
				level.setX(screenWidth / 3);
				level.setY(-400);
				movables.add(level);
			}

		}
		// iterate on each movable gift
		try {
			for (GameObject o : movables) {
				if (((AbstractGiftObject) o).getGiftNum().contains("Level")) {

					o.setY(o.getY() + currentLevel.getLevelSpeed() + 4);
					if (o.getY() >= getHeight()) {
						movables.remove(o);
					}
					continue;
				}

				// on left belt
				else if (o.getX() < 305) {
					o.setX(o.getX() + currentLevel.getLevelSpeed());

					// on right belt
				} else if (o.getX() > 1030) {
					o.setX(o.getX() - currentLevel.getLevelSpeed());

					// falling
				} else {
					// out of frame
					if (o.getY() >= getHeight()) {

						// hey flyWeight , it is available again

						if (((AbstractGiftObject) o).getGiftNum().contains("gift")) {
							availableGifts.add(((AbstractGiftObject) o).getGiftNum());
						}

						// say bye bye to this gift
						movables.remove(o);
						

						// please unregister this gift

						continue;
					}
					// falling style

					o.setY(o.getY() + 2 + currentLevel.getLevelSpeed());
					o.setX(o.getX() + (Math.random() > 0.5 ? 1 : -1));
					// System.out.println("i have been called1");
			//		System.out.println("eih ba2a");
					((AbstractSantaObject) userConrolled.get(0)).notifyObserver();
					((AbstractSantaObject) userConrolled.get(1)).notifyObserver();

					// System.out.println(((AbstractGiftObject)
					// o).isIntersected());
					// System.out.println("i have been called");

					if (((AbstractGiftObject) o).isIntersected()) {
				//		System.out.println("ok1");
						if (((AbstractGiftObject) o).getGiftNum().contains("reset")) {
							// snapshot
							CreateLogger.fatal("ok");
							for (int i = 0; i < 2; i++) {

								
					
									
									if(i == 0){
										height1 = firstSantaLeftHand.size()- snapShoter.getSantaGifts().get(0).size();
										height1 *= o.getHeight() ;
										CreateLogger.fatal(height1 + "");
										((AbstractSantaObject) userConrolled.get(i)).setDistination(-height1, 0);
										//counter++;
										
										height1 = firstSantaRightHand.size() - snapShoter.getSantaGifts().get(1).size();
										height1 *= o.getHeight() ;
										CreateLogger.fatal(height1 + "");
										((AbstractSantaObject) userConrolled.get(i)).setDistination(0,- height1);
										//counter++;
										
										CreateLogger.debug("*********************Stacks******************");
										printStacks();
									}
						
									else{
										height1 = secondSantaLeftHand.size()- snapShoter.getSantaGifts().get(2).size();
										height1 *=o.getHeight();
										((AbstractSantaObject) userConrolled.get(i)).setDistination(-height1, 0);
										//counter++;
										CreateLogger.fatal(height1 + "");
										height1 = secondSantaRightHand.size() - snapShoter.getSantaGifts().get(3).size();
										height1 *=o.getHeight();
										CreateLogger.fatal(height1 + "");
										((AbstractSantaObject) userConrolled.get(i)).setDistination(0,- height1);
										//counter++;
										
										CreateLogger.debug("*********************Stacks******************");
										printStacks();
									}
									
								//}
							}
							height1 = 0;
							showSnapshot();
							movables.remove(o);
							continue;
						} else if (((AbstractGiftObject) o).getGiftNum().contains("x2")) {
							// strategy
							int score = scoreStrategy.getScore();
							scoreStrategy = new ScoreStrategy2(score);
							startOfStrategy = System.currentTimeMillis();

							movables.remove(o);

							continue;
						}
						AbstractGiftObject santaGift = ((AbstractGiftObject)Factory.createObject(GiftClass));
						santaGift.setX(o.getX());
						santaGift.setY(o.getY());
						santaGift.setBufferedImagePath(((AbstractGiftObject)o).getGiftNum());
						santaGift.setControled(true);
						userConrolled.add(santaGift);
						
						//CreateLogger.debug(o+"");
						if (Math.abs(
								((AbstractSantaObject) userConrolled.get(0)).getSantaLeftHand()[0] - santaGift.getX()) <=75 ) {

							((AbstractSantaObject) userConrolled.get(0)).setDistination(santaGift.getHeight(), 0);
							
							CreateLogger.debug("firstSantaLeftHand " +santaGift );
							
							firstSantaLeftHand.add(santaGift);
						} else {
							((AbstractSantaObject) userConrolled.get(1)).setDistination(santaGift.getHeight(), 0);
							
							secondSantaLeftHand.add(santaGift);
							
							CreateLogger.debug("secondSantaLeftHand " +santaGift);
						}

						height1 = 0;
						// hey flyWeight , it is available again
						availableGifts.add(((AbstractGiftObject) o).getGiftNum());

						// say bye bye to this gift
						// ((AbstractGiftObject)o).setControled(true);
						movables.remove(o);

						GameIteratable iterator = new GameIteratable();

						iterator.setControlable(firstSantaLeftHand);

						ScoreIterator scIterator = new ScoreIterator(iterator);

						if (scIterator.scoreIncFlag()) {
							scoreStrategy.increaseScore();
							height1 = scIterator.getHeight();
							((AbstractSantaObject) userConrolled.get(0)).setDistination(-height1, 0);
							scIterator.setControlObjects(firstSantaLeftHand);
							firstSantaLeftHand.clear();
							firstSantaLeftHand.addAll(scIterator.getRemoved());
							userConrolled=(LinkedList<GameObject>) scIterator.getControlObjects(userConrolled);
						}
						height1 = 0;

						GameIteratable iterator2 = new GameIteratable();

						iterator2.setControlable(secondSantaLeftHand);

						ScoreIterator scIterator2 = new ScoreIterator(iterator2);

						if (scIterator2.scoreIncFlag()) {
							scoreStrategy.increaseScore();
							height1 = scIterator2.getHeight();
							((AbstractSantaObject) userConrolled.get(1)).setDistination(-height1, 0);
							scIterator2.setControlObjects(secondSantaLeftHand);
							secondSantaLeftHand.clear();
							secondSantaLeftHand.addAll(scIterator2.getRemoved());
							userConrolled=(LinkedList<GameObject>) scIterator2.getControlObjects(userConrolled);
						}
						height1 = 0;
						// userConrolled=(LinkedList<GameObject>)
						// scIterator.getRemoved();
						// height1=scIterator.getHeight();
						// ((AbstractSantaObject)
						// userConrolled.get(0)).setDistination(-height1,0);
						// ((AbstractSantaObject)
						// userConrolled.get(1)).setDistination(-height1,0);

					}
					
					
					if (((AbstractGiftObject) o).isIntersected2()) {
						//System.out.println("ok2");
						if (((AbstractGiftObject) o).getGiftNum().contains("reset")) {
							// snapshot
							for (int i = 0; i < 2; i++) {

								//if (userConrolled.get(i) instanceof AbstractSantaObject) {

									if(i == 0){
										height1 = firstSantaLeftHand.size()- snapShoter.getSantaGifts().get(0).size();
										height1 *= o.getHeight() ;
										((AbstractSantaObject) userConrolled.get(i)).setDistination(-height1, 0);
									//	counter++;
										CreateLogger.fatal(height1 + ""); 
										height1 = firstSantaRightHand.size() - snapShoter.getSantaGifts().get(1).size();
										height1 *= o.getHeight() ;
										((AbstractSantaObject) userConrolled.get(i)).setDistination(0,- height1);
								//		counter++;
										CreateLogger.fatal(height1 + "");
										CreateLogger.debug("*********************Stacks******************");
										printStacks();
									}
									else{
										height1 = secondSantaLeftHand.size()- snapShoter.getSantaGifts().get(2).size();
										height1 *=o.getHeight();
										((AbstractSantaObject) userConrolled.get(i)).setDistination(-height1, 0);
										//counter++;
										CreateLogger.fatal(height1 + "");
										
										height1 = secondSantaRightHand.size() - snapShoter.getSantaGifts().get(3).size();
										height1 *=o.getHeight();
										((AbstractSantaObject) userConrolled.get(i)).setDistination(0,- height1);
										//counter++;
										CreateLogger.fatal(height1 + "");
										CreateLogger.debug("*********************Stacks******************");
										printStacks();
									}
									
								//}
							}
							height1 = 0;
							showSnapshot();
							movables.remove(o);
							continue;
						} else if (((AbstractGiftObject) o).getGiftNum().contains("x2")) {
							// strategy
							int score = scoreStrategy.getScore();
							scoreStrategy = new ScoreStrategy2(score);
							startOfStrategy = System.currentTimeMillis();
							movables.remove(o);
							continue;
						}
						AbstractGiftObject santaGift = ((AbstractGiftObject)Factory.createObject(GiftClass));
						santaGift.setX(o.getX());
						santaGift.setY(o.getY());
						santaGift.setBufferedImagePath(((AbstractGiftObject)o).getGiftNum());
						santaGift.setControled(true);
						userConrolled.add(santaGift);

						CreateLogger.debug("masafaa"+Math.abs(((AbstractSantaObject) userConrolled.get(0)).getSantaRightHand()[0] - o.getX()));
						if (Math.abs(
								((AbstractSantaObject) userConrolled.get(0)).getSantaRightHand()[0] - santaGift.getX()) <= 75) {

							((AbstractSantaObject) userConrolled.get(0)).setDistination(0, santaGift.getHeight());
							//CreateLogger.debug("firstSantaRightHand " +santaGift );
							firstSantaRightHand.add(santaGift);
							CreateLogger.debug("firstSantaRightHand" + " " + o.getX());
						} else {

							((AbstractSantaObject) userConrolled.get(1)).setDistination(0, santaGift.getHeight());
							secondSantaRightHand.add(santaGift);
							CreateLogger.debug("secondSantaRightHand" + " " + santaGift.getX());
						}

						height1 = 0;
						// hey flyWeight , it is available again
						availableGifts.add(((AbstractGiftObject) o).getGiftNum());

						// say bye bye to this gift
						// ((AbstractGiftObject)o).setControled(true);
						movables.remove(o);

						GameIteratable iterator = new GameIteratable();

						iterator.setControlable(firstSantaRightHand);

						ScoreIterator scIterator = new ScoreIterator(iterator);
						
						if (scIterator.scoreIncFlag()) {
							scoreStrategy.increaseScore();
							
							height1 = scIterator.getHeight();
							
							scIterator.setControlObjects(firstSantaRightHand);
							firstSantaRightHand.clear();
							firstSantaRightHand.addAll(scIterator.getRemoved());
							//firstSantaRightHand = (LinkedList<GameObject>) 
							((AbstractSantaObject) userConrolled.get(0)).setDistination(0, -height1);
							userConrolled = (LinkedList<GameObject>) scIterator.getControlObjects(userConrolled);
						}

						GameIteratable iterator2 = new GameIteratable();

						iterator2.setControlable(secondSantaRightHand);
						
						//System.out.println(secondSantaRightHand.size());
						
						ScoreIterator scIterator2 = new ScoreIterator(iterator2);
						
						if (scIterator2.scoreIncFlag()) {
							scoreStrategy.increaseScore();
							
							height1 = scIterator2.getHeight();
							scIterator2.setControlObjects(secondSantaRightHand);
							secondSantaRightHand.clear();
							secondSantaRightHand.addAll(scIterator2.getRemoved());
							//secondSantaRightHand = (LinkedList<GameObject>) 
							((AbstractSantaObject) userConrolled.get(1)).setDistination(0, -height1);
							userConrolled=(LinkedList<GameObject>) scIterator2.getControlObjects(userConrolled);
						}
						height1 = 0;
						//userConrolled = (LinkedList<GameObject>) scIterator.getRemoved();
						//height1 = scIterator.getHeight();
						//((AbstractSantaObject) userConrolled.get(0)).setDistination(0, -height1);

					}

				}

			}
		} catch (Exception e) {
			 System.out.println("error in level"+levelStatus);
			 

		}
		// Game Over is not regarded yet
		return play;
	}

	

	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		long timeStatus = (System.currentTimeMillis() - gameStartTime) / 1000;
		String s = "Level "+levelStatus+" || Score : " + scoreStrategy.getScore() + " || Time : " + timeStatus
				+" || Strategy";
		
		if (scoreStrategy instanceof ScoreStrategy2) {
			timeStatus=((System.currentTimeMillis() - startOfStrategy) / 1000);
			if (scoreStrategy.isEnded(timeStatus)) {
				int score=scoreStrategy.getScore();
				scoreStrategy = new ScoreStrategy1(score);
				
				return s+=" 1";
			}
			s +=" 2 : ";
			s +=  ((ScoreStrategy2) scoreStrategy).getTimeLeft();
		}
		else {s+="  1";}
		return s ;
	}

	@Override
	public int getSpeed() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public int getControlSpeed() {
		// TODO Auto-generated method stub
		return 10;
	}

	private void classSetter() {
		ClassLodaerInterface loader = new ConcreteGameObjectsClassLoader();
		try {
			loadedClass = loader.getLoadedClasses();
			GiftClass = (Class<AbstractGiftObject>) loadedClass[0];
			SantaClass = (Class<AbstractSantaObject>) loadedClass[1];
			ConstantClass = (Class<AbstractConstantObject>) loadedClass[2];
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CreateLogger.fatal(e.toString());
		}

	}

}
