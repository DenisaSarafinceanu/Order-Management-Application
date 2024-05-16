package BusinessLogic.validators;


import Model.Product;

import javax.swing.*;

/**
 *
 * This class validates the product's price
 * This implements validator
 * The package Model {@link Model} is used to access the price of the product.
 * @author Sarafinceanu Denisa
 */
public class PriceValidator implements Validator<Product>{

    @Override
    public void validate(Product product) {

        if(product.getPrice() <= 0){
            JOptionPane.showMessageDialog(null, "This price should be a positive value!","ERROR", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("The price is not a valid one!");
        }
    }
}
