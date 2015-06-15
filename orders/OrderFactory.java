package orders;

/** This factory class builds instances of the Order object based on strings passed in 
 * as arguments to the buildOrder method.
 * @author Joshua Voss
 *
 */
public class OrderFactory {
	
	private static final String ABS_STRING = "abs";
	private static final String TYPE_STRING = "type";
	private static final String SIZE_STRING = "size";
	// Magic numbers
	private static final int REVERSE_PARAM_INDEX = 1;
	private final static int ORDER_NAME_INDEX = 0;
	
	/** This method builds the corresponding Order to the string given as argument.
	 * @param orderName name of the order to be created.
	 * @return an order of that type, it found.
	 * @throws BadOrderNameException 
	 */
	public static Order buildOrder(String[] paramList) throws BadOrderNameException {
		
		String orderName = paramList[ORDER_NAME_INDEX];
		String reverseParam = null;
		if(paramList.length > 1) {
			// then we can access the param part of the array without exceeding bounds:
			reverseParam = paramList[REVERSE_PARAM_INDEX];
		}
		
		// Based on the name of the order create the corresponding Order instance
		switch(orderName) {
			
		case ABS_STRING:
			return new AbsoluteOrder(reverseParam);
			// No "break" is needed here (it would be unreachable due to return)
			
		case TYPE_STRING:
			return new TypeOrder(reverseParam);
			
		case SIZE_STRING:
			return new SizeOrder(reverseParam);
		
		// Otherwise, the orderName doesn't match an order that we expect, 
		default:
			throw new BadOrderNameException();
		
		}
	}

}
