package main.java.se.anabelandrola.saleProcess.view;

import java.text.DecimalFormat;

import main.java.se.anabelandrola.saleProcess.model.AbstractTotalRevenueObserver;
import main.java.se.anabelandrola.saleProcess.util.LogOfErrorInSaleProcess;

/**
 * Shows amount paid for the sale.
 */
public class TotalReveneuView extends AbstractTotalRevenueObserver {
	private ErrorMessageToUser errorMessage = new ErrorMessageToUser();
	private LogOfErrorInSaleProcess log;

	/**
	 * Prints The total amount paid for the sale.
	 */
	@Override
	protected void printPresentAmountPaid() {
		DecimalFormat format = new DecimalFormat("#.##");
		System.out.println("Total reveneu is: " + format.format(this.getTotalRevenue()));
	}

	@Override
	protected void handleErrors(Exception e) {
		String msg = "There is a problem with print Total Revenue on console, please try again";
		errorMessage.displayErrorMessage(msg);
		log.logException(e);
	}
}
