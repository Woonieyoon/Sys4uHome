package ky.sys4u.file.socketfilecopy.directory.exception;

public class NetworkInputFail extends RuntimeException {

	private static final long serialVersionUID = -5278787896262487047L;

	public NetworkInputFail() {
		super();
	}

	public NetworkInputFail(String message, Throwable cause) {
		super(message, cause);
	}

	public NetworkInputFail(String message) {
		super(message);
	}

	public NetworkInputFail(Throwable cause) {
		super(cause);
	}

}
