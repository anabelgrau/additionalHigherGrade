package main.java.se.anabelandrola.saleProcess.view;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TotalReveneuViewTest {
	private TotalReveneuView totalReveneuView;
	private ByteArrayOutputStream printOut;
	private PrintStream originalSystemOut;

	@BeforeEach
	void setUp() throws Exception {
		totalReveneuView = new TotalReveneuView();
		printOut = new ByteArrayOutputStream();
		PrintStream inMemSystemOut = new PrintStream(printOut);
		originalSystemOut = System.out;
		System.setOut(inMemSystemOut);
	}

	@AfterEach
	void tearDown() throws Exception {
		totalReveneuView = null;
		printOut = null;
		System.setOut(originalSystemOut);
	}

	@Test
	void testPrintPresentAmountPaid() {
		totalReveneuView.printPresentAmountPaid();
		String printSystemOut = printOut.toString();
		String expectResult = "Total reveneu";
		assertTrue(printSystemOut.contains(expectResult), "Wrong printout, message.");
	}

}
