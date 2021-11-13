package esi.sisad.seman.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class HomeController {
    @FXML
    private StackPane root_home;
    @FXML
    private JFXButton book_btn;
    @FXML
    private JFXButton graphic_btn;

    @FXML
    private void initialize() throws IOException {
        StackPane book = FXMLLoader.load(getClass().getResource("/templates/country.fxml"));
        root_home.getChildren().setAll(book);
    }

    @FXML
    private void onChangeView(ActionEvent event) throws IOException {
        JFXButton button = (JFXButton) event.getSource();
        switch (button.getId()) {
            case "book_btn":
                StackPane book = FXMLLoader.load(getClass().getResource("/templates/country.fxml"));
                root_home.getChildren().setAll(book);
                break;
            case "graphic_btn":
                StackPane statistic = FXMLLoader.load(getClass().getResource("/templates/graph.fxml"));
                root_home.getChildren().setAll(statistic);
                break;
            default:
                System.out.println("Nothing to do!");
        }
    }
}
