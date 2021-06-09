package main.java.se.anabelandrola.saleProcess.integration;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import main.java.se.anabelandrola.saleProcess.model.AbstractTotalRevenueObserver;
import main.java.se.anabelandrola.saleProcess.model.TotalRevenueObserver;
import main.java.se.anabelandrola.saleProcess.util.LogOfErrorInSaleProcess;

/**
 * Shows amount paid for the sale in a file.
 */

public class TotalRevenueFileOutput extends AbstractTotalRevenueObserver {
	private static final String LOG_FILE_NAME = "C://Users/Anabel Grau/eclipse-workspace/seminarium3/log/totalRevenueFile.txt";
	private PrintWriter logFile;
	private LogOfErrorInSaleProcess log;

	/**
	 * Creates a log file.
	 */
	public TotalRevenueFileOutput() throws IOException {
		logFile = new PrintWriter(new FileWriter(LOG_FILE_NAME), true);
	}

	/**
	 * Add total revenue and print i file.
	 */
	@Override
	protected void printPresentAmountPaid() {
		DecimalFormat format = new DecimalFormat("#.##");
		StringBuilder logMessageBuilder = new StringBuilder();
		logMessageBuilder.append("Total revenue is:");
		logMessageBuilder.append(format.format(this.getTotalRevenue()));
		logFile.println(logMessageBuilder);
	}

	/**
	 * Add a log to the logfile if an exception occurs
	 */
	@Override
	protected void handleErrors(Exception e) {
		log.logException(e);
	}

}
