package models;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import utils.Database;

/**
 * Represents a Course.
 */
public class Course implements Serializable {

	/** Represents the serialVersionUID. */
	private static final long serialVersionUID = -1560901521487830899L;
	
	/** Represents the  MAXCOURSECODELENGTH. */
	private static final int MAXCOURSECODELENGTH = 6;
	
	/** Represents the  CLASSNAME. */
	private static final String CLASSNAME = "COURSE";
	
	/** Represents the  FILEPATH. */
	private static final String FILEPATH = "src/data/";
	
	/** Represents the  FILENAME. */
	private static final String FILENAME = "courses.ser";
	
	/** Represents the  The Database */
	private static Database d = new Database();

	/** Represents all the Courses */
	// Store All Courses
	private static ArrayList<Course> coursesList = new ArrayList<Course>();


	/** Represents the Course's Name. */
	// Variables for Specific Course
	private String courseName;
	
	/** Represents the Course's Code. */
	private String courseCode;
	
	/** Represents the Course's Coordinator. */
	private Professor coordinator;
	
	/** Represents the Students Enrolled in the Course. */
	private ArrayList<Student> enrollment;
	
	/** Represents the Lessons within the Course. */
	private ArrayList<Lesson> lessonsList;
	
	/** Represents the Gradeable Components of the Course */
	private Weightages weightages;

	/**
	 * Instantiates a new Course.
	 *
	 * @param courseCode A String Containing the Course's Code.
	 * @param courseName A String Containing the Course's Name.
	 * @param examWeightage An Integer Containing the Course's Exam Weightage.
	 * @param coordinator An object Containing the Course's Coordinator.
	 */
	public Course(String courseCode, String courseName, int examWeightage,
			Professor coordinator) {
		this.courseCode = courseCode.toUpperCase();
		this.courseName = courseName;
		this.coordinator = coordinator;
		this.enrollment = new ArrayList<Student>();
		this.weightages = new Weightages(examWeightage);
		this.lessonsList = new ArrayList<Lesson>();
		addCourse(this);
	}
		

	/**
	 * Adds the Course to the Courses List that Stores all Courses.
	 *
	 * @param The object containing the Course to be added.
	 */
	public void addCourse(Course c) {
		coursesList.add(c);
		updateFile();
	}

	/**
	 * Gets the Course's Coordinator.
	 *
	 * @return An object representing the Course's Coordinator
	 */
	public Professor getCoordinator() {
		return this.coordinator;
	}
	
	/**
	 * Sets the Course's Coordinator.
	 *
	 * @param p An Object containing the new Course's Coordinator.
	 */
	public void setCoordinator(Professor p){
		this.coordinator = p; 	
	}

	/**
	 * Gets the Lessons list.
	 *
	 * @return An ArrayList representing all the lessons within a course.
	 */
	public ArrayList<Lesson> getLessonsList() {
		return this.lessonsList;
	}

	/**
	 * Gets the Weightages.
	 *
	 * @return An object representing all the Weightages of the Course.
	 */
	public Weightages getWeightages() {
		return this.weightages;
	}

	/**
	 * Gets the Maximum Course Code Length Allowed.
	 *
	 * @return An Integer representing the  Maximum Course Code Length Allowed.
	 */
	public static int getMaxCourseCodeLength() {
		return MAXCOURSECODELENGTH;
	}

	/**
	 * Gets the Courses' Name.
	 *
	 * @return A String representing  the Course's Name.
	 */
	public String getCourseName() {
		return this.courseName;
	}

	/**
	 * Gets the Course's Code.
	 *
	 * @return A String representing  the Course's Code.
	 */
	public String getCourseCode() {
		return this.courseCode;
	}
	
	/**
	 * Sets the Course's Name
	 *
	 * @param courseName A String containing the Course's Name.
	 */
	public void setCourseName(String courseName){
		this.courseName = courseName;
	}

	/**
	 * Gets the Courses' list.
	 *
	 * @return An Arraylist representing all the Courses
	 */
	public static ArrayList<Course> getCoursesList() {
		return coursesList;
	}
	
	/**
	 * Gets the Course.
	 *
	 * @param i An Integer containing the index.
	 * @return An Object representing the Course retrieved.
	 */
	public static Course getCourse(int i){
		return coursesList.get(i);
	}

