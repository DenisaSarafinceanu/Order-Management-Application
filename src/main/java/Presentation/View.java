/**
 * The Presentation package contains classes responsible for the graphical user interface (GUI)
 * presentation and control logic of the application.
 * <p>
 * It includes classes such as {@link View}, {@link ClientFrame}, {@link ProductFrame},
 * {@link OrdersFrame}, {@link Controller}, and other GUI-related components.
 * </p>
 */
package Presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
/**
 * The View class represents the main graphical user interface (GUI) of the application.
 * <p>
 * It extends {@link JFrame} to create a window where users can interact with the application.
 * The View class contains buttons for navigating to different panels such as client, product,
 * and order panels.
 * </p>
 * The package Presentation {@link Presentation} is used to get the data for the elements in
 * the interface.
 */
public class View extends JFrame {
    private JPanel contentPane;
    private JPanel inputPanel;
    private JButton clientButton;
    private JButton productButton;
    private JButton orderButton;
    Controller controller;
    private ClientFrame clientFrame = new ClientFrame();
    private ProductFrame productFrame = new ProductFrame();
    private OrdersFrame orderFrame = new OrdersFrame();
    public View(String name){
        super(name);
        this.prepareGUI();
        this.controller = new Controller(this);
        this.setupButtonActions();
    }

    public void prepareGUI(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);

        this.contentPane = new JPanel(new BorderLayout());
        this.contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.prepareInputPanel();
        this.contentPane.add(this.inputPanel, BorderLayout.CENTER);

        this.setContentPane(this.contentPane);

    }

    public void prepareInputPanel(){
        this.inputPanel = new JPanel(new GridLayout(9, 2, 10, 10));

        this.clientButton = new JButton("Clients");
        this.inputPanel.add(this.clientButton);

        this.productButton = new JButton("Products");
        this.inputPanel.add(this.productButton);

        this.orderButton = new JButton("Orders");
        this.inputPanel.add(this.orderButton);
    }

    public void setupButtonActions() {
        this.clientButton.addActionListener(controller);
        this.clientButton.setActionCommand("Clients");

        this.productButton.addActionListener(controller);
        this.productButton.setActionCommand("Products");

        this.orderButton.addActionListener(controller);
        this.orderButton.setActionCommand("Orders");
    }

    public void showClientPanel(){
        this.clientFrame.prepareClientGUI();
        this.clientFrame.initializaTable();
    }
    public void showProductPanel(){
        this.productFrame.prepareProductGUI();
        this.productFrame.initializeTable();
    }
    public void showOrderPanel(){
        this.orderFrame.prepareOrderGUI();
        //this.orderFrame.initializeClientTable();
        //this.orderFrame.initializeProductTable();
        this.orderFrame.initializeTables();
    }
}
