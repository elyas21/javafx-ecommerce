package com.example.demo2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.stage.StageStyle;


import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.net.URL;
public class LoginController implements Initializable {


    @FXML
    private Button cancelButton;
    @FXML
    private Button loginButton;

    @FXML
    private  Label loginMessageLabel;
    @FXML
    public ImageView brandingImageView;
    @FXML
    public ImageView lockImageView;
    @FXML
    private TextField userNameTextFiled;
    @FXML
    private PasswordField userNamePasswordFiled;

    @Override
    public  void initialize(URL url, ResourceBundle resourceBundle){
//        File brandingFile = new File("imgs/cart1.jpg");
//        Image brandingImage = new Image(brandingFile.toURI().toString());
//        System.out.println(brandingImage);
//        brandingImageView.setImage(brandingImage);

        File lockFile = new File("imgs/lock.jpg");
        Image lockImage = new Image(lockFile.toURI().toString());
        System.out.println(lockImage);
        lockImageView.setImage(lockImage);
    }

    public void loginButtonOnAction(ActionEvent e){


        if(userNameTextFiled.getText().isBlank() == false &&
                userNamePasswordFiled.getText().isBlank() == false) {
            validateLogin();
        } else {

            loginMessageLabel.setText("please enter username and password");
        }
    }
    public void validateLogin(){
        DatabaseConnectio DBcon = new DatabaseConnectio();
        Connection cond = DBcon.getConnection();

        String verifyLogin = " select count(1) from user_account where user_name = '" + userNameTextFiled.getText() + "' And password ='" + userNamePasswordFiled.getText() + "'";
        try {
            Statement statement  = cond.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()){
                if(queryResult.getInt(1) == 1){
                    loginMessageLabel.setText("congratulations");
                    final Stage oldstage = (Stage) loginButton.getScene().getWindow();
                    oldstage.close();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
                    HBox mainBox = (HBox) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.DECORATED);
                    stage.setTitle("Ecommerce");
                    stage.setScene(new Scene(mainBox, 900, 800));
                    stage.show();

                    CommonObjects commonObjects = CommonObjects.getInstance();
                    commonObjects.setMainBox(mainBox);

                } else {
                    loginMessageLabel.setText("invalid input");

                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public  void onCancle(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();

    }
}