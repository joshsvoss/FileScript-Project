package filters;

/** This Exception is thrown when the parameters passed to the filter are not valid.
 * @author Joshua Voss
 *
 */
public class InvalidFilterParamException extends oop.ex5.filescript.TypeIException {
	
	private static final long serialVersionUID = 1L;

	/** Identical to Exception(String msg) constructor.  
	 * @param msg message of the exception to be passed up the stack. 
	 */
	public InvalidFilterParamException(String msg) {
		super(msg);
	}

	/** Identical to the Exception no-args constructor.  
	 * 
	 */
	public InvalidFilterParamException() {
		super();
	}

}
