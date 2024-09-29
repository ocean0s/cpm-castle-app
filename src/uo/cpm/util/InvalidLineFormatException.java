package uo.cpm.util;

public class InvalidLineFormatException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidLineFormatException() {
	}

	public InvalidLineFormatException(String message) {
		super(message);
	}

	public InvalidLineFormatException(Throwable cause) {
		super(cause);
	}

	public InvalidLineFormatException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidLineFormatException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
