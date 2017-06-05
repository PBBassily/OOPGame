package eg.edu.alexu.csd.oop.game.strategy;

public class ScoreStrategy1 implements ScoreStrategyInterfcae {
	private int score ;
	
	
	public ScoreStrategy1(int score) {
		this.score = score;
	}


	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return score;
	}

	

	@Override
	public boolean isEnded(long sec) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void increaseScore() {
		// TODO Auto-generated method stub
		score+=1;
		
	}

	

}
