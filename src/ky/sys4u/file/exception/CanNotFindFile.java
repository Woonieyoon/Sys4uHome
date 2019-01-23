package ky.sys4u.file.exception;

public class CanNotFindFile extends RuntimeException{

	private static final long serialVersionUID = -2118287137474274467L;

	public CanNotFindFile() {
		super();
	}

	public CanNotFindFile(String message, Throwable cause) {
		super(message, cause);
	}

	public CanNotFindFile(String message) {
		super(message);
	}

	public CanNotFindFile(Throwable cause) {
		super(cause);
	}
	

}
