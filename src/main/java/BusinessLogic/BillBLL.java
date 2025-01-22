package BusinessLogic;

import BusinessLogic.validators.Validator;
import DataAccess.BillDAO;
import Model.Bill;
import Model.Client;
import Model.Orders;
import Model.Product;

import java.util.List;
/**
 * Business Logic Layer for handling operations related to Bills.
 * This class interacts with the Data Access Layer to perform CRUD operations on Bill entities.
 */
public class BillBLL {
    private BillDAO billDAO;
    /**
     * Constructor that initializes the Bill Data Access Object.
     */
    public BillBLL(){
        billDAO = new BillDAO();
    }
    /**
     * Retrieves all bills from the database.
     *
     * @return a list of all Bill objects.
     */
    public List<Bill> findAllBills(){
        return billDAO.findAll();
    }
    /**
     * Inserts a new bill into the database.
     *
     * @param bill the Bill object to be inserted.
     */
    public void insertBill(Bill bill){
        billDAO.insert(bill);
    }
    /**
     * Generates and inserts a new bill into the database based on the provided client, product, and order details.
     *
     * @param client  the Client object containing client details.
     * @param product the Product object containing product details.
     * @param orders  the Orders object containing order details.
     */
    public void generateBill(Client client, Product product, Orders orders){
        try{
            billDAO.insert(new Bill(client.getName(),product.getName(), orders.getQuantity(), orders.getTotalPrice()));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
