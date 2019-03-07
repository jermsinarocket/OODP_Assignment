package controllers;

import java.util.ArrayList;

import models.Course;
import models.Lesson;
import models.Professor;
import models.Student;
import models.Weightage;
import models.Weightages;
import utils.Controller;
import views.CourseView;

/**
 * Represents the Course Controller.
 */
public class CourseController extends Controller {

	/** Represents the Course model. */
	private Course courseModel;
	
	/** Represents the Course View. */
	private CourseView view = new CourseView();
	
	/** Represents the Main Controller. */
	private MainController mainController;

	/* (non-Javadoc)
	 * @see utils.Controller#run()
	 */
	public void run() throws Throwable {

		mainController = new MainController();
		
		String[] menu = { 
				"Add a Course ",
				"Add Lessons to Course",
				"Check available slot in a class (vacancy in a class) ",
				"Enter course assessment components weightage ",
				"Print student list by lecture, tutorial or laboratory session for a course. ",
				"Print Course Statistics", 
				"Edit a Course",
				"Back" 
				};

		int choice = 0;

		while (choice <= menu.length) {
			
			gi.displayTitle("COURSE FUNCTIONS");
			gi.display(menu);

			choice = gi.inputInteger("choice", 1, menu.length);

			switch (choice) {

			case 1:
				String courseCode;
				String courseName;
				int examWeightage;
				Professor p;

				do {
					courseCode = gi.inputString("Course Code",
							Course.getMaxCourseCodeLength(), "Eg. CZ3001");

					if (Course.duplicateCourseCode(courseCode)) {
						gi.display("Course Code: " + courseCode
								+ " already exists!");
					}
				} while (Course.duplicateCourseCode(courseCode));

				courseName = gi.inputString("Course Name",
						"Eg. Advanced Computer Architecture");

				examWeightage = gi.inputInteger("Enter Exam Weightage(%)", 0,
						100);
				
				ArrayList<String> profMenu = new ArrayList<String>();
				for (Professor s : Professor.getProfessorsList()) {

					profMenu.add(s.getName() + "(" + s.getId() + ")");
				}

				gi.displayTitle("Select Course Coordinater");
			
				gi.display(profMenu);

				choice = gi.inputInteger("choice", 1, profMenu.size());

				p = Professor.getProfessor(choice-1);

				new Course(courseCode, courseName, examWeightage, p);

			
				gi.displayTitle("\nCOURSE HAS BEEN ADDED SUCCESSFULLY");
			
				gi.display("CURRENT COURSES");
				gi.display("------------------------------------");
				
				int courseCount = 1;
				for (Course c : Course.getCoursesList()) {

					gi.display("["+courseCount+"]");
					view.printCoureseAddedInfo(c.getCourseCode(),
							c.getCourseName(),
							c.getCoordinator().getName() + " (" + c.getCoordinator().getDesignation() + ")",
							c.getWeightages().getExamWeight());
					gi.display("----------------------------");
					courseCount++;
				}
				
				Course.updateFile();

				Thread.sleep(2000);
				mainController.run();

			case 2:
				
				//Add Lessons to Course
				if(!Course.getCoursesList().isEmpty()){
					
					gi.displayTitle("Select Course to Add Lessons for");
					
					
					gi.display(Course.getCourseMenu());

					choice = gi.inputInteger("choice", 1, Course.getCourseMenu().size());

					courseModel = Course.getCourse(choice-1);
					
					
					gi.displayTitle("ADDING LESSON FOR " + courseModel.getCourseCode() + ":" + courseModel.getCourseName());
					
					
	
						gi.display(Lesson.getLessonTypes());
						
						choice = gi.inputInteger("choice", 1, Lesson.getLessonTypes().length);
						
						String lessonName;
						int lessonCapacity;
						String lessonCode;
						
						lessonName = Lesson.getLessonTypes()[choice-1];
						
						//Only Considered Duplicate if same Lesson Code and same Lesson Type
						do {
							lessonCode = gi.inputString("Lesson Code",
									Lesson.getMaxLessonCodeLength(), "Eg. FEP3");

							if (courseModel.duplicateLessonCode(lessonCode) && courseModel.duplicateLessonType(lessonName)) {
								gi.display("Lesson Code: " + lessonCode
										+ " already exists for " + lessonName + "!");
							}
						} while (courseModel.duplicateLessonCode(lessonCode) && courseModel.duplicateLessonType(lessonName));
						
						
						
						lessonCapacity = gi.inputInteger("Enter Lesson Capacity", 1, Lesson.getMaxLessonCapacity());
						
						courseModel.getLessonsList().add(new Lesson(lessonCapacity,lessonName,lessonCode));
						
						gi.display("");
						
						view.printLessonAddedInfo(lessonName,lessonCode, courseModel.getCourseCode(), courseModel.getCourseName());
						
						Course.updateFile();
			
					
				}else{
					gi.display("");
					gi.display("PLEASE ADD A COURSE FIRST!");
				}
				
				Thread.sleep(2000);	
				mainController.run();
			
				
				
			case 3:
				
				// Check Course Vacancy
				if (!Course.getCoursesList().isEmpty()) {
					
				
					gi.displayTitle("Select Course to Check Vacancy for");
					
		
					gi.display(Course.getCourseMenu());

					choice = gi.inputInteger("choice", 1,Course.getCourseMenu().size());

					courseModel = Course.getCourse(choice-1);
					if (courseModel.getLessonsList().isEmpty()) {
						System.out
								.println("\nPlease Input Lessons for Course first!");
					}else{
						
						gi.display("\nVacancy for " + courseModel.getCourseCode() + ": " + courseModel.getCourseName());
						gi.display("-----------------------------------");
						for(Lesson l: courseModel.getLessonsList()){
							gi.display(l.getLessonType() + "(" + l.getLessonCode() + ") [Vacancy: " + l.getVacancy() + "/ Total Size: " + l.getlessonCapacity() +"]");
						}
						
					}
				} else {
					gi.display("PLEASE ADD A COURSE FIRST!");
				}

				Thread.sleep(2000);
				mainController.run();


			case 4:

				//Enter Coursework Components Weightage
				if (!Course.getCoursesList().isEmpty()) {

			
					gi.displayTitle("Select Course to Add Course Components For");
					

					gi.display(Course.getCourseMenu());

					choice = gi.inputInteger("choice", 1,Course.getCourseMenu().size());

					courseModel = Course.getCourse(choice-1);

					String compName;
					int compWeightage;

					double maxCompWeightage = courseModel.getWeightages()
							.getCourseworkWeight();

					gi.display("\nComponents Breakdown for Course - Total Allowed ("
							+ courseModel.getWeightages().getCourseworkWeight() + "%)");
					gi.display("------------------------------------");

					if (!courseModel.getWeightages().getCourseworkList().isEmpty()) {

						for (Weightage w : courseModel.getWeightages()
								.getCourseworkList()) {
							view.printWeightageName(w.getName());
							System.out.print(" - ");
							view.printWeightageWeight(w.getWeight());
							System.out.println("\n");
							maxCompWeightage -= w.getWeight();
						}
						
						gi.display("REMAINING - " + maxCompWeightage + "%");
					} else {
						gi.display("NONE");
					}
					gi.display("-----------------------------------");

					if (maxCompWeightage == 0) {
						gi.display("No more Components can be added!");
					} else {

						compName = gi.inputString("Component Name",
								"E.g. Assignment");

						compWeightage = gi.inputInteger(
								"Component Weightage(%)", 1, (int)maxCompWeightage);

						courseModel.getWeightages().getCourseworkList()
								.add(new Weightage(compName, compWeightage));

						ArrayList<String> components = new ArrayList<String>();
						ArrayList<Double> componentMarks = new ArrayList<Double>();
						
						for(Weightage w : courseModel.getWeightages().getCourseworkList()){
							components.add(w.getName());
							componentMarks.add(w.getWeight());
						}
						
					
						gi.displayTitle(courseModel.getCourseCode() + ": " + courseModel.getCourseName() + " has been updated!");
		
						
						view.printCurrentWeightage(courseModel.getCourseCode(), 
								courseModel.getCourseName(), 
								courseModel.getWeightages().getExamWeight(), 
								courseModel.getWeightages().getCourseworkWeight(), 
								components, 
								componentMarks);
						
					}

				} else {
					gi.display("\nNO COURSES FOUND! PLEASE ADD A COURSE FIRST!");
				}
				Course.updateFile();

				Thread.sleep(3000);
				mainController.run();

			case 5:
				
				
				gi.displayTitle("Select Course to Print Student List");
				
	
				gi.display(Course.getCourseMenu());

				choice = gi.inputInteger("choice", 1,Course.getCourseMenu().size());

				courseModel = Course.getCourse(choice-1);
				
				if(!courseModel.getEnrollment().isEmpty()){
					
					
					gi.displayTitle("Select Lesson to Print Student List for " + courseModel.getCourseCode() + ": " + courseModel.getCourseName() + ")");
				
					
						int lessonCount = 1;
						for(Lesson l : courseModel.getLessonsList()){
							
							gi.display("[" + lessonCount + "]" );
							view.printLessonsList(
									l.getLessonType(),l.getLessonCode(),l.getVacancy(),l.getlessonCapacity());
							lessonCount++;
						}
					
						
					choice = gi.inputInteger("choice", 1, courseModel.getLessonsList().size());
					Lesson l = courseModel.getLessonsList().get(choice-1);
					
					if(!l.getEnrolled().isEmpty()){
						
						
						gi.displayTitle("STUDENT LIST FOR " + courseModel.getCourseCode() + ":" + courseModel.getCourseName() +  " - " + l.getLessonType() + "(" + l.getLessonCode() + ")");
						
						
						int studentCount = 1;
						for(Student s : l.getEnrolled()){
							gi.display("["+studentCount+"]");
							gi.display(s.getName().toUpperCase() + " (" + s.getId() + ")");
							studentCount++;
						}
					
						
					}else{
						gi.display("");
						gi.display("NO STUDENTS HAVE BEEN ENROLLED TO THIS LESSON YET!");
					}
				
				
				}else{
					gi.display("");
					gi.display("NO STUDENTS HAVE BEEN ENROLLED TO THIS COURSE YET!");
				}
				
				Thread.sleep(2000);
				mainController.run();
				
			case 6:

				gi.displayTitle("Select Course to Print Statistics for");
				
	
				gi.display(Course.getCourseMenu());

				choice = gi.inputInteger("choice", 1,Course.getCourseMenu().size());

				courseModel = Course.getCourse(choice-1);
				Weightages w = courseModel.getWeightages();
				
				if(!courseModel.getEnrollment().isEmpty()){
					
					gi.displayTitle("COURSE STATISTICS FOR " + courseModel.getCourseCode()+ ": " + courseModel.getCourseName());
					
					double averageExamMarks = 0;
					Double[] courseWorkMarks;
					String[] courseWorkName;
					
					if(courseModel.getWeightages().getCourseworkList().isEmpty()){
						courseWorkMarks = new Double[1];
						courseWorkName = new String[1];
					}else{
						courseWorkMarks = new Double[courseModel.getWeightages().getCourseworkList().size()];
						courseWorkName = new String[courseModel.getWeightages().getCourseworkList().size()];
					}
					
					for(int i = 0 ; i < courseWorkMarks.length;i++){
						courseWorkMarks[i] = 0.0;
					}
					
	
					boolean checkExist = true;
					
					for(Student s: courseModel.getEnrollment()){
									
						if (!w.getExam().checkGradeExist(s.getId())) {
							checkExist = false;
							gi.display("Please enter EXAM MARKS for "
									+ s.getName().toUpperCase() + "( " + s.getId()
									+ ") First!");
						}else{
							averageExamMarks += w.getExam().getGrade(s.getId());
						}

						if (!w.getCourseworkList().isEmpty()) {
							
							int courseworkCount = 0;
							for (Weightage ww : w.getCourseworkList()) {
								if (!ww.checkGradeExist(s.getId())) {

									checkExist = false;
									gi.display("Please enter " + ww.getName().toUpperCase()
											+ " marks " + s.getName()
											+ "( " + s.getId() + ") first!");
								}else{
									courseWorkMarks[courseworkCount] += ww.getGrade(s.getId());
									courseWorkName[courseworkCount++] = ww.getName();
								}
							}
						}

						else {
							if (!w.getCoursework().checkGradeExist(s.getId())) {

								checkExist = false;
								gi.display("Please enter COURSEWORK MARKS for "
										+ s.getName().toUpperCase() + "( " + s.getId()
										+ ") first!");
							}else{
								courseWorkMarks[0] = w.getCoursework().getGrade(s.getId());
								courseWorkName[0] = "Course Work";
							}
						}
						
						if(!checkExist)
							gi.display("");
					}
						
						
					if(checkExist){
						view.printCourseStatistics(courseModel.getTotalEnrolledStudents(), averageExamMarks, courseWorkMarks, courseWorkName);
					
					}
					
					
				}else{
					gi.display("NO STUDENTS HAVE BEEN ENROLLED TO THIS COURSE YET!");
				}
				

			    Thread.sleep(4000);
				mainController.run();
				
				
			case 7:
				
				if (!Course.getCoursesList().isEmpty()) {
					gi.displayTitle("Select a Course to Edit");

					gi.display(Course.getCourseMenu());

					choice = gi.inputInteger("choice", 1, Course
							.getCourseMenu().size());

					courseModel = Course.getCourse(choice - 1);
					
					String[] editMenu = {
							"Edit Course Name ",
							"Edit Course Coordinater " ,
							"Edit Exam Weightage",
							"Edit Coursework" , 
							"Edit Lessons",
							"Remove this Course",
							"Back"
					};
					
					
					int editChoice = 0;
					
					while(editChoice <= editMenu.length) {
						
						gi.displayTitle("Editting: " + courseModel.getCourseCode() + ": " + courseModel.getCourseName());

						gi.display(editMenu);
						
						editChoice = gi.inputInteger("choice", 1, editMenu.length);
						
						switch(editChoice){
						case 1:
							courseModel.setCourseName(gi.inputString("New Course Name","Current Name: " + courseModel.getCourseName()));
							break;
							
						case 2:
							
							ArrayList<String> newProfMenu = new ArrayList<String>();
							for (Professor s : Professor.getProfessorsList()) {

								newProfMenu.add(s.getName() + "(" + s.getId() + ")");
							}
							
							gi.displayTitle("Select New Course Coordinater. Current Course Coordinator: " + courseModel.getCoordinator().getName());
							
							gi.display(newProfMenu);

							choice = gi.inputInteger("choice", 1, newProfMenu.size());

							courseModel.setCoordinator(Professor.getProfessor(choice-1));
							
							break;
							
						case 3:
							
							examWeightage = gi.inputInteger("Current Weightage: " + courseModel.getWeightages().getExamWeight() + "% Enter New Exam Weightage(%)", 0,
									100);
							
							Weightage exams = courseModel.getWeightages().getExam();
							Weightage courseWork = courseModel.getWeightages().getCoursework();
						
							int newCourseWorkWeightage = 100 - examWeightage;
							
							double newCourseWorkWeightagePercent = newCourseWorkWeightage/courseWork.getWeight();
							
							exams.recalculateGrade(exams.getWeight(), examWeightage);
							exams.setWeight(examWeightage);
							
							courseWork.recalculateGrade(courseWork.getWeight(), newCourseWorkWeightage);
							courseWork.setWeight(newCourseWorkWeightage);
							
						
							
							if(!courseModel.getWeightages().getCourseworkList().isEmpty()){
								for(Weightage ww : courseModel.getWeightages().getCourseworkList()){
									
									double newComponentWeightage = newCourseWorkWeightagePercent * ww.getWeight();
									ww.recalculateGrade(ww.getWeight(), newComponentWeightage);
									ww.setWeight(newComponentWeightage);
								}
							}
						
							gi.display("ALL WEIGHTAGES AND GRADES HAVE BEEN RECALCULATED");
							
							break;
							
						case 4:
							
							if(courseModel.getWeightages().getCourseworkList().isEmpty()){

								int courseWorkWeightage = gi.inputInteger("Current Weightage: " + courseModel.getWeightages().getCourseworkWeight() + "% Enter New Coursework Weightage(%)", 0,
										100);

								 exams = courseModel.getWeightages().getExam();
								 courseWork = courseModel.getWeightages().getCoursework();
								 
								 int newExamWeightage = 100 - courseWorkWeightage;
								 
								    courseWork.recalculateGrade(courseWork.getWeight(), courseWorkWeightage);
									courseWork.setWeight(courseWorkWeightage);
									
									exams.recalculateGrade(exams.getWeight(), newExamWeightage);
									exams.setWeight(newExamWeightage);
									
							
								
							}else{
								int componentCount = 1;
								
								gi.displayTitle("Select Coursework Component to Edit");
								for(Weightage ww :courseModel.getWeightages().getCourseworkList()){
									gi.display("[" + componentCount++ + "] " + ww.getName() + " (" + ww.getWeight() + "%)");
								}
								
								int componentEditChoice = 0; 
								
								String[] componentEditMenu = {
										"Edit Component Name",
										"Edit Component Weightage",
										"Delete this Component",
										"Back"
								};
								
								Weightage component = courseModel.getWeightages().getCourseworkList().get((gi.inputInteger("Select Coursework Component", 1, courseModel.getWeightages().getCourseworkList().size()))-1);
								
								while(componentEditChoice <= componentEditMenu.length){
									
									gi.displayTitle("Editting: " + component.getName() + " (" + component.getWeight() + "%)");
									
									gi.display(componentEditMenu);
									
									
									componentEditChoice = gi.inputInteger("Select Coursework Component", 1, componentEditMenu.length);
								
									switch(componentEditChoice){
									
									case 1:
										component.setName(gi.inputString("New Component Name", "Current Name: " + component.getName()));
										break;
										
									case 2:
										courseWork = courseModel.getWeightages().getCoursework();
										
										double componentWeightage = gi.inputInteger("Current Weightage: " + component.getWeight() + "% Enter New Component Weightage(%)", 0,
												(int) Math.round(courseWork.getWeight()));
										
										 exams = courseModel.getWeightages().getExam();
										 
										 
										 double diffWeightage = componentWeightage - component.getWeight();
										 
										 component.recalculateGrade(component.getWeight(), componentWeightage);
										 component.setWeight(componentWeightage);
										 
										 courseWork.recalculateGrade(courseWork.getWeight(), courseWork.getWeight()+ diffWeightage);
										 courseWork.setWeight(courseWork.getWeight()+ diffWeightage);
										 
										 exams.recalculateGrade(exams.getWeight(), 100-courseWork.getWeight());
										 exams.setWeight(100-courseWork.getWeight());
											
					
										 gi.display("ALL WEIGHTAGES AND GRADES HAVE BEEN RECALCULATED");
										 
										break;
										
									case 3:
										
										 
										courseModel.getWeightages().removeComponent(component);
										gi.displayTitle("Component has been sucessfully removed!");
										gi.display("ALL WEIGHTAGES AND GRADES HAVE BEEN RECALCULATED");
										
										
										Course.updateFile();
										Thread.sleep(3000);
										mainController.run();
										
										break;
									case 4:
										
										this.run();
									}
									
									
									ArrayList<String> components = new ArrayList<String>();
									ArrayList<Double> componentMarks = new ArrayList<Double>();
									
									for(Weightage ww : courseModel.getWeightages().getCourseworkList()){
										components.add(ww.getName());
										componentMarks.add(ww.getWeight());
									}
									

									gi.displayTitle(courseModel.getCourseCode() + ": " + courseModel.getCourseName().toUpperCase() +  " has been succesfully updated");
									view.printCurrentWeightage(
											courseModel.getCourseCode(), 
											courseModel.getCourseName(), 
											courseModel.getWeightages().getExamWeight(), 
											courseModel.getWeightages().getCourseworkWeight(), 
											components, 
											componentMarks);
									gi.display("Course Coordinator: " + courseModel.getCoordinator().getName());
									
									Course.updateFile();
									Thread.sleep(3000);
									mainController.run();
								}
							}
							break;
							
						case 5:
							
							if(!courseModel.getLessonsList().isEmpty()){
								
								gi.displayTitle("Select Lesson to Edit");
								int lessonCount = 1;
								for(Lesson l: courseModel.getLessonsList()){
									gi.display("[" + lessonCount++ + "] " + l.getLessonType() + "(" + l.getLessonCode() + ") [Vacancy: " + l.getVacancy() + "/ Total Size: " + l.getlessonCapacity() +"]");
								}
								
								Lesson l = courseModel.getLessonsList().get((gi.inputInteger("Select Lesson to Edit", 1, courseModel.getLessonsList().size()))-1);
				
								String[] lessonEditMenu = {
										"Edit Lesson Type",
										"Edit Lesson Capacity",
										"Remove this Lesson",
										"Back"
								};
								
								int lessonEditChoice = 0;
								
								while(lessonEditChoice <= lessonEditMenu.length) {
									
									gi.displayTitle("Editting: " + l.getLessonType() + " (" + l.getLessonCode() + ")");
									
									gi.display(lessonEditMenu);
									
									lessonEditChoice = gi.inputInteger("choice", 1, lessonEditMenu.length);
									
									switch(lessonEditChoice){
									
									case 1:
										
										gi.displayTitle("Select New Lesson Type");
										gi.display(Lesson.getLessonTypes());
										
										choice = gi.inputInteger("New Lesson Type", 1, Lesson.getLessonTypes().length);
										
										l.setLessonType(choice-1);
										break;
										
									case 2:
										
										int newCapacity =  gi.inputInteger("New Lesson Capactiy", 1, Lesson.getMaxLessonCapacity());
										
										while(newCapacity < l.getEnrolled().size()){
											gi.display("CAPACITY CANNOT BE LESS THAN NUMBER OF STUDENTS ENROLLED (" + l.getEnrolled().size() + ")" );
											
											newCapacity =  gi.inputInteger("New Lesson Capactiy", 1, Lesson.getMaxLessonCapacity());
										}
										
										l.setLessonCapacity(newCapacity);
										break;
										
									case 3:
										
										if(l.getEnrolled().isEmpty()){
											
											courseModel.removeLesson(l);
											gi.displayTitle("LESSON HAS BEEN SUCCESSFULLY REMOVED");
											
											Course.updateFile();
											Thread.sleep(3000);
											mainController.run();
											
										}else{
											gi.display("PLEASE UNENROLL ALL STUDENTS BEFORE REMOVING THIS LESSON");
											Thread.sleep(3000);
											mainController.run();
										}
										break;
									case 4:
										
										this.run();
										
									}
									gi.displayTitle(l.getLessonType() + " (" + l.getLessonCode() + ") has been updated for " + courseModel.getCourseCode() + ": " + courseModel.getCourseName().toUpperCase());
									
									view.printLessonUpdatedInfo(
											l.getLessonType(), 
											l.getLessonCode(), 
											l.getVacancy(), 
											l.getlessonCapacity());
									
									Course.updateFile();
									Thread.sleep(3000);
									mainController.run();
						
								}
							
							}else{
								gi.display("PLEASE ADD A LESSON FIRST");
								Thread.sleep(3000);
								mainController.run();
							}
							
						case 6:
							
							boolean canRemove = true;
				
							for(Lesson l : courseModel.getLessonsList()){
									if(!l.getEnrolled().isEmpty()){
										gi.display("Please Unenroll all Students from Lesson: " + l.getLessonType() + " (" + l.getLessonCode() + ") first");
										canRemove = false;
									}
						
							}
							
							
							if(canRemove){
								Course.removeCourse(courseModel);
								
								Course.updateFile();
								gi.displayTitle("Course has been successfully removed");
								Thread.sleep(3000);
								mainController.run();
							}else{
								
								Thread.sleep(3000);
								mainController.run();
							}
							
							
						case 7:
							this.run();
							
							
						}
						
						ArrayList<String> components = new ArrayList<String>();
						ArrayList<Double> componentMarks = new ArrayList<Double>();
						
						for(Weightage ww : courseModel.getWeightages().getCourseworkList()){
							components.add(ww.getName());
							componentMarks.add(ww.getWeight());
						}
						

						gi.displayTitle(courseModel.getCourseCode() + ": " + courseModel.getCourseName().toUpperCase() +  " has been succesfully updated");
						view.printCurrentWeightage(
								courseModel.getCourseCode(), 
								courseModel.getCourseName(), 
								courseModel.getWeightages().getExamWeight(), 
								courseModel.getWeightages().getCourseworkWeight(), 
								components, 
								componentMarks);
						gi.display("Course Coordinator: " + courseModel.getCoordinator().getName());
						
						Course.updateFile();
						Thread.sleep(3000);
						mainController.run();
					
					}
					
		
					
				} else {
					gi.display("PLEASE CREATE A COURSE FIRST!");
				}
				
				
				mainController.run();
				
			case 8:
				
				mainController.run();
			}
		}

	}
}
