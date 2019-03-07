package models;

/**
 * Represents the Interface Person.
 */
public interface Person{
	
	/**
	 * Gets the Name of the Person.
	 *
	 * @return A String representing the Name of the Person.
	 */
	public abstract String getName();
	
	/**
	 * Gets the Email Adress of the Person.
	 *
	 * @return A String representing the Email Address of the Person.
	 */
	public abstract String getEmailAddress();
	
	/**
	 * Gets the ID of the Person.
	 *
	 * @return A String representing the ID of the Person.
	 */
	public abstract String getId();
	
	/**
	 * Gets the Contact Number of the Prson.
	 *
	 * @return A String representing the Contact Number of the Person.
	 */
	public abstract String getContactNo();
	
}
