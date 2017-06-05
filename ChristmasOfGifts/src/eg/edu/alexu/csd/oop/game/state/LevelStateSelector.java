package eg.edu.alexu.csd.oop.game.state;

public class LevelStateSelector {
	private LevelStateInterface state1;
	private LevelStateInterface state2;
	private LevelStateInterface state3;

	private LevelStateInterface myState;

	public LevelStateSelector() {
		state1 = new LevelState1(this);
		state2 = new LevelState2(this);
		state3 = new LevelState3(this);

		myState = state1;

	}

	public void setLevelState(LevelStateInterface newATMState) {

		myState= newATMState;

	}
	
	public int getLevelSpeed(){
		return myState.getLevelSpeed();
	}
	public int getFactorySpeed(){
		return myState.getFactorySpeed();
	}
	public void decreaseLevelSpeed(){
		myState.decreaseLevel();
	}
	public void increaseLevel(){
		myState.increaseLevel();
	}
	public long getPromotionTime(){
		return myState.promotionTime();
	}
	public LevelStateInterface getLevel1(){return state1;} 
	public LevelStateInterface getLevel2(){return state2;} 
	public LevelStateInterface getLevel3(){return state3;} 
	
	

}
