package Model;
/**
 * The Orders class represents an order entity.
 * It provides attributes and methods to manage orders information.
 * @author Sarafinceanu Denisa
 */
public class Orders {
    /**
     * The ID of the order.
     */
    private int ID;

    /**
     * The ID of the client placing the order.
     */
    private int clientID;

    /**
     * The ID of the product in the order.
     */
    private int productID;

    /**
     * The quantity of the product in the order.
     */
    private int quantity;

    /**
     * The total price of the order.
     */

    /**
     * Constructs a new Orders object with default values.
     */
    public Orders() {
    }

    /**
     * Constructs a new Orders object with the specified attributes.
     *
     * @param ID         The ID of the order.
     * @param clientID   The ID of the client placing the order.
     * @param productID  The ID of the product in the order.
     * @param quantity   The quantity of the product in the order.
     */
    public Orders(int ID, int clientID, int productID, int quantity) {
        this.ID = ID;
        this.clientID = clientID;
        this.productID = productID;
        this.quantity = quantity;
    }

    /**
     * Constructs a new Orders object with the specified attributes.
     *
     * @param clientID   The ID of the client placing the order.
     * @param productID  The ID of the product in the order.
     * @param quantity   The quantity of the product in the order.
     */
    public Orders(int clientID, int productID, int quantity) {
        this.clientID = clientID;
        this.productID = productID;
        this.quantity = quantity;
    }

    /**
     * Gets the ID of the order.
     *
     * @return The ID of the order.
     */
    public int getID() {
        return ID;
    }

    /**
     * Sets the ID of the order.
     *
     * @param ID The ID of the order.
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Gets the ID of the client placing the order.
     *
     * @return The ID of the client placing the order.
     */
    public int getClientID() {
        return clientID;
    }

    /**
     * Sets the ID of the client placing the order.
     *
     * @param clientID The ID of the client placing the order.
     */
    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    /**
     * Gets the ID of the product in the order.
     *
     * @return The ID of the product in the order.
     */
    public int getProductID() {
        return productID;
    }

    /**
     * Sets the ID of the product in the order.
     *
     * @param productID The ID of the product in the order.
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    /**
     * Gets the quantity of the product in the order.
     *
     * @return The quantity of the product in the order.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the product in the order.
     *
     * @param quantity The quantity of the product in the order.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    /**
     * Returns a string representation of the order.
     *
     * @return A string representation of the order.
     */
    @Override
    public String toString() {
        return "Order [id=" + ID + ", clientID=" + clientID + ", productID=" + productID + ", quantity=" + quantity + "]";
    }
}
