package ky.sys4u.file.exception;

public class CanNotReadWriteFileException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CanNotReadWriteFileException() {
		super();
	}

	public CanNotReadWriteFileException(String message, Throwable cause) {
		super(message, cause);
	}

	public CanNotReadWriteFileException(String message) {
		super(message);
	}

	public CanNotReadWriteFileException(Throwable cause) {
		super(cause);
	}
	
	
}
