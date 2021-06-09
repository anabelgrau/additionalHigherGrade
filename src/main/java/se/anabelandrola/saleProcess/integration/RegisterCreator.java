package main.java.se.anabelandrola.saleProcess.integration;

/**
 * This class is responsible for instantiating registers.
 */
public class RegisterCreator {

	private SaleRegister saleRegister = new SaleRegister();
	private CustomerRegister customerRegister = new CustomerRegister();
	private DiscountRuleRegister discountRuleRegister = new DiscountRuleRegister();

	/**
	 * Get the value of SaleRegister
	 *
	 * @return the value of SaleRegister
	 */
	public SaleRegister getSaleRegister() {
		return saleRegister;
	}

	/**
	 * Get the value of CustomerRegister
	 *
	 * @return the value of CustomerRegister
	 */
	public CustomerRegister getCustomerRegister() {
		return customerRegister;
	}

	/**
	 * Get the value of DiscountRuleRegister
	 *
	 * @return the value of DiscountRuleRegister
	 */
	public DiscountRuleRegister getDiscountRuleRegister() {
		return discountRuleRegister;
	}
}
