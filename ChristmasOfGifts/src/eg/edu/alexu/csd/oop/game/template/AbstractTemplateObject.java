package eg.edu.alexu.csd.oop.game.template;

import eg.edu.alexu.csd.oop.game.GameObject;

public abstract class AbstractTemplateObject implements GameObject{
	private int x =0;
	private boolean visible =true;
	
	// the template methods 
	@Override
	public final int getX() {
		// TODO Auto-generated method stub
		return x;
	}
	
	// the template methods 
	@Override
	public final void setX(int x) {
		// TODO Auto-generated method stub
		this.x=x;
	}
	
	// the template methods 
	@Override
	public final boolean isVisible() {
		// TODO Auto-generated method stub
		return visible;
	}
	
	// template method and overriding the interface
	public final void setVisibilty(boolean flag){
		this.visible=flag;
	}
	 
	// overriding the interface
	public abstract void setBufferedImagePath(String path);	

}
