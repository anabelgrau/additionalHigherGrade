package main.java.se.anabelandrola.saleProcess.model;

/**
 * The strategy interface declares operations common to calculate discount
 *
 */

public interface DiscountStrategy {
	/**
	 * Invoked when will calculate discount
	 * 
	 * @param amountTotalWithVAT The amount that is apply for discount.
	 * @param sumPercentDiscount The percent of discount
	 * @return amount discount
	 */
	public double calculate(double amountTotalWithVAT, double sumPercentDiscount);

}
