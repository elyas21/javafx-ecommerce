package com.example.demo2.Controller;

import com.example.demo2.DatabaseConnectio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class getItemsController implements Initializable {

    ObservableList<ItemModel> data = FXCollections.observableArrayList();

    public int index;
    @FXML
    private TableColumn<ItemModel, String> ItemBrand;
    @FXML
    private TableColumn<ItemModel, String> ItemId;
    @FXML
    private TableColumn<ItemModel, String> ItemName;

    @FXML
    private TableColumn<ItemModel, String> ItemSold;
    @FXML
    private TableColumn<ItemModel, String> ItemPrice;
    @FXML
    private TextField itemNameForm;
    @FXML
    private TextField itemBrandForm;
    @FXML
    private TableColumn<ItemModel, String> update;
    @FXML
    private TableColumn<ItemModel, String> ItemStok;
    @FXML
    private TableView<ItemModel> ItemTable;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ItemId.setCellValueFactory(new PropertyValueFactory<ItemModel, String>("id"));
        ItemBrand.setCellValueFactory(new PropertyValueFactory<ItemModel, String>("brand"));
        ItemName.setCellValueFactory(new PropertyValueFactory<ItemModel, String>("name"));
        ItemPrice.setCellValueFactory(new PropertyValueFactory<ItemModel, String>("price"));
        ItemSold.setCellValueFactory(new PropertyValueFactory<ItemModel, String>("sold"));
        ItemStok.setCellValueFactory(new PropertyValueFactory<ItemModel, String>("stok"));
        this.getItems();
        ItemTable.setItems(data);
    }


    public void getItems() {
        DatabaseConnectio DBcon = new DatabaseConnectio();
        Connection cond = DBcon.getConnection();


        String verifyLogin = " select * from Item";
        try {
            Statement statement = cond.createStatement();
            ResultSet queryResult = statement.executeQuery("select * from Item");
//                    .executeQuery(verifyLogin);

            while (queryResult.next()) {
//             this.data(new ItemModel("001","book","mega","30","34","32"));
                this.data.add(new ItemModel(queryResult.getString("id"),
                        queryResult.getString("name"),
                        queryResult.getString("brand"),
                        queryResult.getString("price"),
                        queryResult.getString("stockAmount"),
                        queryResult.getString("soldAmount")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void opUpdate() {
        this.index = ItemTable.getSelectionModel().getSelectedIndex();
        ItemTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                ItemTable.getSelectionModel().clearSelection();
            }
            System.out.println(newSelection.getBrand());
            this.itemNameForm.setText(newSelection.getName());
            this.itemBrandForm.setText(newSelection.getBrand());

        });

    }
    public void opRemove() {
        this.index = ItemTable.getSelectionModel().getSelectedIndex();
        ItemTable.getItems().remove(this.index);
    }

    public void onSubmit() {
//        tbi
    }
}