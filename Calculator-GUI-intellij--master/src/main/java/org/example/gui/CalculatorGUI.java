package org.example.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CalculatorGUI extends Application {

    private TextField display = new TextField();
    private double num1 = 0;
    private String operator = "";

    @Override
    public void start(Stage stage) {

        display.setEditable(false);
        display.setPrefHeight(50);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(5);
        grid.setVgap(5);

        String[] buttons = {
                "7","8","9","/",
                "4","5","6","*",
                "1","2","3","-",
                "0",".","=","+"
        };

        int row = 1;
        int col = 0;

        for (String text : buttons) {
            Button btn = new Button(text);
            btn.setPrefSize(60,60);

            btn.setOnAction(e -> handleButton(text));

            grid.add(btn, col, row);

            col++;
            if (col == 4) {
                col = 0;
                row++;
            }
        }

        grid.add(display,0,0,4,1);

        Scene scene = new Scene(grid,260,300);
        stage.setTitle("Calculator");
        stage.setScene(scene);
        stage.show();
    }

    private void handleButton(String value) {

        if (value.matches("[0-9.]")) {
            display.appendText(value);
        }
        else if (value.matches("[+\\-*/]")) {
            num1 = Double.parseDouble(display.getText());
            operator = value;
            display.clear();
        }
        else if (value.equals("=")) {

            double num2 = Double.parseDouble(display.getText());
            double result = 0;

            switch(operator){
                case "+": result = num1 + num2; break;
                case "-": result = num1 - num2; break;
                case "*": result = num1 * num2; break;
                case "/": result = num1 / num2; break;
            }

            display.setText(String.valueOf(result));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
