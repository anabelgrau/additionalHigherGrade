package main.java.se.anabelandrola.saleProcess.integration;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains information about the discount rules.
 */
public class DiscountRuleRegister {

	private List<DiscountRuleData> discountRule = new ArrayList<>();

	DiscountRuleRegister() {
		addDiscountRules();
	}

	private void addDiscountRules() {
		discountRule.add(new DiscountRuleData(3, 1, 1, 9999999, "Member plus"));
		discountRule.add(new DiscountRuleData(1, 2, 1, 9999999, "Member"));
		discountRule.add(new DiscountRuleData(4, 1, 555555555, 40, "Member plus"));
		discountRule.add(new DiscountRuleData(2, 2, 555555555, 40, "Member"));
	}

	/**
	 * Get the value of discount rules
	 *
	 * @return the value of discount rules
	 */
	public List<DiscountRuleDTO> getDiscountRules() {
		List<DiscountRuleDTO> discountRuleList = new ArrayList<>();
		for (DiscountRuleData discount : discountRule)
			discountRuleList.add(new DiscountRuleDTO(discount.percentDiscount, discount.typeMember,
					discount.quantityItem, discount.totalAmount, discount.description));
		return discountRuleList;
	}

	private static class DiscountRuleData {
		private double percentDiscount;
		private int typeMember;
		private int quantityItem;
		private double totalAmount;
		private String description;

		public DiscountRuleData(double percentDiscount, int typeMember, int quantityItem, double totalAmount,
				String description) {
			this.percentDiscount = percentDiscount;
			this.typeMember = typeMember;
			this.quantityItem = quantityItem;
			this.totalAmount = totalAmount;
			this.description = description;
		}
	}
}
