package ky.sys4u.file.exception;

public class NoSuchConverterException extends RuntimeException {
	private static final long serialVersionUID = -3877659211243347185L;

	public NoSuchConverterException() {
		super();
	}

	public NoSuchConverterException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoSuchConverterException(String message) {
		super(message);
	}

	public NoSuchConverterException(Throwable cause) {
		super(cause);
	}
}
