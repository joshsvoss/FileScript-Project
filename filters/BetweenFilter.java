package filters;

import java.io.File;

public class BetweenFilter implements Filter {
	
	private static final int K_BYTES = 1024; 
	private double floor;
	private double roof;

	public BetweenFilter(String floorString, String roofString) throws InvalidFilterParamException {
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
	}

	@Override
	public boolean doesPass(String filepath) {
		File file = new File(filepath); //TODO do I have to deal with possibility of filepath being to a directory?
										//TODO no exception can be thrown her from making a file of a nonexistent file?
		
		// If the file does not exist,
		if (file.length() == 0L ) {
			return false;
		}
		else {
			return (file.length() >= floor && file.length() <= roof);
		}
	}

}
