package Presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The Controller class implements the {@link ActionListener} interface to handle user interactions
 * with the graphical user interface (GUI) components.
 * <p>
 * It directs the {@link View} to display the appropriate panel based on user actions such as
 * selecting clients, products, or orders.
 * </p>
 * The package Presentation {@link Presentation} makes the interface
 */
public class Controller implements ActionListener {
    private View view;
    private ClientFrame clientFrame;
    private ProductFrame productFrame;
    private OrdersFrame orderFrame;
    /**
     * Constructs a new Controller with the specified view.
     *
     * @param view The view associated with this controller.
     */
    public Controller(View view){
        this.view = view;
    }
    /**
     * Handles action events triggered by user interactions with GUI components.
     * Shows the corresponding panel based on the action command.
     *
     * @param e The action event representing the user interaction.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Clients":
                view.showClientPanel();
                break;
            case "Products":
                view.showProductPanel();
                break;
            case "Orders":
                view.showOrderPanel();
                break;
        }
    }
}
