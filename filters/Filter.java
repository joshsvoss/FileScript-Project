package filters;

/** This interface provides an api for all of the filter classes
 * that implement it.
 * @author Joshua Voss
 *
 */
public interface Filter { 
	
	/** This method matches files to the specified filter.
	 * @param filepath the path to the file to be matched.
	 * @return true if the file matches the filter, false otherwise.
	 */
	public boolean doesPass(String filepath);
}


