package com.example.demo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;

public class MainController {
    @FXML
    private HBox mainBox;

    @FXML
    public  void  initialize(){
            opHome();
    }
    public void opAddItems() {

        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("addItem.fxml"));
        try{

            AnchorPane panelHome = (AnchorPane) fxmlLoader1.load();

            if (mainBox.getChildren().size() > 1){
                mainBox.getChildren().remove(1);
            }
            mainBox.getChildren().add(panelHome);
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public  void opSellItem(){
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("sellItem.fxml"));
        try{

            AnchorPane PaneGetItems = (AnchorPane) fxmlLoader1.load();

            if (mainBox.getChildren().size() > 1){
                mainBox.getChildren().remove(1);
            }
            mainBox.getChildren().add(PaneGetItems);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void opGetItems(ActionEvent actionEvent) {

        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("getItems.fxml"));
        try{

            AnchorPane PaneGetItems = (AnchorPane) fxmlLoader1.load();

            if (mainBox.getChildren().size() > 1){
                mainBox.getChildren().remove(1);
            }
            mainBox.getChildren().add(PaneGetItems);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void opHome() {
        URL url = getClass().getClassLoader().getResource("home.fxml");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home.fxml"));
//        Parent root1 = (Parent) fxmlLoader.load();
        try{

            AnchorPane panelHome = (AnchorPane) fxmlLoader.load();

            if (mainBox.getChildren().size() > 1){
                mainBox.getChildren().remove(1);
            }
            mainBox.getChildren().add(panelHome);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
