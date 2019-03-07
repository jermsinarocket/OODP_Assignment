package utils;

import views.GeneralInterface;

// TODO: Auto-generated Javadoc
/**
 * Represents the Controller.
 */
public abstract class Controller{
	
	/** Represents the General Interface. */
	protected GeneralInterface gi;
	
	/**
	 * Instantiates a new Controller.
	 */
	public Controller() {
		gi = new GeneralInterface();
	}
	
	/**
	 * Runs the Controller.
	 *
	 * @throws IOEXCEPTION if Input/Output has an error
	 * @throws ClassNotFoundException if Class cannot be found.
	 */
	public abstract void run() throws Throwable;

}
