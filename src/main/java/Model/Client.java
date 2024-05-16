package Model;
/**
 * The Client class represents a client entity.
 * It provides attributes and methods to manage client information.
 * @author Sarafinceanu Denisa
 */
public class Client {
    private int ID;
    private String name;
    private String address;
    private String email;

    /**
     * Constructs a new Client object with default values.
     */
    public Client(){

    }

    /**
     * Constructs a new Client object with the specified ID, name, address, and email.
     * @param ID The ID of the client.
     * @param name The name of the client.
     * @param address The address of the client.
     * @param email The email of the client.
     */
    public Client(int ID, String name, String address, String email) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.email = email;
    }

    /**
     * Constructs a new Client object with the specified name, address, and email.
     * @param name The name of the client.
     * @param address The address of the client.
     * @param email The email of the client.
     */
    public Client(String name, String address, String email) {
        this.name = name;
        this.address = address;
        this.email = email;
    }

    /**
     * Gets the ID of the client.
     * @return The ID of the client.
     */
    public int getID() {
        return ID;
    }

    /**
     * Sets the ID of the client.
     * @param ID The ID of the client.
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Gets the name of the client.
     * @return The name of the client.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the client.
     * @param name The name of the client.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the address of the client.
     * @return The address of the client.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the client.
     * @param address The address of the client.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the email of the client.
     * @return The email of the client.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the client.
     * @param email The email of the client.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns a string representation of the Client object.
     * @return A string representation of the Client object.
     */
    @Override
    public String toString(){
        return "Client [id=" + ID + ", name=" + name + ", address=" + address + ", email=" + email +"]";
    }

}