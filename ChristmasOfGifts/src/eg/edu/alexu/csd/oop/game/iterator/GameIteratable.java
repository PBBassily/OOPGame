package eg.edu.alexu.csd.oop.game.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;

public class GameIteratable implements GiftsIterator{
	
	private ArrayList<GameObject>  userConrolled ;

	public GameIteratable() {
		
		userConrolled = new ArrayList<GameObject>();
	}
	
	public void setControlable(List<GameObject> control){
		
		for(int i = 0 ; i < control.size() ; i++){
			
			userConrolled.add(control.get(i));
			
		}
		
	}
	
	@Override
	public Iterator createIterator() {
		
		return userConrolled.iterator();
	}
	
	public List<GameObject> getControl(){
		return userConrolled;
	}

}
