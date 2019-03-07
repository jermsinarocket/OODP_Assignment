package utils;

import java.io.File;

/**
 * Represents the File Model.
 */
public class FileModel {
	
	/**
	 * Checks if File Exists.
	 *
	 * @param path A String containing the File's Path.
	 * @return true, if File Exists.
	 */
	public boolean fileExists(String path) {
		
		File f = new File(path); 
		
		if(f.isFile()) {
			return true;
		}else {
			return false;
		}
	}

	
	/**
	 * Gets the File.
	 *
	 * @param fileName A String containing the File's Name.
	 * @param filePath A String containing the File's Path.
	 * @return A String Representing the Full File (File Path + File Name).
	 */
	public String getFile(String fileName, String filePath) {
		return filePath.concat(fileName);
	}
	
}
