package eg.edu.alexu.csd.oop.game.template;


import eg.edu.alexu.csd.oop.game.observer.Observer;
public abstract class AbstractGiftObject extends AbstractTemplateObject implements Observer {
	
	public abstract String getGiftNum();
	public abstract boolean isIntersected();
	public abstract boolean isIntersected2();
	public abstract void setControled(boolean controled);
	public abstract void observeSanta(AbstractSantaObject santa);

}
