package main.java.se.anabelandrola.saleProcess.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.time.LocalDate;

import main.java.se.anabelandrola.saleProcess.integration.CustomerDTO;
import main.java.se.anabelandrola.saleProcess.integration.DiscountRuleDTO;
import main.java.se.anabelandrola.saleProcess.integration.ItemDTO;
import main.java.se.anabelandrola.saleProcess.integration.Printer;

/**
 * Represents one specific sale.
 */
public class Sale {
	private SaleDTO sale;
	private ItemList itemList;
	private Payment pay;
	private List<TotalRevenueObserver> totalRevenueObserver = new ArrayList<>();

	/**
	 * Creates a new instance of a sale
	 */
	public Sale() {
		this.itemList = new ItemList();
	}

	/**
	 * Register all the items for the actual sale.
	 *
	 * @param foundItem The item that will be registered.
	 * @return the item has been registered
	 */
	public ItemListDTO registerItem(ItemDTO foundItem) {
		boolean existItem = itemList.searchItemList(foundItem);
		if (!existItem)
			itemList.saveItem(foundItem);
		else
			itemList.increaseQuantity(foundItem);
		return itemList.getItemList(foundItem);
	}

	/**
	 * Saves the sale without discount.
	 * 
	 * @return the sale
	 */
	public SaleDTO saveSale() {
		String date = this.getDate();
		String time = this.getTime();
		List<ItemListDTO> listItem = itemList.getItemList();
		double amountSale = 0;
		double amountVAT = 0;
		double amountDiscount = 0;
		double amountTotalWithVAT = 0;
		for (ItemListDTO item : listItem) {
			amountSale += item.getTotalAmountWithoutVAT();
			amountVAT += item.getTotalVAT();
			amountTotalWithVAT += item.getTotalAmountWithVAT();
		}
		this.sale = new SaleDTO(date, time, amountSale, amountVAT, amountDiscount, amountTotalWithVAT, listItem);
		return sale;
	}

	/**
	 * Saves the sale with discount.
	 * 
	 * @param customer The customer information
	 * @param discount The discount rules
	 * @return the sale
	 */
	public SaleDTO saveSaleWithDiscount(CustomerDTO customer, List<DiscountRuleDTO> discount) {
		String date = this.getDate();
		String time = this.getTime();
		List<ItemListDTO> listItem = itemList.getItemList();
		double amountSale = 0;
		double amountVAT = 0;
		double amountDiscount = 0;
		double amountTotalWithVAT = 0;
		for (ItemListDTO item : listItem) {
			amountSale += item.getTotalAmountWithoutVAT();
			amountVAT += item.getTotalVAT();
			amountTotalWithVAT += item.getTotalAmountWithVAT();
		}
		if (customer.getTypeCustomer() == 1)
			amountDiscount = doDiscountMemberPlus(amountTotalWithVAT, discount);
		if (customer.getTypeCustomer() == 2)
			amountDiscount = doDiscountMember(amountTotalWithVAT, discount);

		this.sale = new SaleDTO(date, time, amountSale, amountVAT, amountDiscount, amountTotalWithVAT, listItem);
		return sale;
	}

	private String getDate() {
		LocalDate dateLD = LocalDate.now();
		String date = dateLD.toString();
		return date;
	}

	private String getTime() {
		Calendar c = Calendar.getInstance();
		String time = (Integer.toString(c.get(Calendar.HOUR_OF_DAY))) + ":" + (Integer.toString(c.get(Calendar.MINUTE)))
				+ ":" + (Integer.toString(c.get(Calendar.SECOND)));
		return time;
	}

	private double doDiscountMemberPlus(double amountTotalWithVAT, List<DiscountRuleDTO> discounts) {
		double sumPercentDiscount = 0;
		double amount = 0;
		for (DiscountRuleDTO disc : discounts) {
			if ((disc.getTypeMember() == 1) && (itemList.getItemList().size() > disc.getQuantityItem()))
				sumPercentDiscount += disc.getPercentDiscount();
			if ((disc.getTypeMember() == 1) && (amountTotalWithVAT > disc.getTotalAmount()))
				sumPercentDiscount += disc.getPercentDiscount();
		}
		Discount discount = new Discount(new MemberPlusDiscountStrategy());
		amount = discount.executeDiscountStrategy(amountTotalWithVAT, sumPercentDiscount);

		return amount;
	}

	private double doDiscountMember(double amountTotalWithVAT, List<DiscountRuleDTO> discounts) {
		double sumPercentDiscount = 0;
		double amount = 0;

		for (DiscountRuleDTO disc : discounts) {
			if ((disc.getTypeMember() == 2) && (itemList.getItemList().size() > disc.getQuantityItem()))
				sumPercentDiscount += disc.getPercentDiscount();
			if ((disc.getTypeMember() == 2) && (amountTotalWithVAT > disc.getTotalAmount()))
				sumPercentDiscount += disc.getPercentDiscount();
		}
		Discount discount = new Discount(new MemberDiscountStrategy());
		amount = discount.executeDiscountStrategy(amountTotalWithVAT, sumPercentDiscount);

		return amount;
	}

	/**
	 * This sale is paid using the specified payment.
	 * 
	 * @param pay The payment used to pay this sale.
	 */
	public void pay(Payment pay) throws IllegalArgumentException {
		pay.calculateTotalAmount(sale);
		if (pay.getPaidAmount() < pay.getTotalCost())
			throw new IllegalArgumentException("The amount paid is less than the total cost of the sale");
		this.pay = pay;

	}

	/**
	 * This returns payment.
	 * 
	 * @return pay the value of pay
	 */
	public Payment getPayment() {
		return pay;
	}

	/**
	 * This returns sale.
	 * 
	 * @return the value of sale
	 */
	public SaleDTO getSale() {
		return sale;
	}

	/**
	 * Prints a receipt for the current sale on the specified printer.
	 * 
	 * @param printer The printer where the receipt is printed.
	 */
	public void printReceipt(Printer printer) {
		Receipt receipt = new Receipt(this);
		printer.printReceipt(receipt);
		notifyObservers();
	}

	private void notifyObservers() {
		for (TotalRevenueObserver obs : totalRevenueObserver) {
			obs.addTotalRevenue(pay.getTotalCost());
		}
	}

	/**
	 * The specified observer will be notified when this sale has been paid.
	 * 
	 * @param obs The observer to notify.
	 */
	public void addTotalRevenueObserver(TotalRevenueObserver obs) {
		totalRevenueObserver.add(obs);
	}

	/**
	 * All the specified observers will be notified when this sale has been paid.
	 * 
	 * @param observers The observers to notify.
	 */
	public void addTotalRevenueObservers(List<TotalRevenueObserver> observers) {
		totalRevenueObserver.addAll(observers);
	}

}
