package BusinessLogic;

import BusinessLogic.validators.SelectedQuantityValidator;
import BusinessLogic.validators.Validator;
import DataAccess.OrdersDAO;
import Model.Orders;

import java.util.ArrayList;
import java.util.List;

/**
 * The OrdersBLL class provides business logic functionalities related to Orders entities.
 * The packages DataAccess {@link DataAccess} and Model {@link Model} are used to get the
 * data of the Orders as the attributes and the queries to be executed in the database.
 * @author Sarafinceanu Denisa
 */
public class OrdersBLL {
    private List<Validator<Orders>> validators;
    private OrdersDAO ordersDAO;

    /**
     * Constructs a new OrdersBLL object and initializes validators and DAO.
     */
    public OrdersBLL(){
        validators = new ArrayList<>();
        validators.add(new SelectedQuantityValidator());
        ordersDAO = new OrdersDAO();
    }

    /**
     * Retrieves all orders.
     * @return A list of all Orders objects.
     */
    public List<Orders> findAllOrders(){
        return ordersDAO.findAll();
    }

    /**
     * Inserts a new order into the database.
     * @param order The Orders object to insert.
     * @return The ID of the newly inserted order.
     */
    public int insertOrder(Orders order){
        for(Validator<Orders> validator : validators){
            validator.validate(order);
        }

        return ordersDAO.insert(order);
    }
}
