package main.java.se.anabelandrola.saleProcess.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.text.DecimalFormat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.se.anabelandrola.saleProcess.integration.DatabaseIsNotBeCalledException;
import main.java.se.anabelandrola.saleProcess.integration.ItemIsNotFoundException;
import main.java.se.anabelandrola.saleProcess.integration.Printer;
import main.java.se.anabelandrola.saleProcess.integration.RegisterCreator;
import main.java.se.anabelandrola.saleProcess.model.ItemListDTO;
import main.java.se.anabelandrola.saleProcess.model.SaleDTO;

class ControllerTest {
	private Controller control;
	private RegisterCreator registerCreator;
	private Printer printer;
	int idItem = 63514896;
	SaleDTO sale;
	ItemListDTO item;
	DecimalFormat format = new DecimalFormat("#.##");

	@BeforeEach
	void setUp() throws Exception {
		registerCreator = new RegisterCreator();
		printer = new Printer();
		control = new Controller(registerCreator, printer);
	}

	@AfterEach
	void tearDown() throws Exception {
		registerCreator = null;
		printer = null;
		control = null;
	}

	@Test
	void testStartSaleSuccessfully() {
		control.startSale();
		boolean result = false;
		try {
			item = control.registerItem(idItem);
			if (item != null)
				result = true;
			boolean expectedResult = true;
			assertEquals(expectedResult, result, "Item is not regristed");
		} catch (ItemIsNotFoundException e) {
			boolean expectedResult = false;
			assertEquals(expectedResult, result, "Item is not regristed");
		} catch (DatabaseIsNotBeCalledException e) {
			boolean expectedResult = false;
			assertEquals(expectedResult, result, "Item is not regristed");
		}
	}

	@Test
	void testRegisterItemSuccessfully() {
		try {
			control.startSale();
			boolean result = false;
			item = control.registerItem(idItem);
			if (item != null)
				result = true;
			boolean expectedResult = true;
			assertEquals(expectedResult, result, "Item is not regristed");
		} catch (ItemIsNotFoundException e) {
			boolean expectedResult = false;
			assertEquals(expectedResult, true, "Item is not regristed");
		} catch (DatabaseIsNotBeCalledException e) {
			boolean expectedResult = false;
			assertEquals(expectedResult, true, "Item is not regristed");
		}

	}

	@Test
	void testRegisterItemSaleIsNotCreate() {
		int result = 0;
		int expectedResult = 0;
		String idCustomer = "8406183765";
		try {
			item = control.registerItem(idItem);
			sale = control.generateSale(idCustomer);
			result = sale.getItems().size();
		} catch (ItemIsNotFoundException e) {
			assertEquals(expectedResult, result, "Sale is created");
		} catch (DatabaseIsNotBeCalledException e) {
			assertEquals(expectedResult, result, "Sale is created");
		} catch (NullPointerException e) {
			assertEquals(expectedResult, result, "Sale is created");
		}
	}

	@Test
	void testRegisterItemNotExist() {
		idItem = 63514234;
		control.startSale();
		boolean result = false;
		try {
			item = control.registerItem(idItem);
		} catch (ItemIsNotFoundException e) {
			if (item != null)
				result = true;
			boolean expectedResult = false;
			assertEquals(expectedResult, result, "Sale is created");
		} catch (DatabaseIsNotBeCalledException e) {
			boolean expectedResult = false;
			assertEquals(expectedResult, result, "Sale is created");
		}
	}

	@Test
	void testRegisterItemIsZero() {
		idItem = 0;
		control.startSale();
		boolean result = false;
		try {
			item = control.registerItem(idItem);
		} catch (ItemIsNotFoundException e) {
			if (item != null)
				result = true;
			boolean expectedResult = false;
			assertEquals(expectedResult, result, "Sale is created");
		} catch (DatabaseIsNotBeCalledException e) {
			boolean expectedResult = false;
			assertEquals(expectedResult, result, "Sale is created");
		}
	}

