package filescript;

public class TypeIException extends FileScriptException {

	// TODO what's meaning?
	private static final long serialVersionUID = 1L;
	
	// TODO if I don't define a constructore, does java do the following for me automatically?  Call the super()?
	public TypeIException() {
		super();
	}
	
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
