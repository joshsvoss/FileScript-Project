package oop.ex5.filescript;

/** An exception specific to this filescript project.  This exception will either be of
 * typeI or typeII.
 * @author Joshua Voss
 *
 */
public class FileScriptException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	/** Identical to the identical method under Exception.
	 * 
	 */
	public FileScriptException() {
		super();
	}
	
	/** Identical to the identical method under Exception.
	 * @param msg message to be passed up with the exception.
	 */
	public FileScriptException(String msg) {
		super(msg);
	}
	
}
