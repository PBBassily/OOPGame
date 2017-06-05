package eg.edu.alexu.csd.oop.game.observer;

public interface Subject {
	
	public void register(Observer o);
	public void unregister(Observer o);
	public void notifyObserver();
	public void setDistination(int distinationY ,int distinationY1 );
	
}

