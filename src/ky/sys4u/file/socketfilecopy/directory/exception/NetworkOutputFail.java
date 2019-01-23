package ky.sys4u.file.socketfilecopy.directory.exception;

public class NetworkOutputFail extends NetworkConnectionFail {

	private static final long serialVersionUID = -1044466642676556238L;

	public NetworkOutputFail() {
		super();
	}

	public NetworkOutputFail(String message, Throwable cause) {
		super(message, cause);
	}

	public NetworkOutputFail(String message) {
		super(message);
	}

	public NetworkOutputFail(Throwable cause) {
		super(cause);
	}
}
