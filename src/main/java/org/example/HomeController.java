package org.example;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.UnaryOperator;

public class HomeController {
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    Button tempBtnIgnore = new Button();
    Label tempLabelIgnore = new Label();
    Button tempBtnIgnoreRent = new Button();
    Button tempBtnIgnoreRemove = new Button();
    String tempStrIgnore;
    String tempStrIgnore1;
    public static Account CurrentUser;
    @FXML
    AnchorPane anchorPane;
    @FXML
    Button focusBtn;
    @FXML
    HBox hbox;
    @FXML
    ImageView imgView;
    @FXML
    Label usernameDisplay;
    @FXML
    Label accNoDisplay;
    @FXML
    Pane homePane;
    @FXML
    Pane transactionsPane;
    @FXML
    Pane loanPane;
    @FXML
    GridPane transactionsMenu;
    @FXML
    VBox loanMenu;
    @FXML
    Pane depositPane;
    @FXML
    TextField depAmount;
    @FXML
    Label depErrMsg;
    @FXML
    JFXButton depositBtn2;
    @FXML
    Pane withdrawPane;
    @FXML
    TextField withAmount;
    @FXML
    Label withErrMsg;
    @FXML
    JFXButton withdrawBtn2;
    @FXML
    Pane transferPane;
    @FXML
    TextField transAmount;
    @FXML
    TextField destAcc;
    @FXML
    Label transErrMsg;
    @FXML
    JFXButton transferBtn2;
    @FXML
    Pane viewTransacPane;
    @FXML
    TableView<Transaction> transacTableView;
    @FXML
    Pane disburseLoanPane;
    @FXML
    TextField disbAmount;
    @FXML
    JFXComboBox<String> takenLoan;
    @FXML
    Label disbLoanErrMsg;
    @FXML
    JFXButton disburseLoanBtn2;
    @FXML
    Pane viewLoansPane;
    @FXML
    TableView<Loan> loansTableView;
    @FXML
    Text accBalance1;
    @FXML
    Text accBalance2;
    @FXML
    Button logoutBtn;

    Scene loginScene;
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

    public void depEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            depositBtn2.fire();
        }
    }
    public void withEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            withdrawBtn2.fire();
        }
    }
    public void transEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            destAcc.requestFocus();
        }
    }
    public void destEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            transferBtn2.fire();
        }
    }
    public void disbEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            takenLoan.requestFocus();
            takenLoan.show();
        }
    }
    public void loanTypeEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            disburseLoanBtn2.fire();
        }
    }
    public void back(){
        setvisible("back");
    }
    private void clearFields() {
        depAmount.clear();
        withAmount.clear();
        transAmount.clear();
        destAcc.clear();
        disbAmount.clear();
        depErrMsg.setText("");
        withErrMsg.setText("");
        transErrMsg.setText("");
        disbLoanErrMsg.setText("");
        takenLoan.getSelectionModel().clearSelection();
    }
    private void defaultStyle(TextField field){
        field.setStyle("-fx-background-radius:20;-fx-border-radius:20;-fx-focus-color:transparent;-fx-faint-focus-color:transparent;");
    }
