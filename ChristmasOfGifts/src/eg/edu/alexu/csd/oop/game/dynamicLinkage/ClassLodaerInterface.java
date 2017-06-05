package eg.edu.alexu.csd.oop.game.dynamicLinkage;





public interface ClassLodaerInterface {
	/**
	 * loads all concrete game objects class at run time 
	 * @return
	 */
	public Class<?>[] getLoadedClasses() throws Exception;
	
	
}
