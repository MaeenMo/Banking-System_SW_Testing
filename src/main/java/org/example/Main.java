package org.example;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

public class Main extends Application {
    Stage MainStage;;
    Scene loginScene;
    @Override
    public void start(Stage mainStage){
        MainStage=mainStage;
        mainSetup();
        mainStage.show();
    }
    public void mainSetup(){
        MainStage.setTitle("Bank System - Login");

        MainStage.setResizable(false);
        MainStage.getIcons().add(new Image(Main.class.getResourceAsStream("BankIco.png")));

        loginWindow();
        MainStage.setScene(loginScene);
        MainStage.initStyle(StageStyle.TRANSPARENT);
    }

    public void loginWindow(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            loginScene = new Scene(root, 1280, 720, Color.TRANSPARENT);
            loginScene.getStylesheets().add(this.getClass().getResource("styling.css").toExternalForm());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}