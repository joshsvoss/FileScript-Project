package oop.ex5.filescript;

public class BadCommandSyntax extends TypeIIException {


	/** This constructor allows us to pass a message up the stack. 
	 * @param msg message to be passed up.
	 */
	public BadCommandSyntax(String msg) {
		super(msg);
	}

	private static final long serialVersionUID = 1L;

}
