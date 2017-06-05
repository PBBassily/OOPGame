package eg.edu.alexu.csd.oop.game.state;

public class LevelState3 implements LevelStateInterface {
	// 15 pixel per frame
	private final int levelSpeed = 8;
	private final int factoryspeed = 1500;
	private LevelStateSelector  currentLevel;
	
	public LevelState3( LevelStateSelector currentLevel) {
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
		// enta 3bit yalla
		
	}

	@Override
	public void decreaseLevel() {
		// TODO Auto-generated method stub
		currentLevel.setLevelState(currentLevel.getLevel2());
	}
	
	@Override
	public long promotionTime() {
		// TODO Auto-generated method stub
		// level 3 has no limit
		return 1000000 ;
	}

}