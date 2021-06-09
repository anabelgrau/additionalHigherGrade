package main.java.se.anabelandrola.saleProcess.integration;

/**
 * Thrown when trying to call database.
 */
public class DatabaseIsNotBeCalledException extends Exception {
	/**
	 * Creates a new instance with a message specifying and cause
	 * 
	 * @param msg   The message about database can not call
	 * @param cause of generate exception
	 */
	public DatabaseIsNotBeCalledException(String msg, Exception cause) {
		super(msg, cause);

	}
}
