package hyper.momitor.exception;

public class HMException extends Exception{
	private static final long serialVersionUID = -5194657821641636776L;

	/**
	 * @param message
	 * @param cause
	 */
	public HMException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public HMException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public HMException(Throwable cause) {
		super(cause);
	}
}
