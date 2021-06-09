package main.java.se.anabelandrola.saleProcess.view;

import java.io.IOException;
import java.text.DecimalFormat;

import main.java.se.anabelandrola.saleProcess.controller.Controller;
import main.java.se.anabelandrola.saleProcess.integration.DatabaseIsNotBeCalledException;
import main.java.se.anabelandrola.saleProcess.integration.ItemIsNotFoundException;
import main.java.se.anabelandrola.saleProcess.model.ItemListDTO;
import main.java.se.anabelandrola.saleProcess.model.SaleDTO;
import main.java.se.anabelandrola.saleProcess.util.LogOfErrorInSaleProcess;

public class View {

	private Controller control;
	private ErrorMessageToUser errorMessage = new ErrorMessageToUser();
	private LogOfErrorInSaleProcess log;

	/**
	 * Creates a new instance.
	 *
	 * @param control The controller that is used for all the operations.
	 * @throws IOException
	 * 
	 */
	public View(Controller control) throws IOException {
		this.control = control;
		this.control.addTotalRevenueObserver(new TotalReveneuView());
		this.log = new LogOfErrorInSaleProcess();
	}

	/**
	 * Stimulates a user's input that generates calls to all the system operations.
	 */
	public void sampleProcessSaleExecution() {
		try {
			DecimalFormat format = new DecimalFormat("#.##");

			control.startSale();
			System.out.println("A new sale has been started.");

			int isIdItem = 43560294;
			int isIdItem2 = 57306946;
			int isNotIdItem = 63514810;
			int isIdItemDatabase = 57306978;
			ItemListDTO item = null;
			try {
				item = control.registerItem(isNotIdItem);
			} catch (ItemIsNotFoundException e) {
				String msg = "Item with id: " + isNotIdItem + " is not valid. Please try igen.";
				errorMessage.displayErrorMessage(msg);
			} catch (DatabaseIsNotBeCalledException e) {
				String msg = "Item with id:" + isNotIdItem + " could not be found. Please try igen.";
				this.sendToLogMessage(msg, e);
			}
			try {
				item = control.registerItem(isIdItemDatabase);
			} catch (ItemIsNotFoundException e) {
				String msg = "Item with id: " + isIdItemDatabase + " is not valid. Please try igen.";
				errorMessage.displayErrorMessage(msg);
			} catch (DatabaseIsNotBeCalledException e) {
				String msg = "Item with id: " + isIdItemDatabase + " could not be found. Please try igen.";
				this.sendToLogMessage(msg, e);
			}
			item = control.registerItem(isIdItem);
			System.out.println("Item's description: " + item.getDescription() + " price: "
					+ item.getTotalAmountWithoutVAT() + " Total price(incl. VAT): " + item.getTotalAmountWithVAT());
			item = control.registerItem(isIdItem2);
			System.out.println("Item's description: " + item.getDescription() + " price: "
					+ item.getTotalAmountWithoutVAT() + " Total price(incl. VAT): " + item.getTotalAmountWithVAT());
			item = control.registerItem(isIdItem);
			System.out.println("Item's description: " + item.getDescription() + " price: "
					+ item.getTotalAmountWithoutVAT() + " Total price(incl. VAT): " + item.getTotalAmountWithVAT());
			String idCustomer = "8406183765";
			SaleDTO sale = control.generateSale(idCustomer);
			System.out.println("The sale is finished.");
			System.out.println("Total price of the sale is: " + format.format(sale.getAmountTotalWithVAT()));

			double paidAmount = 64;
			control.pay(paidAmount);

			control.startSale();
			System.out.println("A new sale has been started.");

			item = control.registerItem(isIdItem2);
			System.out.println("Item's description: " + item.getDescription() + " price: "
					+ item.getTotalAmountWithoutVAT() + " Total price(incl. VAT): " + item.getTotalAmountWithVAT());
			idCustomer = "5311076248";
			sale = control.generateSale(idCustomer);
			System.out.println("The sale is finished.");
			System.out.println("Total price of the sale is: " + format.format(sale.getAmountTotalWithVAT()));

			paidAmount = 60;
			control.pay(paidAmount);

		} catch (ItemIsNotFoundException e) {
			String msg = "Item is not valid. Please try igen..";
			errorMessage.displayErrorMessage(msg);
		} catch (DatabaseIsNotBeCalledException e) {
			String msg = "Operation is not done, please try again";
			this.sendToLogMessage(msg, e);
		} catch (Exception e) {
			String msg = "Faild sale, please try again";
			this.sendToLogMessage(msg, e);
		}
	}

	private void sendToLogMessage(String message, Exception e) {
		errorMessage.displayErrorMessage(message);
		log.logException(e);
	}
}