//    public void hboxCell(ListView<HBoxCell> listView, String btnlbl){
//        for(Node entity: listView.getItems()){
//            for(Node nested: ((HBoxCell)entity).getChildren()){
//                if(nested.getClass() == tempLabelIgnore.getClass()){
//                    tempStrIgnore = ((Label) nested).getText();
//                }
//                else if(nested.getClass() == tempBtnIgnore.getClass()){
//                    if(((Button) nested).getText().equals(btnlbl)){
//                        tempBtnIgnoreRent = ((Button) nested);
//                        setEvent(tempStrIgnore, "",tempBtnIgnoreRent, tempBtnIgnoreRemove);
//                    }
//                }
//            }
//        }
//        for(Node entity: listView.getItems() ){
//            for(Node nested: ((HBoxCell)entity).getChildren()){
//                if(nested.getClass() == tempLabelIgnore.getClass()){
//                    tempStrIgnore = ((Label) nested).getText();
//                } else if(nested.getClass() == tempBtnIgnore.getClass()) {
//                    if (((Button) nested).getText().equals("Remove")) {
//                        tempBtnIgnoreRemove = ((Button) nested);
//                        setEvent(tempStrIgnore, "",tempBtnIgnoreRent, tempBtnIgnoreRemove);
//                    }
//                }
//            }
//        }
//    }
//    public void hboxCell(ListView<HBoxCell> listView){
//        int i=0;
//        for(Node entity: listView.getItems() ){
//            for(Node nested: ((HBoxCell)entity).getChildren()){
//                if(nested.getClass() == tempLabelIgnore.getClass()){
//                    if (i % 2 == 0){
//                        tempStrIgnore1 = ((Label) nested).getText();
//                    }else tempStrIgnore = ((Label) nested).getText();
//                } else if(nested.getClass() == tempBtnIgnore.getClass()) {
//                    if (((Button) nested).getText().equals("✔")) {
//                        tempBtnIgnoreRent = ((Button) nested);
//                        setEvent(tempStrIgnore,tempStrIgnore1,tempBtnIgnoreRent, tempBtnIgnoreRemove);
//                    }
//                }
//                i++;
//            }
//        }
//        i=0;
//        for(Node entity: listView.getItems() ){
//            for(Node nested: ((HBoxCell)entity).getChildren()){
//                if(nested.getClass() == tempLabelIgnore.getClass()){
//                    if (i % 2 == 0){
//                        tempStrIgnore1 = ((Label) nested).getText();
//                    }else tempStrIgnore = ((Label) nested).getText();
//                } else if(nested.getClass() == tempBtnIgnore.getClass()) {
//                    if (((Button) nested).getText().equals("X")) {
//                        tempBtnIgnoreRemove = ((Button) nested);
//                        setEvent(tempStrIgnore, tempStrIgnore1,tempBtnIgnoreRent, tempBtnIgnoreRemove);
//                    }
//                }
//                i++;
//            }
//        }
//    }
//    public void setEvent(String lbl,String lbl1, Button btn1, Button btn2){
//        if (btn1.getText().equals("Edit")){
//            btn1.setOnAction(e -> {
//                setvisible("none");
//                clearFields();
//                setvisible("edit");
//                focusBtn.requestFocus();
//                String[] data;
//                userNameField.setText(lbl);
//                showPassBtn.setVisible(true);
//                hideIco.setVisible(true);
//                showPassBtn.setVisible(true);
//                try {
//                    data = User.requestInfo(lbl);
//                } catch (IOException ex) {
//                    throw new RuntimeException(ex);
//                }
//                passField.setText(data[1]);
//
//                firstnameField.setText(data[3]);
//                type.setValue(data[2]);
//                lastnameField.setText(data[4]);
//                addressField.setText(data[5]);
//                cellphoneField.setText(data[6]);
//                emailField.setText(data[7]);
//                if(data[8].equals("true"))
//                    blockedCB.setSelected(true);
//                else
//                    blockedCB.setSelected(false);
//                saveBtn.setOnAction(y -> {
//                    userEdited.setText("");
//                    if (User.validate(userNameField,usernameErr,passField,passFieldShown,passErr,type.getValue(),typeErr,firstnameField,firstnameErr,lastnameField,lastnameErr,emailField,emailErr,cellphoneField,cellphoneErr,addressField,addressErr)){
//                        try {
//                            Type = type.getValue().toString();
//                        } catch (NullPointerException | NumberFormatException exc) {
//                        }
//                        try {
//                            Object[] newData = {
//                                    userNameField.getText(),
//                                    passField.getText(),
//                                    type.getValue().toString(),
//                                    firstnameField.getText(),
//                                    lastnameField.getText(),
//                                    addressField.getText(),
//                                    cellphoneField.getText(),
//                                    emailField.getText(),
//                                    blockedCB.isSelected(),
//                            };
//                            if(User.checkChange(newData, data)){
//                                System.out.println(" EDIT BUTTON PRESSED");
//                                if(User.getUserName().equals(data[0])){
//                                    if(new Alert().Alert("Hold it right there!", "Applying these changes will log you out, are you sure you want to continue?", "red", "Yes", "No")){
//                                        librarian.removeUser(lbl);
//                                        librarian.addUser(new User(((String)newData[0]), ((String)newData[1]), ((String)newData[2]), ((String)newData[3]), ((String)newData[4]), ((String)newData[5]), ((String)newData[6]), ((String)newData[7]), ((Boolean)newData[8])).getUserData());
//                                        logoutBtn.fire();
//                                    }
//                                }
//                                else{
//                                    librarian.removeUser(lbl);
//                                    librarian.addUser(new User(((String)newData[0]), ((String)newData[1]), ((String)newData[2]), ((String)newData[3]), ((String)newData[4]), ((String)newData[5]), ((String)newData[6]), ((String)newData[7]), ((Boolean)newData[8])).getUserData());
//                                    userEdited.setText("");
//                                    userEdited.setText("Info updated");
//                                    userEdited.setStyle("-fx-text-fill:limegreen");
//                                }
//
//                            }
//                            else{
//                                userEdited.setText("Please update one of the fields");
//                                userEdited.setStyle("-fx-text-fill:red");
//                            }
//
//                        } catch( IOException | NumberFormatException exc) {
//                        }
//                    }
//                });
//            });
//            btn2.setOnAction(e ->{
//                if(User.getUserName().equals(lbl)){
//                    if (new Alert().Alert("Warning","This account will be deleted and you will be logged out.\nAre you sure you want to continue?","red","Yes","No")){
//                        librarian.removeUser(lbl);
//                        logoutBtn.fire();
//                    }
//                }else {
//                    if (new Alert().Alert("Warning","Are you sure you want to delete "+ lbl +" ?","red","Yes","No")){
//                        librarian.removeUser(lbl);
//                    }
//                    if (!searchField.getText().equals("") ) {
//                        searchBtn.fire();
//                    }else{
//                        bookListview.setItems(null);
//                        displayUsers();
//                    }
//                }
//            });
//        }else if(btn1.getText().equals("Rent")){
//            btn1.setOnAction(e->{
//                if (!book.checkRent(User.getUserName(),lbl)){
//                    Book.rentBook(lbl);
//                    new Alert("Info",lbl+" was added to your order list","green");
//                }
//            });
//            btn2.setOnAction(e -> {
//                if (new Alert().Alert("Warning","Are you sure you want to delete "+ lbl +" ?","red","Yes","No")){
//                    Book.removeBook(lbl);
//                }
//                if (!searchField.getText().equals("") ) {
//                    searchBtn.fire();
//                }else{
//                    bookListview.setItems(null);
//                    displayBooks();
//                }
//            });
//        }else if(btn1.getText().equals("✔")){
//            btn1.setOnAction(e->{
//                Librarian.acceptRequest(lbl1,lbl);
//                orderList();
//            });
//            btn2.setOnAction(e->{
//                Librarian.denyRequest(lbl1,lbl);
//                orderList();
//            });
//        }
//    }

    public void updateBalance(){
        BigDecimal result= BigDecimal.valueOf(CurrentUser.getBalance());
        BigDecimal divisor1 = new BigDecimal(1000000);
        BigDecimal divisor2 = new BigDecimal(1000000000);
        if(CurrentUser.getBalance()<1000000) {
            accBalance1.setText("$" + CurrentUser.getBalance());
            accBalance2.setText("$" + CurrentUser.getBalance());
        }
        else if(CurrentUser.getBalance()<1000000000){
            result=result.divide(divisor1);
            accBalance1.setText("$" + result.setScale(2, RoundingMode.HALF_UP)+"M");
            accBalance2.setText("$" + result.setScale(2, RoundingMode.HALF_UP)+"M");
        }
        else{
            result=result.divide(divisor2);
            accBalance1.setText("$" + result.setScale(2, RoundingMode.HALF_UP)+"B");
            accBalance2.setText("$" + result.setScale(2, RoundingMode.HALF_UP)+"B");
        }
    }
    public void displayTransactionsMenu(ActionEvent actionEvent) {
        setvisible("transaction");
    }

    public void displayLoanMenu(ActionEvent actionEvent) {
        setvisible("loan");
    }

    public void depositView(ActionEvent actionEvent) {
        setvisible("deposit");
    }

    public void withdrawView(ActionEvent actionEvent) {
        setvisible("withdraw");
    }

    public void transferView(ActionEvent actionEvent) {
        setvisible("transfer");
    }

    public void transactionsView(ActionEvent actionEvent) {
        setvisible("viewTransac");
        ObservableList<Transaction> transactionsData = FXCollections.observableArrayList(CurrentUser.getTransactionList());
        transacTableView.setItems(transactionsData);

    }
    private void createTransactionsViewTable(){
        // Create columns
        TableColumn<Transaction, String> idColumn = new TableColumn<>("Transaction ID");
        TableColumn<Transaction, String> typeColumn = new TableColumn<>("Transaction Type");
        TableColumn<Transaction, String> amountColumn = new TableColumn<>("Amount");
        TableColumn<Transaction, String> dateColumn = new TableColumn<>("Transaction Date");
    //        TableColumn<Transaction, String> descriptionColumn = new TableColumn<>("Description");

    // Set up value factories
        idColumn.setCellValueFactory(new PropertyValueFactory<>("transactionId"));
        typeColumn.setCellValueFactory(cellData -> {
            String type = cellData.getValue().getTransactionType();
            return switch (type) {
                case "D" -> new SimpleStringProperty("Deposit");
                case "W" -> new SimpleStringProperty("Withdraw");
                case "T" -> {
                    if (cellData.getValue().getFromAccountId().equals(CurrentUser.getAccountId()))
                        yield new SimpleStringProperty("Transfer To " + cellData.getValue().getToAccountId());
                    if (cellData.getValue().getToAccountId().equals(CurrentUser.getAccountId()))
                        yield new SimpleStringProperty("Transfer From " + cellData.getValue().getFromAccountId());
                    yield new SimpleStringProperty("Unknown");
                }
                case "DL" -> new SimpleStringProperty("Disburse Loan");
                case "PL" -> new SimpleStringProperty("Pay Loan");
                default -> new SimpleStringProperty("Unknown");
            };
        });
        amountColumn.setCellValueFactory(cellData -> {
            Transaction transaction = cellData.getValue();
            String transactionType = transaction.getTransactionType();
            double amount = transaction.getAmount();

            if ("D".equals(transactionType) || "DL".equals(transactionType)) {
                return new SimpleStringProperty("+" + amount);
            } else if ("W".equals(transactionType) || "PL".equals(transactionType)) {
                return new SimpleStringProperty("-" + amount);
            } else if ("T".equals(transactionType)){
                if (transaction.getFromAccountId().equals(CurrentUser.getAccountId()))
                    return new SimpleStringProperty("-" + amount);
                else
                    return new SimpleStringProperty("+" + amount);
            } else {
                return new SimpleStringProperty(String.valueOf(amount));
            }
        });
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("transactionDate"));
    //        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

    // Add columns to table
        transacTableView.getColumns().add(idColumn);
        transacTableView.getColumns().add(amountColumn);
        transacTableView.getColumns().add(typeColumn);
        transacTableView.getColumns().add(dateColumn);
        setTableStyle(transacTableView);
        transacTableView.setClip(roundedListview(985, 410));
    //        transacTableView.getColumns().add(descriptionColumn);
    }

    public void deposit(ActionEvent actionEvent) {
        if (depAmount.getText().isEmpty()){
            labelMsg(depErrMsg,"Please Enter An Amount","red");
        }
        else {
            boolean isSuccess = CurrentUser.processTransaction(Double.parseDouble(depAmount.getText()),"D");
            if (isSuccess) labelMsg(depErrMsg,"Successfully Deposited","limegreen");
            else labelMsg(depErrMsg,"Invalid Amount","red");
            updateBalance();
            depAmount.clear();
        }
    }

    public void withdraw(ActionEvent actionEvent) {
        if (withAmount.getText().isEmpty()){
            labelMsg(withErrMsg,"Please Enter An Amount","red");
        }
        else {
            boolean isSuccess = CurrentUser.processTransaction(Double.parseDouble(withAmount.getText()), "W");
            if (isSuccess) labelMsg(withErrMsg, "Successfully Withdrawn", "limegreen");
            else labelMsg(withErrMsg, "Error! Couldn't Complete the Withdraw", "red");
            updateBalance();
            withAmount.clear();
        }
    }

    public void transfer(ActionEvent actionEvent) {
        if (transAmount.getText().isEmpty() && destAcc.getText().isEmpty()){
            labelMsg(transErrMsg,"Please Enter Amount and Destination Account","red");
        }
        else if (transAmount.getText().isEmpty()){
            labelMsg(transErrMsg,"Please Enter An Amount","red");
        }
        else if (destAcc.getText().isEmpty()){
            labelMsg(transErrMsg,"Please Enter the Destination Account","red");
        }
        else {
            boolean isSuccess = CurrentUser.processTransaction(Bank.getAccount(destAcc.getText()),Double.parseDouble(transAmount.getText()),"T");
            if (isSuccess) labelMsg(transErrMsg,"Successfully Transferred","limegreen");
            else labelMsg(transErrMsg,"Error! Couldn't Complete the Transfer","red");
            updateBalance();
            transAmount.clear();
            destAcc.clear();
        }

    }

    public void disburseloanView(ActionEvent actionEvent) {
        setvisible("disburse");
    }

    public void disburseLoan(ActionEvent actionEvent) {
        if (disbAmount.getText().isEmpty() && takenLoan.getValue() == null){
            labelMsg(disbLoanErrMsg,"Please Enter Amount and Select Loan Type","red");
        }
        else if (disbAmount.getText().isEmpty()){
            labelMsg(disbLoanErrMsg,"Please Enter An Amount","red");
        }
        else if (takenLoan.getValue() == null){
            labelMsg(disbLoanErrMsg,"Please Select Loan Type","red");
        }
        else {
            boolean isSuccess = CurrentUser.takeLoan(getTakeLoanId(),Double.parseDouble(disbAmount.getText()));
            if (isSuccess) labelMsg(disbLoanErrMsg,"Loan Taken Successfully","limegreen");
            else labelMsg(disbLoanErrMsg,"Error! Couldn't Complete the Loan","red");
            updateBalance();
            disbAmount.clear();
            takenLoan.getSelectionModel().clearSelection();
        }

    }

    public void viewLoansView(ActionEvent actionEvent) {
        setvisible("viewloans");
        ObservableList<Loan> loansData = FXCollections.observableArrayList(CurrentUser.getTakenLoans());
        loansTableView.setItems(loansData);
    }
    public void createLoansViewTable(){
        // Define the columns for loan details
        TableColumn<Loan, String> loanIdColumn = new TableColumn<>("Loan ID");
        loanIdColumn.setCellValueFactory(new PropertyValueFactory<>("loanId"));

        TableColumn<Loan, Double> loanAmountColumn = new TableColumn<>("Loan Amount");
        loanAmountColumn.setCellValueFactory(new PropertyValueFactory<>("loanAmount"));

        TableColumn<Loan, Integer> periodColumn = new TableColumn<>("Period");
        periodColumn.setCellValueFactory(new PropertyValueFactory<>("period"));

        TableColumn<Loan, Integer> startYearColumn = new TableColumn<>("Start Year");
        startYearColumn.setCellValueFactory(new PropertyValueFactory<>("startYear"));

        TableColumn<Loan, Double> interestRateColumn = new TableColumn<>("Interest Rate");
        interestRateColumn.setCellValueFactory(new PropertyValueFactory<>("interestRate"));

        // Create the "Pay Loan" column
        TableColumn<Loan, Void> payLoanColumn = new TableColumn<>("Pay Loan");
        Callback<TableColumn<Loan, Void>, TableCell<Loan, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Loan, Void> call(final TableColumn<Loan, Void> param) {
                final TableCell<Loan, Void> cell = new TableCell<>() {
                    private final JFXButton btn = new JFXButton("Pay Loan");
                    {
                        btn.getStyleClass().add("ButtonG");
                        btn.setOnAction((ActionEvent event) -> {
                            Loan loan = getTableView().getItems().get(getIndex());
                            // Call the method to pay the loan here
                            if(CurrentUser.payLoan(loan.getLoanId())){
                                updateBalance();
                                viewLoansView(event);// Refresh the table view
                                new Alert("Info","Loan Paid Successfully","green");
                            }else {
                                new Alert("Error","Error! Insufficient Balance","red");
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                            setAlignment(Pos.CENTER);
                        }
                    }
                };
                return cell;
            }
        };
        payLoanColumn.setCellFactory(cellFactory);

        // Add the columns to the TableView
        loansTableView.getColumns().add(loanIdColumn);
        loansTableView.getColumns().add(loanAmountColumn);
        loansTableView.getColumns().add(periodColumn);
        loansTableView.getColumns().add(startYearColumn);
        loansTableView.getColumns().add(interestRateColumn);
        loansTableView.getColumns().add(payLoanColumn);
        setTableStyle(loansTableView);
        loansTableView.setClip(roundedListview(985, 410));
    }

//    public static class HBoxCell extends HBox {
//        Label text = new Label();
//        Label text1 = new Label();
//        Button btn1 = new Button();
//        Button btn2 = new Button();
//        HBoxCell(String labelText, String buttonText1, String color1, String buttonText2, String color2, String type) {
//            super();
//            text.setStyle("-fx-font-size:21;-fx-font-weight:bold;");
//            text.setText(labelText);
//            text.setMaxWidth(Double.MAX_VALUE);
//            HBox.setHgrow(text, Priority.ALWAYS);
//            getStylesheets().add(getClass().getResource("styling.css").toExternalForm());
//            this.getChildren().add(text);
//            if (type.equals("Librarian")){
//                btn2 = new Button();
//                btnColor(btn2,color2);
//                btn2.setText(buttonText2);
//                btn2.setPadding(new Insets(4, 46, 4, 46));
//                this.getChildren().add(btn2);
//                HBox.setMargin(btn2, new Insets(0, 10, 0, 0));
//                btnColor(btn1,color1);
//                this.getChildren().add(btn1);
//                btn1.setText(buttonText1);
//                btn1.setPadding(new Insets(4, 30, 4, 30));
//            }else {
//                this.getChildren().add(btn1);
//                btnColor(btn1,color1);
//                btn1.setText(buttonText1);
//                btn1.setPadding(new Insets(4,46,4,46));
//            }
//        }
//        HBoxCell(String users, String books){
//            super();
//            Button confirm = new Button("✔");
//            Button reject = new Button("X");
//            btnColor(confirm,"99ff33");
//            btnColor(reject,"ff4c33");
//            confirm.setPadding(new Insets(5,10,5,10));
//            reject.setPadding(new Insets(5,12,4,12));
//            HBox.setMargin(confirm, new Insets(0, 10, 0, 0));
//            text.setText(users);
//            text1.setText(books);
//            text.setStyle("-fx-font-size:20;");
//            text1.setStyle("-fx-font-size:20");
//            text.setMaxWidth(Double.MAX_VALUE);
//            text1.setMaxWidth(Double.MAX_VALUE);
//            HBox.setHgrow(text, Priority.ALWAYS);
//            HBox.setHgrow(text1, Priority.ALWAYS);
//            this.getChildren().addAll(text,text1,confirm,reject);
//        }
//        public void btnColor(Button btn,String color){
//            switch (color) {
//                case "crimson" -> btn.getStyleClass().add("ButtonR");
//                case "limegreen" -> btn.getStyleClass().add("ButtonG");
//                case "#FFC107" -> btn.getStyleClass().add("ButtonY");
//                case "99ff33" -> btn.getStyleClass().add("orderBtn1");
//                case "ff4c33" -> btn.getStyleClass().add("orderBtn2");
//            }
//        }
//        HBoxCell(String labelText){
//            super();
//            text.setText(labelText);
//            text.setStyle("-fx-font-size:21;-fx-font-weight:bold;-fx-alignment:center");
//            text.setMaxWidth(Double.MAX_VALUE);
//            HBox.setHgrow(text, Priority.ALWAYS);
//            this.getChildren().add(text);
//        }
//        HBoxCell(String col1, String col2, int width){
//            super();
//            text.setText(col1);
//            text1.setText(col2);
//            text.setStyle("-fx-font-size:24;-fx-font-weight:bold;");
//            text1.setStyle("-fx-font-size:24;-fx-font-weight:bold;");
//            text.setMaxWidth((double) width/2);
//            HBox.setHgrow(text, Priority.ALWAYS);
//            this.getChildren().addAll(text,text1);
//        }
//    }
    public Rectangle roundedListview(int width, int height){
        Rectangle clip = new Rectangle();
        clip.setWidth(width);
        clip.setHeight(height);
        clip.setArcHeight(20);
        clip.setArcWidth(20);
        return clip;
    }
    public void logout(){
        try {
            CurrentUser = null;
            setvisible("none");
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            loginScene = new Scene(root, 1280, 720, Color.TRANSPARENT);
            Stage window = (Stage)(logoutBtn.getScene().getWindow());
            window.setTitle("Bank System - Login");
            window.setScene(loginScene);
            window.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void labelMsg(Label label, String msg, String color){
        label.setText(msg);
        label.setStyle("-fx-text-fill:"+color);
    }
    private void setTableStyle(TableView tableView) {
//        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        double columnWidth = tableView.getPrefWidth() / tableView.getColumns().size();
        for (Object column : tableView.getColumns()) {
            ((TableColumn<?, ?>) column).setResizable(false);
            ((TableColumn<?, ?>) column).setReorderable(false);
            ((TableColumn<?, ?>) column).setSortable(false);
            ((TableColumn<?, ?>) column).setPrefWidth(columnWidth);
            if (!((TableColumn<Object, Object>) column).getText().equals("Pay Loan")) {
                // Center the content of the column
                ((TableColumn<Object, Object>) column).setCellFactory(tc -> {
                    TableCell<Object, Object> cell = new TableCell<>() {
                        @Override
                        protected void updateItem(Object item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty || item == null) {
                                setText(null);
                            } else {
                                setText(item.toString());
                                setAlignment(Pos.CENTER);
                            }
                        }
                    };
                    return cell;
                });
            }
        }
    }
//        tableColumn.setStyle("-fx-background-color:transparent;-fx-border-color:transparent;-fx-table-cell-border-color:transparent;-fx-table-header-border-color:transparent;");

    private void setvisible(String window){
        switch (window) {
            case "transaction" -> {
                setvisible("back");
                transactionsPane.setVisible(true);
                loanPane.setVisible(false);
            }
            case "loan" -> {
                setvisible("back");
                loanPane.setVisible(true);
                transactionsPane.setVisible(false);
            }
            case "deposit" -> {
                depositPane.setVisible(true);
            }
            case "withdraw" -> {
                withdrawPane.setVisible(true);
            }
            case "transfer" -> {
                transferPane.setVisible(true);
            }
            case "viewTransac" -> {
                viewTransacPane.setVisible(true);
            }
            case "disburse" -> {
                disburseLoanPane.setVisible(true);
            }
            case "viewloans" -> {
                viewLoansPane.setVisible(true);
            }
            case "back" ->{
                depositPane.setVisible(false);
                withdrawPane.setVisible(false);
                transferPane.setVisible(false);
                viewTransacPane.setVisible(false);
                disburseLoanPane.setVisible(false);
                viewLoansPane.setVisible(false);
                clearFields();
            }
            case "none" -> {
                transactionsPane.setVisible(false);
                loanPane.setVisible(false);
                depositPane.setVisible(false);
                withdrawPane.setVisible(false);
                transferPane.setVisible(false);
                viewTransacPane.setVisible(false);
                disburseLoanPane.setVisible(false);
                viewLoansPane.setVisible(false);
                clearFields();
            }
        }
    }
    private void initialzeLoans(){
        Bank.loans.add(new Loan("L1", 0, null, 10, 1));
        Bank.loans.add(new Loan("L2", 0, null, 15, 3));
        Bank.loans.add(new Loan("L3", 0, null, 20, 5));
        Bank.loans.add(new Loan("L4", 0, null, 30, 10));
    }
    private String getTakeLoanId(){
        switch (takenLoan.getValue()) {
            case "1 Year with 10% interest" -> {
                return "L1";
            }
            case "3 Years With 15% interest" -> {
                return "L2";
            }
            case "5 Years with 20% interest" -> {
                return "L3";
            }
            case "10 Years with 30% interest" -> {
                return "L4";
            }
            default -> {
                return null;
            }
        }
    }
    public void initialize(){
        if (CurrentUser != null) {
            usernameDisplay.setText(CurrentUser.getAccountOwner());
            accNoDisplay.setText(CurrentUser.getAccountId());
            updateBalance();
        }
        initialzeLoans();
        takenLoan.getItems().addAll("1 Year with 10% interest","3 Years With 15% interest","5 Years with 20% interest","10 Years with 30% interest");
        createTransactionsViewTable();
        createLoansViewTable();
        setvisible("none");
        UnaryOperator<TextFormatter.Change> filter1 = change -> {
            String text = change.getControlNewText();
            if (text.isEmpty()) {
                return change;
            }
            if (text.matches("^\\d+(\\.\\d{0,2})?")) {
                return change; // Accept valid double numbers with up to 2 decimal places
            }
            return null;
        };
        UnaryOperator<TextFormatter.Change> filter2 = change -> {
            String text = change.getControlNewText();
            if (text.isEmpty()) {
                return change;
            }
            if (text.matches("\\d{0,8}")) {
                return change; // Accept valid 8 digit account numbers
            }
            return null;
        };
        TextFormatter<Double> amount1 = new TextFormatter<>(new DoubleStringConverter(), null, filter1);
        TextFormatter<Double> amount2 = new TextFormatter<>(new DoubleStringConverter(), null, filter1);
        TextFormatter<Double> amount3 = new TextFormatter<>(new DoubleStringConverter(), null, filter1);
        TextFormatter<Double> amount4 = new TextFormatter<>(new DoubleStringConverter(), null, filter1);
        TextFormatter<String> destAccID = new TextFormatter<>(new StringConverter<String>() {
            @Override
            public String toString(String s) {
                return s;
            }

            @Override
            public String fromString(String s) {
                return s;
            }
        }, null, filter2);
        depAmount.setTextFormatter(amount1);
        withAmount.setTextFormatter(amount2);
        transAmount.setTextFormatter(amount3);
        disbAmount.setTextFormatter(amount4);
        destAcc.setTextFormatter(destAccID);
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
            imgView.setImage(new Image("file:src/main/resources/org/example/side1.png",false));
            Rectangle clip = new Rectangle();
            clip.setWidth(1280.0);
            clip.setHeight(750);
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