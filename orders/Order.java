package orders;

import java.io.*;
import java.security.InvalidParameterException;

/** This abstract class is extended by the specific order classes.  
 * @author Joshua Voss
 *
 */
public abstract class Order implements java.util.Comparator<File>{ 
													
	// This variable keeps track of whether the results are in order or reversed
	protected boolean reverse;
	protected final static String REVERSE_STRING = "REVERSE";
	protected final static int REVERSE_MULTIPLIER = -1;
	
	/** Constructor, called using super() by the sub classes.  
	 * @param reverseParam
	 */
	public Order(String reverseParam) {
		
		if (reverseParam == null) {
			this.reverse = false;
		}
		
		else if (!reverseParam.equals(REVERSE_STRING)) {
			// Then we've got some invalid input,
			throw new InvalidParameterException("In Order constructor, param was invalid.");
		}
		else {
			// Otherwise, the passed in argument is "REVERSE", set the flag:
			this.reverse = true;
		}
	}
	
	
}