	/**
	 * Checks for the duplication of Course Code
	 *
	 * @param courseCode A String Containing the Course Code to be checked.
	 * @return true, if there is a duplicate.
	 */
	public static boolean duplicateCourseCode(String courseCode) {

		for (Course c : coursesList) {

			if (c.getCourseCode().equalsIgnoreCase(courseCode)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Checks for the duplication of Lesson Code.
	 *
	 * @param lessonCode A String Containing the Lesson Code to be checked.
	 * @return true, if there is a duplicate.
	 */
	public boolean duplicateLessonCode(String lessonCode) {
		for (Lesson l : lessonsList) {
			if (l.getLessonCode().equalsIgnoreCase(lessonCode)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Checks for the duplication of Lesson Type.
	 *
	 * @param lessonName A String Containing the Lesson Name to be checked.
	 * @return true, if there is a duplicate.
	 */
	public boolean duplicateLessonType(String lessonName) {
		for (Lesson l : lessonsList) {
			if (l.getLessonType().equalsIgnoreCase(lessonName)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Gets the Students enrolled in the Course.
	 *
	 * @return An ArrayList representing all the Students enrolled in the Course.
	 */
	public ArrayList<Student> getEnrollment() {
		return this.enrollment;
	}

	/**
	 * Gets the Total Number of Students Enrolled in the Course.
	 *
	 * @return An Integer representing the Total Number of Students Enrolled in the Course.
	 */
	public int getTotalEnrolledStudents() {
		return this.enrollment.size();
	}


	/**
	 * Check if a Student is enrolled in the Course.
	 *
	 * @param s An Object containing the Student to be checked.
	 * @return true, if Student is enrolled in the Course.
	 */
	public boolean checkEnrollment(Student s) {

		for (Student ss : enrollment) {
			if (ss.getId().equalsIgnoreCase(s.getId())) {
				return true;
			}
		}

		return false;
	}
	
	/**
	 * Enroll a Student to the Course.
	 *
	 * @param s An object containing the Student to be enrolled.
	 */
	public void enrollStudent(Student s) {
		this.enrollment.add(s);
	}

	
	/**
	 * Unenroll a Student from the Course.
	 
	 * @param s An Object containing the Student to be unenrolled.
	 */
	public void unenrollStudent(Student s){
		
		for(Iterator<Student> iterator = enrollment.iterator(); iterator.hasNext(); ) {
		    if(iterator.next().getId().equalsIgnoreCase(s.getId()))
		        iterator.remove();
		}
	}
	
	/**
	 * Updates a Student that is enrolled in the Course.
	 *
	 * @param s An Object containing the Student to be updated.
	 */
	public void updateEnrollment(Student s){
		for(ListIterator<Student> iterator = enrollment.listIterator(); iterator.hasNext(); ) {
		    if(iterator.next().getId().equalsIgnoreCase(s.getId()))
		        iterator.set(s);
		}
	}
	
	/**
	 * Removes a Lesson from the Course.
	 *
	 * @param l An Object containing the Lesson to be removed
	 */
	public void removeLesson(Lesson l){
		lessonsList.remove(l); 	
	}
	
	/**
	 * Removes a Course
	 *
	 * @param c An object containg the Course to be removed.
	 */
	public static void removeCourse(Course c){
		coursesList.remove(c);
	}

	/**
	 * Gets the Course Menu to be displayed.
	 *
	 * @return An Arraylist representing all the Courses for display purposes.
	 */
	public static ArrayList<String> getCourseMenu() {

		ArrayList<String> courseMenu = new ArrayList<String>();

		for (Course c : coursesList) {

			courseMenu.add(c.getCourseCode() + ": " + c.getCourseName());
		}

		return courseMenu;
	}

	/**
	 * Update the Courses' list.
	 *
	 * @param An ArrayList containing the new Courses' List  to be updated.
	 */
	public static void updateCoursesList(ArrayList<Course> c) {
		coursesList = c;

	}

	/**
	 * Initialize the Course file.
	 *
	 * @throws IOEXCEPTION if Input/Output has an error
	 * @throws ClassNotFoundException if Class cannot be found.
	 */
	// Read Courses.ser File
	public static void initializeFile() throws Throwable {

		d.initializeFile(CLASSNAME, FILENAME, FILEPATH);	
	}

	/**
	 * Updates the Course File.
	 */
	public static void updateFile() {

		d.updateFile(coursesList, FILENAME, FILEPATH);
	}

}
