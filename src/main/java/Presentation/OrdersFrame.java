/**
 * The Presentation package contains classes responsible for the graphical user interface (GUI)
 * presentation and control logic of the application.
 * <p>
 * It includes classes such as {@link ClientFrame}, {@link ProductFrame}, {@link OrdersFrame},
 * {@link OrderController}, and other GUI-related components.
 * </p>
 */
package Presentation;

import BusinessLogic.ClientBLL;
import BusinessLogic.OrdersBLL;
import BusinessLogic.ProductBLL;
import Model.Client;
import Model.Orders;
import Model.Product;
import Start.Reflection;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;
/**
 * The OrdersFrame class represents the frame for managing orders in the graphical user interface (GUI).
 * It displays tables of clients, products, and orders along with fields for placing new orders.
 * <p>
 * This frame includes methods to prepare and initialize the GUI components, such as client, product,
 * and order tables, text fields, labels, and buttons. It also provides functionality to update and
 * display data dynamically.
 * </p>
 * The packages BusinessLogic {@link BusinessLogic} and Model {@link Model} are taking the
 * data corresponding to client, product, order from the database.
 * The package Start {@link Start} is taking the Reflection function.
 */
public class OrdersFrame extends JFrame {
    private JPanel ordersPanel;
    private JTable clientTable;
    private JTable productTable;
    private JTable orderTable;
    private JPanel clientPane;
    private JPanel productPane;
    private JPanel orderPane;
    private JLabel clientIDLabel;
    private JTextField clientIDTextField;
    private JLabel productIDLabel;
    private JTextField productIDTextField;
    private JLabel quantityLabel;
    private JTextField quantityTextField;
    private JButton orderButton;
    private OrderController controller = new OrderController(this);
    public void prepareOrderGUI(){
        this.setTitle("Orders");
        this.ordersPanel = new JPanel();
        this.clientPane = new JPanel();
        this.productPane = new JPanel();
        this.orderPane = new JPanel();
        //this.orderTable = new JTable();
        this.setVisible(true);
        this.setSize(1550, 1550);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        //this.clientPane.setLayout(null);
        //this.productPane.setLayout(null);
        //prepareOrderFrame();
        this.clientPane.setLayout(new BorderLayout());
        this.productPane.setLayout(new BorderLayout());
        this.orderPane.setLayout(new FlowLayout(FlowLayout.LEFT));

        initializeTables();
        //initializeClientTable();
        //initializeProductTable();
        //initializeOrderTable();
        prepareOrderPanel();
        this.clientPane.add(clientTable.getTableHeader(), BorderLayout.NORTH);
        this.clientPane.add(new JScrollPane(clientTable), BorderLayout.CENTER);

        this.productPane.add(new JScrollPane(productTable), BorderLayout.CENTER);
        this.productPane.add(orderPane, BorderLayout.SOUTH);

        this.ordersPanel.add(this.clientPane, BorderLayout.NORTH);
        this.ordersPanel.add(this.productPane, BorderLayout.CENTER);
        this.ordersPanel.add(this.orderPane, BorderLayout.SOUTH);

        this.setContentPane(this.ordersPanel);
        this.orderPane.add(new JScrollPane(orderTable), BorderLayout.CENTER); // Add this line
    }
    public void initializeClientTable(){
        ClientBLL clientBLL = new ClientBLL();
        List<Client> allClients = clientBLL.findAllClients();
        Reflection reflection = new Reflection();
        clientTable = reflection.retrieveProperties(allClients);

        prepareTableClient(clientTable);
    }

    public void initializeProductTable(){
        ProductBLL productBLL = new ProductBLL();
        List<Product> allProducts = productBLL.findAllProducts();
        Reflection reflection1 = new Reflection();
        productTable = reflection1.retrieveProperties(allProducts);
        prepareTableProduct(productTable);
    }

    public void initializeOrderTable(){
        OrdersBLL ordersBLL = new OrdersBLL();
        List<Orders> allOrders = ordersBLL.findAllOrders();
        Reflection reflection2 = new Reflection();
        orderTable = new JTable();
        orderTable = reflection2.retrieveProperties(allOrders);
        prepareTableOrders(orderTable);
    }
    public void initializeTables(){
        ClientBLL clientBLL = new ClientBLL();
        List<Client> allClients = clientBLL.findAllClients();
        Reflection reflection = new Reflection();
        //clientTable = new JTable();
        clientTable = reflection.retrieveProperties(allClients);

        ProductBLL productBLL = new ProductBLL();
        List<Product> allProducts = productBLL.findAllProducts();
        //productTable = new JTable();
        //Reflection reflection1 = new Reflection();
        productTable = reflection.retrieveProperties(allProducts);


        OrdersBLL ordersBLL = new OrdersBLL();
        List<Orders> allOrders = ordersBLL.findAllOrders();
        Reflection reflection2 = new Reflection();
       // orderTable = new JTable();
        orderTable = reflection.retrieveProperties(allOrders);

        prepareTableClient(clientTable);
        prepareTableProduct(productTable);
        prepareTableOrders(orderTable);
    }

