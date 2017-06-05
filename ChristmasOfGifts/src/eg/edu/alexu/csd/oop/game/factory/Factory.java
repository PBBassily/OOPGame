package eg.edu.alexu.csd.oop.game.factory;

import eg.edu.alexu.csd.oop.game.log4j.CreateLogger;
import eg.edu.alexu.csd.oop.game.template.AbstractTemplateObject;

public class Factory {
	private static AbstractTemplateObject object;

	public static AbstractTemplateObject createObject(Class<?> objectClass) {

		try {
			// the factory produces object according to the objectClass type 
			object = (AbstractTemplateObject) objectClass.newInstance();
			CreateLogger.error(object+"");
			

		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
		CreateLogger.fatal(	e.toString());
		}
		return object;
	}

}
