package main.java.se.anabelandrola.saleProcess.integration;

/**
 * Thrown when trying to found an item that does not exist in ItemRegistry.
 */
public class ItemIsNotFoundException extends Exception {
	/**
	 * Creates a new instance with a message specifying for which idItem is not
	 * founded
	 *
	 * @param idItem The idItem is not founded.
	 * @param msg    The message about idItem is not valid
	 */
	public ItemIsNotFoundException(String msg, int idItem) {
		super(idItem + msg);
	}

}
