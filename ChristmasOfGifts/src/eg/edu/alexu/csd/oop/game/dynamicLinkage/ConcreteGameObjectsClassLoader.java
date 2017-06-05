package eg.edu.alexu.csd.oop.game.dynamicLinkage;



import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import eg.edu.alexu.csd.oop.game.log4j.CreateLogger;
import eg.edu.alexu.csd.oop.game.template.AbstractGiftObject;
import eg.edu.alexu.csd.oop.game.template.AbstractSantaObject;

public class ConcreteGameObjectsClassLoader implements ClassLodaerInterface {
	
	private Class<?>[] classes = new Class<?>[3] ;
	
	
	@Override
	public Class<?>[] getLoadedClasses() throws Exception {
			final String pathToJar =System.getProperty("user.home") + "/Desktop/GameConcereteClasses.jar";
			
			CreateLogger.debug(pathToJar);
			
			JarFile jarFile = new JarFile(pathToJar);
			Enumeration<JarEntry> e = jarFile.entries();
			URL[] urls = { new URL("jar:file:" + pathToJar+"!/") };
			URLClassLoader cl = URLClassLoader.newInstance(urls);

			    while (e.hasMoreElements()) {
			        JarEntry je = (JarEntry) e.nextElement();
			        if(je.isDirectory() || !je.getName().endsWith(".class")){
			            continue;
			        }
			    // -6 because of .class
			        String className = je.getName().substring(0,je.getName().length()-6);
			        className = className.replace('/', '.');
			        Class<?> c = cl.loadClass(className);
			        if(AbstractGiftObject.class.isAssignableFrom(c)){
			        	// gift class
			    		classes[0]=c;  
			    		
			    	}
			        else if(AbstractSantaObject.class.isAssignableFrom(c)){
			        	// santa class
			        	classes[1]=c;
			    	}
			    	else {
			    		// constant class
			    		classes[2]=c;
			    		}
			    		
			        }
			   
			   
			   jarFile.close();

	
		return classes;
	}
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ClassLodaerInterface o = new ConcreteGameObjectsClassLoader();
		try {
			o.getLoadedClasses();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}