package main.java.se.anabelandrola.saleProcess.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Calendar;

/**
 * This class is responsible for the log.
 */

public class LogOfErrorInSaleProcess {
	private static final String LOG_FILE_NAME = "C://Users/Anabel Grau/eclipse-workspace/seminarium3/log/saleProcess-logRepport.txt";
	private PrintWriter logFile;

	public LogOfErrorInSaleProcess() throws IOException {
		logFile = new PrintWriter(new FileWriter(LOG_FILE_NAME), true);
	}

	/**
	 * Writes a log with information about the thrown exception.
	 * 
	 * @param exception The exception that will be in the log file.
	 */
	public void logException(Exception e) {
		StringBuilder logMessageBuilder = new StringBuilder();
		logMessageBuilder.append(getDateTime());
		logMessageBuilder.append(", Exception was caught: ");
		logMessageBuilder.append(e.getMessage());
		logFile.println(logMessageBuilder);
		e.printStackTrace(logFile);
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
