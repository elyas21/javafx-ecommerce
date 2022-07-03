package com.example.demo2.Controller;

import com.example.demo2.CommonObjects;
import com.example.demo2.DatabaseConnectio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class getItemsController implements Initializable {

    @FXML
    private TableColumn<ItemModel, String> ItemBrand;

    @FXML
    private TableColumn<ItemModel, String> ItemId;

    @FXML
    private TableColumn<ItemModel, String> ItemName;

    @FXML
    private TableColumn<ItemModel, String> ItemPrice;

    @FXML
    private TableColumn<ItemModel, String> ItemSold;

    @FXML
    private TableColumn<ItemModel, String> ItemStok;

    @FXML
    private TableView<ItemModel> ItemTable;
    ObservableList<ItemModel> data = FXCollections.observableArrayList();

    @Override
    public void  initialize(URL url, ResourceBundle rb){

        ItemId.setCellValueFactory(new PropertyValueFactory<ItemModel,String>("id"));
        ItemBrand.setCellValueFactory(new PropertyValueFactory<ItemModel,String>("brand"));
        ItemName.setCellValueFactory(new PropertyValueFactory<ItemModel,String>("name"));
        ItemPrice.setCellValueFactory(new PropertyValueFactory<ItemModel,String>("price"));
        ItemSold.setCellValueFactory(new PropertyValueFactory<ItemModel,String>("sold"));
        ItemStok.setCellValueFactory(new PropertyValueFactory<ItemModel,String>("stok"));
        this.getItems();
        ItemTable.setItems(data);
    }



    public void getItems(){
        DatabaseConnectio DBcon = new DatabaseConnectio();
        Connection cond = DBcon.getConnection();


        String verifyLogin = " select * from Item";
        try {
            Statement statement  = cond.createStatement();
            ResultSet queryResult = statement.executeQuery("select * from Item");
//                    .executeQuery(verifyLogin);

            while (queryResult.next()){
//             this.data(new ItemModel("001","book","mega","30","34","32"));
                this.data.add(new ItemModel(queryResult.getString("id"),
                        queryResult.getString("name"),
                        queryResult.getString("brand"),
                        queryResult.getString("price"),
                        queryResult.getString("stockAmount"),
                        queryResult.getString("stockAmount")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}