package main.java.se.anabelandrola.saleProcess.model;

import static org.junit.jupiter.api.Assertions.*;

import java.text.DecimalFormat;
import java.time.LocalDate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.se.anabelandrola.saleProcess.integration.DatabaseIsNotBeCalledException;
import main.java.se.anabelandrola.saleProcess.integration.ItemDTO;
import main.java.se.anabelandrola.saleProcess.integration.ItemIsNotFoundException;
import main.java.se.anabelandrola.saleProcess.integration.Printer;

class ReceiptTest {
	private Receipt receipt;
	private Sale sale;

	@BeforeEach
	void setUp() throws Exception {
		sale = new Sale();

	}

	@AfterEach
	void tearDown() throws Exception {
		sale = null;
	}

	@Test
	void testCreateReceiptSuccessfully() {
		DecimalFormat format = new DecimalFormat("#.##");
		ItemDTO item = new ItemDTO(63514896, "Yoghurt", 12, 6, 3);
		sale.registerItem(item);
		sale.saveSale();
		double paidAmount = 40;
		Payment pay = new Payment(paidAmount);
		sale.pay(pay);
		Printer printer = new Printer();
		sale.printReceipt(printer);
		receipt = new Receipt(sale);
		String result = receipt.createReceipt();
		String date = LocalDate.now().toString();
		String description = "Yoghurt";
		String price = String.valueOf(12.72);
		String expectedResult = "Item's name:" + description;
		String change = format.format(sale.getPayment().getChange());
		String discount = format.format(sale.getSale().getAmountDiscount());
		assertTrue(result.contains(expectedResult), "Wrong printout.");
		assertTrue(result.contains(price), "Wrong printout, price.");
		assertTrue(result.contains(date), "Wrong printout, date.");
		assertTrue(result.contains(change), "Wrong printout, change.");
		assertTrue(result.contains(discount), "Wrong printout, change.");

	}
}
