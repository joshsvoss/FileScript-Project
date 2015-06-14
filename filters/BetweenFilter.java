package filters;

import java.io.File;

/** This filter matches files whose size is between the floor and roof double parameters.  
 * 
 * This class constructor will throw an InvalidFilterParamException if the paremeters passed 
 * in are in any way not valid.  This will be caught and dealt with above in the parser.  
 * @author Joshua Voss
 *
 */
public class BetweenFilter implements Filter {
	
	private static final int K_BYTES = 1024; 
	private double floor;
	private double roof;

	/** Between filter constructor.  This constructor throws an 
	 * InvalidFilterParamException if the paremeters passed 
	 * in are in any way not valid.  This will be caught and dealt with above in the parser.  
	 * @param floorString to be converted into a double, lowest size value
	 * @param roofString highest possible size value for the file
	 * @throws InvalidFilterParamException if the parameters are out of order,
	 * not numerical, or negative.  
	 */
	public BetweenFilter(String floorString, String roofString) throws InvalidFilterParamException {
		
		// Make sure the floorString and roofString aren't null:
		if (floorString == null || floorString == null) {
			throw new InvalidFilterParamException("String arg is null");
		}
		
		try {
			this.floor = Double.parseDouble(floorString) * K_BYTES;
			this.roof = Double.parseDouble(roofString) * K_BYTES;
		}
		catch(NumberFormatException e) {
			throw new InvalidFilterParamException("The param passed to the greater_then filter"
					+ " wasn't fully numerical."); //TODO repitition with greater_than constructor. Maybe use some inheritance to combine common functionality?
		}
		
		// Make sure size bounds aren't negative
		if (this.roof < 0 || this.floor < 0) {
			throw new InvalidFilterParamException("The size parameters for the filter can't be negative.");
		}
		
		// Also make sure that floor is indeed smaller than roof
		if (this.floor > this.roof) {
			throw new InvalidFilterParamException("The floor param was larger than the roof param "
					+ "in the between filter");
			
		}
	}

	@Override
	public boolean doesPass(String filepath) {
		File file = new File(filepath); 
		
		// If the file does not exist,
		if (file.length() == 0L ) {
			return false;
		}
		else {
			return (file.length() >= floor && file.length() <= roof);
		}
	}

}
