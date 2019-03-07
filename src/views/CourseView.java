package views;

import java.text.DecimalFormat;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * Represents the Course View.
 */
public class CourseView {

	/**
	 * Prints the Information of the Course that has been added.
	 *
	 * @param courseCode A String containing the Course's Code.
	 * @param courseName A String containing the Course's Name.
	 * @param coordinator A String containing the Course's Coordinator Name.
	 * @param examWeightage A Double containing the Course's Exam Weightage.
	 */
	public void printCoureseAddedInfo(String courseCode,String courseName, String coordinator,double examWeightage){
		System.out.print("Course Code: " + courseCode + "\n");
		System.out.print("Course Name: " + courseName + "\n");
		System.out.print("Course Weightage: Exam - " + "(" + examWeightage + "%), Coursework - " + "("  + (100-examWeightage) + "%)\n");
		System.out.print("Course Coordinator: " + coordinator + "\n");
		
	}
	
	/**
	 * Prints the Information of the Lesson that has been added.
	 *
	 * @param lessonType A String containing the Lesson's Type.
	 * @param lessonCode A String containing the Lesson's Code.
	 * @param courseCode A String containing the Course's Code.
	 * @param courseName A String containing the Course's Name.
	 */
	public void printLessonAddedInfo(String lessonType,String lessonCode, String courseCode, String courseName){
		System.out.println(lessonType.toUpperCase() + "(" + lessonCode +") has been added for " + courseCode + ": " + courseName.toUpperCase());
	}
	
	/**
	 * Prints the Information of the Lesson that has been updated.
	 *
	 * @param lessonType A String containing the Lesson's Type.
	 * @param lessonCode A String containing the Lesson's Code.
	 * @param lessonVacancy An Integer containing the Lesson's Vacancy.
	 * @param lessonCapacity An Integer containing the Lesson's Capacity.
	 */
	public void printLessonUpdatedInfo(String lessonType, String lessonCode, int lessonVacancy, int lessonCapacity){
		System.out.println("Lesson Code: " + lessonCode);
		System.out.println("Lesson Type: " + lessonType);
		System.out.println("Lesson Vacancy: [Vacancy: " + lessonVacancy + "/ Total Size: " + lessonCapacity +"]");
	}
	
	/**
	 * Prints the Current Weightage of the Course.
	 *
	 * @param courseCode A String containing the Course's Code.
	 * @param courseName A String containing the Course's Name.
	 * @param examWeightage A Double containing the Course's Exam Weightage.
	 * @param examWeightage A Double containing the Course's Coursework Weightage.
	 * @param components An ArrayList containing the Name of the Coursework Components.
	 * @param componentMarks An ArrayList containing the Grades of the Coursework Components.
	 */
	public void printCurrentWeightage(String courseCode,String courseName,double examWeight,double courseworkWeight, ArrayList<String> components,ArrayList<Double> componentMarks){
		
		DecimalFormat df = new DecimalFormat("#.00");

		System.out.print("Course Code: " + courseCode + "\n");
		System.out.print("Course Name: " + courseName + "\n");
		System.out.println("Course Weightage:");
		System.out.println("   Exam - " + df.format(examWeight) + "%");
		System.out.println("   Coursework -" +df.format(courseworkWeight) + "%");
		
		if(!components.isEmpty()){
			for(int i = 0; i < components.size();i++){
				System.out.println("        " + components.get(i) + " - " + df.format(componentMarks.get(i)) + "%");
			}
		}
	}

	/**
	 * Prints the Weightage's Name.
	 *
	 * @param weightageName A String containing the Weightage's Name to be printed.
	 */
	public void printWeightageName(String weightageName){
		System.out.print(weightageName);
	}
	
	/**
	 * Prints the Weightage's Weight.
	 *
	 * @param weightageWeight A Double containing the Weightage's Weight to be printed.
	 */
	public void printWeightageWeight(double weightageWeight){
		System.out.print(weightageWeight + "%");
		
	}
	
	/**
	 * Prints the Lesson's Information.
	 *
	 * @param lessonType A String containing the Lesson's Type.
	 * @param lessonCode A String containing the Lesson's Code.
	 * @param lessonVacancy An Integer containing the Lesson's Vacancy.
	 * @param lessonCapacity An Integer containing the Lesson's Capacity.
	 */
	public void printLessonsList(String lessonType, String lessonCode, int lessonVacancy, int lessonCapacity){
		System.out.println(lessonType + "(" + lessonCode + ") [Vacancy: " + lessonVacancy + "/ Total Size: " + lessonCapacity +"]");
	}
	
	/**
	 * Prints the Statistics of the Course.
	 *
	 * @param totalNoOfStudents An Integer containing the total number of students.
	 * @param examMarks A Double containing the average Exam Marks.
	 * @param componentMarks An ArrayList containing the average Component Marks.
	 * @param componentNames An ArrayList containing the Component Names.
	 */
	public void printCourseStatistics(int totalNoOfStudents,double examMarks, Double[] componentMarks, String[] componentNames){
		
		double totalAverageMarks = 0 ;
		System.out.println("Total Number of Students: " + totalNoOfStudents);
		System.out.println("Average Exam Marks: " + examMarks/totalNoOfStudents);
		totalAverageMarks += examMarks/totalNoOfStudents;
		
		for(int i = 0; i < componentMarks.length ; i ++){
			System.out.println("Average " + componentNames[i] + " Marks: " + (componentMarks[i]/totalNoOfStudents));
			
			totalAverageMarks += componentMarks[i]/totalNoOfStudents;
		}
		
		System.out.println("Average Total Marks: " + totalAverageMarks);


	}

}
