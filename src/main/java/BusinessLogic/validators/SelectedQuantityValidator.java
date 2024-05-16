package BusinessLogic.validators;

import BusinessLogic.ProductBLL;
import Model.Orders;
import Model.Product;

import javax.swing.*;
/**
 *
 * This class validates the order's quantity
 * This implements validator
 * The package Model {@link Model} is used to access the stock of the product.
 * The package BusinessLogic {@link BusinessLogic} is used to access the product
 * by its id.
 * @author Sarafinceanu Denisa
 */
public class SelectedQuantityValidator implements Validator<Orders>{
    @Override
    public void validate(Orders orders) {

        ProductBLL productBLL = new ProductBLL();
        Product product = productBLL.findProductByID(orders.getProductID());
        if(product.getStock() < orders.getQuantity()){
            JOptionPane.showMessageDialog(null, "Limited stock!","ERROR", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Limited stock!");
        }
    }
}
