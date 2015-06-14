package oop.ex5.filescript;

/** TypeIIExcpetions are fatal Exceptions as per the spec for ex5.
 * @author Joshua Voss
 *
 */
public class TypeIIException extends FileScriptException {


	private static final long serialVersionUID = 1L;
	
	/** No-args constructor for the TypeIIException.
	 * Simply calls the no-arg constructor of the parent class.
	 * 
	 */
	public TypeIIException() {
		super(); 
	}
	
	/** This constructor allows a message to be passed up the stack as the Exception 
	 * floats to the top
	 * @param msg message to be passed.
	 */
	public TypeIIException(String msg) {
		super(msg);
	}
	
	/** This method prints out the error message
	 * specified for TypeII errors.
	 * 
	 */
	public void printErrorMessage() {
		System.err.println("ERROR");
	}
	

}
