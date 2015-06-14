package orders;

import oop.ex5.filescript.TypeIException;

public class BadOrderNameException extends TypeIException {
	
	public BadOrderNameException() {
		super();
	}
	
	public BadOrderNameException(String msg) {
		super(msg);
	}

}
