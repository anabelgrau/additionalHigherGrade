package main.java.se.anabelandrola.saleProcess.integration;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains information about the Customer Register.
 */
public class CustomerRegister {
	private List<CustomerData> customers = new ArrayList<>();

	CustomerRegister() {
		addCustomers();
	}

	private void addCustomers() {
		customers.add(new CustomerData("8406183765", "David", "Andersson", "Oslogatn21", 1));
		customers.add(new CustomerData("5311076248", "Anna", "Rodriguez", "Fyrspanngatan 39", 2));
		customers.add(new CustomerData("0007102869", "Jakob", "Smith", "Norgegatan 26", 1));
	}

	/**
	 * Search for a Customer in the CustomerRegister.
	 *
	 * @param personalCode This contains identifier of the customer.
	 * @return Customer if the customer is in the CustomerRegister or null if the
	 *         customer is not in CustomerRegister
	 */

	public CustomerDTO searchCustomer(String personalCode) {
		for (CustomerData customer : customers) {
			if (customer.personalCode.equals(personalCode)) {
				return new CustomerDTO(customer.personalCode, customer.name, customer.lastName, customer.address,
						customer.typeCustomer);
			}
		}
		return null;
	}

	private static class CustomerData {
		private String personalCode;
		private String name;
		private String lastName;
		private String address;
		private int typeCustomer;

		public CustomerData(String personalCode, String name, String lastName, String address, int typeCustomer) {
			this.personalCode = personalCode;
			this.name = name;
			this.lastName = lastName;
			this.address = address;
			this.typeCustomer = typeCustomer;
		}
	}
}
