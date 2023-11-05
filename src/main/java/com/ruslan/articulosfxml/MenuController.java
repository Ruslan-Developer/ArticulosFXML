package com.ruslan.articulosfxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private Button button_editar;

    @FXML
    private Button button_insertar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void insertarArticulo(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("insertar-articulos.fxml"));

        // Cargo el padre
        Parent root = loader.load();

        // Obtengo el controlador
        InsertarArticuloController controlador = loader.getController();

        // Creo la scene y el stage
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        // Asocio el stage con el scene
        stage.setScene(scene);
        stage.show();

        // Indico que debe hacer al cerrar
        stage.setOnCloseRequest(e -> {
            try {
                controlador.closeWindows();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        // Ciero la ventana donde estoy
        Stage myStage = (Stage) this.button_insertar.getScene().getWindow();
        myStage.close();



    }


    @FXML
    private void consultarArticulo(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("editar-articulo.fxml"));

        // Cargo el padre
        Parent root = loader.load();

        // Obtengo el controlador
        EditarArticuloController controlador = loader.getController();

        // Creo la scene y el stage
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        // Asocio el stage con el scene

        stage.setScene(scene);
        stage.setTitle("Menú");
        stage.show();

        // Indico que debe hacer al cerrar
        stage.setOnCloseRequest(e -> {
            try {
                controlador.closeWindows();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        // Ciero la ventana donde estoy
        Stage myStage = (Stage) this.button_editar.getScene().getWindow();
        myStage.close();


    }
}