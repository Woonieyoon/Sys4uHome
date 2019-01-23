package ky.sys4u.file.exception;

public class ConversionException extends RuntimeException {
	private static final long serialVersionUID = -3877659211243347185L;

	public ConversionException() {
		super();
	}

	public ConversionException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConversionException(String message) {
		super(message);
	}

	public ConversionException(Throwable cause) {
		super(cause);
	}
}
