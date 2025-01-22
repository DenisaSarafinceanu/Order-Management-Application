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

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The ProductController class handles user actions and events related to product management
 * in the graphical user interface (GUI).
 * <p>
 * It implements the {@link ActionListener} interface to respond to user interactions with
 * product-related components, such as adding, updating, and deleting products.
 * </p>
 * The package BusinessLogic {@link BusinessLogic} is used to access the database for Product
 * and to update the data. The package Model {@link Model} is taking the attributes of the
 * Product type.
 */
public class ProductController implements ActionListener {
    private ProductFrame productFrame;
    private ProductBLL productBLL = new ProductBLL();
    public ProductController(ProductFrame productFrame){

        this.productFrame = productFrame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("Add product")){
            addProduct();
        }
        if(command.equals("Update product")){
            editProduct();
        }
        if(command.equals("Delete product")){
            deleteProduct();
        }
    }

    private void addProduct(){
        String idText = productFrame.getProductIDTextField().getText();
        Integer ID = Integer.parseInt(idText);
        String name = productFrame.getProductNameTextField().getText();
        String priceText = productFrame.getProductPriceTextField().getText();
        //BigDecimal price = new BigDecimal(priceText);
        float price = Float.parseFloat(priceText);
        String quantityText = productFrame.getProductQuantityTextField().getText();
        int quantity = Integer.parseInt(quantityText);
        Product product = new Product(ID, name, price, quantity);
        productBLL.insertProduct(product);
        productFrame.initializeTable();
        JOptionPane.showMessageDialog(null, product.toString() + " was successfully added!","SUCCESS", JOptionPane.INFORMATION_MESSAGE);
    }
    private void editProduct(){
        String idText = productFrame.getProductIDTextField().getText();
        Integer ID = Integer.parseInt(idText);
        String name = productFrame.getProductNameTextField().getText();
        String priceText = productFrame.getProductPriceTextField().getText();
        //BigDecimal price = new BigDecimal(priceText);
        float price = Float.parseFloat(priceText);
        String quantityText = productFrame.getProductQuantityTextField().getText();
        int quantity = Integer.parseInt(quantityText);
        Product product = new Product(ID, name, price, quantity);
        productBLL.updateProduct(product);
        productFrame.initializeTable();
        JOptionPane.showMessageDialog(null, product.toString() + " was successfully updated!","SUCCESS", JOptionPane.INFORMATION_MESSAGE);
    }
    private void deleteProduct(){
        String idText = productFrame.getDeleteProductTextField().getText();
        Integer ID = Integer.parseInt(idText);
        productBLL.deleteProduct(ID);
        productFrame.initializeTable();
        JOptionPane.showMessageDialog(null, "Product [id=" + ID + "] was successfully deleted!","SUCCESS", JOptionPane.INFORMATION_MESSAGE);
    }
}
