package models;


import java.io.Serializable;
import java.util.ArrayList;

import utils.Database;

/**
 * Represents a Student.
 */
public class Student implements Person,Serializable{

	/** Represents the serialVersionUID. */
	private static final long serialVersionUID = -2383957615948367596L;
	
	/** Represents the MAXSTUDENTIDLENGTH. */
	private static final int MAXSTUDENTIDLENGTH = 9;
	
	/** Represents the CLASSNAME. */
	private static final String CLASSNAME = "STUDENT";
	
	/** Represents theFILEPATH. */
	private static final String FILEPATH = "src/data/";
	
	/** Represents the FILENAME. */
	private static final String FILENAME = "students.ser";

	/** Represents the Students list. */
	private static ArrayList<Student> studentsList = new ArrayList<Student>();


	/** Represents the Database */
	private static Database d = new Database();
	
	/** Represents the Student's ID. */
	private String studentId;
	
	/** Represents the Student's Name. */
	private String studentName;
	
	/** Represents the Student's Email Address. */
	private String studentEmail;
	
	/** Represents the Student's Contact Number. */
	private String studentContactNo;

	/**
	 * Instantiates a New Student.
	 *
	 * @param studentId A String containing the Student's ID.
	 * @param studentName A String containing the Student's Name.
	 * @param studentEmail A String containing the Student's Email Address.
	 * @param studentContactNo A String containing the Student's Contact Number.
	 */
	public Student(String studentId,String studentName, String studentEmail, String studentContactNo) {
		this.studentId = studentId.toUpperCase();
		this.studentName = studentName;
		this.studentEmail = studentEmail;
		this.studentContactNo = studentContactNo;
		addStudent(this);
	}
	
	/**
	 * Sets the Student's Name.
	 *
	 * @param studentName A String containing the new Student's Name.
	 */
	public void setStudentName(String studentName){
		this.studentName = studentName;
	}
	
	/* (non-Javadoc)
	 * @see models.Person#getName()
	 */
	public String getName(){
		return this.studentName;
	}
	
	/* (non-Javadoc)
	 * @see models.Person#getEmailAddress()
	 */
	public String getEmailAddress(){
		return this.studentEmail;
	}
	
	/**
	 * Sets the Student's Email Address.
	 *
	 * @param studentEmail A String containing the new Student's Email Address.
	 */
	public void setEmailAddress(String studentEmail){
		this.studentEmail =  studentEmail;
	}
	
	/* (non-Javadoc)
	 * @see models.Person#getContactNo()
	 */
	public String getContactNo(){
		return this.studentContactNo;
	}
	
	/**
	 * Sets the Student's Contact Number.
	 *
	 * @param studentContactNo A String containing the new Student's Contact Number.
	 */
	public void setStudentContactNo(String studentContactNo){
		this.studentContactNo = studentContactNo;
	}
	
	/**
	 * Adds the Student to the Students' List.
	 *
	 * @param s An Object containing the Student to be added to the Students' List.
	 */
	public void addStudent(Student s) {
		studentsList.add(s);
		updateFile();
	}
	

	/* (non-Javadoc)
	 * @see models.Person#getId()
	 */
	public String getId() {
		return this.studentId;
	}
	
	/**
	 * Checks for the duplication of Student
	 *
	 * @param studentId A String containing the ID of the Student to be checked
	 * @return true, if the Student already exists.
	 */
	public static boolean duplicateStudentId(String studentId) {

		for (Student s : studentsList) {

			if (s.getId().equalsIgnoreCase(studentId)) {
				return true;
			}
		}

		return false;
	}



	/**
	 * Gets the maximum Student Id Length.
	 *
	 * @return An Integer representing the maximum Student Id Length.
	 */
	public static int getMaxStudentIdLength() {
		return MAXSTUDENTIDLENGTH;
	}
	
	/**
	 * Gets the total number of Students.
	 *
	 * @return An Integer representing the total number of Students.
	 */
	//Get Total Number of Students
	public static int getTotalNumberOfStudents(){
		return studentsList.size();
	}
	
	/**
	 * Gets the Students' List.
	 *
	 * @return An ArrayList representing the Students' list.
	 */
	public static ArrayList<Student> getStudentsList(){
		return studentsList;
	}
	
	
	/**
	 * Gets the Student.
	 *
	 * @param i An Integer containing the index of the Student.
	 * @return An Object representing the Student being retrieved.
	 */
	public static Student getStudent(int i){
		return studentsList.get(i);
	}
	
	/**
	 * Removes the Student.
	 *
	 * @param s An Object containing the Student to be removed
	 */
	public static void removeStudent(Student s){
		studentsList.remove(s);
	}
	
	/**
	 * Gets the Student Menu to be displayed.
	 *
	 * @return An Arraylist representing all the Students for display purposes.
	 */
	public static ArrayList<String> getStudentMenu(){
		
		ArrayList<String> studentMenu = new ArrayList<String>();
		
		for (Student s : studentsList) {

			studentMenu.add(s.getName() + " ("
					+ s.getId() + ")");
		}
		
		return studentMenu;
	}
	
	/**
	 * Updates the Students' list.
	 *
	 * @param s An ArrayList containing the new Students' List to be updated.
	 */
	public static void updateStudentsList(ArrayList<Student> s){
		studentsList = s;
	}

	/**
	 * Initialize the Student's file.
	 *
	  * @throws IOEXCEPTION if Input/Output has an error
	 * @throws ClassNotFoundException if Class cannot be found.
	 */
	public static void initializeFile() throws Throwable {

		d.initializeFile(CLASSNAME, FILENAME, FILEPATH);

	}

	/**
	 * Updates the Student's file.
	 */
	public static void updateFile() {


		d.updateFile(studentsList,FILENAME,FILEPATH);
	}

}
