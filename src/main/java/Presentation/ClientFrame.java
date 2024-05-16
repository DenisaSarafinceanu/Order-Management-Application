/**
 * The Presentation package {@link Presentation} contains classes responsible for the graphical user interface (GUI)
 * presentation of the application.
 * <p>
 * It includes the {@link ClientFrame} class, which represents the main frame for managing clients,
 * and the {@link ClientController} class, which handles user interactions with the client GUI.
 * </p>
 * In the package {@link Model} is defined the Client and in package {@link BusinessLogic} are called
 *  * the functions to update the database based on the inserted client.
 * The package Start {@link Start} uses the Reflection function.
 */
package Presentation;

import BusinessLogic.ClientBLL;
import Model.Client;
import Start.Reflection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
/**
 * The ClientFrame class represents the main frame for managing clients in the application GUI.
 * It extends {@link JFrame} and contains various components such as text fields, buttons, and a table
 * for displaying client information.
 */
public class ClientFrame extends JFrame {
    private JTextField clientIDTextField;
    private JTextField clientNameTextField;
    private JTextField clientAddressTextField;
    private JTextField clientEmailTextField;
    private JPanel clientPanel;
    private JButton addClientButton;
    private JButton editClientButton;
    private JTextField deleteClientTextField;
    private JButton deleteClientButton;
    private JPanel tablePanel;

    public JTable table;
    private ClientController controller = new ClientController(this);
    /**
     * Prepares the graphical user interface (GUI) for managing clients.
     * Sets up the frame, panels, components, and event listeners.
     */

    public void prepareClientGUI(){
        this.setTitle("Clients");
        this.clientPanel = new JPanel();
        this.tablePanel = new JPanel();
        this.setVisible(true);
        this.setSize(700, 750);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        this.tablePanel.setLayout(null);
        this.tablePanel.setBounds(10,50,750,400);
        prepareClientFrame();
        initializaTable();
        this.setContentPane(this.clientPanel);
    }
    /**
     * Prepares the client frame layout, including text fields, buttons, and labels.
     */
    public void prepareClientFrame(){
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
        this.clientIDTextField = new JTextField(5);
        topPanel.add(this.clientIDTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        topPanel.add(new JLabel("Name:"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        this.clientNameTextField = new JTextField(15);
        topPanel.add(this.clientNameTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        topPanel.add(new JLabel("Address:"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        this.clientAddressTextField = new JTextField(15);
        topPanel.add(this.clientAddressTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        topPanel.add(new JLabel("Email:"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        this.clientEmailTextField = new JTextField(15);
        topPanel.add(this.clientEmailTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        topPanel.add(new JLabel(""), constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        this.addClientButton = new JButton("Add client");
        this.addClientButton.addActionListener(this.controller);
        this.editClientButton = new JButton("Update client");
        this.editClientButton.addActionListener(this.controller);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(this.addClientButton);
        buttonPanel.add(this.editClientButton);
        topPanel.add(buttonPanel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        topPanel.add(new JLabel("Delete the client with ID:"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        this.deleteClientTextField = new JTextField(15);
        topPanel.add(this.deleteClientTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        topPanel.add(new JLabel(""), constraints);

        constraints.gridx = 1;
        constraints.gridy = 6;
        this.deleteClientButton = new JButton("Delete client");
        this.deleteClientButton.addActionListener(this.controller);

        topPanel.add(this.deleteClientButton, constraints);

        this.add(topPanel, BorderLayout.NORTH);

        this.clientPanel.add(topPanel);
    }
    /**
     * Initializes the client table with data from the database.
     */
    public void initializaTable(){
        ClientBLL clientBLL = new ClientBLL();
        List<Client> allClients = clientBLL.findAllClients();
        Reflection reflection = new Reflection();
        table = reflection.retrieveProperties(allClients);

        prepareTable(table);

    }
    /**
     * Prepares the client table for display, including customization of column widths and
     * table header appearance.
     *
     * @param table1 The client table to be prepared.
     */
    public void prepareTable(JTable table1){
        this.tablePanel.removeAll();

        JTableHeader header = table1.getTableHeader();
        header.setBackground(Color.pink);
        JScrollPane pane = new JScrollPane(table1);
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table1.getColumnModel().getColumn(0).setPreferredWidth(70);
        table1.getColumnModel().getColumn(1).setPreferredWidth(150);
        table1.getColumnModel().getColumn(2).setPreferredWidth(250);
        table1.getColumnModel().getColumn(3).setPreferredWidth(250);

        this.tablePanel.add(pane);
        this.table = table1;

        this.tablePanel.setLayout(new GridLayout(1, 1));

        this.clientPanel.add(tablePanel);

        tablePanel.revalidate();
        tablePanel.repaint();
    }

    public JTextField getClientIDTextField() {
        return clientIDTextField;
    }

    public void setClientIDTextField(JTextField clientIDTextField) {
        this.clientIDTextField = clientIDTextField;
    }

    public JTextField getClientNameTextField() {
        return clientNameTextField;
    }

    public void setClientNameTextField(JTextField clientNameTextField) {
        this.clientNameTextField = clientNameTextField;
    }

    public JTextField getClientAddressTextField() {
        return clientAddressTextField;
    }

    public void setClientAddressTextField(JTextField clientAddressTextField) {
        this.clientAddressTextField = clientAddressTextField;
    }

    public JTextField getClientEmailTextField() {
        return clientEmailTextField;
    }

    public void setClientEmailTextField(JTextField clientEmailTextField) {
        this.clientEmailTextField = clientEmailTextField;
    }

    public JTextField getDeleteClientTextField() {
        return deleteClientTextField;
    }

    public void setDeleteClientTextField(JTextField deleteClientTextField) {
        this.deleteClientTextField = deleteClientTextField;
    }
}
