package orders;

import java.io.File;

/** The SizeOrder object organizes files by their size.  
 * @author Joshua Voss
 *
 */
public class SizeOrder extends Order {
	
	/** This constructor calls the parent Order() constructor.
	 * @param reverseParam whther or not the order is to be reversed.  
	 */
	public SizeOrder(String reverseParam) {
		super(reverseParam);
	}
	

	// TODO should I be returning bigger number depending on the size of the difference?  
	// TODO is there a way to use a compareTo() of the longs returned?
	/* This method is demanded by the interface Comparator and allows this comparator
	 * to be used in Collections.sort().
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
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
			// According to the spec, they should then be ordered by the abs order
			AbsoluteOrder absOrder = new AbsoluteOrder(null);
			toReturn = absOrder.compare(f1, f2);
		}
		
		// Reverse result if necessary:
		if (this.reverse) {
			toReturn = toReturn * REVERSE_MULTIPLIER;
		}
		
		return toReturn;
		
	}
	
	

}
