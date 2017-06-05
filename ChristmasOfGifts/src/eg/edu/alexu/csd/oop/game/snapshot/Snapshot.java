package eg.edu.alexu.csd.oop.game.snapshot;


import java.util.LinkedList;

import eg.edu.alexu.csd.oop.game.GameObject;

public interface Snapshot {
	
	public void takeSnapshot(LinkedList<GameObject> userControlle, String lvl, String score,LinkedList<LinkedList<GameObject>> santaGifts );
	
	public LinkedList<GameObject> getUserControlle();
	
	public String getLvl();
	
	public String getScore();
	public LinkedList<LinkedList<GameObject>> getSantaGifts();
	
	

}
