package views;

import java.text.DecimalFormat;

/**
 * Represents the StudentView.
 */
public class StudentView {
	
	/**
	 * Prints the Information of the Student that has been added.
	 *
	 * @param studentId A String containing the Student's ID.
	 * @param studentName A String containing the Student's Name.
	 * @param studentEmail A String containing the Student's Email Address.
	 * @param studentContactNo A String containing the Student's Contact Number.
	 */
	public void printNewStudentRecordInfo(String studentId,String studentName, String studentEmail,String studentContactNo){
		System.out.println("Student ID: " + studentId );
		System.out.println("Student Name: " + studentName.toUpperCase() );
		System.out.println("Student Email: " + studentEmail.toUpperCase() );
		System.out.println("Student Contact No: " + studentContactNo );
		
	}
	
	/**
	 * Prints the Information of a Student's enrollment to a Lesson.
	 *
	 * @param studentId  A String containing the Student's ID.
	 * @param studentName A String containing the Student's Name.
	 * @param courseCode A String containing the Course's Code.
	 * @param courseName A String containing the Course's Name.
	 * @param lessonType A String containing the Lesson's Type.
	 * @param lessonCode A String containing the Lesson's Code.
	 */
	public void printEnrollment(String studentId,String studentName, String courseCode, String courseName, String lessonType, String lessonCode){
		System.out.println(studentName + "(" + studentId + ") has been successfully registered for " + courseCode + ":" + courseName +  " - " + lessonType + "(" + lessonCode + ")" );
	}
	
	/**
	 * Prints the updated Grade.
	 *
	 * @param weightage A Double containing the Weightage's Weight.
	 * @param grade A Double containing the old Grade.
	 * @param weightageName A String containing the Weightage's Name.
	 * @param marks  A Double containing the new Grade.
	 */
	public void printMarks(double weightage, double grade, String weightageName,double newGrade){
		System.out.println("\nThe grade of " + grade + " has been recalculated as " + newGrade + " for " + weightageName + " (" + weightage + "%)");
	}
	
	/**
	 * Prints the Student's Transcript.
	 *
	 * @param weightageName A String containing the Weightage's Name.
	 * @param weightage A Double containing the Weightage's Weight.
	 * @param grade A Double containing the Grade.
	 */
	public void printTranscript(String weightageName, double weightage,double grade){
		
		DecimalFormat df = new DecimalFormat("#.00");
		
		System.out.println(weightageName + "(" + df.format(weightage) + ")% - "  + df.format(grade));
	}

}
