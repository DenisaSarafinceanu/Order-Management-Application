package Start;

import Presentation.View;

import javax.swing.*;

/**
        * The Main class is the entry point of the application. The package Presentation {@link Presentation}
        * is used to have the opportunity to access the GUI.
        * @author Sarafinceanu Denisa
        */
public class Main {

    /**
     * The main method initializes the main frame of the application and makes it visible.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Create a new View frame with the title "Start Page"
        JFrame frame = new View("Start Page");

        // Set the default close operation to exit the application when the frame is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Make the frame visible
        frame.setVisible(true);
    }
}