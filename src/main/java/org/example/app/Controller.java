package org.example.app;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class Controller {

    private final DB db = new DB();

    @FXML
    private TextField product_field;
    @FXML
    private ListView<String> listView = new ListView<>();

    @FXML
    void initialize() {
        loadInfo();
    }

    public void buttonAddClick(){
        try{
            if(!product_field.getText().trim().isEmpty()) {
                db.addProduct(product_field.getText());
                product_field.setText("");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        loadInfo();
    }

    private void loadInfo() {
        try {
            ArrayList<String> products = db.getProducts();

            ObservableList<String> items = FXCollections.observableArrayList();

            for (String product : products) {
                items.add(product);
            }

            listView.setItems(items);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void buttonClearClick(ActionEvent actionEvent) throws SQLException {
        db.clear();

        db.autoIncrement();

        loadInfo();
    }

    public void buttonDeleteClick(ActionEvent actionEvent) throws SQLException {
        String selectedItem = listView.getSelectionModel().getSelectedItem();

        String[] separatedValues = selectedItem.split("\\. ");

        db.deleteItem(separatedValues[1]);

        db.autoIncrement();

        loadInfo();
    }
}
