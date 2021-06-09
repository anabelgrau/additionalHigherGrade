package main.java.se.anabelandrola.saleProcess.model;

/**
 * A abstract class for observer the total revenue.
 */

public abstract class AbstractTotalRevenueObserver implements TotalRevenueObserver {

	private double totalRevenue;

	/**
	 * Creates a new instance, with the amount paid set to zero.
	 */
	protected AbstractTotalRevenueObserver() {
		totalRevenue = 0;
	}

	/**
	 * This is the method defined in the observer interface
	 */
	public void addTotalRevenue(double amountPaid) {
		totalRevenue += amountPaid;
		showTotalIncome();
	}

	private void showTotalIncome() {
		try {
			printPresentAmountPaid();
		} catch (Exception e) {
			handleErrors(e);
		}
	}

	/**
	 * Prints The total amount paid for the sale.
	 */
	protected abstract void printPresentAmountPaid() throws Exception;

	/**
	 * Add a log to the logfile or print a message to user if an exception occurs
	 */
	protected abstract void handleErrors(Exception e);

	/**
	 * This is the method return totalRevenue
	 * 
	 * @return totalRevenue (amount paid)
	 */
	public double getTotalRevenue() {
		return totalRevenue;
	}
}
