package main.java.se.anabelandrola.saleProcess.controller;

import main.java.se.anabelandrola.saleProcess.integration.ItemRegistry;

import java.util.ArrayList;
import java.util.List;

import main.java.se.anabelandrola.saleProcess.integration.CustomerDTO;
import main.java.se.anabelandrola.saleProcess.integration.CustomerRegister;
import main.java.se.anabelandrola.saleProcess.integration.DatabaseIsNotBeCalledException;
import main.java.se.anabelandrola.saleProcess.integration.DiscountRuleDTO;
import main.java.se.anabelandrola.saleProcess.integration.DiscountRuleRegister;
import main.java.se.anabelandrola.saleProcess.integration.RegisterCreator;
import main.java.se.anabelandrola.saleProcess.integration.SaleRegister;
import main.java.se.anabelandrola.saleProcess.integration.ItemDTO;
import main.java.se.anabelandrola.saleProcess.integration.ItemIsNotFoundException;
import main.java.se.anabelandrola.saleProcess.integration.Printer;
import main.java.se.anabelandrola.saleProcess.model.CashRegister;
import main.java.se.anabelandrola.saleProcess.model.ItemListDTO;
import main.java.se.anabelandrola.saleProcess.model.Sale;
import main.java.se.anabelandrola.saleProcess.model.SaleDTO;
import main.java.se.anabelandrola.saleProcess.model.TotalRevenueObserver;
import main.java.se.anabelandrola.saleProcess.model.Payment;

public class Controller {

	private ItemRegistry itemRegistry;
	private SaleRegister saleRegister;
	private CustomerRegister customerRegister;
	private DiscountRuleRegister discountRuleRegister;
	private Printer printer;
	private Sale sale;
	private CashRegister cashRegister;
	private List<TotalRevenueObserver> totalRevenueObserver = new ArrayList<>();

	/**
	 * Creates a new instance.
	 *
	 * @param registerCreator Used to get classes that handle database calls.
	 * @param printer         Interface to printer.
	 */
	public Controller(RegisterCreator registerCreator, Printer printer) {
		this.itemRegistry = registerCreator.getSaleRegister().getItemRegistry();
		this.saleRegister = registerCreator.getSaleRegister();
		this.customerRegister = registerCreator.getCustomerRegister();
		this.printer = printer;
		this.cashRegister = new CashRegister();
		this.discountRuleRegister = registerCreator.getDiscountRuleRegister();

	}

	/**
	 * Start a new sale.
	 */
	public void startSale() {
		this.sale = new Sale();
	}

	/**
	 * Registers an item in the list of items for sale.
	 *
	 * @param idItem This contains the identifier of the item.
	 * @return The item's information
	 * @throws ItemIsNotFoundException.        The exception when item is not
	 *                                         founded.
	 * @throws DatabaseIsNotBeCalledException. The exception when there is problem
	 *                                         with database.
	 */
	public ItemListDTO registerItem(int idItem) throws ItemIsNotFoundException, DatabaseIsNotBeCalledException {
		ItemDTO foundItem = itemRegistry.searchItemInventory(idItem);
		ItemListDTO item = sale.registerItem(foundItem);
		return item;
	}

	/**
	 * Generates a sale.
	 * 
	 * @return The sale's information
	 */
	public SaleDTO generateSale(String personalCode) {
		SaleDTO saleGen;
		CustomerDTO foundCustomer = customerRegister.searchCustomer(personalCode);
		if (foundCustomer == null)
			saleGen = sale.saveSale();
		else {
			List<DiscountRuleDTO> discount = discountRuleRegister.getDiscountRules();
			saleGen = sale.saveSaleWithDiscount(foundCustomer, discount);
		}
		return saleGen;
	}

	/**
	 * Handles sale payment. Calculates change. Updates the sale of the sale
	 * register and updates the external system item registry and the accounting
	 * registry. Prints the receipt.
	 *
	 * @param paidAmount The paid amount.
	 */
	public void pay(double paidAmount) {
		sale.addTotalRevenueObservers(totalRevenueObserver);
		Payment pay = new Payment(paidAmount);
		sale.pay(pay);
		cashRegister.increasesAmount(pay);
		SaleDTO saleD = sale.getSale();
		saleRegister.registerSale(saleD);
		sale.printReceipt(printer);
	}

	/**
	 * Get the value of amount in CashRegister
	 *
	 * @return the value of amount in CashRegister
	 */
	public double getAmountCashRegister() {
		return cashRegister.getAmount();
	}

	/**
	 * The specified observer will be notified when a sale has been paid.
	 * 
	 * @param obs The observer to notify.
	 */
	public void addTotalRevenueObserver(TotalRevenueObserver obs) {
		totalRevenueObserver.add(obs);
	}

}
