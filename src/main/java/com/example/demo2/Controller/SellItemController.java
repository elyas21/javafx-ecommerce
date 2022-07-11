package com.example.demo2.Controller;

import com.example.demo2.DatabaseConnectio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.sql.*;

public class SellItemController {

    @FXML
    private Label errorLabel;

    @FXML Label ItemSoldInfo;
    private int itemId;
    private int itemCount;
    private int soldCount;
    @FXML
    private Label errornowordLabel;

    @FXML
    private TextField howMuchTextField;

    @FXML
    private AnchorPane itemAnchor;

    @FXML
    private ImageView itemImage;

    @FXML
    private Label priceLabel;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchFiled;

    @FXML
    private Label stockLabel;

    @FXML
    private Label nameLabel;

    @FXML
    void opSearch(ActionEvent event) {
        errornowordLabel.setText("");
        errorLabel.setText("");
        if (searchFiled.getText().isBlank() == false) {

            searchItem(searchFiled.getText());

        } else {
            errornowordLabel.setText("please enter Search");
        }
    }

    @FXML
    void opSell(ActionEvent event) throws SQLException {
        System.out.println("sell");

        updateDb(this.itemId, this.itemCount, this.soldCount);
    }

    private void searchItem(String iteId) {
        DatabaseConnectio DBcon = new DatabaseConnectio();
        Connection cond = DBcon.getConnection();
        this.errorLabel.setText("");
        this.errornowordLabel.setText("");
        this.priceLabel.setText("");
        this.stockLabel.setText("");
//        errorLabel.setText("invalid input");

        try {
            System.out.println(iteId);
            Statement statement = cond.createStatement();
            ResultSet queryResult = statement.executeQuery("select * from Item where name= '" + iteId + "'");
            System.out.println("select * from Item where name= '" + iteId + "'");
            this.priceLabel.setText("");
            this.nameLabel.setText("");
            this.stockLabel.setText("");
            while (queryResult.next()) {
                System.out.println(queryResult.getString("price"));
                System.out.println(queryResult.getString("name"));
                System.out.println(queryResult.getString("brand"));
                this.priceLabel.setText(queryResult.getString("price"));
                this.nameLabel.setText(queryResult.getString("name"));
                this.stockLabel.setText(queryResult.getString("stockAmount"));
                searchFiled.setText("");
                this.displayImage(queryResult.getString("id"));
                this.itemId = queryResult.getInt("id");
                this.itemCount = queryResult.getInt("stockAmount");
                this.soldCount = queryResult.getInt("soldAmount");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void displayImage(String id) {
        System.out.println("imgs/item/item" + id + ".jpeg");
        String iPath = "file:///media/black/epart/project/java/demo2/src/main/resources/com/example/demo2/imgs/item/item" + id + ".jpeg";
        Image image = new Image(iPath);
        itemImage.setImage(image);
    }

    private void updateDb(int id, int count, int soldCount) throws SQLException {
        System.out.println(id);
        System.out.println(count);
        System.out.println(id + count);
        System.out.println(soldCount);
        System.out.println(Integer.parseInt(this.howMuchTextField.getText()));

        DatabaseConnectio DBcon = new DatabaseConnectio();
        Connection cond = DBcon.getConnection();


        String query = "update Item set stockAmount = ?, soldAmount = ?  where id = ?";
        PreparedStatement preparedStmt = cond.prepareStatement(query);
        int stockAmoutNow = count - Integer.parseInt(this.howMuchTextField.getText());
        int soldAmoutNow = soldCount + Integer.parseInt(this.howMuchTextField.getText());
        preparedStmt.setInt(1, stockAmoutNow);
        preparedStmt.setInt(2, soldAmoutNow);
        preparedStmt.setInt(3, id);

        if (preparedStmt.executeUpdate() == 1) {
            System.out.println("ok");


            this.ItemSoldInfo.setText(this.howMuchTextField.getText()+"" +this.nameLabel.getText() +" sold");

            this.priceLabel.setText("");
            this.nameLabel.setText("");
            this.stockLabel.setText("");
            itemImage.setImage(null);

        }
    }
}
