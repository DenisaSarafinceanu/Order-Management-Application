package Connection;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;
import java.util.logging.Level;


/**
 * The ConnectionFactory class provides methods for creating and managing database connections.
 * @author Sarafinceanu Denisa
 */
public class ConnectionFactory {
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String DBURL = "jdbc:postgresql://localhost:5432/PT_Database";
    private static final String USER = "postgres";
    private static final String PASS = "denisa15";

    private static ConnectionFactory singleInstance = new ConnectionFactory();

    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private ConnectionFactory(){
        try{
            Class.forName(DRIVER);
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * Creates a database connection.
     * @return The Connection object representing the database connection.
     */
    private Connection createConnection(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(DBURL, USER, PASS);
        } catch (SQLException e){
            LOGGER.log(Level.WARNING, "An error occurred while trying to connect to the database");
        }
        return connection;
    }

    /**
     * Retrieves a database connection.
     * @return The Connection object representing the database connection.
     */
    public static Connection getConnection(){
        return singleInstance.createConnection();
    }

    /**
     * Closes the database connection.
     * @param connection The Connection object to be closed.
     */
    public static void close(Connection connection){
        if(connection != null){
            try{
                connection.close();
            } catch (SQLException e){
                LOGGER.log(Level.WARNING, "Error: Cannot close the connection");
            }
        }
    }

    /**
     * Closes the SQL statement.
     * @param statement The Statement object to be closed.
     */
    public static void close(Statement statement){
        if(statement != null){
            try{
                statement.close();
            } catch (SQLException e){
                LOGGER.log(Level.WARNING, "Error: Cannot close the statement");
            }
        }
    }

    /**
     * Closes the ResultSet.
     * @param resultSet The ResultSet object to be closed.
     */
    public static void close(ResultSet resultSet){
        if(resultSet != null){
            try{
                resultSet.close();
            } catch (SQLException e){
                LOGGER.log(Level.WARNING, "Error: Cannot close the resultSet");
            }
        }
    }
}
