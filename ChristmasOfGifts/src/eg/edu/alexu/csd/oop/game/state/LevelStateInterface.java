package eg.edu.alexu.csd.oop.game.state;

public interface LevelStateInterface {
	/**
	 * levels are varing on speed
	 * @return int speed
	 */
	public int getLevelSpeed();
	public int getFactorySpeed();
	public void increaseLevel();
	public void decreaseLevel();
	public long promotionTime();
	

}
