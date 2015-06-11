package filescript;

public class TypeIIException extends Exception {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** No-args constructor for the TypeIIException.
	 * Simply calls the no-arg constructor of the parent class.
	 * 
	 */
	public TypeIIException() {
		super(); //TODO is this redundant?
	}
	
	/** This constructor allows a message to be passed up the stack as the Exception 
	 * floats to the top
	 * @param msg message to be passed.
	 */
	public TypeIIException(String msg) {
		super(msg);
	}
	

}
