package in.casekartin.exception;

public class ValidationException extends Exception {
	public ValidationException(String message, ValidationException e) {
		super(message,e);
	}

	public ValidationException(String string) {
		super(string);
	}

}
