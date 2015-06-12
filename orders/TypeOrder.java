package orders;

import java.io.File;

public class TypeOrder extends Order {
	// Magic numbers
	private static final int OFFSET_ONE = 1;
	private static final String PERIOD = ".";
	
	public TypeOrder(String reverseParam) {
		super(reverseParam);
	}

	@Override
	public int compare(File f1, File f2) {
		int toReturn = getType(f1).compareTo(getType(f2));
		
		// Reverse result if neccesary:
		if (this.reverse) {
			toReturn = toReturn * -1;
		}
		
		return toReturn;
	}
	
	private static String getType(File file) {
		String name = file.getName();
		int periodIndex = name.lastIndexOf(PERIOD);
		String type = name.substring(periodIndex + OFFSET_ONE);
		return type;
	}

}
