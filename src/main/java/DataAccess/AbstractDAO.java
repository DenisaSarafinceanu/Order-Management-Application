package DataAccess;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connection.ConnectionFactory;


/**
 * The AbstractDAO class provides a generic implementation for Data Access Objects (DAOs) that interact with a database.
 * The package Connection {@link Connection} is used to access the functions that create the queries in the database.
 * @author Sarafinceanu Denisa
 * @param <T> The type of object handled by the DAO.
 */
public class AbstractDAO<T> {

    // Logger for logging messages
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    // The class type handled by the DAO
    private final Class<T> type;

    /**
     * Constructs a new AbstractDAO.
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO(){
        // Get the type of T using reflection
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Creates a SQL SELECT query based on the specified field.
     *
     * @param field The field to filter the SELECT query.
     * @return The generated SELECT query.
     */
    public String createSelectQuery(String field){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT ");
        stringBuilder.append(" * ");
        stringBuilder.append(" FROM ");
        stringBuilder.append(type.getSimpleName());
        stringBuilder.append(" WHERE " + field + " =?");
        return stringBuilder.toString();
    }

    /**
     * Retrieves an object from the database based on its ID.
     *
     * @param ID The ID of the object to retrieve.
     * @return The object with the specified ID, or null if not found.
     */
    public T findByID(Integer ID){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("ID");
        try{
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, ID);
            resultSet = preparedStatement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch(SQLException e){
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findByID " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(preparedStatement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Creates a SQL SELECT query to retrieve all objects from the database.
     *
     * @return The generated SELECT query.
     */
    private String createSelectAllQuery(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT ");
        stringBuilder.append(" * ");
        stringBuilder.append(" FROM ");
        stringBuilder.append(type.getSimpleName());
        //stringBuilder.append(" ORDER BY ID ");
        return stringBuilder.toString();
    }

    /**
     * Retrieves all objects from the database.
     *
     * @return A list of all objects retrieved from the database.
     */
    public List<T> findAll(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();
        try{
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e){
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(preparedStatement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Creates objects based on the ResultSet obtained from the database.
     *
     * @param resultSet The ResultSet containing the data retrieved from the database.
     * @return A list of objects created from the ResultSet.
     */
    private List<T> createObjects(ResultSet resultSet){
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;

    }

    /**
     * Creates a SQL INSERT query for inserting an object into the database.
     *
     * @param t The object to be inserted into the database.
     * @return The generated INSERT query.
     */
    private String createInsertQuery(T t){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO ");
        stringBuilder.append(type.getSimpleName());
        stringBuilder.append(" (");
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("VALUES (");
        int i = 0;
        for(Field field : type.getDeclaredFields()){
            field.setAccessible(true);
            try{
                stringBuilder.append(field.getName());
                stringBuilder1.append("'");
                stringBuilder1.append(field.get(t));
                stringBuilder1.append("'");
                if(i < type.getDeclaredFields().length - 1){
                    stringBuilder.append(", ");
                    stringBuilder1.append(", ");
                }
                else {
                    stringBuilder.append(") ");
                    stringBuilder1.append(") ");
                }
                i++;
            } catch (IllegalArgumentException e){
                e.printStackTrace();
            } catch (IllegalAccessException e){
                e.printStackTrace();
            }
        }
        stringBuilder.append(stringBuilder1);
        return stringBuilder.toString();
    }

    /**
     * Inserts an object into the database.
     *
     * @param t The object to be inserted into the database.
     * @return The ID of the inserted object, or -1 if insertion fails.
     */
    public void insert(T t){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int ID = -1;
        try{
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(createInsertQuery(t), Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                ID = resultSet.getInt(1);
            }
        } catch (SQLException e){
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(preparedStatement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Creates a SQL DELETE query for deleting an object from the database.
     *
     * @param field The field used for filtering the DELETE query.
     * @return The generated DELETE query.
     */
    private String createDeleteQuery(String field){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DELETE ");
        stringBuilder.append(" FROM ");
        stringBuilder.append(type.getSimpleName());
        stringBuilder.append(" WHERE ");
        stringBuilder.append(field);
        stringBuilder.append("=?");
        return stringBuilder.toString();
    }

    /**
     * Deletes an object from the database based on its ID.
     *
     * @param ID The ID of the object to be deleted.
     */
    public void delete(Integer ID){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = createDeleteQuery("ID");
        try{
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, ID);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(preparedStatement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Creates a SQL UPDATE query for updating an object in the database.
     *
     * @param t The object to be updated.
     * @return The generated UPDATE query.
     */
    private String createUpdateQuery(T t){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE ");
        stringBuilder.append(type.getSimpleName());
        stringBuilder.append(" SET ");
        Integer i = 0, ID = 0;
        for(Field field : type.getDeclaredFields()){
            field.setAccessible(true);
            try{
                stringBuilder.append(field.getName());
                stringBuilder.append("=");
                stringBuilder.append("'");
                stringBuilder.append(field.get(t));
                stringBuilder.append("'");
                if(i < type.getDeclaredFields().length - 1){
                    stringBuilder.append(", ");
                }
                if(field.getName().equals("ID")){
                    ID = (Integer) field.get(t);
                }
                i++;
            } catch (IllegalArgumentException e){
                e.printStackTrace();
            } catch (IllegalAccessException e){
                e.printStackTrace();
            }
        }
        stringBuilder.append("WHERE ID=");
        stringBuilder.append(ID);
        return stringBuilder.toString();
    }

    /**
     * Updates an object in the database.
     *
     * @param t The object to be updated.
     * @return The updated object.
     */
    public T update(T t){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = createUpdateQuery(t);
        try{
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.executeUpdate();
        } catch (SQLException e){
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(preparedStatement);
            ConnectionFactory.close(connection);
        }
        return t;
    }
}
