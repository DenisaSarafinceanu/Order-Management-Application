package Model;

/**
 * The Product class represents a product entity.
 * It provides attributes and methods to manage product information.
 * @author Sarafinceanu Denisa
 *
 */
public class Product {
    /**
     * The ID of the product.
     */
    private int ID;

    /**
     * The name of the product.
     */
    private String name;

    /**
     * The price of the product.
     */
    private float price;

    /**
     * The stock quantity of the product.
     */
    private int stock;

    /**
     * Constructs a new Product object with default values.
     */
    public Product(){
    }

    /**
     * Constructs a new Product object with the specified attributes.
     * @param ID The ID of the product.
     * @param name The name of the product.
     * @param price The price of the product.
     * @param stock The stock quantity of the product.
     */
    public Product(int ID, String name, float price, int stock) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    /**
     * Gets the ID of the product.
     * @return The ID of the product.
     */
    public int getID() {
        return ID;
    }

    /**
     * Sets the ID of the product.
     * @param ID The ID of the product.
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Gets the name of the product.
     * @return The name of the product.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product.
     * @param name The name of the product.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the price of the product.
     * @return The price of the product.
     */
    public float getPrice() {
        return price;
    }

    /**
     * Sets the price of the product.
     * @param price The price of the product.
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Gets the stock quantity of the product.
     * @return The stock quantity of the product.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets the stock quantity of the product.
     * @param stock The stock quantity of the product.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Returns a string representation of the product.
     * @return A string representation of the product.
     */
    @Override
    public String toString(){
        return "Product [id=" + ID + ", name=" + name + ", price=" + price + ", stock=" + stock +"]";
    }
}