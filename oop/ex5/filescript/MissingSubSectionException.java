package oop.ex5.filescript;

/** This exception is thrown when a section in the command file is missing 
 * a subsection.  
 * @author Joshua Voss
 *
 */
public class MissingSubSectionException extends TypeIIException {


	/** This constructor allows us to pass a message up the stack. 
	 * @param msg message to be passed up.
	 */
	public MissingSubSectionException(String msg) {
		super(msg);
	}

	private static final long serialVersionUID = 1L;

}
