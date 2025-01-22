/**
 * The Presentation package contains classes responsible for the graphical user interface (GUI)
 * presentation of the application.
 * <p>
 * The main class in this package is {@link Presentation}, which sets up the GUI presentation
 * by initializing the {@link ClientFrame} and {@link ClientController}, and setting up action
 * listeners for the buttons in the frame.
 * In the package {@link Model} is defined the Client and in package {@link BusinessLogic} are called
 * the functions to update the database based on the inserted client.
 * </p>
 */
package Presentation;

import BusinessLogic.ClientBLL;
import Model.Client;
import Start.Reflection;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
/**
 * The ClientController class is responsible for controlling user interactions with the client GUI.
 * It implements the {@link ActionListener} interface to handle button clicks and perform
 * corresponding actions.
 * @author Sarafinceanu Denisa
 */
public class ClientController implements ActionListener {
    private ClientFrame clientFrame;
    private ClientBLL clientBLL = new ClientBLL();
    /**
     * Constructs a new ClientController with the specified client frame.
     *
     * @param clientFrame The client frame associated with this controller.
     */
    public ClientController(ClientFrame clientFrame){

        this.clientFrame = clientFrame;
    }
    /**
     * Invoked when an action occurs. This method determines the action command
     * associated with the action event and delegates the corresponding action.
     *
     * @param e The action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("Add client")){
            addClient();
        }
        if(command.equals("Update client")){
            editClient();
        }
        if(command.equals("Delete client")){
            deleteClient();
        }
    }
    /**
     * Adds a new client based on the input fields in the client frame.
     * Displays a success message upon successful addition.
     */
    private void addClient(){
        String idText = clientFrame.getClientIDTextField().getText();
        Integer ID = Integer.parseInt(idText);
        String name = clientFrame.getClientNameTextField().getText();
        String address = clientFrame.getClientAddressTextField().getText();
        String email = clientFrame.getClientEmailTextField().getText();
        Client client = new Client(ID, name, address, email);
        clientBLL.insertClient(client);
        clientFrame.initializaTable();
        JOptionPane.showMessageDialog(null, client.toString() + " was successfully added!","SUCCESS", JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * Edits an existing client based on the input fields in the client frame.
     * Displays a success message upon successful update.
     */
    private void editClient(){
        String idText = clientFrame.getClientIDTextField().getText();
        Integer ID = Integer.parseInt(idText);
        String name = clientFrame.getClientNameTextField().getText();
        String address = clientFrame.getClientAddressTextField().getText();
        String email = clientFrame.getClientEmailTextField().getText();
        Client client = new Client(ID, name, address, email);
        clientBLL.updateClient(client);
        clientFrame.initializaTable();
        JOptionPane.showMessageDialog(null, client.toString() + " was successfully updated!","SUCCESS", JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * Deletes a client based on the input field in the client frame.
     * Displays a success message upon successful deletion.
     */
    private void deleteClient(){
        String idText = clientFrame.getDeleteClientTextField().getText();
        Integer ID = Integer.parseInt(idText);
        clientBLL.deleteClient(ID);
        clientFrame.initializaTable();
        JOptionPane.showMessageDialog(null, "Client [id=" + ID + "] was successfully deleted!","SUCCESS", JOptionPane.INFORMATION_MESSAGE);
    }
}
