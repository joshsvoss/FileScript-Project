package filters;

public class InvalidFilterParamException extends filescript.TypeIException {
	
	private static final long serialVersionUID = 1L;

	public InvalidFilterParamException(String msg) {
		super(msg);
	}

	public InvalidFilterParamException() {
		super();
	}

}
