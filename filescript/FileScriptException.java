package filescript;

/** An exception specific to this filescript project.  This exception will either be of
 * typeI or typeII.
 * @author Joshua Voss
 *
 */
public class FileScriptException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public FileScriptException() {
		super();
	}
	
	public FileScriptException(String msg) {
		super(msg);
	}
	
}
