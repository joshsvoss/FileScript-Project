package orders;

import java.io.*;
import java.security.InvalidParameterException;

public abstract class Order implements java.util.Comparator<File>{ //TODO do I have to specify <generic type>?
													// TODO why did Java have me switch "implements" to extends?
													// TODO  Should it compare String filepaths or file objects?
	//TODO does Order have anything to add that Comparator doesn't have?
	
	// This variable keeps track of whether the results are in order or reversed
	protected boolean reverse;
	
	protected final static String REVERSE_STRING = "REVERSE";
	protected final static int REVERSE_MULTIPLIER = -1;
	
	public Order(String reverseParam) {
		
		if (reverseParam == null) {
			this.reverse = false;
		}
		
		if (!reverseParam.equals(REVERSE_STRING)) {
			// Then we've got some invalid input,
			throw new InvalidParameterException("In Order constructor, param was invalid.");
		}
		else {
			// Otherwise, the passed in argument is "REVERSE", set the flag:
			this.reverse = true;
		}
	}
	
	
}
