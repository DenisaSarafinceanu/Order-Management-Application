package BusinessLogic.validators;

import Model.Product;

import javax.swing.*;
/**
 *
 * This class validates the product's stock
 * This implements validator
 * The package Model {@link Model} is used to access the stock of the product.
 * @author Sarafinceanu Denisa
 */
public class QuantityValidator implements Validator<Product>{
    @Override
    public void validate(Product product) {

        if(product.getStock() <= 0){
            JOptionPane.showMessageDialog(null, "The product is out of stock!","ERROR", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("The product is out of stock!");
        }
    }
}
