package eg.edu.alexu.csd.oop.game.template;

import eg.edu.alexu.csd.oop.game.observer.Subject;

public abstract class AbstractSantaObject extends AbstractTemplateObject implements Subject{
	
	public abstract int[] getSantaRightHand();
	
	public abstract int[] getSantaLeftHand();
	

	

}
