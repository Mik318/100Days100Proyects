package com.example.day3.controllers;

import com.example.day3.business.MoneyTranslate;
import com.example.day3.models.Catalogo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private ComboBox<Catalogo> catOriginSelect;
    @FXML
    private ComboBox<Catalogo> catConverterSelect;
    @FXML
    private TextField textValueOrigin;
    @FXML
    private TextField textValueConverter;

    public void initialize() {
        MoneyTranslate initData = new MoneyTranslate();
        // Crear datos de ejemplo
        Catalogo elemento1 = new Catalogo(1, "USD");
        Catalogo elemento2 = new Catalogo(2, "MXN");
        Catalogo elemento3 = new Catalogo(3, "EUR");

        // Crear lista observable de elementos
        ObservableList<Catalogo> elementos = FXCollections.observableArrayList(
                elemento1, elemento2, elemento3
        );
        // Configurar catOriginSelect
        initData.initComboBox(catOriginSelect, elementos);

        // Configurar catConverterSelect
        initData.initComboBox(catConverterSelect, elementos);
    }

    @FXML
    protected void onTranslateValue() {
        welcomeText.setText("Valor convertido");
    }

    @FXML
    protected void onInputTextChangedOrigin() {
        String valor = textValueOrigin.getText();
        System.out.println(catOriginSelect.getValue());
        if (detectString(valor)) {
            textValueOrigin.setText(stripNonNumeric(valor));
        }
        this.detectTextFieldConverter();
    }

    @FXML
    protected void onInputTextChangedConverter() {
        String valor = textValueConverter.getText();
        this.validateComboBox(catOriginSelect);
        this.validateComboBox(catConverterSelect);
        if (detectString(valor)) {
            textValueConverter.setText(stripNonNumeric(valor));
        }
    }

    @FXML
    protected void onCleanForm(){
        this.textValueOrigin.setText("");
        this.textValueConverter.setText("");
    }

    private void validateComboBox(ComboBox<?> comboBox) {
        if (comboBox.getValue() == null) {
            comboBox.setStyle("-fx-border-color: red; -fx-border-width: 2px; -fx-border-radius: 2px");
        } else {
            comboBox.setStyle("");
        }
    }


    private Boolean detectString(String text) {
        return !text.matches("\\d*");
    }

    // Método para eliminar caracteres no numéricos de una cadena
    private String stripNonNumeric(String input) {
        return input.replaceAll("[^\\d]", "");
    }

    private void detectTextFieldConverter() {
        MoneyTranslate converter = new MoneyTranslate();

        String textValueConverterText = this.textValueConverter.getText();
        String textValueOriginText = this.textValueOrigin.getText();

        if (!textValueConverterText.isEmpty()) {
            try {
                double textValueConverterDouble = Double.parseDouble(textValueConverterText);
                this.textValueOrigin.setText(converter.convertirMoneda(catOriginSelect.getValue(), catConverterSelect.getValue(), textValueConverterDouble));
            } catch (NumberFormatException e) {
                System.out.println("Error al convertir a double: " + e.getMessage());
            }
        }

        if (!textValueOriginText.isEmpty()) {
            try {
                double textValueOriginDouble = Double.parseDouble(textValueOriginText);
                this.textValueConverter.setText(converter.convertirMoneda(catOriginSelect.getValue(), catConverterSelect.getValue(), textValueOriginDouble));
            } catch (NumberFormatException e) {
                System.out.println("Error al convertir a double: " + e.getMessage());
            }
        }
    }


}