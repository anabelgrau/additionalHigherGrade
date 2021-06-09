package main.java.se.anabelandrola.saleProcess.model;

/**
 * A listener interface for receiving notifications about the total revenue.
 */
public interface TotalRevenueObserver {
	/**
	 * Invoked when a sale has been paid.
	 *
	 * @param amountPaid The total amount paid for the sale.
	 */
	void addTotalRevenue(double amountPaid);

}
