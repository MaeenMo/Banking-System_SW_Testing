package org.example;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXPasswordField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginController {
    static Bank bank = new Bank();
    @FXML
    Button focus;
    @FXML
    JFXTextField usernameField;
    @FXML
    JFXPasswordField passwordField;
    @FXML
    JFXTextField passwordShown;
    @FXML
    Button showPassBtn;
    @FXML
    Label errmsgLable;
    @FXML
    JFXButton loginBtn;
    @FXML
    ImageView hideIco;
    @FXML
    ImageView showIco;
    @FXML
    ImageView imgView;
    boolean visible = false;
    @FXML
    AnchorPane anchorPane;
    @FXML
    HBox hbox;
    @FXML
    public void closeWindow(){
        System.exit(0);
    }
    @FXML
    public void minimizeWindow(){
        ((Stage) anchorPane.getScene().getWindow()).setIconified(true);
    }
    private double xOffset = 0;
    private double yOffset = 0;

    public void enter1(KeyEvent e){
        if (e.getCode().equals(KeyCode.ENTER))
            passwordField.requestFocus();
    }
    public void enter2(KeyEvent e){
        if (e.getCode().equals(KeyCode.ENTER)) {
            loginBtn.fire();
            loginBtn.requestFocus();
        }
    }
    public void login(){
        Account user = validate(usernameField, passwordField, passwordShown, errmsgLable);
        if(user != null) {
            homeScreen(user);
        }
    }
    public static Account validate(TextField username, PasswordField password, TextField passShown , Label msg){
        if (!checkFields(username,password,passShown,msg)){
            return null;
        }
        for (Account acc : Bank.accounts){
            if (acc.getAccountId().equals(username.getText())){
                if (password.getText().equals(acc.getPassword()) || passShown.getText().equals(acc.getPassword())) {
                    return acc;
                }else {
                    msg.setText("Wrong Credentials");
                    msg.setStyle("-fx-text-fill:red");
                }
                username.clear();
                passShown.clear();
                password.clear();
            } else {
                msg.setText("Wrong Credentials");
                msg.setStyle("-fx-text-fill:red");
            }
        }
        return null;
    }
    public static boolean checkFields(TextField username, PasswordField password,TextField passShown , Label msg) {
        if (password.getText().isEmpty())
            password.setText(passShown.getText());
        if (username.getText().isEmpty() && password.getText().isEmpty()) {
            msg.setText("Please enter your Username & Password");
            msg.setStyle("-fx-text-fill:red");
            return false;
        } else if (username.getText().isEmpty()) {
            msg.setText("Please enter your Username");
            msg.setStyle("-fx-text-fill:red");
            return false;
        } else if (password.getText().isEmpty() && passShown.getText().isEmpty()) {
            msg.setText("Please enter your Password");
            msg.setStyle("-fx-text-fill:red");
            return false;
        }
        return true;
    }
    public void homeScreen(Account User){
        try {
//            new Alert("Welcome "+ User.getAccountOwner(),"Login Successful!","green");
            HomeController.CurrentUser = Bank.getAccount(User.getAccountId());
            Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Scene homeScene = new Scene(root, 1280, 750, Color.TRANSPARENT);
            Stage window = (Stage)(loginBtn.getScene().getWindow());
            window.setTitle("Bank System - Home: " + User.getAccountOwner());
            window.setScene(homeScene);
            window.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void showPass(){
        if(!visible){
            hideIco.setVisible(false);
            showIco.setVisible(true);
            passwordShown.setText(passwordField.getText());
            passwordShown.setVisible(true);
            passwordField.setVisible(false);
            visible=true;
        }else {
            hideIco.setVisible(true);
            showIco.setVisible(false);
            passwordField.setText(passwordShown.getText());
            passwordField.setVisible(true);
            passwordShown.setVisible(false);
            visible=false;
        }
    }
    public void initiateAccounts(){
        bank.addAccount(new Account("12345678", "John Doe", 1000.0, "pass1"));
        bank.addAccount(new Account("23456789", "Jane Smith", 1500.0, "pass2"));
        bank.addAccount(new Account("34567890", "Alice Johnson", 2000.0, "pass3"));
        bank.addAccount(new Account("45678901", "Bob Brown", 1200.0, "pass4"));
        bank.addAccount(new Account("56789012", "Emma Lee", 1800.0, "pass5"));
        bank.addAccount(new Account("67890123", "Michael Wilson", 1700.0, "pass6"));
        bank.addAccount(new Account("78901234", "Sophia Garcia", 1600.0, "pass7"));
        bank.addAccount(new Account("89012345", "David Martinez", 1400.0, "pass8"));
        bank.addAccount(new Account("90123456", "Olivia Hernandez", 1300.0, "pass9"));
        bank.addAccount(new Account("01234567", "Ethan Taylor", 1100.0, "pass10"));
    }
    public void initialize(){
        usernameField.setStyle("-fx-text-fill: white;-fx-prompt-text-fill: white;");
        passwordField.setStyle("-fx-text-fill: white;-fx-prompt-text-fill: white;");
        passwordShown.setStyle("-fx-text-fill: white;-fx-prompt-text-fill: white;");
        if (Bank.accounts.size() == 0)
            initiateAccounts();
        anchorPane.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        hbox.setOnMouseDragged(event -> {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
        if (imgView != null){
            imgView.setImage(new Image("file:src/main/resources/org/example/loginBG.jpg",false));
            Rectangle clip = new Rectangle();
            clip.setWidth(1280.0);
            clip.setHeight(720);
            clip.setArcHeight(40);
            clip.setArcWidth(40);
            clip.setStroke(Color.BLACK);
            imgView.setClip(clip);
            SnapshotParameters parameters = new SnapshotParameters();
            parameters.setFill(Color.TRANSPARENT);
            WritableImage image = imgView.snapshot(parameters, null);
            imgView.setClip(null);
            imgView.setImage(image);
        }
    }
}