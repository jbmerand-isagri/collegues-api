package dev.collegues_api.exception;

public class CollegueNonTrouveException extends RuntimeException {

	/**
	 * Constructor
	 * 
	 */
	public CollegueNonTrouveException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public CollegueNonTrouveException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 * @param cause
	 */
	public CollegueNonTrouveException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 */
	public CollegueNonTrouveException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor
	 * 
	 * @param cause
	 */
	public CollegueNonTrouveException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
