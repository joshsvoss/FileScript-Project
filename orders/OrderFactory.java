package orders;

/** This factory class builds instances of the Order object based on strings passed in 
 * as arguments to the buildOrder method.
 * @author Joshua Voss
 *
 */
public class OrderFactory {
	
	/** This method builds the corresponding Order to the string given as argument.
	 * @param orderName name of the order to be created.
	 * @return an order of that type, it found.
	 * @throws BadOrderNameException 
	 */
	public static Order buildOrder(String orderName) throws BadOrderNameException {
		
		// Based on the name of the order create the corresponding Order instance
		switch(orderName) {
			
		case "abs":
			return new AbsoluteOrder();
			// No "break" is needed here (it would be unreachable due to return)
		case "type":
			return new TypeOrder();
		case "size":
			return new SizeOrder();
		
		// Otherwise, the orderName doesn't match an order that we expect, 
		default:
			throw new BadOrderNameException();
		
		}
	}

}
