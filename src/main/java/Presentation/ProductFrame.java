/**
 * The Presentation package contains classes responsible for the graphical user interface (GUI)
 * presentation and control logic of the application.
 * <p>
 * It includes classes such as {@link ClientFrame}, {@link ProductFrame}, {@link OrdersFrame},
 * {@link OrderController}, and other GUI-related components.
 * </p>
 */
package Presentation;


import BusinessLogic.ProductBLL;
import Model.Product;
import Start.Reflection;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;
/**
 * The ProductFrame class represents the graphical user interface (GUI) for managing products.
 * <p>
 * It extends {@link JFrame} to create a window where users can view, add, update, and delete products.
 * </p>
 * The package BusinessLogic {@link BusinessLogic} is connecting the code to the database Product
 * and the package Model {@link Model} is used to take the attributes from the class Product.
 * The package Start {@link Start} is using the Reflection function.
 */
public class ProductFrame extends JFrame{
    private JTextField productIDTextField;
    private JTextField productNameTextField;
    private JTextField productPriceTextField;
    private JTextField productQuantityTextField;
    private JPanel productPanel;
    private JButton addProductButton;
    private JButton editProductButton;
    private JTextField deleteProductTextField;
    private JButton deleteProductButton;
    private JPanel tablePanel;

    public JTable table;
    private ProductController controller = new ProductController(this);

    public void prepareProductGUI(){
        this.setTitle("Products");
        this.productPanel = new JPanel();
        this.tablePanel = new JPanel();
        this.setVisible(true);
        this.setSize(700, 750);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        this.tablePanel.setLayout(null);
        this.tablePanel.setBounds(10,50,750,400);
        prepareProductFrame();
        initializeTable();
        this.setContentPane(this.productPanel);
    }
    public void prepareProductFrame(){
        this.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.anchor = GridBagConstraints.WEST;

        constraints.gridx = 0;
        constraints.gridy = 0;
        topPanel.add(new JLabel("ID:"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        this.productIDTextField = new JTextField(5);
        topPanel.add(this.productIDTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        topPanel.add(new JLabel("Product:"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        this.productNameTextField = new JTextField(15);
        topPanel.add(this.productNameTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        topPanel.add(new JLabel("Price:"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        this.productPriceTextField = new JTextField(15);
        topPanel.add(this.productPriceTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        topPanel.add(new JLabel("Stock:"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        this.productQuantityTextField = new JTextField(15);
        topPanel.add(this.productQuantityTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        topPanel.add(new JLabel(""), constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        this.addProductButton = new JButton("Add product");
        this.addProductButton.addActionListener(this.controller);
        this.editProductButton = new JButton("Update product");
        this.editProductButton.addActionListener(this.controller);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(this.addProductButton);
        buttonPanel.add(this.editProductButton);
        topPanel.add(buttonPanel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        topPanel.add(new JLabel("Delete the product with id:"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        this.deleteProductTextField = new JTextField(15);
        topPanel.add(this.deleteProductTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        topPanel.add(new JLabel(""), constraints);

        constraints.gridx = 1;
        constraints.gridy = 6;
        this.deleteProductButton = new JButton("Delete product");
        this.deleteProductButton.addActionListener(this.controller);

        topPanel.add(this.deleteProductButton, constraints);

        this.add(topPanel, BorderLayout.NORTH);

        this.productPanel.add(topPanel);
    }

    public void initializeTable(){
        ProductBLL productBLL = new ProductBLL();
        List<Product> allProducts = productBLL.findAllProducts();
        Reflection reflection = new Reflection();
        table = reflection.retrieveProperties(allProducts);

        prepareTable(table);

    }

    public void prepareTable(JTable table1){
        this.tablePanel.removeAll();

        JTableHeader header = table1.getTableHeader();
        header.setBackground(Color.pink);
        JScrollPane pane = new JScrollPane(table1);
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table1.getColumnModel().getColumn(0).setPreferredWidth(70);
        table1.getColumnModel().getColumn(1).setPreferredWidth(150);
        table1.getColumnModel().getColumn(2).setPreferredWidth(120);
        table1.getColumnModel().getColumn(3).setPreferredWidth(110);

        this.tablePanel.add(pane);
        this.table = table1;

        this.tablePanel.setLayout(new GridLayout(1, 1));

        this.productPanel.add(tablePanel);

        tablePanel.revalidate();
        tablePanel.repaint();
    }

    public JTextField getProductIDTextField() {
        return productIDTextField;
    }

    public void setProductIDTextField(JTextField productIDTextField) {
        this.productIDTextField = productIDTextField;
    }

    public JTextField getProductNameTextField() {
        return productNameTextField;
    }

    public void setProductNameTextField(JTextField productNameTextField) {
        this.productNameTextField = productNameTextField;
    }

    public JTextField getProductPriceTextField() {
        return productPriceTextField;
    }

    public void setProductPriceTextField(JTextField productPriceTextField) {
        this.productPriceTextField = productPriceTextField;
    }

    public JTextField getProductQuantityTextField() {
        return productQuantityTextField;
    }

    public void setProductQuantityTextField(JTextField productQuantityTextField) {
        this.productQuantityTextField = productQuantityTextField;
    }

    public JTextField getDeleteProductTextField() {
        return deleteProductTextField;
    }

    public void setDeleteProductTextField(JTextField deleteProductTextField) {
        this.deleteProductTextField = deleteProductTextField;
    }
}
