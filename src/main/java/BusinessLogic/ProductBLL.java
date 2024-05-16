package BusinessLogic;

import BusinessLogic.validators.PriceValidator;
import BusinessLogic.validators.QuantityValidator;
import BusinessLogic.validators.Validator;
import DataAccess.ProductDAO;
import Model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The ProductBLL class provides business logic functionalities related to Product entities.
 * The packages DataAccess {@link DataAccess} and Model {@link Model} are used to get the
 * data of the Product as the attributes and the queries to be executed in the database.
 * @author Sarafinceanu Denisa
 */
public class ProductBLL {
    private List<Validator<Product>> validators;
    private ProductDAO productDAO;

    /**
     * Constructs a new ProductBLL object and initializes validators and DAO.
     */
    public ProductBLL(){
        validators = new ArrayList<>();
        validators.add(new PriceValidator());
        validators.add(new QuantityValidator());
        productDAO = new ProductDAO();
    }

    /**
     * Finds a product by its ID.
     * @param ID The ID of the product to find.
     * @return The Product object if found, otherwise throws NoSuchElementException.
     * @throws NoSuchElementException If the product with the given ID is not found.
     */
    public Product findProductByID(Integer ID){
        Product product = productDAO.findByID(ID);
        if(product == null){
            throw new NoSuchElementException("The product with id=" + ID + " was not found!");
        }
        return product;
    }

    /**
     * Retrieves all products.
     * @return A list of all Product objects.
     */
    public List<Product> findAllProducts(){
        return productDAO.findAll();
    }

    /**
     * Inserts a new product into the database.
     * @param product The Product object to insert.
     */
    public void insertProduct(Product product){
        for(Validator<Product> validator : validators){
            validator.validate(product);
        }
        productDAO.insert(product);
    }

    /**
     * Deletes a product from the database by its ID.
     * @param ID The ID of the product to delete.
     */
    public void deleteProduct(Integer ID){
        productDAO.delete(ID);
    }

    /**
     * Updates a product in the database.
     * @param product The Product object with updated information.
     */
    public void updateProduct(Product product){
        for(Validator<Product> validator : validators){
            validator.validate(product);
        }
        productDAO.update(product);
    }
}
