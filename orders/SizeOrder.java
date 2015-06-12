package orders;

import java.io.File;

public class SizeOrder implements Order {

	// TODO should I be returning bigger number depending on the size of the difference?  
	// TODO is there a way to use a compareTo() of the longs returned?
	@Override
	public int compare(File f1, File f2) {
		if (f1.length() < f2.length()) {
			return -1;
		}
		else if (f1.length() > f2.length()) {
			return 1;
		}
		// Otherwise, they're equal
		else {
			return 0;
		}
		
	}
	
	
	// TODO why isn't eclipse requiring me to implement equals() as well? Cuz it's implemented at Object?

}
