package filters;

public class BetweenFilter implements Filter {
	
	int floor;
	int roof;

	public BetweenFilter(String floorString, String roofString) throws InvalidFilterParamException {
		try {
			this.floor = Integer.parseInt(floorString);
			this.roof = Integer.parseInt(roofString);
		}
		catch(NumberFormatException e) {
			throw new InvalidFilterParamException("The param passed to the greater_then filter"
					+ " wasn't fully numerical."); //TODO repitition with greater_than constructor. Maybe use some inheritance to combine common functionality?
		}
	}

	@Override
	public boolean doesPass(String filepath) {
		// TODO Auto-generated method stub
		return false;
	}

}
