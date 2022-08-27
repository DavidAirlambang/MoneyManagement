package com.tugasbesar.tugasbesar;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MenuController {
    public JFXButton dashboardView;
    public JFXButton transactionView;
    public JFXButton balanceView;
    public Pane layerBeranda;

    public void initialize(){

        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("dashboard-view.fxml"));
            layerBeranda.getChildren().removeAll();
            layerBeranda.getChildren().setAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void dashboardView(ActionEvent actionEvent) throws IOException {

        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("dashboard-view.fxml"));
            layerBeranda.getChildren().removeAll();
            layerBeranda.getChildren().setAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void transactionView(ActionEvent actionEvent) {

        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("transaction-view.fxml"));
            layerBeranda.getChildren().removeAll();
            layerBeranda.getChildren().setAll(fxml);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void balanceView(ActionEvent actionEvent) {
    }
}
