package com.example.day3.business;

import com.example.day3.models.Catalogo;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;

public class MoneyTranslate {
    private double[][] tasasDeCambio = {
            // MXN a USD, EUR
            {1.0, 0.049, 0.042},
            // USD a MXN, EUR
            {20.41, 1.0, 0.85},
            // EUR a MXN, USD
            {23.81, 1.18, 1.0}
    };
    public void initComboBox(ComboBox<Catalogo> comboBox, ObservableList<Catalogo> elementos) {
        // Configurar el ComboBox con la lista de elementos
        comboBox.setItems(elementos);

        // Configurar la fábrica de celdas para mostrar el nombre de los elementos
        comboBox.setCellFactory(cell -> new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(Catalogo item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });

        // Configurar el visor de la celda seleccionada para mostrar el nombre del elemento
        comboBox.setButtonCell(new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(Catalogo item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });
    }

    public String convertirMoneda(Catalogo monedaOrigen, Catalogo monedaDestino, double cantidad) {
        int indiceMonedaOrigen = obtenerIndiceMoneda(monedaOrigen.getName());
        int indiceMonedaDestino = obtenerIndiceMoneda(monedaDestino.getName());
        System.out.println(cantidad);

        if (indiceMonedaOrigen == -1 || indiceMonedaDestino == -1) {
            System.out.println("MonedaOrigen: " + monedaOrigen.getName() + ", MonedaDestino: " + monedaDestino.getName());
            throw new IllegalArgumentException("Moneda no válida");
        }


        double tasaDeCambio = tasasDeCambio[indiceMonedaOrigen][indiceMonedaDestino];
        double cantidadConvertida = cantidad * tasaDeCambio;

        // Devuelve la cantidad convertida como una cadena con dos decimales
        return String.format("%.2f", cantidadConvertida);
    }

    private int obtenerIndiceMoneda(String moneda) {
        String[] monedasValidas = {"MXN", "USD", "EUR"};
        for (int i = 0; i < monedasValidas.length; i++) {
            if (monedasValidas[i].equals(moneda)) {
                return i;
            }
        }
        return -1;
    }
}
