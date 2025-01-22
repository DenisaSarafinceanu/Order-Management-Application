/**
 * The Presentation package contains classes responsible for the graphical user interface (GUI)
 * presentation and control logic of the application.
 * <p>
 * It includes classes such as {@link ClientFrame}, {@link ProductFrame}, {@link OrdersFrame},
 * and {@link OrderController} representing frames for managing clients, products, orders, and
 * the controller for placing orders, respectively.
 * </p>
 */
package Presentation;

import BusinessLogic.BillBLL;
import BusinessLogic.ClientBLL;
import BusinessLogic.OrdersBLL;
import BusinessLogic.ProductBLL;
import Model.Client;
import Model.Orders;
import Model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * The OrderController class implements the {@link ActionListener} interface to handle user
 * interactions related to placing orders in the graphical user interface (GUI).
 * <p>
 * It facilitates the process of placing an order by retrieving client and product information
 * from the GUI components, calculating the total price, updating stock levels, and inserting
 * the order into the database.
 * </p>
 * The packages BusinessLogic {@link BusinessLogic} and Model {@link Model} are taking the
 * data corresponding to client, product, order from the database.
 */
public class OrderController implements ActionListener {
    private OrdersFrame ordersFrame;
    private OrdersBLL ordersBLL = new OrdersBLL();
    private ClientBLL clientBLL = new ClientBLL();
    private ProductBLL productBLL = new ProductBLL();
    private BillBLL billBLL = new BillBLL();

    /**
     * Constructs a new OrderController with the specified OrdersFrame.
     *
     * @param ordersFrame The OrdersFrame associated with this controller.
     */
    public OrderController(OrdersFrame ordersFrame){

        this.ordersFrame = ordersFrame;
    }
    /**
     * Handles action events triggered by user interactions with GUI components.
     * Places an order when the "Place order" button is clicked.
     *
     * @param e The action event representing the user interaction.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("Place Order")){
            placeOrder();
        }
    }
    /**
     * Places an order by retrieving client and product information from the GUI components,
     * calculating the total price, updating stock levels, and inserting the order into the database.
     * Displays appropriate success or error messages.
     */
    private void printInFile(Client client, Product product, Orders orders, int quantity){
        Random random = new Random();
        File fileOutput = new File("Log.txt");
        FileWriter write = null;
        try{
            write = new FileWriter(fileOutput);
        } catch (IOException e){
            e.printStackTrace();
        }

        PrintWriter printWriter = new PrintWriter(write);
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        printWriter.println("BILL ID: " + random.nextInt(1000));
        printWriter.println("DATE: " + date.format(formatter));
        printWriter.println("CLIENT NAME: " + client.getName());
        printWriter.println("CLIENT ADDRESS: " + client.getAddress());
        printWriter.println("CLIENT EMAIL: " + client.getEmail());
        printWriter.println("PRODUCT: " + product.getName());
        printWriter.println("PRICE: " + quantity +"x" + product.getPrice());
        printWriter.println("--------------------------------");
        printWriter.println("TOTAL PRICE: " + quantity * product.getPrice());
        printWriter.close();
    }

    private void placeOrder(){
            String clientIdText = ordersFrame.getClientIDTextField().getText();
            Integer clientID = Integer.parseInt(clientIdText);
            String productIdText = ordersFrame.getProductIDTextField().getText();
            Integer productId = Integer.parseInt(productIdText);
            String quantityText = ordersFrame.getQuantityTextField().getText();
            Integer quantity = Integer.parseInt(quantityText);
            try {
                Client client = clientBLL.findClientById(clientID);
                Product product = productBLL.findProductByID(productId);
                float totalPrice = quantity * product.getPrice();
                Orders orders = new Orders(clientID, productId, quantity, totalPrice);
                ordersBLL.insertOrder(orders);

                if(product.getStock() == quantity){
                    product.setStock(0);
                    productBLL.updateProduct(product);
                    try{
                        billBLL.generateBill(client, product, orders);
                        printInFile(client, product, orders, quantity);
                    } catch (NumberFormatException e){
                        e.printStackTrace();
                    }
                    ordersFrame.initializeTables();
                    JOptionPane.showMessageDialog(null, orders.toString() + " was successfully added!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);

                }
                else{
                    product.setStock(product.getStock() - quantity);
                    productBLL.updateProduct(product);try{
                        billBLL.generateBill(client, product, orders);
                        printInFile(client, product, orders, quantity);
                    } catch (NumberFormatException e){
                        e.printStackTrace();
                    }

                    ordersFrame.initializeTables();
                    JOptionPane.showMessageDialog(null, orders.toString() + " was successfully added!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);

                }


            } catch (IllegalArgumentException e){
                JOptionPane.showMessageDialog(null, "Limited stock!" , "ERROR", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
    }
}
