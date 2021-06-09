package main.java.se.anabelandrola.saleProcess.integration;

/**
 * Contains information about one particular discount rule.
 */
public class DiscountRuleDTO {
	private double percentDiscount;
	private int typeMember;
	private int quantityItem;
	private double totalAmount;
	private String description;

	/**
	 * Creates a new instance representing a particular discount rule.
	 *
	 * @param percentDiscount The percent of the discount.
	 * @param typeMember      Type of discount e.g <code>1</code> if the customer is
	 *                        member plus, <code>2</code> if the customer is member
	 * @param quantityItem    The item's quantity to do discount.
	 * @param totalAmount     The total amount to do discount.
	 * @param description     The description of the item.
	 */
	public DiscountRuleDTO(double percentDiscount, int typeMember, int quantityItem, double totalAmount,
			String description) {
		this.percentDiscount = percentDiscount;
		this.typeMember = typeMember;
		this.quantityItem = quantityItem;
		this.totalAmount = totalAmount;
		this.description = description;
	}

	/**
	 * Get the value of percentDiscount
	 *
	 * @return the value of percentDiscount
	 */
	public double getPercentDiscount() {
		return percentDiscount;
	}

	/**
	 * Get the value of typeMember
	 *
	 * @return the value of typeMember
	 */
	public int getTypeMember() {
		return typeMember;
	}

	/**
	 * Get the value of quantityItem
	 *
	 * @return the value of quantityItem
	 */
	public int getQuantityItem() {
		return quantityItem;
	}

	/**
	 * Get the value of totalAmount
	 *
	 * @return the value of totalAmount
	 */
	public double getTotalAmount() {
		return totalAmount;
	}

	/**
	 * Get the value of description
	 *
	 * @return the value of description
	 */
	public String getdescription() {
		return description;
	}

}
