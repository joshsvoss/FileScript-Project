package filters;

public class GreaterThanFilter implements Filter{
	
	int floor;
	
	public GreaterThanFilter(String floorString) { //TODO shoudl arg be String instead of int?
		
		// Try to convert the string into an int, it it's not all numerical, 
		// we have a  TypeI error
		try {
			this.floor = Integer.parseInt(floorString);
		}
		catch (NumberFormatException e) {
			// If the string couldn't be converted into a number, throw up a TypeI exception
			throw new InvalidFilterParamException("The param passed to the greater_then filter"
					+ " wasn't fully numerical."); //TODO maybe I don't need this string since I have a standard print out for typeI's anyway
		}
	}
	
	
	@Override
	public boolean doesPass(String filepath) {
		// TODO Auto-generated method stub
		return false;
	}

}
