package orders;

import java.io.File;

public class SizeOrder extends Order {
	
	public SizeOrder(String reverseParam) {
		super(reverseParam);
	}
	

	// TODO should I be returning bigger number depending on the size of the difference?  
	// TODO is there a way to use a compareTo() of the longs returned?
	@Override
	public int compare(File f1, File f2) {
		
		int toReturn;
		
		if (f1.length() < f2.length()) {
			toReturn = -1;
		}
		else if (f1.length() > f2.length()) {
			toReturn = 1;
		}
		// Otherwise, they're equal
		else {
			toReturn = 0;
		}
		
		// Reverse result if neccesary:
		if (this.reverse) {
			toReturn = toReturn * -1;
		}
		
		return toReturn;
		
	}
	
	
	// TODO why isn't eclipse requiring me to implement equals() as well? Cuz it's implemented at Object?

}
