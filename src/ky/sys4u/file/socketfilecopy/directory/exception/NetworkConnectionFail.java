package ky.sys4u.file.socketfilecopy.directory.exception;

public class NetworkConnectionFail extends RuntimeException {

	private static final long serialVersionUID = -6712812299196387519L;

	public NetworkConnectionFail() {
		super();
	}

	public NetworkConnectionFail(String message, Throwable cause) {
		super(message, cause);
	}

	public NetworkConnectionFail(String message) {
		super(message);
	}

	public NetworkConnectionFail(Throwable cause) {
		super(cause);
	}
}
