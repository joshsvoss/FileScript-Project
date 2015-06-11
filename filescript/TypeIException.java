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

}
