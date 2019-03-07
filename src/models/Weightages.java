package models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents all the Weightages.
 */
public class Weightages implements Serializable{
	
	/** Represents the serialVersionUID. */
	private static final long serialVersionUID = -8714008254913167259L;
	
	/** Represents the Exam. */
	public Weightage exam;
	
	/** Represents the Coursework. */
	public Weightage courseWork;
	
	/** Represents the Courseworks' list. */
	public ArrayList<Weightage> courseWorkList;
	
	/**
	 * Instantiates a new Weightages.
	 *
	 * @param examweight An Integer containing the Exam Weight of the Weightages.
	 */
	public Weightages(int examweight) {
		exam = new Weightage("Exam", examweight);
		courseWork = new Weightage("Coursework", 100 - examweight);
		courseWorkList = new ArrayList<Weightage>();
		}
	
	/**
	 * Gets the Courseworks' list.
	 *
	 * @return An ArrayList representing the Courseworks' list.
	 */
	public ArrayList<Weightage> getCourseworkList() {
        return this.courseWorkList;
    }
	
	/**
	 * Gets the Exam.
	 *
	 * @return An Object representing the Exam
	 */
	public Weightage getExam(){
		return this.exam;
	}
	
	/**
	 * Gets the Coursework.
	 *
	 * @return An Object representing the Coursework
	 */
	public Weightage getCoursework(){
		return this.courseWork;
	}
	
	/**
	 * Gets the Exam's Weight.
	 *
	 * @return A Double representing the Exam's Weight
	 */
	public double getExamWeight() {
		return exam.getWeight();
	}
	
	/**
	 * Sets the Exam's Grade.
	 *
	 * @param studentId A String containing the ID of the Student whose Exam's Grade is to be set.
	 * @param grade A Double containing the Student's Grade.
	 */
	public void setExamGrade(String studentId, double grade){
		this.exam.setGrade(studentId, grade);
	}
	
	/**
	 * Sets the Coursework's Grade.
	 *
	 * @param studentId A String containing the ID of the Student whose Coursework's Grade is to be set.
	 * @param grade A Double containing the Student's Grade.
	 */
	public void setCourseworkGrade(String studentId, double grade){
		this.courseWork.setGrade(studentId, grade);
	}
	
	
	/**
	 * Sets the Exam's Weight.
	 *
	 * @param examWeightage A double containing the new Exam's Weight.
	 */
	public void setExamWeight(double examWeightage) {
		exam.setWeight(examWeightage);
		courseWork.setWeight(100 - examWeightage);
	}
	
	/**
	 * Gets the Coursework's Weight.
	 *
	 * @return A Double containing the Coursework's Weight.
	 */
	public double getCourseworkWeight() {
		return courseWork.getWeight();
	}
	
	/**
	 * Sets the Coursework's Weight.
	 *
	 * @param courseworkWeightage A double containing the new Coursework's Weight.
	 */
	public void setCourseworkWeight(double courseworkWeightage) {
		courseWork.setWeight(courseworkWeightage);
		exam.setWeight(100 - courseworkWeightage);
	}
	
	/**
	 * Removes the Coursework Component.
	 *
	 * @param component An object containing the Couresework component to be removed.
	 */
	public void removeComponent(Weightage component){
		this.courseWorkList.remove(component);
	}
}
