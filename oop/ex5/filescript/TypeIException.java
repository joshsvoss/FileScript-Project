package oop.ex5.filescript;

/** TypeIExceptions are non fatal exceptions as defined in the spec for ex5.
 * 
 * They are to be caught, a warning printed, and the program continues.  
 * @author Joshua Voss
 *
 */
public class TypeIException extends FileScriptException {

	private static final long serialVersionUID = 1L;
	
	/** Identical to that of the Exception class.
	 * 
	 */
	public TypeIException() {
		super();
	}
	
	/** Identical to that of the Exception class.
	 * @param msg Message to be sent up the stack with the exception.
	 */
	public TypeIException(String msg) {
		super(msg);
	}
	
	/** This method prints out the error message specified for TypeI errors.
	 * 
	 */
	public void printErrorMessage(int lineNum) {
		System.err.println("Warning in line " + lineNum );
	}

}
