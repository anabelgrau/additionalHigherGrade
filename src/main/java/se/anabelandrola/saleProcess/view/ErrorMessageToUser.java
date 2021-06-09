package main.java.se.anabelandrola.saleProcess.view;

import java.time.LocalDate;
import java.util.Calendar;

/**
 * This class is responsible for showing error messages to the user.
 */

public class ErrorMessageToUser {
	/**
	 * Show the specified error message.
	 * 
	 * @param message The error message.
	 */
	void displayErrorMessage(String message) {
		StringBuilder errorMessageBuilder = new StringBuilder();
		errorMessageBuilder.append("Date and Time: ");
		errorMessageBuilder.append(getDateTime());
		errorMessageBuilder.append(", Error is: ");
		errorMessageBuilder.append(message);
		System.out.println(errorMessageBuilder);
	}

	private String getDateTime() {
		LocalDate dateLD = LocalDate.now();
		String date = dateLD.toString();
		Calendar c = Calendar.getInstance();
		String time = (Integer.toString(c.get(Calendar.HOUR_OF_DAY))) + ":" + (Integer.toString(c.get(Calendar.MINUTE)))
				+ ":" + (Integer.toString(c.get(Calendar.SECOND)));
		String dateTime = date + " " + time;
		return dateTime;
	}
}
