package main.java.se.anabelandrola.saleProcess.model;

/**
 * This class calculate discount for the sale to member.
 */

public class MemberPlusDiscountStrategy implements DiscountStrategy {
	private double amount;
	private final double member = 5;

	/**
	 * Calculate discount for the sale to member plus.
	 */
	@Override
	public double calculate(double amountTotalWithVAT, double sumPercentDiscount) {
		amount = (amountTotalWithVAT * (sumPercentDiscount + member)) / 100;
		return amount;
	}

}
