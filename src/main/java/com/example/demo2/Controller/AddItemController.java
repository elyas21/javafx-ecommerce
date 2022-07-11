package com.example.demo2.Controller;

import com.example.demo2.DatabaseConnectio;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.Statement;

public class AddItemController {

    @FXML
    private TextField ItemBrand;

    @FXML
    private Button chooseImage;

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

    public void onChooseImage() {

    }
    public void saveFile(){
//                FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Save file");
//        fileChooser.setInitialFileName("defaultFileName");
//        File savedFile = fileChooser.showSaveDialog(savedStage);
//
//        if (savedFile != null) {
//
//            try {
//                saveFileRoutine(savedFile);
//            }
//            catch(IOException e) {
//
//                e.printStackTrace();
//                actionStatus.setText("An ERROR occurred while saving the file!");
//                return;
//            }
//
//            actionStatus.setText("File saved: " + savedFile.toString());
//        }
//        else {
//            actionStatus.setText("File save cancelled.");
//        }
    }

    public void validateItemAdd() {
        DatabaseConnectio DBcon = new DatabaseConnectio();
        Connection cond = DBcon.getConnection();

        String itemName = ItemName.getText();
        String brandName = ItemBrand.getText();
        String price = ItemPrice.getText();
        String stockAmount = ItemPrice.getText();
        String soldItem = ItemPrice.getText();


//        String verifyLogin = " select count(1) from user_account where user_name = '" + userNameTextFiled.getText() + "' And password ='" + userNamePasswordFiled.getText() + "'";
        String itemsToAdd = " insert into Item (name,brand,price,imgUrl,stockAmount,soldAmount) values ('";
        String values = itemName + "','" + brandName + "','" + price + "','" + "imgUrl" + "','" + stockAmount + "','" + 0 + "')";

        String addQuery = itemsToAdd + values;
        try {
            System.out.println(addQuery);
            Statement statement = cond.createStatement();
            statement.executeUpdate(addQuery);

            ItemAddMessegeLabel.setText(itemName + "Add successfully");
            System.out.println("success");

            ItemName.setText("");
            ItemBrand.setText("");
            ItemPrice.setText("");
            ItemPrice.setText("");
            ItemPrice.setText("");

        } catch (Exception e) {
            System.out.println("failure");
            throw new RuntimeException(e);
        }
    }

}