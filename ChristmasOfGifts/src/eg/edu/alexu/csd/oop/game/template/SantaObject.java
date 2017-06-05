package eg.edu.alexu.csd.oop.game.template;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import eg.edu.alexu.csd.oop.game.observer.Observer;



public class SantaObject extends AbstractSantaObject {

	private final int FRAMES_NUM = 1;
	private BufferedImage[] objectFrameImg = new BufferedImage[FRAMES_NUM];
	private ArrayList<Observer> observers= new ArrayList<Observer>();
	private int[] pos = new int[2];
	private int distinationY =0 ;
	private int distinationY2 =0 ;
	
	
	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 380;
	}

	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return objectFrameImg[0].getWidth();
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return objectFrameImg[0].getHeight();
	}

	@Override
	public BufferedImage[] getSpriteImages() {
		// TODO Auto-generated method stub
		return objectFrameImg;
	}

	public int[] getSantaRightHand() {
		
		pos[0] = getX() + getWidth() - 10;
		pos[1] = getY() + 90 - distinationY2;
		return pos;
	}

	public int[] getSantaLeftHand() {
		
		pos[0] = getX() + 10;
		pos[1] = getY() + 90  - distinationY;
		
		return pos;
	}

	public void setBufferedImagePath(String path) {
		// TODO Auto-generated method stub

		try {
			objectFrameImg[0] = ImageIO.read(getClass().getResourceAsStream(
					path));
		} catch (IOException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}
	}

	@Override
	public void register(Observer o) {
		observers.add(o);
		
	}

	@Override
	public void unregister(Observer o) {
		int observerIndex = observers.indexOf(o);
		
		observers.remove(observerIndex);
		
	}

	@Override
	public void notifyObserver() {
		
		for(Observer observer : observers){
			//System.out.println(getSantaLeftHand()[0] + " " + getSantaLeftHand()[1] );
			observer.update( getSantaLeftHand()[0] , getSantaLeftHand()[1] ,getSantaRightHand()[0] ,getSantaRightHand()[1] );
			
		}
		
	}

	@Override
	public void setDistination(int distinationY ,int distinationY2 ) {
		
		pos[1]=distinationY;
		
		this.distinationY += distinationY ;
		this.distinationY2 += distinationY2;
		
		//notifyObserver();
		
	}
}
