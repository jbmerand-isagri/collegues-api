package dev.collegues_api.exception;

/**
 * Classe de l'exception CollegueInvalideException
 */
public class CollegueInvalideException extends RuntimeException {

	/**
	 * Constructor
	 * 
	 */
	public CollegueInvalideException() {
		super();
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
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 * @param cause
	 */
	public CollegueInvalideException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 */
	public CollegueInvalideException(String message) {
		super(message);
	}

	/**
	 * Constructor
	 * 
	 * @param cause
	 */
	public CollegueInvalideException(Throwable cause) {
		super(cause);
	}

}
