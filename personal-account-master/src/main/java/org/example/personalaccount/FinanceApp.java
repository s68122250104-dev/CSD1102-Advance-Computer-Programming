package org.example.personalaccount;

import javafx.application.Application;
import javafx.collections.*;
import javafx.collections.transformation.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.beans.property.*;
import java.time.LocalDate;

public class FinanceApp extends Application {

    TableView<Transaction> table = new TableView<>();
    ObservableList<Transaction> data = FXCollections.observableArrayList();

    Label balanceLabel = new Label("Balance : 0");

    @Override
    public void start(Stage stage) {

        TextField amountField = new TextField();
        amountField.setPromptText("Amount");

        ComboBox<String> typeBox = new ComboBox<>();
        typeBox.getItems().addAll("Income","Expense");

        DatePicker datePicker = new DatePicker();

        Button saveBtn = new Button("Save");
        Button deleteBtn = new Button("Delete");
        Button editBtn = new Button("Edit");

        TextField searchField = new TextField();
        searchField.setPromptText("Search type or date");

        Button searchBtn = new Button("Search");

        // TABLE
        TableColumn<Transaction,String> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(c -> c.getValue().dateProperty());

        TableColumn<Transaction,String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(c -> c.getValue().typeProperty());

        TableColumn<Transaction,Number> amountCol = new TableColumn<>("Amount");
        amountCol.setCellValueFactory(c -> c.getValue().amountProperty());

        typeCol.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {

                super.updateItem(item, empty);

                if(empty || item == null){
                    setText(null);
                    setStyle("");
                }
                else{

                    setText(item);

                    if(item.equals("Income")){
                        setStyle("-fx-text-fill: green;");
                    }
                    else{
                        setStyle("-fx-text-fill: red;");
                    }

                }
            }
        });

        table.getColumns().addAll(dateCol,typeCol,amountCol);
        table.setItems(data);

        saveBtn.setOnAction(e -> {

            try{

                double amount = Double.parseDouble(amountField.getText());
                String type = typeBox.getValue();
                LocalDate date = datePicker.getValue();

                Transaction t = new Transaction(date.toString(),type,amount);
                data.add(t);

                updateBalance();
                amountField.clear();

            }
            catch(Exception ex){
                System.out.println("Invalid input");
            }

        });

        deleteBtn.setOnAction(e -> {

            Transaction selected = table.getSelectionModel().getSelectedItem();

            if(selected != null){
                data.remove(selected);
                updateBalance();
            }

        });

        editBtn.setOnAction(e -> {

            Transaction selected = table.getSelectionModel().getSelectedItem();

            if(selected != null){

                amountField.setText(String.valueOf(selected.getAmount()));
                typeBox.setValue(selected.getType());
                datePicker.setValue(LocalDate.parse(selected.getDate()));

                data.remove(selected);

            }

        });

        searchBtn.setOnAction(e -> {

            String keyword = searchField.getText().toLowerCase();

            FilteredList<Transaction> filtered =
                    new FilteredList<>(data, t ->

                            t.getType().toLowerCase().contains(keyword) ||
                                    t.getDate().contains(keyword)

                    );

            table.setItems(filtered);

        });

        GridPane input = new GridPane();
        input.setHgap(10);
        input.setVgap(10);

        input.add(new Label("Amount"),0,0);
        input.add(amountField,1,0);

        input.add(new Label("Type"),0,1);
        input.add(typeBox,1,1);

        input.add(new Label("Date"),0,2);
        input.add(datePicker,1,2);

        HBox buttons = new HBox(10,saveBtn,editBtn,deleteBtn);

        HBox searchBox = new HBox(10,searchField,searchBtn);

        VBox root = new VBox(15,input,buttons,searchBox,table,balanceLabel);
        root.setPadding(new Insets(15));

        Scene scene = new Scene(root,600,450);

        stage.setTitle("Personal Finance Manager");
        stage.setScene(scene);
        stage.show();
    }

    private void updateBalance(){

        double balance = 0;

        for(Transaction t : data){

            if(t.getType().equals("Income")){
                balance += t.getAmount();
            }
            else{
                balance -= t.getAmount();
            }

        }

        balanceLabel.setText("Balance : " + balance);

    }

    public static void main(String[] args){
        launch();
    }
}

class Transaction {

    private StringProperty date;
    private StringProperty type;
    private DoubleProperty amount;

    public Transaction(String date,String type,double amount){

        this.date = new SimpleStringProperty(date);
        this.type = new SimpleStringProperty(type);
        this.amount = new SimpleDoubleProperty(amount);

    }

    public String getDate(){
        return date.get();
    }

    public StringProperty dateProperty(){
        return date;
    }

    public String getType(){
        return type.get();
    }

    public StringProperty typeProperty(){
        return type;
    }

    public double getAmount(){
        return amount.get();
    }

    public DoubleProperty amountProperty(){
        return amount;
    }
}
