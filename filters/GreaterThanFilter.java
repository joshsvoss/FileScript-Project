package filters;

public class GreaterThanFilter implements Filter{
	
	int floor;
	
	public GreaterThanFilter(int floor) { //TODO shoudl arg be String instead of int?
		this.floor = floor;
	}
	
	
	@Override
	public boolean doesPass(String filepath) {
		// TODO Auto-generated method stub
		return false;
	}

}
