package Start;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
        * The Reflection class provides methods for retrieving properties of objects using reflection.
        * @author Sarafinceanu Denisa
        * @param <T> The type of objects whose properties are being retrieved.
        */
public class Reflection<T> {

    /**
     * Retrieves properties of objects using reflection and creates a JTable to display the data.
     *
     * @param objects A list of objects from which properties are to be retrieved.
     * @return A JTable containing the properties of the objects.
     */
    public JTable retrieveProperties(List<T> objects){

        // Determine the number of columns based on the fields of the first object
        String[] col = new String[objects.get(0).getClass().getDeclaredFields().length];

        // Create a 2D array to store data for each object
        Object[][] data = new Object[objects.size()][col.length];

        int indexData = 0;
        // Iterate through each object
        for(Object object: objects){
            int j = 0;
            // Iterate through each field of the object
            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value;
                try {
                    // Store the field name as column header
                    col[j]=field.getName();
                    // Retrieve the value of the field for the current object
                    value = field.get(object);
                    // Store the value in the data array
                    data[indexData][j] = value;
                    j++;

                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            indexData++;
        }

        // Create a TableModel with the retrieved data and column headers
        TableModel model = new DefaultTableModel(data, col)
        {
            // Make the cells non-editable
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };

        // Create a JTable with the TableModel and return it
        JTable table = new JTable(model);
        return table;
    }
}
