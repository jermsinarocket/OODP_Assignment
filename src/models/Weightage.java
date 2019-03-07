package models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a Weightage.
 */
public class Weightage implements Serializable {

	/** Represents the serialVersionUID. */
	private static final long serialVersionUID = 3198641263087315883L;
	
	/** Represents the Score Weightage. */
	private double scoreWeightage;
	
	/** Represents the Weightage's Name. */
	private String weightageName;
	
	/** Represents the Students Grades. */
	private Map<String,Double> grades;
	
	/**
	 * Instantiates a New Weightage.
	 *
	 * @param weightageName A String containing the Weightage's Name.
	 * @param scoreWeightage A Double containing  the Score's Weightage.
	 */
	public Weightage(String weightageName, double scoreWeightage) {
		this.weightageName = weightageName;
		this.scoreWeightage = scoreWeightage;
		this.grades = new HashMap<String, Double>();
	}
	
	/**
	 * Sets the Weightage's Name.
	 *
	 * @param weightageName A String containing the new Weightage's Name.
	 */
	public void setName(String weightageName) {
		this.weightageName = weightageName;
	}
	
	/**
	 * Gets the Weightage's Name.
	 *
	 * @return A String representing the Weightage's Name
	 */
	public String getName() {
		return this.weightageName;
	}

	
	/**
	 * Sets the Weightage's Weight.
	 *
	 * @param scoreWeightage A Double containing the new Weightage's Weight.
	 */
	public void setWeight(double scoreWeightage) {
		this.scoreWeightage = scoreWeightage;
	}
	
	/**
	 * Gets the Weightage's Weight.
	 *
	 * @return A Double representing the Weightage's Weight.
	 */
	public double getWeight() {
		return this.scoreWeightage;
	}
	
	
	/**
	 * Sets the Student's grade.
	 *
	 * @param studentId A String containing the ID of the Student whose grade is to be set.
	 * @param grade A Double representing the Grade of the Student.
	 */
	public void setGrade(String studentId,double grade){
	
		this.grades.put(studentId,(grade/100)*this.scoreWeightage);
	}
	
	/**
	 * Gets the Student's Grade.
	 *
	 * @param studentId A string containing the ID of the Student whose grade is to be retrieved.
	 * @return A double representing the Grade of the Student.
	 */
	public double getGrade(String studentId){
		return grades.get(studentId);
	}
	
	/**
	 * Checks if the Student's Grade exists.
	 *
	 * @param studentId A String containg the ID of the Student whose grade is to be checked
	 * @return true, if the Student's Grade exists.
	 */
	public boolean checkGradeExist(String studentId){
		
		if(grades.containsKey(studentId)){
			return true;
		}else
			return false;
	}
	
	/**
	 * Recalculate all the Students' Grade.
	 *
	 * @param currentWeightage A double containing the Current Weightage's Weight.
	 * @param newWeightage A double containing the New Weightage's Weight.
	 */
	public void recalculateGrade(double currentWeightage, double newWeightage){
		for (Map.Entry<String, Double> entry : grades.entrySet()) {
		    entry.setValue((entry.getValue()/currentWeightage) * newWeightage);
		}
	}
}
