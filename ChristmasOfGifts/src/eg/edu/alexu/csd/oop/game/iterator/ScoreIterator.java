package eg.edu.alexu.csd.oop.game.iterator;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.log4j.CreateLogger;
import eg.edu.alexu.csd.oop.game.singleton.GameEngineHandler;
import eg.edu.alexu.csd.oop.game.template.AbstractGiftObject;

public class ScoreIterator {
	
	private GiftsIterator interfaceGameIteratable ;
	
	private int size = -1 ;
	
	private int height = 0 ;
	
	private LinkedList<GameObject> l  = new LinkedList<GameObject>();
	
	private LinkedList<GameObject> removed = new LinkedList<GameObject>();
	//private boolean score =  ;
	
	public ScoreIterator(GiftsIterator g) {
		interfaceGameIteratable = g ;
	}

	
	public boolean scoreIncFlag(){
		
		Iterator gameIteratable =  interfaceGameIteratable.createIterator();
		
		String giftType = "";
		String prevGiftType = "";
		int counter = 0 ;
		//boolean isFirst = false ;
		//int x1 = 0 ;
		//int x2 = 0;
		
		int index = -1 ;
		
		
		while(gameIteratable.hasNext()){
			
			try{
				
				index++;
				
				AbstractGiftObject o = (AbstractGiftObject) gameIteratable.next();
			
				giftType = o.getGiftNum();
				CreateLogger.debug(giftType);			//	x1 = o.getX() ;
				

				
				//System.out.println(giftType);
				//System.out.println("x2 = " + x2 + "x1 = " + x1);
				
				//lista.add(o);
				
				if(giftType.equals(prevGiftType)){
					
					//lista.add(o);
					
					//System.out.println(lista.size());
					
					counter++;
					
					System.out.println(counter);
					
					if(counter == 2){
						
						height = o.getHeight() ;
						
						size  =index ; 
						CreateLogger.debug(size + " ");
						//System.out.println(size);
						//CreateLogger.debug(size+"");
						//isFirst = true ;
						//getRemoved();
						//counter = 0 ;
						
						return true ;
						//GameEngineHandler.getGameHandlerInstance().setScore(score);
						//do some thing
						//System.out.println("Score yabaaaaaaaaaa " + score );
					}
					
				}
				else{counter=0;}
				/*if(!isFirst){
					lista.clear();
				}*/
				
				prevGiftType = giftType ;	
				//x2 = x1 ;
				//isFirst = false ;
				//System.out.println(o);
				
			}catch(Exception e){
				
			}
			
			
			
		}
		return false ;
	}
	
	public List<GameObject> getRemoved(){
		
		
		//List l = ((GameIteratable) interfaceGameIteratable).getControl();
		int counter = 0 ;
		if(size != -1){
			for(int i = size  ;i >size-3 ;i-- ){
				removed.add((GameObject) l.remove(i));
				CreateLogger.warn(""+removed.get(counter));
				counter++;
			}
		}
		
		
		size = -1 ;
		
		return l;
		
	}
	public List<GameObject> getControlObjects(List<GameObject> userControl){
		
		for(int j = 0 ; j < removed.size() ; j++){
			for(int i =2  ; i < userControl.size() ;i++){
				try{
					AbstractGiftObject o = ((AbstractGiftObject) userControl.get(i));
					System.out.println(removed.get(j).getX()+" " + o.getX() + " " +removed.get(j).getY() + " " + o.getY());
					if(removed.get(j).getX() == o.getX() && removed.get(j).getY() == o.getY() ){
						userControl.remove(i);
					}
				}catch(Exception e){
					
				}
				
			}
		}
		removed.clear();
		
		return userControl ;
	}
	
	public int getHeight(){
		return height * 3;
	}
	public void setControlObjects(List<GameObject> userControl){
		l.clear();
		l.addAll(userControl) ;
		for(int i = 0 ; i<l.size(); i++){
			l.get(i);
		}
	}
	

}
