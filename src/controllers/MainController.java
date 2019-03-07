package controllers;

import utils.Controller;

/**
 * Represents the Main Controller.
 */
public class MainController extends Controller {

	/** Represents the Student Controller. */
	private StudentController studentController = new StudentController();
	
	/** Represents the Course Controller. */
	private CourseController courseController = new CourseController();
	
	
	/* (non-Javadoc)
	 * @see utils.Controller#run()
	 */
	public void run() throws Throwable{
		
		int choice = 0;

		String[] menu = {
				"Student Functions" ,
				"Course Functions",
				"Exit"
		};

		
		while(choice < menu.length) {

			gi.displayTitle("Welcome to the Student Course Registration and Mark Entry Application(SCRAME) ");


			gi.display(menu);
			
			choice = gi.inputInteger("choice", 1, menu.length);
			
			switch(choice) {
			
			case 1:
				studentController.run();
				break;
			case 2:
				courseController.run();
				break;
			
			case 3:
				System.out.println("Exiting Application...");
				return;

			}
		}
				
	}
	
}
