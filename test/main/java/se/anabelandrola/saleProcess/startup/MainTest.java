package main.java.se.anabelandrola.saleProcess.startup;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.se.anabelandrola.saleProcess.controller.Controller;
import main.java.se.anabelandrola.saleProcess.integration.Printer;
import main.java.se.anabelandrola.saleProcess.integration.RegisterCreator;
import main.java.se.anabelandrola.saleProcess.view.View;

class MainTest {
	private Controller control;
	private RegisterCreator registerCreator;
	private Printer printer;
	private View instanceViewToTest;
	private ByteArrayOutputStream printOut;
	private PrintStream originalSystemOut;

	@BeforeEach
	void setUp() throws Exception {
		registerCreator = new RegisterCreator();
		printer = new Printer();
		control = new Controller(registerCreator, printer);
		instanceViewToTest = new View(control);
		printOut = new ByteArrayOutputStream();
		PrintStream inMemSystemOut = new PrintStream(printOut);
		originalSystemOut = System.out;
		System.setOut(inMemSystemOut);
	}

	@AfterEach
	void tearDown() throws Exception {
		registerCreator = null;
		printer = null;
		control = null;
		instanceViewToTest = null;
		printOut = null;
		System.setOut(originalSystemOut);
	}

	@Test
	void testMainSuccessfully() {
		String[] args = null;
		Main.main(args);
		instanceViewToTest.sampleProcessSaleExecution();
	}

	@Test
	void testMainSampleProcessSaleExecutionStartSale() {
		String[] args = null;
		Main.main(args);
		instanceViewToTest.sampleProcessSaleExecution();
		String printSystemOut = printOut.toString();
		String expectResult = "started";
		assertTrue(printSystemOut.contains(expectResult), "System did not started correctly.");
	}

	@Test
	void testMainSampleProcessSaleExecutionRegisterItemIsNotValid() {
		String[] args = null;
		Main.main(args);
		instanceViewToTest.sampleProcessSaleExecution();
		String printSystemOut = printOut.toString();
		int isNotIdItem = 63514810;
		String expectResult = isNotIdItem + " is not valid";
		assertTrue(printSystemOut.contains(expectResult), "Wrong printout, Item is not valid.");
	}

	@Test
	void testMainSampleProcessSaleExecutionDatabaseIsNotBeCalled() {
		String[] args = null;
		Main.main(args);
		instanceViewToTest.sampleProcessSaleExecution();
		String printSystemOut = printOut.toString();
		int isIdItemDatabase = 57306978;
		String expectResult = isIdItemDatabase + " could not be found";
		assertTrue(printSystemOut.contains(expectResult), "Wrong printout, Database is not be called.");
	}

	@Test
	void testMainSampleProcessSaleExecutionRegisterItem() {
		String[] args = null;
		Main.main(args);
		instanceViewToTest.sampleProcessSaleExecution();
		String printSystemOut = printOut.toString();
		String expectResult = "Item's description: Milk";
		assertTrue(printSystemOut.contains(expectResult), "Wrong printout, register item Milk.");
		expectResult = "Item's description: Beans price: 32.0 Total price(incl. VAT): 35.84";
		assertTrue(printSystemOut.contains(expectResult), "Wrong printout, register item Beans.");
		expectResult = "Item's description: Milk price: 20.0 Total price(incl. VAT): 21.2";
		assertTrue(printSystemOut.contains(expectResult), "Wrong printout, register item quantity.");
	}

	@Test
	void testMainSampleProcessSaleExecutionGenerateSale() {
		String[] args = null;
		Main.main(args);
		instanceViewToTest.sampleProcessSaleExecution();
		String printSystemOut = printOut.toString();
		String expectResult = "finished";
		assertTrue(printSystemOut.contains(expectResult), "Sale did not generated.");
		expectResult = "Total price of the sale";
		assertTrue(printSystemOut.contains(expectResult), "Sale did not generated correctly.");

	}

	@Test
	void testMainSampleProcessSaleExecutionReceipt() {
		String[] args = null;
		Main.main(args);
		instanceViewToTest.sampleProcessSaleExecution();
		String printSystemOut = printOut.toString();
		String expectResult = "Store's name: ICA, address: Fridhemsplan";
		assertTrue(printSystemOut.contains(expectResult), "Receipt did not generated.");
		expectResult = "Change:";
		assertTrue(printSystemOut.contains(expectResult), "Receipt did not generated correctly.");
	}

	@Test
	void testMainSampleProcessSaleExecutionObserver() {
		instanceViewToTest.sampleProcessSaleExecution();
		String printSystemOut = printOut.toString();
		String expectResult = "Total reveneu is";
		assertTrue(printSystemOut.contains(expectResult), "Wrong printout, observer.");
	}

	@Test
	void testMainSampleProcessSaleExecutionNewSale() {
		String[] args = null;
		Main.main(args);
		instanceViewToTest.sampleProcessSaleExecution();
		String printSystemOut = printOut.toString();
		String expectResult = "A new sale ";
		assertTrue(printSystemOut.contains(expectResult), "A new sale did not started correctly.");
	}

}
