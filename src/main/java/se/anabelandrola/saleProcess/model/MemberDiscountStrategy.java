package main.java.se.anabelandrola.saleProcess.model;

/**
 * This class calculate discount for the sale to member plus.
 */
public class MemberDiscountStrategy implements DiscountStrategy {
	private double amount;

	/**
	 * Calculate discount for the sale to member plus.
	 */
	@Override
	public double calculate(double amountTotalWithVAT, double sumPercentDiscount) {
		amount = ((amountTotalWithVAT * sumPercentDiscount) / 100);
		return amount;
	}

}
