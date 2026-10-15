package org.example.demo;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Button btnVaciar;
    @FXML
    private TextField inputtext;
    @FXML
    private ListView<String> lisT;
    @FXML
    private Button btnAñadir;
    @FXML
    private Button btnCerrar;


    @FXML
    public void onBotonCerrar(ActionEvent actionEvent) {
        System.exit(0);
    }


    @FXML
    public void onbuttonAñadir(ActionEvent actionEvent) {
        if (!inputtext.getText().equals("")) {
            lisT.getItems().add(inputtext.getText().trim());
            lisT.getSelectionModel().select(null);
            inputtext.clear();

        }

    }

    @FXML
    public void onBotonVaciar(ActionEvent actionEvent) {

        if (lisT.getItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informacion");
            alert.setHeaderText("Aviso");
            alert.setContentText("la lista ya esta vacia");
            alert.showAndWait();
        }else {
            if (lisT.getSelectionModel().getSelectedIndex() != -1) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmacion");
                alert.setHeaderText(null);
                alert.setContentText("¿quiere eliminar este elemento?");
                alert.showAndWait();
                if (alert.getResult() == ButtonType.OK) {
                    lisT.getItems().remove(lisT.getSelectionModel().getSelectedIndex());
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmacion");
                alert.setHeaderText(null);
                alert.setContentText("¿quiere borrar esta lista?");
                alert.showAndWait();
                if (alert.getResult() == ButtonType.OK) {
                    lisT.getItems().clear();
                }

            }
            lisT.getSelectionModel().select(null);
        }

    }

    @FXML
    public void onbotonsalido(Event event) {
        if (lisT.getSelectionModel().getSelectedIndex() != -1){
            lisT.getSelectionModel().select(null);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
btnAñadir.setDisable(true);
        lisT.getItems().addAll("Hugo","Francisco","Adrian");
        lisT.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            actualizaa(oldValue, newValue);

        });





    }

    private void actualizaa(String oldValue, String newValue) {
        if (newValue != null) {
            inputtext.setText(newValue.toString().trim());
            if (inputtext.getText().length() < 5) {
                btnAñadir.setDisable(true);
            }else {
                btnAñadir.setDisable(false);
            }
        }
        if(newValue == null && oldValue.equals(inputtext.getText().trim())){
            inputtext.setText("");
            btnAñadir.setDisable(true);
        }
    }

    @FXML
    public void onActivar(Event event) {
        if (inputtext.getText().length() < 5) {
            btnAñadir.setDisable(true);
        }else {
            btnAñadir.setDisable(false);
        }
    }
}
