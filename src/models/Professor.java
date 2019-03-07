package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import utils.FileModel;

// TODO: Auto-generated Javadoc
/**
 * Represents a Professor.
 */
public class Professor implements Person, Serializable {

	
	/** Represents the serialVersionUID. */
	private static final long serialVersionUID = -3033179224749978889L;
	
	/** Represents the FILEPATH. */
	private static final String FILEPATH = "src/data/";
	
	/** Represents the FILENAME. */
	private static final String FILENAME = "professors.txt";

	/** Represents the Professors' List. */
	private static ArrayList<Professor> professorsList = new ArrayList<Professor>();

	/** Represents the Professor's ID. */
	private String professorId;
	
	/** Represents the Professor's Name  */
	private String professorName;
	
	/** Represents the Professors' Email Address */
	private String professorEmail;
	
	/** Represents the Professor's Contact Number. */
	private String professorContactNo;
	
	/** Represents the Professor's Designation. */
	private String professorDeg;
	
	/**
	 * Instantiates a new Professor.
	 *
	 * @param professorId A String containing the Professor's id.
	 * @param professorName A String containing the Professor's Name.
	 * @param professorEmail A String containing the Professor's email Address.
	 * @param professorContactNo A String containing the Professor's Contact Number.
	 * @param professorDeg A String containing the Professor's Designation
	 */
	public Professor(String professorId,String professorName, String professorEmail, String professorContactNo, String professorDeg) {
		this.professorId = professorId.toUpperCase();
		this.professorName = professorName;
		this.professorEmail = professorEmail;
		this.professorContactNo = professorContactNo;
		this.professorDeg = professorDeg;
		addProfessor(this);
	}
	
	/**
	 * Adds the Professor to the Professors' List.
	 *
	 * @param p An object containing the Professor to be added.
	 */
	public void addProfessor(Professor p) {
		professorsList.add(p);
	}
	
	/* (non-Javadoc)
	 * @see models.Person#getId()
	 */
	public String getId(){
		return this.professorId;
	}
	
	/* (non-Javadoc)
	 * @see models.Person#getContactNo()
	 */
	public String getContactNo(){
		return this.professorContactNo;
	}
	
	/* (non-Javadoc)
	 * @see models.Person#getName()
	 */
	public String getName(){
		return this.professorName;
	}
	
	/* (non-Javadoc)
	 * @see models.Person#getEmailAddress()
	 */
	public String getEmailAddress(){
		return this.professorEmail;
	}
	
	/**
	 * Gets the Professor's Designation.
	 *
	 * @return A Strining containing the Professor's Designation.
	 */
	public String getDesignation(){
		return this.professorDeg;
	}
	
	/**
	 * Gets the Professors' List.
	 *
	 * @return An Array:List containing all the Professors.
	 */
	public static ArrayList<Professor> getProfessorsList(){
		
		return professorsList;

	}
	
	/**
	 * Gets the Professor.
	 *
	 * @param i An Integer containing the index of the Professor.
	 * @return An Object representing the Professor retrieved.
	 */
	public static Professor getProfessor(int i){
		return professorsList.get(i);
	}

	/**
	 * Initialize the Professors' File.
	 *
	 * @throws IOEXCEPTION if Input/Output has an error
	 * @throws ClassNotFoundException if Class cannot be found.
	 */
	public static void initializeFile() throws Throwable {
		
		 try {
				FileModel model = new FileModel();
	            File f = new File(model.getFile(FILENAME, FILEPATH));
	            Scanner sc = new Scanner(f);


	            while(sc.hasNextLine()){
	                String line = sc.nextLine();
	                String[] details = line.split(":");
	                String id = details[0];
	                String name = details[1];
	                String email = details[2];
	                String contactNo = details[3];
	                String designation = details[4];
	                new Professor(id, name, email,contactNo,designation);
	            }

	           sc.close();
	        } catch (FileNotFoundException e) {         
	            e.printStackTrace();
	        }

	}

}
