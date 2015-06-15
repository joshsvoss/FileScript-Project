package filters;

import java.io.File;

/** This class implements the SmallerThanFilter.  It matches all files who's size is smaller
 * than the specified roofString.  
 * @author Joshua Voss
 *
 */
public class SmallerThanFilter implements Filter {
	
	private static final int K_BYTES = 1024; 
	
	private double roof;
	
	/** Constructor method.
	 * @param roofString string to be converted into the maximum (exclusive) for the file size.
	 * @throws InvalidFilterParamException thrown if the roofString is negative or null or not numeric.
	 */
	public SmallerThanFilter(String roofString) throws InvalidFilterParamException {
		
		// Make sure the roofString isn't null:
		if (roofString == null) {
			throw new InvalidFilterParamException("String arg is null");
		}
		
		try {
			this.roof = Double.parseDouble(roofString) * K_BYTES;
		}
		catch(NumberFormatException e) {
			throw new InvalidFilterParamException();
		}
		
		// Make sure size roof isn't negative
		if (this.roof < 0) {
			throw new InvalidFilterParamException("The size parameter for the filter can't be negative.");
		}
	}

	
	
	@Override
	public boolean doesPass(String filepath) {
		
		File file = new File(filepath);
		
		
		return (file.length() < this.roof);
		
	}

}
