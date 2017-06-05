package eg.edu.alexu.csd.oop.game.log4j;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class CreateLogger {

	final static Logger log = Logger.getLogger(CreateLogger.class);
	
	
	public static void debug(String debug){
		PropertyConfigurator.configure(System.getProperty("user.home") + "/Desktop/log4j.properties");
		log.debug(debug);
	}
	
	public static void info(String info){
		log.info(info);
	}
	
	public static void error(String error){
		log.error(error);
	}

	public static void warn(String warn){
		log.warn(warn);

	}

	public static void fatal(String fatal){
		log.fatal(fatal);

	}
	
}
