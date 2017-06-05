package eg.edu.alexu.csd.oop.game.strategy;

public class ScoreStrategy2 implements ScoreStrategyInterfcae {
	
	private int score;
	private final long timeToEnd= 40;
	private long currentTime= 40;
	
	
	public ScoreStrategy2(int score) {
		
		this.score=score;
		
		
	}



	@Override
	public int getScore() {
		// TODO Auto-generated method stub;
		return score;
	}

	@Override
	public boolean isEnded(long time) {
		// TODO Auto-generated method stub
		currentTime=time;
		return timeToEnd<time;
	}

	@Override
	public void increaseScore() {
		// TODO Auto-generated method stub
		if(score==0){
			score=1;
		}
		score *=4;
	}
	public long getTimeLeft(){
		return (timeToEnd- currentTime);
	}
	
	
	
}
