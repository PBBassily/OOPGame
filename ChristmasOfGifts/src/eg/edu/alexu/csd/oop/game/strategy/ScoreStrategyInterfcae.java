package eg.edu.alexu.csd.oop.game.strategy;

public interface ScoreStrategyInterfcae {
	
	/**
	 * During playing, score is varing
	 * @return int Score
	 */

	
	public void increaseScore();
	
	public int getScore();
	
	public boolean isEnded(long sec);
}
