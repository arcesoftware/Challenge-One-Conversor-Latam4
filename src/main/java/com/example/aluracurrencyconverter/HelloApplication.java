package com.example.aluracurrencyconverter;

import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;


public class Application extends Application {
    private static final double[] exchangeRates = {1.0, 0.94, 0.83, 134.44, 1295.18, 0.89, 143.54, 1382.38, 161.71, 1557.79, 9.63};
    private static final String[] currencyUnits = new String[]{"USD", "EUR", "GBP", "JPY", "KRW"};

    @Override
    public void start(Stage stage) {
        stage.setTitle("Conversor de Divisas");
        stage.setResizable(false);

        ComboBox<String> unitChoice = new ComboBox<>();
        unitChoice.getItems().addAll("Divisas");
        unitChoice.getSelectionModel().selectFirst();

        ComboBox<String> currencyChoice1 = new ComboBox<>();
        currencyChoice1.getItems().addAll(currencyUnits);
        currencyChoice1.getSelectionModel().selectFirst();

        ComboBox<String> currencyChoice2 = new ComboBox<>();
        currencyChoice2.getItems().addAll(currencyUnits);
        currencyChoice2.getSelectionModel().selectFirst();

        TextField amountInput = new TextField();
        amountInput.setPromptText("Ingrese el monto");

        Label resultLabel = new Label();

        Button convertButton = new Button("Convertir");
        convertButton.setOnAction(event -> {
            try {
                double amount = Double.parseDouble(amountInput.getText());
                double result = convertCurrency(amount, currencyChoice1.getValue(), currencyChoice2.getValue());

                String resultStr = String.format("%.2f", amount) + " " + currencyChoice1.getValue() + " equivalen a " + String.format("%.2f", result) + " " + currencyChoice2.getValue() + ".";
                resultLabel.setText(resultStr);
            } catch (NumberFormatException e) {
                resultLabel.setText("El monto ingresado no es válido.");
            }
        });

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(unitChoice, currencyChoice1, currencyChoice2, amountInput, convertButton, resultLabel);

        Scene scene = new Scene(vbox, 320, 240);
        stage.setScene(scene);
        stage.show();
    }

    private double convertCurrency(double amount, String from, String to) {
        int fromIndex = getIndex(from);
        int toIndex = getIndex(to);

        if (fromIndex == toIndex) {
            return amount;
        } else if (fromIndex == 0) {
            return amount * exchangeRates[toIndex];
        } else if (toIndex == 0) {
            return amount / exchangeRates[fromIndex];
        } else {
            return amount * exchangeRates[fromIndex + (toIndex - 1) * 5];
        }
    }

    private int getIndex(String currency) {
        switch (currency) {
            case "USD":
                return 0;
            case "EUR":
                return 1;
            case "GBP":
                return 2;
            case "JPY":
                return 3;
            case "KRW":
                return 4;
            default:
                return -1;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
