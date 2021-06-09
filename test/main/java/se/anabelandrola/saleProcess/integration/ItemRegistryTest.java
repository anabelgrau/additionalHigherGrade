package main.java.se.anabelandrola.saleProcess.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.se.anabelandrola.saleProcess.model.ItemListDTO;
import main.java.se.anabelandrola.saleProcess.model.SaleDTO;

class ItemRegistryTest {

	private int idItem = 63514896;
	private ItemRegistry itemRegistry;
	private List<ItemListDTO> itemList = new ArrayList<>();
	private SaleDTO sale;

	@BeforeEach
	void setUp() throws Exception {
		itemRegistry = new ItemRegistry();
	}

	@AfterEach
	void tearDown() throws Exception {
		itemRegistry = null;
	}

	@Test
	void testSearchItemInventoryItemIsValid() {
		try {
		ItemDTO itemYoghurt = new ItemDTO(63514896, "Yoghurt", 12, 6, 3);
		ItemDTO expectedResult = itemYoghurt;
		ItemDTO result = itemRegistry.searchItemInventory(idItem);
		assertEquals(expectedResult, result, "idItem is not valid");
		} catch (ItemIsNotFoundException e) {
			boolean expectedResult = false;
			assertTrue(expectedResult, "IdItem is not valid");
		} catch (DatabaseIsNotBeCalledException e) {
			boolean expectedResult = false;
			assertTrue(expectedResult, "IdItem is not found");
		}
		
	}

	@Test
	void testSearchItemInventoryItemIsNotValid() {
		ItemDTO result= null;
		try {
		idItem = 0;
		result = itemRegistry.searchItemInventory(idItem);
		} catch (ItemIsNotFoundException e) {
			ItemDTO expectedResult = null;
			assertEquals(expectedResult, result, "Items with null idItem are the same");
		} catch (DatabaseIsNotBeCalledException e) {
			boolean expectedResult = false;
			assertTrue(expectedResult, "IdItem is not found");
		}
	}

	@Test
	void testSearchItemInventoryItemIdItemWithNegativeValue() {
		ItemDTO result = null;
		try {
			idItem = -10;
			result = itemRegistry.searchItemInventory(idItem);
		} catch (ItemIsNotFoundException e) {
			ItemDTO expectedResult = null;
			assertEquals(expectedResult, result, "idItem with a negative value is not null in the end of the search.");
		} catch (DatabaseIsNotBeCalledException e) {
			boolean expectedResult = false;
			assertTrue(expectedResult, "IdItem is not found");
		}
	}

	@Test
	void testUpdateInventorySuccessfully() {
		itemList.add(new ItemListDTO(63514896, "Yoghurt", 1, 12, 0.72, 12.72));
		sale = new SaleDTO("2021-05-02", "11:48:20", 12, 0.72, 0.0, 12.72, itemList);
		itemRegistry.updateInventory(sale);
		ItemDTO expectedResult = new ItemDTO(63514896, "Yoghurt", 12, 6, 4);
		ItemDTO result = itemRegistry.getItems().get(0);
		assertEquals(expectedResult, result, "Quantity sold do not update");
	}

	@Test
	void testUpdateInventorySaleIsNull() {
		sale = null;
		ItemDTO expectedResult = null;
		ItemDTO result = itemRegistry.getItems().get(0);
		try {
			itemRegistry.updateInventory(sale);
		} catch (NullPointerException e) {
			result = null;
			assertEquals(expectedResult, result, "idItem with a negative value is not null in the end of the search.");
		}
	}

	@Test
	void testGetItems() {
		int result = itemRegistry.getItems().size();
		int expectedResult = 4;
		assertEquals(expectedResult, result, "Item register returns the wrong value");
	}

	@Test
	void testGetItemsIsNull() {
		itemRegistry = null;
		int result = 0;
		int expectedResult = 0;
		try {
			result = itemRegistry.getItems().size();
		} catch (NullPointerException e) {
			assertEquals(expectedResult, result, "Item register returns the wrong value");
		}
	}
	
	@Test
	void testSearchItemInventoryItemIsNotValidExceptionIsThrown() {
		ItemDTO result= null;
		try {
		idItem = 0;
		result = itemRegistry.searchItemInventory(idItem);
		fail("Could found the item.");
		} catch (ItemIsNotFoundException e) {
			assertTrue(e.getMessage().contains(" is not a valid idItem."));
		} catch (DatabaseIsNotBeCalledException e) {
			fail("Could found the database.");
		}
	}
	@Test
	void testSearchItemInventoryConnectionWithDatabaseExceptionIsThrown() {
		ItemDTO result= null;
		try {
		idItem = 57306978;
		result = itemRegistry.searchItemInventory(idItem);
		fail("Could found the item.");		
		} catch (DatabaseIsNotBeCalledException e) {
			assertTrue(e.getMessage().contains("Could not found idItem"));
		} catch (ItemIsNotFoundException e) {
			boolean expectedResult = false;
			assertTrue(expectedResult, "IdItem is not valid");
		}
	}


}
