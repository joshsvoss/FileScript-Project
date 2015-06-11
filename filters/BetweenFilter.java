package filters;

import java.io.File;

public class BetweenFilter implements Filter {
	
	private static final int K_BYTES = 1024; 
	int floor;
	int roof;

	public BetweenFilter(String floorString, String roofString) throws InvalidFilterParamException {
		try {
			this.floor = Integer.parseInt(floorString) * K_BYTES;
			this.roof = Integer.parseInt(roofString) * K_BYTES;
		}
		catch(NumberFormatException e) {
			throw new InvalidFilterParamException("The param passed to the greater_then filter"
					+ " wasn't fully numerical."); //TODO repitition with greater_than constructor. Maybe use some inheritance to combine common functionality?
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
