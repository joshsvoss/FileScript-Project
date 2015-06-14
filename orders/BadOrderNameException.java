package orders;

import oop.ex5.filescript.TypeIException;

/** This exception is thrown when a bad order name is encountered.  
 * @author Joshua Voss
 *
 */
public class BadOrderNameException extends TypeIException {
	
	private static final long serialVersionUID = 1L;

	/** Identical to method of the Exception class
	 * 
	 */
	public BadOrderNameException() {
		super();
	}
	
	/** Identical to method of the Exception class
	 * @param msg message to be carried with the exception up the stack.  
	 */
	public BadOrderNameException(String msg) {
		super(msg);
	}

}