    public void prepareTableClient(JTable table1){
        this.clientPane.removeAll();

        JTableHeader header = table1.getTableHeader();
        header.setBackground(Color.pink);
        JScrollPane pane = new JScrollPane(table1);
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table1.getColumnModel().getColumn(0).setPreferredWidth(50);
        table1.getColumnModel().getColumn(1).setPreferredWidth(100);
        table1.getColumnModel().getColumn(2).setPreferredWidth(150);
        table1.getColumnModel().getColumn(3).setPreferredWidth(150);
        table1.setRowHeight(20);
        /*
        this.clientPane.add(Box.createVerticalStrut(20));
        this.clientPane.add(pane);
        this.clientTable = table1;
        this.clientPane.add(Box.createVerticalStrut(20));
        //this.clientTable.setLayout(new GridLayout(1, 1));

       // this.clientPane.add(clientTable);

        this.clientPane.revalidate();
        this.clientPane.repaint();

        this.ordersPanel.add(this.clientPane);*/
    }

    public void prepareTableProduct(JTable table1){
        this.productPane.removeAll();

        JTableHeader header = table1.getTableHeader();
        header.setBackground(Color.pink);
        JScrollPane pane = new JScrollPane(table1);
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table1.getColumnModel().getColumn(0).setPreferredWidth(50);
        table1.getColumnModel().getColumn(1).setPreferredWidth(100);
        table1.getColumnModel().getColumn(2).setPreferredWidth(150);
        table1.getColumnModel().getColumn(3).setPreferredWidth(150);
        table1.setRowHeight(20);
        this.productTable = table1;

    }

    public void prepareTableOrders(JTable table1){
        this.orderPane.removeAll();

        JTableHeader header = table1.getTableHeader();
        header.setBackground(Color.pink);
        JScrollPane pane = new JScrollPane(table1);
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table1.getColumnModel().getColumn(0).setPreferredWidth(50);
        table1.getColumnModel().getColumn(1).setPreferredWidth(100);
        table1.getColumnModel().getColumn(2).setPreferredWidth(150);
        table1.getColumnModel().getColumn(3).setPreferredWidth(150);

        this.orderPane.add(pane);
        this.orderTable = table1;

        this.orderPane.setLayout(new GridLayout(1, 1));

        this.ordersPanel.add(orderPane);

        orderPane.revalidate();
        orderPane.repaint();
    }

    public void prepareOrderPanel(){
        clientIDLabel = new JLabel("Client ID:");
        clientIDTextField = new JTextField(5); // Change the size of the text field to 5
        productIDLabel = new JLabel("Product ID:");
        productIDTextField = new JTextField(5); // Change the size of the text field to 5
        quantityLabel = new JLabel("Quantity:");
        quantityTextField = new JTextField(5); // Change the size of the text field to 5
        orderButton = new JButton("Place Order");
        this.orderButton.addActionListener(this.controller);

        // Add the components to a GridBagLayout to improve the layout
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        orderPane.setLayout(layout);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        layout.setConstraints(clientIDLabel, constraints);
        orderPane.add(clientIDLabel);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        layout.setConstraints(clientIDTextField, constraints);
        orderPane.add(clientIDTextField);

        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        layout.setConstraints(productIDLabel, constraints);
        orderPane.add(productIDLabel);

        constraints.gridx = 3;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        layout.setConstraints(productIDTextField, constraints);
        orderPane.add(productIDTextField);

        constraints.gridx = 4;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        layout.setConstraints(quantityLabel, constraints);
        orderPane.add(quantityLabel);

        constraints.gridx = 5;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        layout.setConstraints(quantityTextField, constraints);
        orderPane.add(quantityTextField);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 6;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        layout.setConstraints(orderButton, constraints);
        orderPane.add(orderButton);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 6;
        constraints.fill = GridBagConstraints.BOTH;
        layout.setConstraints(new JScrollPane(orderTable), constraints);
        orderPane.add(new JScrollPane(orderTable));

        // Adjust the weighty constraint to push the button to the bottom of the container
        constraints.weighty = 1.0;
        constraints.anchor = GridBagConstraints.PAGE_END;
        layout.setConstraints(orderButton, constraints);
        orderPane.add(orderButton);

        // Reset the weighty constraint to its default value
        constraints.weighty = 0.0;
    }

    public JTextField getClientIDTextField() {
        return clientIDTextField;
    }

    public void setClientIDTextField(JTextField clientIDTextField) {
        this.clientIDTextField = clientIDTextField;
    }

    public JLabel getProductIDLabel() {
        return productIDLabel;
    }

    public void setProductIDLabel(JLabel productIDLabel) {
        this.productIDLabel = productIDLabel;
    }

    public JTextField getProductIDTextField() {
        return productIDTextField;
    }

    public void setProductIDTextField(JTextField productIDTextField) {
        this.productIDTextField = productIDTextField;
    }

    public JLabel getQuantityLabel() {
        return quantityLabel;
    }

    public void setQuantityLabel(JLabel quantityLabel) {
        this.quantityLabel = quantityLabel;
    }

    public JTextField getQuantityTextField() {
        return quantityTextField;
    }

    public void setQuantityTextField(JTextField quantityTextField) {
        this.quantityTextField = quantityTextField;
    }
}
