package eg.edu.alexu.csd.oop.game.template;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;


public class GiftObject extends AbstractGiftObject {

	private final int FRAMES_NUM = 1;
	private int y;
	private String path;
	private BufferedImage[] objectFrameImg = new BufferedImage[FRAMES_NUM];
	private boolean isControlled=false;
	private LinkedList<Integer> distinationY = new LinkedList<>();
	private LinkedList<Integer> distinationX = new LinkedList<>();
	private LinkedList<Integer> distinationY1 = new LinkedList<>();
	private LinkedList<Integer> distinationX1 = new LinkedList<>();

	
	@Override
	public int getY() {
		// TODO Auto-generated method stub
		
		return y;
	}

	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub
		if(isControlled)return;
		this.y = y;
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

	public String getGiftNum() {
		return this.path;
	}
	public void setControled(boolean controled){
		isControlled=controled;
	}
	public void setBufferedImagePath(String path) {
		// TODO Auto-generated method stub
		this.path = path;
		try {
			objectFrameImg[0] = ImageIO.read(getClass().getResourceAsStream(
					path));
		} catch (IOException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}
	}

	@Override
	public void update(int distinationX, int distinationY ,int distinationX1, int distinationY1 ) {

		this.distinationX.add(distinationX);

		this.distinationY.add(distinationY);
		
		this.distinationX1.add(distinationX1);

		this.distinationY1.add(distinationY1);

	}

	public boolean isIntersected() {
		
			int x = getX();

			int y = getY();

			for (int i = 0; i < distinationX.size(); i++) {
				/*if (x == distinationX && y == distinationY) {
							return true;
						}*/
				if (distinationX.get(i) >= x && distinationX.get(i) <= x + getWidth()
						&& Math.abs(y + getHeight() - distinationY.get(i)) <= 5) {
					return true;
				} 
			}
			distinationX.clear();
			distinationY.clear();
		return false;

	}

	public boolean isIntersected2() {
		
			int x = getX();

			int y = getY();

			/*if (x == distinationX && y == distinationY) {
				return true;
			}*/
			
			for (int i = 0; i < distinationX1.size(); i++) {
				if (distinationX1.get(i) >= x && distinationX1.get(i) <= x + getWidth()
						&& Math.abs(y + getHeight() - distinationY1.get(i)) <= 5) {
					return true;
				} 
			}
			distinationX1.clear();
			distinationY1.clear();
		return false;

	}

	@Override
	public void observeSanta(AbstractSantaObject santa) {
		// TODO Auto-generated method stub
		santa.register(this);
		
	}
}