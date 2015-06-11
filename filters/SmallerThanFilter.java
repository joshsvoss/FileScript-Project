package filters;

import java.io.File;

public class SmallerThanFilter implements Filter {
	
	private static final int K_BYTES = 1024; // TODO repitition, how can it share this with greater than and between?
	
	private int roof;
	
	public SmallerThanFilter(String roofString) throws InvalidFilterParamException {
		try {
			this.roof = Integer.parseInt(roofString) * K_BYTES;
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
		
		// If file doesn't exist,
		if (file.length() == 0L) { //TODO REPITITION here and in greatern than and between (size filters).  Maybce create abstract class for them to inherit from? make helper method for common code?
			return false;
		}
		else {
			return (file.length() <= this.roof);
		}
	}

}
