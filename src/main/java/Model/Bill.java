package Model;

import DataAccess.BillDAO;
/**
 * Represents a bill record containing client name, product name, quantity, and total price.
 * This record is used for storing and retrieving bill information from the database.
 */
public record Bill(String clientName, String productName, int quantity, float totalPrice ) {
    /**
     * Constructs a new Bill record.
     *
     * @param clientName the name of the client.
     * @param productName the name of the product.
     * @param quantity the quantity of the product ordered.
     * @param totalPrice the total price of the order.
     */
    public Bill(String clientName, String productName, int quantity, float totalPrice){
        this.clientName = clientName;
        this.productName = productName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
}
