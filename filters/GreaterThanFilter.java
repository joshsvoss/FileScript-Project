package filters;

import java.io.File;

public class GreaterThanFilter implements Filter{
	
	double floor;
	
	public GreaterThanFilter(String floorString) throws InvalidFilterParamException { 
		
		// Try to convert the string into an int, it it's not all numerical, 
		// we have a  TypeI error
		try {
			this.floor = Double.parseDouble(floorString);
		}
		catch (NumberFormatException e) {
			// If the string couldn't be converted into a number, throw up a TypeI exception
			throw new InvalidFilterParamException("The param passed to the greater_then filter"
					+ " wasn't fully numerical."); //TODO maybe I don't need this string since I have a standard print out for typeI's anyway
		}
		
		// Make sure size floor isn't negative
		if (this.floor < 0) {
			throw new InvalidFilterParamException("The size parameter for the filter can't be negative.");
		}
	}
	
	
	@Override
	public boolean doesPass(String filepath) {
File file = new File(filepath);
		
		// If file doesn't exist,
		if (file.length() == 0L) { //TODO REPITITION here and in greatern than and between (size filters).  Maybce create abstract class for them to inherit from? make helper method for common code?
			return false;
		}
		else {
			return (file.length() > this.floor);
		}
	}

}
