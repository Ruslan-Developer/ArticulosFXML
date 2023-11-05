package com.ruslan.articulosfxml;

import com.ruslan.articulosfxml.modelo.Articulo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditarArticuloController implements Initializable {

    @FXML
    private Button button_cancelar;

    @FXML
    private Button button_eliminar;

    @FXML
    private Button button_modificar;

    @FXML
    private ListView<Articulo> lv_articulos;

    @FXML
    private TextField tf_id;

    @FXML
    private TextField tf_codigo;

    @FXML
    private TextField tf_descripcion;

    @FXML
    private TextField tf_fecha;

    @FXML
    private TextField tf_origen;

    @FXML
    private TextField tf_nombre;

    @FXML
    private TextField tf_stock;

    @FXML
    private TextField tf_precio;

    @FXML
    private TextField tf_seccion;

    @FXML
    private Button button_visualizar;
    private ObservableList<Articulo> articulos;

    @FXML
    private MenuBar menu_menu;

    @FXML
    private Label lb_insertar_id;

    @FXML
    private Label lb_mensaje_accion;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        lv_articulos.setOnMouseClicked(event ->{
            if(event.getClickCount() == 2){
                Articulo a = this.lv_articulos.getSelectionModel().getSelectedItem();
                if(a != null){
                    this.tf_id.setText(String.valueOf(a.getId()));
                    this.tf_codigo.setText(a.getCodigo());
                    this.tf_nombre.setText(a.getNombre());
                    this.tf_seccion.setText(a.getSeccion());
                    this.tf_descripcion.setText(a.getDescripcion());
                    this.tf_precio.setText(String.valueOf(a.getPrecio()));
                    this.tf_fecha.setText(a.getFecha());
                    this.tf_stock.setText(String.valueOf(a.getStock()));
                    this.tf_origen.setText(a.getOrigen());
                }

            }
        });


    }
    @FXML
    public void visualizarArticulos(ActionEvent event){
        articulos = FXCollections.observableArrayList();
        ArticuloImplementar ai = new ArticuloImplementar();
        ObservableList<Articulo> items = ai.getArticulos();

        this.lv_articulos.setItems(items);
        this.lv_articulos.refresh();

    }

    @FXML
    public void modificarArticulo(ActionEvent event){

        int id = Integer.parseInt(this.tf_id.getText());
        String codigo = this.tf_codigo.getText();
        String nombre = this.tf_nombre.getText();
        String seccion = this.tf_seccion.getText();
        String descripcion = this.tf_descripcion.getText();
        double precio = Double.parseDouble(this.tf_precio.getText());
        String fecha = this.tf_fecha.getText();
        int stock = Integer.parseInt(this.tf_stock.getText());
        String origen = this.tf_origen.getText();

        Articulo a = new Articulo(id,codigo,nombre,seccion,descripcion,precio,fecha,stock,origen);
        Repositorio repo = new ArticuloImplementar();

        try {
            int resultado = repo.modificarArticulo(a);
            if( resultado > 0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Informacion");
                alert.setContentText("Se ha modificado correctamente");
                alert.showAndWait();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("No se ha podido modificar articulo con codigo: "+codigo);
            }

            //this.lb_mensaje_accion.setText("Articulo " + nombre +" se ha modificado con éxito");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public boolean eliminarArticulo(ActionEvent event) {
       //Pendiente revisar las comprobaciones si el articulo exitse o no con el id que se quiere eliminar

        boolean eliminado = false;

        int id = Integer.parseInt(this.tf_id.getText());

        Repositorio repo = new ArticuloImplementar();
        try {
            eliminado = repo.eliminarArticulo(id);
            if (eliminado) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Informacion");
                alert.setContentText("Se ha eliminado correctamente");
                alert.showAndWait();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("No existe o no se pudo eliminar");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        return eliminado;
    }

    public void closeWindows() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));

        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = new Stage();


        stage.setScene(scene);
        stage.setTitle("Consulta productos:");
        stage.show();

        Stage myStage = (Stage) this.button_cancelar.getScene().getWindow();
        myStage.close();
    }

}