	@Test
	void testGenerateSaleSuccessful() {
		try {
			control.startSale();
			item = control.registerItem(idItem);
			String idCustomer = "8406183765";
			sale = control.generateSale(idCustomer);
			boolean result = false;
			if (sale != null)
				result = true;
			boolean expectedResult = true;
			assertEquals(expectedResult, result, "Sale is not saved");
		} catch (ItemIsNotFoundException e) {
			boolean expectedResult = false;
			assertTrue(expectedResult, "IdItem is not valid");
		} catch (DatabaseIsNotBeCalledException e) {
			boolean expectedResult = false;
			assertTrue(expectedResult, "IdItem is not found");
		}
	}

	@Test
	void testGenerateSaleIsSaleNull() {
		boolean result = false;
		try {
			String idCustomer = "8406183765";
			sale = control.generateSale(idCustomer);

		} catch (NullPointerException e) {
			if (sale != null)
				result = true;
			boolean expectedResult = false;
			assertEquals(expectedResult, result, "Sale is null och is generated");
		}
	}

	@Test
	void testPaySuccesfullCashRegisterIncreases() {
		try {
			double paidAmount = 40;
			control.startSale();
			item = control.registerItem(idItem);
			String idCustomer = "8406183765";
			sale = control.generateSale(idCustomer);
			control.pay(paidAmount);
			String expectedResult = "12,08";
			String result = format.format(control.getAmountCashRegister());
			assertEquals(expectedResult, result, "Cash Register is not increased");
		} catch (ItemIsNotFoundException e) {
			boolean expectedResult = false;
			assertTrue(expectedResult, "IdItem is not valid");
		} catch (DatabaseIsNotBeCalledException e) {
			boolean expectedResult = false;
			assertTrue(expectedResult, "IdItem is not found");
		}

	}

	@Test
	void testPaySuccesfullSaleRegisterUpdates() {
		try {
			double paidAmount = 40;
			control.startSale();
			item = control.registerItem(idItem);
			String idCustomer = "8406183765";
			control.generateSale(idCustomer);
			control.pay(paidAmount);
			int expectedResult = 1;
			int result = registerCreator.getSaleRegister().getSaleRegister().size();
			assertEquals(expectedResult, result, "SaleRegister is not updated");
		} catch (ItemIsNotFoundException e) {
			boolean expectedResult = false;
			assertTrue(expectedResult, "IdItem is not valid");
		} catch (DatabaseIsNotBeCalledException e) {
			boolean expectedResult = false;
			assertTrue(expectedResult, "IdItem is not found");
		}
	}

	@Test
	void testPayPaidAmountIsZero() {
		try {
			double paidAmount = 0;
			control.startSale();
			item = control.registerItem(idItem);
			String idCustomer = "8406183765";
			sale = control.generateSale(idCustomer);
			try {
				control.pay(paidAmount);
			} catch (IllegalArgumentException e) {
				double expectedResult = 0;
				double result = control.getAmountCashRegister();
				assertEquals(expectedResult, result,
						"The amount paid is less than the total cost of the sale. Sale is paid");
			}
		} catch (ItemIsNotFoundException e) {
			boolean expectedResult = false;
			assertTrue(expectedResult, "IdItem is not valid");
		} catch (DatabaseIsNotBeCalledException e) {
			boolean expectedResult = false;
			assertTrue(expectedResult, "IdItem is not found");
		}
	}

	@Test
	void testRegisterItemNotExistExceptionIsThrown() {
		idItem = 63514234;
		control.startSale();
		boolean result = false;
		try {
			item = control.registerItem(idItem);
			fail("Could found the item.");
		} catch (ItemIsNotFoundException e) {
			assertTrue(e.getMessage().contains(" is not a valid idItem."));
		} catch (DatabaseIsNotBeCalledException e) {
			boolean expectedResult = false;
			assertTrue(expectedResult, "IdItem is not found");
		}
	}

	@Test
	void testRegisterItemConnectionWithDatabaseExceptionIsThrown() {
		control.startSale();
		idItem = 57306978;
		try {
			item = control.registerItem(idItem);
			fail("Could found the item.");
		} catch (DatabaseIsNotBeCalledException e) {
			assertTrue(e.getMessage().contains("Could not found idItem"));
		} catch (ItemIsNotFoundException e) {
			boolean expectedResult = false;
			assertTrue(expectedResult, "IdItem is not valid");
		}
	}

}
