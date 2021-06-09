package main.java.se.anabelandrola.saleProcess.view;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ErrorMessageToUserTest {
	private ErrorMessageToUser errorMsgToUser;
	private ByteArrayOutputStream printOut;
	private PrintStream originalSystemOut;

	@BeforeEach
	void setUp() throws Exception {
		errorMsgToUser = new ErrorMessageToUser();
		printOut = new ByteArrayOutputStream();
		PrintStream inMemSystemOut = new PrintStream(printOut);
		originalSystemOut = System.out;
		System.setOut(inMemSystemOut);
	}

	@AfterEach
	void tearDown() throws Exception {
		errorMsgToUser = null;
		printOut = null;
		System.setOut(originalSystemOut);
	}

	@Test
	void testDisplayErrorMessage() {
		String message = "Exempel of message";
		errorMsgToUser.displayErrorMessage(message);
		String printSystemOut = printOut.toString();
		String expectResult = "message";
		assertTrue(printSystemOut.contains(expectResult), "Wrong printout, message.");
		expectResult = "Date and Time";
		assertTrue(printSystemOut.contains(expectResult), "Wrong printout, date.");
		expectResult = "Error is: ";
		assertTrue(printSystemOut.contains(expectResult), "Wrong printout, type of error.");
	}

}
