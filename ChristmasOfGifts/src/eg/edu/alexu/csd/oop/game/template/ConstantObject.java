package eg.edu.alexu.csd.oop.game.template;


import java.awt.image.BufferedImage;
import java.io.IOException;


import javax.imageio.ImageIO;



public class ConstantObject extends AbstractConstantObject {
	private final int FRAMES_NUM = 1;
	private int y;
	private BufferedImage[] objectFrameImg = new BufferedImage[FRAMES_NUM];

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub
		this.y=y;
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

	@Override
	public void setBufferedImagePath(String path) {
		// TODO Auto-generated method stub
		
		try {
			objectFrameImg[0] = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		
	}

	

}
