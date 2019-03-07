package utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import models.Course;
import models.Professor;
import models.Student;

// TODO: Auto-generated Javadoc
/**
 * Represents the Database.
 */
public class Database{
	
	/** Represents the File Model. */
	private FileModel model = new FileModel();

	/**
	 * Initialize all Data.
	 *
	 * @throws IOEXCEPTION if Input/Output has an error.
	 * @throws ClassNotFoundException if Class cannot be found.
	 */
	public static void initializeAllData() throws Throwable {

		Student.initializeFile();
		Course.initializeFile();
		Professor.initializeFile();
	}
	
	/**
	 * Initialize the File.
	 *
	 * @param CLASSNAME A String containing the Class Name.
	 * @param FILENAME A String containing the File Name.
	 * @param FILEPATH A String containing the File Path.
	 */
	@SuppressWarnings("unchecked")
	public void initializeFile(String CLASSNAME,String FILENAME, String FILEPATH) {

		if (!model.fileExists(model.getFile(FILENAME, FILEPATH))) {

			// If Does not exist, create one

			try {
				// use buffering
				OutputStream file = new FileOutputStream(model.getFile(
						FILENAME, FILEPATH));
				OutputStream buffer = new BufferedOutputStream(file);
				ObjectOutput output = new ObjectOutputStream(buffer);
				try {
					
					if(CLASSNAME.equals("STUDENT")){
		
						output.writeObject(Student.getStudentsList());
					
					}else{
						output.writeObject(Course.getCoursesList());
					}
				} finally {
					output.close();
				}

			} catch (IOException ex) {
				System.out.println("Cannot perform output.");
				ex.printStackTrace();
			}

		} else {

			// If exists, write into array list
			try {
				// use buffering
				InputStream file = new FileInputStream(model.getFile(FILENAME,
						FILEPATH));
				InputStream buffer = new BufferedInputStream(file);
				ObjectInput input = new ObjectInputStream(buffer);

				try {

					if(CLASSNAME.equals("STUDENT")){
					 Student.updateStudentsList((ArrayList<Student>) input.readObject());
					}else{
						Course.updateCoursesList((ArrayList<Course>) input.readObject());
					}
				} finally {
					input.close();
				}
			} catch (IOException ex) {
				System.out.println(ex);
			} catch (ClassNotFoundException ex) {
				System.out.println("Class not found.");
			}
		}
	}
	
	
	/**
	 * Updates the File.
	 *
	 * @param a An ArrayList containing the Object to be written.
	 * @param FILENAME A String containing the File Name.
	 * @param FILEPATH A String containing the File Path.
	 */
	public void updateFile(ArrayList<?> a,String FILENAME, String FILEPATH){

		try {
			// use buffering
			OutputStream file = new FileOutputStream(model.getFile(FILENAME,
					FILEPATH), false);
			OutputStream buffer = new BufferedOutputStream(file);
			ObjectOutput output = new ObjectOutputStream(buffer);
			try {

				output.writeObject(a);
			} finally {
				output.close();
			}

		} catch (IOException ex) {
			System.out.println("Cannot perform output.");
			ex.printStackTrace();
		}
	}

}
