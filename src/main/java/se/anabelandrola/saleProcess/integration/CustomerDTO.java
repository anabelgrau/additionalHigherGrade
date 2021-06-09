package main.java.se.anabelandrola.saleProcess.integration;

/**
 * Contains information about one particular customer.
 */
public final class CustomerDTO {
	private final String personalCode;
	private final String name;
	private final String lastName;
	private final String address;
	private final int typeCustomer;

	/**
	 * Creates a new instance representing a particular customer.
	 *
	 * @param personalCode The customers personal code.
	 * @param name         The name of the customer.
	 * @param lastName     The lastname of the customer.
	 * @param address      The customer's address.
	 * @param typeCustomer The Customer type e.g <code>1</code> if the customer is
	 *                     member plus, <code>2</code> if the customer is member.
	 */

	public CustomerDTO(String personalCode, String name, String lastName, String address, int typeCustomer) {
		this.personalCode = personalCode;
		this.name = name;
		this.lastName = lastName;
		this.address = address;
		this.typeCustomer = typeCustomer;
	}

	/**
	 * Represent CustomerDTO as a string
	 *
	 * @return CustomerDTO as a string
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("personalCode: " + personalCode + ", ");
		builder.append("name: " + name + ", ");
		builder.append("lastName: " + lastName + ", ");
		builder.append("address: " + address + ", ");
		builder.append("type: " + typeCustomer + ", ");
		return builder.toString();
	}

	/**
	 * Two <code>CustomerDTO</code> instances are equal if all fields are equal.
	 *
	 * @param otherObjectCustomer The <code>CustomerDTO</code> to compare with this
	 *                            <code>CustomerDTO</code>.
	 * @return <code>true</code> if all fields in the specified
	 *         <code>CustomerDTO</code> are equal to corresponding fields in this
	 *         <code>CustomerDTO</code>, <code>false</code> if they are not.
	 */
	@Override
	public boolean equals(Object otherObjectCustomer) {
		if (otherObjectCustomer == null) {
			return false;
		}
		if (getClass() != otherObjectCustomer.getClass()) {
			return false;
		}
		final CustomerDTO other = (CustomerDTO) otherObjectCustomer;
		if (personalCode.equals(other.personalCode)) {
			return false;
		}
		if (!name.equals(other.name)) {
			return false;
		}
		if (!lastName.equals(other.lastName)) {
			return false;
		}
		if (!address.equals(other.address)) {
			return false;
		}
		if (typeCustomer != other.typeCustomer) {
			return false;
		}
		return true;
	}

	/**
	 * Get the value of personalCode
	 *
	 * @return the value of personalCode
	 */
	public String getPersonalCode() {
		return personalCode;
	}

	/**
	 * Get the value of name
	 *
	 * @return the value of name
	 */
	public String getname() {
		return name;
	}

	/**
	 * Get the value of lastName
	 *
	 * @return the value of lastName
	 */
	public String getlastName() {
		return lastName;
	}

	/**
	 * Get the value of address
	 *
	 * @return the value of address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Get the value of type of customer
	 *
	 * @return the value of type of customer
	 */
	public int getTypeCustomer() {
		return typeCustomer;
	}
}
