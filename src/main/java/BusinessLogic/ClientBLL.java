package BusinessLogic;

import BusinessLogic.validators.EmailValidator;
import BusinessLogic.validators.Validator;
import DataAccess.ClientDAO;
import Model.Client;
import Start.Reflection;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The ClientBLL class provides business logic functionalities related to Client entities.
 * The packages DataAccess {@link DataAccess} and Model {@link Model} are used to get the
 *  data of the Orders as the attributes and the queries to be executed in the database.
 * @author Sarafinceanu Denisa
 */
public class ClientBLL {
    private List<Validator<Client>> validators;
    private ClientDAO clientDAO;

    /**
     * Constructs a new ClientBLL object and initializes validators and DAO.
     */
    public ClientBLL() {
        validators = new ArrayList<>();
        validators.add(new EmailValidator());

        clientDAO = new ClientDAO();
    }

    /**
     * Finds a client by ID.
     * @param ID The ID of the client to find.
     * @return The Client object found.
     * @throws NoSuchElementException if the client with the given ID is not found.
     */
    public Client findClientById(Integer ID) {
        Client client = clientDAO.findByID(ID);
        if (client == null) {
            throw new NoSuchElementException("The client with id =" + ID + " was not found!");
        }
        return client;
    }

    /**
     * Retrieves all clients.
     * @return A list of all Client objects.
     */
    public List<Client> findAllClients(){
        return clientDAO.findAll();
    }

    /**
     * Inserts a new client into the database.
     * @param client The Client object to insert.
     */
    public void insertClient(Client client){
        for(Validator<Client> validator : validators){
            validator.validate(client);
        }
        clientDAO.insert(client);
    }

    /**
     * Deletes a client from the database.
     * @param ID The ID of the client to delete.
     */
    public void deleteClient(Integer ID){
        clientDAO.delete(ID);
    }

    /**
     * Updates an existing client in the database.
     * @param client The Client object to update.
     */
    public void updateClient(Client client){
        for(Validator<Client> validator : validators){
            validator.validate(client);
        }
        clientDAO.update(client);
    }
}