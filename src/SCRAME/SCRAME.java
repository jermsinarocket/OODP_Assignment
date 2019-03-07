package SCRAME;
import utils.Database;
import controllers.MainController;


/**
 * Represent the SCRAME Application.
 */
public class SCRAME {
	
	/**
	 * Represents the Main Method.
	 *
	 * @param args The Arguments
	 * @throws IOEXCEPTION if Input/Output has an error
	 * @throws ClassNotFoundException if Class cannot be found.
	 */
	public static void main(String args[]) throws Throwable  {
		
		final MainController mainController = new MainController();
		
		Database.initializeAllData();
		
	    mainController.run();
		
		System.exit(0);
	}
}
