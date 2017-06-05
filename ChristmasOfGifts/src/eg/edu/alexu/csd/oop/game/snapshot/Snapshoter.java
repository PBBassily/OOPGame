package eg.edu.alexu.csd.oop.game.snapshot;


import java.util.LinkedList;

import eg.edu.alexu.csd.oop.game.GameObject;

public class Snapshoter implements Snapshot{
	private  LinkedList<LinkedList<GameObject>> gifts ;
	private LinkedList<GameObject> userControlle;
	private String lvl;
	private String score;
	
	
	public Snapshoter() {
		userControlle = new LinkedList<GameObject>();
		lvl = null;
		score = null;
		gifts = new LinkedList<LinkedList<GameObject>>();
		
	}
	
	@Override
	public void takeSnapshot(LinkedList<GameObject> userControlle, String lvl, String score,LinkedList<LinkedList<GameObject>> santaGifts) {
		this.userControlle.clear();
		this.userControlle.addAll(userControlle);
		this.lvl = lvl;
		this.score = score;
		this.gifts.clear();
		this.gifts .addAll(santaGifts);
		
		
	}

	@Override
	public LinkedList<GameObject> getUserControlle() {

		return userControlle;
	}

	@Override
	public String getLvl() {
		return lvl;
	}

	@Override
	public String getScore() {
		return score;
	}


	@Override
	public LinkedList<LinkedList<GameObject>> getSantaGifts() {
		// TODO Auto-generated method stub
		return gifts;
	}


}
