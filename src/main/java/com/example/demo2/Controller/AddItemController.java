package com.example.demo2.Controller;

import com.example.demo2.CommonObjects;
import com.example.demo2.DatabaseConnectio;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddItemController {

    @FXML
    private TextField ItemBrand;

    @FXML
    private TextField ItemImageUrl;

    @FXML
    private TextField ItemName;

    @FXML
    private TextField ItemPrice;

    @FXML
    private Label ItemNameErrorLabel;

    @FXML
    private Label ItemBrandErrorLabel;
    @FXML
    private Label ItemAddMessegeLabel;

    @FXML
    private TextField ItemStok;

    @FXML
    private Button OnAdd;

    public void OnItemAddd() {
        boolean notValidInput = false;
        if (ItemBrand.getText().isBlank() == true) {
            ItemBrandErrorLabel.setText("Brand is not valid");
            notValidInput = true;
        }
        if (ItemName.getText().isBlank() == true) {
            ItemNameErrorLabel.setText("Name is not valid");
            notValidInput = true;
        }
        if (notValidInput == false) {

            validateItemAdd();
        }
    }



    public void validateItemAdd(){
        DatabaseConnectio DBcon = new DatabaseConnectio();
        Connection cond = DBcon.getConnection();

        String itemName = ItemName.getText();
        String brandName = ItemBrand.getText();
        String price = ItemPrice.getText();
        String stockAmount = ItemPrice.getText();
        String soldItem = ItemPrice.getText();
        String imgUrl = ItemImageUrl.getText();


//        String verifyLogin = " select count(1) from user_account where user_name = '" + userNameTextFiled.getText() + "' And password ='" + userNamePasswordFiled.getText() + "'";
        String itemsToAdd = " insert into Item (name,brand,price,imgUrl,stockAmount,soldAmount) values ('";
        String values = itemName +"','" + brandName+"','" + price +"','" +"imgUrl"+"','" +stockAmount+"','" + 0 +"')";

        String addQuery = itemsToAdd + values;
        try {
            System.out.println(addQuery);
            Statement statement  = cond.createStatement();
            statement.executeUpdate(addQuery);

                ItemAddMessegeLabel.setText(itemName + "Add successfully");
                System.out.println("success");

            ItemName.setText("");
            ItemBrand.setText("");
            ItemPrice.setText("");
            ItemPrice.setText("");
            ItemPrice.setText("");
            ItemImageUrl.setText("");

        } catch (Exception e) {
            System.out.println("failure");
            throw new RuntimeException(e);
        }
    }

}