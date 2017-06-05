package eg.edu.alexu.csd.oop.game.state;

public class LevelState1 implements LevelStateInterface {
	// 5 pixel per frame
	private final int levelSpeed = 2;
	private final int factoryspeed = 3000;
	private final long promotionTime=40000;
	private LevelStateSelector  currentLevel;
	
	public LevelState1( LevelStateSelector currentLevel) {
		// TODO Auto-generated constructor stub
		this.currentLevel=currentLevel;
	}

	@Override
	public int getLevelSpeed() {
		// TODO Auto-generated method stub
		return this.levelSpeed;
	}
	
	@Override
	public int getFactorySpeed() {
		// TODO Auto-generated method stub
		return this.factoryspeed;
	}

	@Override
	public void increaseLevel() {
		// TODO Auto-generated method stub
		currentLevel.setLevelState(currentLevel.getLevel2());
		
		
		
	}

	@Override
	public void decreaseLevel() {
		// TODO Auto-generated method stub
		// enta 3bit yalla
		
	}

	@Override
	public long promotionTime() {
		// TODO Auto-generated method stub
		return promotionTime ;
	}

}
