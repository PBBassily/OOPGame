package eg.edu.alexu.csd.oop.game.state;

public class LevelState2 implements LevelStateInterface {
	// 10 pixel per frame
	private final int levelSpeed = 4;
	private final int factoryspeed = 2000;
	private long promotionTime =70000;
	private LevelStateSelector  currentLevel;
	
	public LevelState2( LevelStateSelector currentLevel) {
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
		currentLevel.setLevelState(currentLevel.getLevel3());
		
	}

	@Override
	public void decreaseLevel() {
		// TODO Auto-generated method stub
		currentLevel.setLevelState(currentLevel.getLevel1());
		
	}
	@Override
	public long promotionTime() {
		// TODO Auto-generated method stub
		return promotionTime ;
	}

}