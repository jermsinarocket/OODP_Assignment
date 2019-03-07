package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * Represents a Lesson.
 */
public class Lesson implements Serializable{
	
	/** Represents the  serialVersionUID. */
	private static final long serialVersionUID = -1996184656523326917L;
	
	/** Represents the  MAXLESSONCAPACITY. */
	private static final int MAXLESSONCAPACITY = 100;
	
	/** Represents the MAXLESSONCODELENGTH. */
	private static final int MAXLESSONCODELENGTH = 4;
	
	/** Represents the  LESSONTYPES. */
	private static final String[] LESSONTYPES = {"Lecture","Tutorial","Lab"};
	
	/** Represents the Students Enrolled in the Lesson */
	private ArrayList<Student> enrolled;
	
	/** Represents the Lesson's Capacity. */
	private int lessonCapacity;
	
	/** Represents the Lesson's Type. */
	private String lessonType;
	
	/** Represents the Lesson's Code. */
	private String lessonCode;
	 
	
	/**
	 * Instantiates a new Lesson.
	 *
	 * @param lessonCapacity An Integer containing the Lesson's Capacity.
	 * @param lessonType A String containing the Lesson's Type.
	 * @param lessonCode A String containing the Lesson's Code.
	 */
	public Lesson(int lessonCapacity, String lessonType,String lessonCode) {
		this.lessonCapacity = lessonCapacity;
		this.lessonType = lessonType;
		this.lessonCode = lessonCode.toUpperCase();
		this.enrolled = new ArrayList<Student>();
	}
	
	
	/**
	 * Gets the available Lesson Types.
	 *
	 * @return An Array representing all the Lesson Types.
	 */
	public static String[] getLessonTypes(){
		return LESSONTYPES;
	}
	
	/**
	 * Gets the type of the Lesson.
	 *
	 * @return A String representing the Lesson's Type.
	 */
	public String getLessonType(){
		return this.lessonType;
	}
	
	/**
	 * Sets the Lessons' Type
	 *
	 * @param i An Integer containing the index of the Lesson Type.
	 */
	public void setLessonType(int i){
		this.lessonType = LESSONTYPES[i];
	}
	
	/**
	 * Gets the Lesson's Code
	 *
	 * @return A String representing the Lesson's Code.
	 */
	public String getLessonCode(){
		return this.lessonCode;
	}
	
	/**
	 * Gets the Maximum Lesson Capacity.
	 *
	 * @return An Integer representing the maximum Lesson's Capacity.
	 */
	public static int getMaxLessonCapacity(){
		return MAXLESSONCAPACITY;
	}
	
	/**
	 * Gets the Maximum Lesson Code Length.
	 *
	 * @return An Integer representing the maximum Lesson's Code lsength.
	 */
	public static int getMaxLessonCodeLength(){
		return MAXLESSONCODELENGTH;
	}
	
	/**
	 * Gets the Lesson's Capacity
	 *
	 * @return An Integer representing the Lesson's Capacity
	 */
	public int getlessonCapacity(){
		return this.lessonCapacity;
	}
	
	/**
	 * Sets the lesson capacity.
	 *
	 * @param lessonCapacity An Integer containingg the new Lesson's Capacity
	 */
	public void setLessonCapacity(int lessonCapacity){
		this.lessonCapacity = lessonCapacity;
	}
	
	/**
	 * Gets the Vacancy of the Lesson.
	 *
	 * @return An Integer representing the Vacancy of the Lesson.
	 */
	public int getVacancy() {
		return this.lessonCapacity - this.enrolled.size();
	}
	
	/**
	 * Gets the Students Enrolled in the Lesson.
	 *
	 * @return An ArrayList representing the Students enrolled in the lesson.
	 */
	public ArrayList<Student> getEnrolled(){
		return this.enrolled;
	}
	
	/**
	 * Check if a Student is enrolled in the Lesson.
	 *
	 * @param s An Object containing the Student to be checked
	 * @return true, if Student is enrolled in the Lesson
	 */
	public boolean checkEnrolled(Student s){
		for(Student ss : enrolled){
			if(ss.getId().equalsIgnoreCase(s.getId())){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Unenroll a Student from the Lesson.
	 *
	 * @param s An Object containing the Student to be Unenrolled
	 */
	public void unenrollStudent(Student s){
		for(Iterator<Student> iterator = enrolled.iterator(); iterator.hasNext(); ) {
		    if(iterator.next().getId().equalsIgnoreCase(s.getId()))
		        iterator.remove();
		}
	}
	
	/**
	 * Updates a Student that is enrolled.
	 *
	 * @param s An Object containing the Student to be updated.
	 */
	public void updateEnrollment(Student s){
		for(ListIterator<Student> iterator = enrolled.listIterator(); iterator.hasNext(); ) {
		    if(iterator.next().getId().equalsIgnoreCase(s.getId()))
		        iterator.set(s);
		}
	}

	/**
	 * Enroll a Student to the lesson.
	 *
	 * @param s An Object containing the Student to be enrolled.
	 */
	public void enrollStudent(Student s){
		this.enrolled.add(s);
	}
	
	
}
