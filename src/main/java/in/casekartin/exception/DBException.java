package in.casekartin.exception;

public class DBException extends Exception {

	public DBException(String string) {
		super(string);
	}

	public DBException(String string, Exception e) {
		super(string,e);
	}

}
