package main.java.se.anabelandrola.saleProcess.model;

/**
 * Represents one specific discount.
 */
public class Discount {

	private DiscountStrategy discountStrategy;

	/**
	 * Creates a new instance.
	 *
	 * @param discountStrategy
	 */
	public Discount(DiscountStrategy discountStrategy) {
		this.discountStrategy = discountStrategy;
	}

	/**
	 * Execute discount
	 *
	 * @param amountTotalWithVAT The amount that is apply for discount.
	 * @param sumPercentDiscount The percent of discount
	 * @return amount discount
	 */
	public double executeDiscountStrategy(double amountTotalWithVAT, double sumPercentDiscount) {
		return discountStrategy.calculate(amountTotalWithVAT, sumPercentDiscount);
	}

}
