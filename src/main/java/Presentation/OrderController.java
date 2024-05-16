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

import BusinessLogic.ClientBLL;
import BusinessLogic.OrdersBLL;
import BusinessLogic.ProductBLL;
import Model.Client;
import Model.Orders;
import Model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
                //double totalPrice = quantity * product.getPrice();
                Orders orders = new Orders(clientID, productId, quantity);
                int ID = ordersBLL.insertOrder(orders);
                orders.setID(ID);
                if(product.getStock() - quantity == 0){
                    productBLL.deleteProduct(productId);
                    JOptionPane.showMessageDialog(null, orders.toString() + " was successfully added!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                    ordersFrame.initializeTables();
                }
                else{
                    product.setStock(product.getStock() - quantity);
                    productBLL.updateProduct(product);
                    JOptionPane.showMessageDialog(null, orders.toString() + " was successfully added!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                    ordersFrame.initializeTables();
                }


            } catch (IllegalArgumentException e){
                JOptionPane.showMessageDialog(null, "Limited stock!" , "ERROR", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }


    }
}
