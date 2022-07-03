package com.example.demo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;

public class HomeController {

    CommonObjects commonObjects = CommonObjects.getInstance();

    public void opGoItem(ActionEvent actionEvent) {
//        URL url = getClass().getClassLoader().getResource("home.fxml");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("item.fxml"));
//        Parent root1 = (Parent) fxmlLoader.load();
        try{

            AnchorPane panelHome = (AnchorPane) fxmlLoader.load();
            HBox mainBox = commonObjects.getMainBox();

            if (mainBox.getChildren().size() > 1){
                mainBox.getChildren().remove(1);
            }
            mainBox.getChildren().add(panelHome);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
