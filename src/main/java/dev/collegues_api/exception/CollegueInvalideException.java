package dev.collegues_api.exception;

public class CollegueInvalideException extends RuntimeException {

	/**
	 * Constructor
	 * 
	 */
	public CollegueInvalideException() {
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
	public CollegueInvalideException(String message, Throwable cause, boolean enableSuppression,
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
	public CollegueInvalideException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 */
	public CollegueInvalideException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor
	 * 
	 * @param cause
	 */
	public CollegueInvalideException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
