package orders;

import java.io.File;

/** This order sorts by absolute name, using getAbsolutePath()
 * @author Joshua Voss
 *
 */
public class AbsoluteOrder extends Order {
	
	/** Constructor method
	 * @param reverseParam This String, if  == "REVERSE" will reverse the order of the sorting.  
	 */
	public AbsoluteOrder(String reverseParam) {
		super(reverseParam);
	}

	@Override
	public int compare(File f1, File f2) {
		int toReturn = f1.getAbsolutePath().compareTo(f2.getAbsolutePath());
		
		// Reverse result if neccesary:
		if (this.reverse) {
			toReturn = toReturn * REVERSE_MULTIPLIER;
		}
		
		return toReturn;
	}

}
