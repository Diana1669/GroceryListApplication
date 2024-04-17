package org.example.app;
import java.sql.*;
import java.util.ArrayList;


public class DB {
    public static final String NAME_USER = "root";
    public static final String PASSWORD = "";
    public static final String URL = "jdbc:mysql://localhost:3306/grocery_list";
    public static Connection connection;
    public static Statement statement;

    static {
        try{
            connection = DriverManager.getConnection(URL, NAME_USER, PASSWORD);
        } catch (SQLException throwables){
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }

    static {
        try {
            statement = connection.createStatement();
        } catch (SQLException throwables){
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void addProduct(String name) throws SQLException {
        statement.executeUpdate("INSERT INTO grocery_list (product) value ('" + name + "')");
    }

    public ArrayList<String> getProducts() throws SQLException {
        ArrayList<String> result = new ArrayList<String>();
        ResultSet rS = statement.executeQuery("SELECT * FROM grocery_list");
        int i = 1;
        while(rS.next()){
            result.add(i + ". " + rS.getString(2));
            i++;
        }

        return result;
    }

    public void clear() throws SQLException {
        statement.executeUpdate("DELETE FROM grocery_list");
    }

    public void autoIncrement() throws SQLException {
        statement.executeUpdate("ALTER TABLE grocery_list AUTO_INCREMENT = 1;");
    }

    public void deleteItem(String product) throws SQLException {
        statement.executeUpdate("DELETE FROM grocery_list WHERE product LIKE '" + product + "'");
    }
}
