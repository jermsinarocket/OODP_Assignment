package controllers;
import java.text.DecimalFormat;

import models.Course;
import models.Lesson;
import models.Student;
import models.Weightage;
import models.Weightages;
import utils.Controller;
import views.StudentView;

/**
 * Represents the Student Controller
 */
public class StudentController extends Controller {
	
	
	/** Represents the Student Model. */
	private Student studentModel;
	
	/** Represents the Student View. */
	private StudentView view = new StudentView();
	
	/** Represents the Main Controller. */
	private MainController mainController;

	/* (non-Javadoc)
	 * @see utils.Controller#run()
	 */
	public void run() throws Throwable{
		
		mainController = new MainController();

		String[] menu = {
				"Add a Student ",
				"Register student for a course (this include registering for Tutorial/Lab classes) " ,
				"Enter coursework mark – inclusive of its components. ",
				"Enter exam mark",
				"Print student transcript. ",
				"Edit a Student ",
				"Back"
		};
		
		int choice = 0;
		
		while(choice <= menu.length) {
			
			gi.displayTitle("STUDENT FUNCTIONS");
			gi.display(menu);
			
			choice = gi.inputInteger("choice", 1, menu.length);
			
			switch(choice) {
			case 1:
				
				String studentId;
				String studentName;
				String studentEmail;
				String studentContactNo;
				
				do{
					studentId = gi.inputString("Students ID", Student.getMaxStudentIdLength(), "Eg. U123456J");
					
					if(Student.duplicateStudentId(studentId)){
						gi.display("Student ID: " + studentId + " already exists!" );						
					}		
				}while(Student.duplicateStudentId(studentId));
				
				studentName = gi.inputString("Student Name", "Eg. Ong Kin Lai");
				
				studentEmail = gi.inputString("Student Email", "Eg. student@e.ntu.edu.sg");
				
				studentContactNo = gi.inputString("Student Contact Number", "98765432");
				
				new Student(studentId,studentName,studentEmail,studentContactNo);
				
			
				gi.displayTitle("\nSTUDENT RECORD HAS BEEN ADDED SUCCESSFULLY");
				gi.display("CURRENT STUDENTS");
				gi.display("------------------------------------");
		
				
				int studentCount = 1;
				for (Student s : Student.getStudentsList()) {
					gi.display("["+studentCount+"]");
					view.printNewStudentRecordInfo(
							s.getId(),
							s.getName(),
							s.getEmailAddress(),
							s.getContactNo()
							);
					gi.display("----------------------------");
					studentCount++;
				}
				
				Thread.sleep(2000);
				mainController.run(); 
				
			case 2:
				
				
				gi.displayTitle("Select Student to Register for Course");
				

				gi.display(Student.getStudentMenu());

				choice = gi.inputInteger("choice", 1, Student.getStudentMenu().size());

				studentModel = Student.getStudentsList().get(choice-1);
				
				if(!Course.getCoursesList().isEmpty()){
				
					gi.displayTitle("Select Course to Register for " + studentModel.getName() + "(" + studentModel.getId() + ")");
					
	
					gi.display(Course.getCourseMenu());
	
					choice = gi.inputInteger("choice", 1, Course.getCourseMenu().size());
	
					Course c = Course.getCourse(choice-1);
					
					if(!c.getLessonsList().isEmpty()){
					
						if(!c.checkEnrollment(studentModel)){
							c.enrollStudent(studentModel);
						}
						
						gi.displayTitle("Select Lesson to Register for " + studentModel.getName() + "(" + studentModel.getId() + ")");
						
						
						int lessonCount = 1;
						for(Lesson l : c.getLessonsList()){
							
								gi.display("[" + lessonCount + "]" );
								gi.display(
										l.getLessonType() + "(" + l.getLessonCode() + ") [Vacancy: " + l.getVacancy() + "/ Total Size: " + l.getlessonCapacity() +"]");
								lessonCount++;
						}
						choice = gi.inputInteger("choice", 1, c.getLessonsList().size());
						Lesson l = c.getLessonsList().get(choice-1);
						
						gi.display("");
						if(!l.checkEnrolled(studentModel)){
							
							if(l.getVacancy() != 0){
							
								l.enrollStudent(studentModel);
							
								view.printEnrollment(
										studentModel.getId(), 
										studentModel.getName(), 
										c.getCourseCode(), 
										c.getCourseName(), 
										l.getLessonType(), 
										l.getLessonCode()
										);
							
							}else{
							
								gi.display("THERE IS NO VACANCY LEFT FOR THIS LESSON");
							}
							
						}else{
							gi.display("STUDENT HAS ALREADY ENROLLED FOR THIS CLASS!");
						}
						
					}else{
						gi.display("");
						gi.display("NO LESSONS FOUND! PLEASE ADD A LESSON FOR THIS COURSE FIRST!");
					}
				}else{
					gi.display("NO COURSES FOUND! PLEASE ADD A COURSE FIRST!");
				}
				
				Course.updateFile();
				
				Thread.sleep(2000);
				mainController.run();
				
			case 3:
				

				gi.displayTitle("Select Student to Enter Coursework Marks For");

				gi.display(Student.getStudentMenu());

				choice = gi.inputInteger("choice", 1, Student.getStudentMenu().size());

				studentModel = Student.getStudentsList().get(choice-1);
						
				gi.displayTitle("Select Course to Enter CourseWork Marks for " + studentModel.getName() + "(" + studentModel.getId() + ")");
				

				gi.display(Course.getCourseMenu());

				choice = gi.inputInteger("choice", 1, Course.getCourseMenu().size());

				Course c = Course.getCourse(choice-1);
				
				if(c.checkEnrollment(studentModel)){
					
					if(c.getWeightages().getCourseworkList().isEmpty()){
						
						double grade = gi.inputInteger("Enter Coursework Marks", 0, 100);
						c.getWeightages().setCourseworkGrade(studentModel.getId(),grade);
						
						view.printMarks(c.getWeightages().getCoursework().getWeight(), grade, c.getWeightages().getCoursework().getName(),c.getWeightages().getCoursework().getGrade(studentModel.getId()));
					}else{
						
						gi.displayTitle("Select Coursework Componenent to Enter Marks for " + studentModel.getName() + "(" + studentModel.getId() + ")");
						int courseworkCount = 1;
						for(Weightage w : c.getWeightages().getCourseworkList()){
							gi.display("["+courseworkCount+"]");
							gi.display(w.getName() + "(" + w.getWeight() + "%)");
							courseworkCount++;
						}
						
						choice = gi.inputInteger("choice", 1, c.getWeightages().getCourseworkList().size());
						
						double grade = gi.inputInteger("Enter Component Marks", 0, 100);
						
						c.getWeightages().getCourseworkList().get(choice-1).setGrade(studentModel.getId(),grade);
						
						view.printMarks(
								c.getWeightages().getCourseworkList().get(choice-1).getWeight(), 
								grade, 
								c.getWeightages().getCourseworkList().get(choice-1).getName(),
								c.getWeightages().getCourseworkList().get(choice-1).getGrade(studentModel.getId()));
					}
				}else{

					gi.display("");
					gi.display("STUDENT IS NOT ENROLLED IN THIS COURSE");
					
				}
				
				Course.updateFile();
				
				Thread.sleep(2000);
				mainController.run();
				
			case 4:
				
				gi.displayTitle("Select Student to Enter Exam Marks For");

				gi.display(Student.getStudentMenu());

				choice = gi.inputInteger("choice", 1, Student.getStudentMenu().size());

				studentModel= Student.getStudent(choice-1);
				
				gi.displayTitle("Select Course to Enter Exam Marks for " + studentModel.getName() + "(" + studentModel.getId() + ")");

				gi.display(Course.getCourseMenu());

				choice = gi.inputInteger("choice", 1, Course.getCourseMenu().size());
				
				c = Course.getCoursesList().get(choice-1);
				
				if(c.checkEnrollment(studentModel)){
				  double grade = gi.inputInteger("Enter Exam Marks", 0, 100);
					
					c.getWeightages().setExamGrade(studentModel.getId(),grade);
					
					view.printMarks(c.getWeightages().getExam().getWeight(), grade, c.getWeightages().getExam().getName(),c.getWeightages().getExam().getGrade(studentModel.getId()));
				}else{
					
					gi.display("");
					gi.display("STUDENT IS NOT ENROLLED IN THIS COURSE");
					
				}
				
				Course.updateFile();
				Thread.sleep(2000);
				mainController.run();
			
			case 5:
				
				gi.displayTitle("Select Student to Print Transcript For");

				gi.display(Student.getStudentMenu());

				choice = gi.inputInteger("choice", 1, Student.getStudentMenu().size());

				studentModel = Student.getStudent(choice-1);
				
				gi.displayTitle("Transcript for " + studentModel.getName() + "(" + studentModel.getId() + ")");
				
				
				int courseCount = 1;
				
				boolean enrolled = false;
				
				for (Course cc : Course.getCoursesList()) {	

					if (cc.checkEnrollment(studentModel)) {
						
						enrolled = true;
						Weightages w = cc.getWeightages();
						gi.display("[" + courseCount + "]");
						gi.display("Course Code: " + cc.getCourseCode());
						gi.display("Course Name: " + cc.getCourseName());

						gi.display("");

						double totalGrade = 0;
						boolean checkExist = true;
						
						if (!w.getExam().checkGradeExist(studentModel.getId())) {

							gi.display("PLEAE ENTER EXAM MARKS FOR "
									+ cc.getCourseCode() + ": "
									+ cc.getCourseName() + " FIRST");
							checkExist = false;
						}

						if (!w.getCourseworkList().isEmpty()) {

							for (Weightage ww : w.getCourseworkList()) {
								if (!ww.checkGradeExist(studentModel.getId())) {

									checkExist = false;
									gi.display("PLEASE ENTER " + ww.getName()
											+ " MARKS FOR " + cc.getCourseCode()
											+ ": " + cc.getCourseName()
											+ " FIRST");
								}
							}

						} else {
							if (!w.getCoursework().checkGradeExist(studentModel.getId())) {
							
								checkExist = false;
								gi.display("PLEASE ENTER COURSEWORK MARKS FOR "
										+ cc.getCourseCode() + ": "
										+ cc.getCourseName() + " FIRST");
							}
						}

						if (checkExist) {

							Weightage exam = w.getExam();	
							double grade;
							grade = exam.getGrade(studentModel.getId());
							
							view.printTranscript(exam.getName(), exam.getWeight(),grade);
					
							
							totalGrade += grade;

							gi.display("Cousework Breakdown: ");

							if (!w.getCourseworkList().isEmpty()) {
								
								for (Weightage ww : w.getCourseworkList()) {
									
										grade = ww.getGrade(studentModel.getId());

										view.printTranscript(ww.getName(),
												ww.getWeight(),
												grade);
									 
									totalGrade += grade;

								}
							
							}else{
								
								Weightage coursework = w.getCoursework();
								
								grade = coursework
										.getGrade(studentModel.getId());
								
								view.printTranscript(coursework
										.getName(), coursework
										.getWeight(),grade);

								totalGrade += grade;
							}
							
						
							
							DecimalFormat df = new DecimalFormat("#.00");
							
							gi.display("TOTAL GRADE: " + df.format(totalGrade)
									+ "/100");
						}

					}

					courseCount++;
					gi.display("");
						
					}
				
				if(!enrolled){
					gi.display("STUDENT IS NOT ENROLLED IN ANY COURSE");
				}
				
				
				Thread.sleep(2000);
				mainController.run();
				
			case 6:
				
				gi.displayTitle("Select a Student to Edit");

				gi.display(Student.getStudentMenu());

				choice = gi.inputInteger("choice", 1, Student.getStudentMenu().size());

				studentModel= Student.getStudent(choice-1);
				
			
				
				String[] editMenu = {
						"Change Name ",
						"Change Email Address " ,
						"Change Contact No ",
						"Unenroll this Student",
						"Delete this Student" , 
						"Back"
				};
				
				
				int editChoice = 0;
				
				while(editChoice <= editMenu.length) {
					
					gi.display("-----------------------------------------------------------------------");
					gi.display("Editting: " + studentModel.getName() + "(" + studentModel.getId() + ")");
					gi.display("-----------------------------------------------------------------------");
					gi.display(editMenu);
					
					editChoice = gi.inputInteger("choice", 1, editMenu.length);
					
					switch(editChoice){
							
						case 1:
							
							studentModel.setStudentName(gi.inputString("New Student Name","Current Name: " + studentModel.getName()));
							
							break;
						case 2:
							
							studentModel.setEmailAddress(gi.inputString("New Student Email Address", "Current Email Address: "+  studentModel.getEmailAddress()));
							break;
						
						case 3:
							
							studentModel.setStudentContactNo(gi.inputString("New Student Contact Number", 8, "Current Contact No: " + studentModel.getContactNo()));
							break;
						
						case 4:						
							
						if (!Course.getCoursesList().isEmpty()) {
							
							gi.displayTitle("Select Course to Unenroll  Student From");
							
							gi.display(Course.getCourseMenu());

							choice = gi.inputInteger("choice", 1, Course.getCourseMenu().size());

							c = Course.getCourse(choice-1);

							if (c.checkEnrollment(studentModel)) {
								
								gi.displayTitle("Select Lesson to Unenroll Student from");
								
								int lessonCount = 1;
								for(Lesson l : c.getLessonsList()){
									
										gi.display("[" + lessonCount + "]" );
										gi.display(
												l.getLessonType() + "(" + l.getLessonCode() + ") [Vacancy: " + l.getVacancy() + "/ Total Size: " + l.getlessonCapacity() +"]");
										lessonCount++;
								}
								
								choice = gi.inputInteger("choice", 1, c.getLessonsList().size());
								Lesson l = c.getLessonsList().get(choice-1);
								
								boolean checkIfStillEnrolled = false;
								
								if(l.checkEnrolled(studentModel)){
									
									l.unenrollStudent(studentModel);{
										gi.display(studentModel.getName() + " (" + studentModel.getId() + ") has been unenrolled succesfully"); 
									}
									
									for(Lesson ll : c.getLessonsList()){
										if(ll.checkEnrolled(studentModel)){
											checkIfStillEnrolled = true;
										}
									}
									
									if(!checkIfStillEnrolled){
										c.unenrollStudent(studentModel);
										
									}
									
								}else{
									gi.display("STUDENT IS NOT ENROLLED IN THIS LESSON");
								}
								
							}else{
								gi.display("STUDENT IS NOT ENROLLED IN THIS COURSE");
							}
						}else{
							gi.display("PLEASE CREATE A COURSE FIRST");
						}
						
						Course.updateFile();
						Thread.sleep(3000);
						mainController.run();
						break;
							
						case 5:
							
				
							for(Course cc : Course.getCoursesList()){
								
								cc.unenrollStudent(studentModel);
								
								for(Lesson ll : cc.getLessonsList()){
									ll.unenrollStudent(studentModel);
								}
							}
							
							Student.removeStudent(studentModel);
							
							gi.displayTitle("ALL RECORDS OF STUDENT HAS BEEN REMOVED FROM THE SYSTEM");
							
							Student.updateFile();
							Course.updateFile();
							Thread.sleep(3000);
							mainController.run();
							
							break;
						
						case 6:
							this.run();
							break;
						
					}
					
						gi.displayTitle("Student Information has been succesfully updated");
						view.printNewStudentRecordInfo(studentModel.getId(), studentModel.getName(), studentModel.getEmailAddress(), studentModel.getContactNo());
						

						for(Course cc : Course.getCoursesList()){
							
							cc.updateEnrollment(studentModel);
							
							for(Lesson ll : cc.getLessonsList()){
								ll.updateEnrollment(studentModel);
							}
						}
						
						Student.updateFile();
						Thread.sleep(3000);
						mainController.run();
				}
	
				
			case 7:
			
				mainController.run();
				
				default:
				mainController.run();
			}
		}
				
	}

}
