package orders;

import java.io.File;

public class TypeOrder implements Order {
	// Magic numbers
	private static final int OFFSET_ONE = 1;
	private static final String PERIOD = ".";

	@Override
	public int compare(File f1, File f2) {
		return getType(f1).compareTo(getType(f2));
	}
	
	private static String getType(File file) {
		String name = file.getName();
		int periodIndex = name.lastIndexOf(PERIOD);
		String type = name.substring(periodIndex + OFFSET_ONE);
		return type;
	}

}
