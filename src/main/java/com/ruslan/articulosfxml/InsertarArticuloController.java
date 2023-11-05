package com.ruslan.articulosfxml;

import com.ruslan.articulosfxml.modelo.Articulo;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class InsertarArticuloController implements Initializable {

    @FXML
    private Button button_insertar;

    @FXML
    private TableColumn<Articulo, String> col_codigo;

    @FXML
    private TableColumn<Articulo, String> col_descripcion;

    @FXML
    private TableColumn<Articulo, String> col_fecha;

    @FXML
    private TableColumn<Articulo, Integer> col_stock;

    @FXML
    private TableColumn<Articulo, String> col_nombre;

    @FXML
    private TableColumn<Articulo, String> col_origen;

    @FXML
    private TableColumn<Articulo, Double> col_precio;

    @FXML
    private TableColumn<Articulo, String> col_seccion;
    @FXML
    private TableColumn<Articulo, Integer> col_id;

    @FXML
    private TableView<Articulo> tbl_articulos;

    @FXML
    private TextField tf_id;

    @FXML
    private TextField tf_codigo;

    @FXML
    private TextField tf_descripcion;

    @FXML
    private TextField tf_fecha;

    @FXML
    private TextField tf_stock;

    @FXML
    private TextField tf_nombre;

    @FXML
    private TextField tf_origen;

    @FXML
    private TextField tf_precio;

    @FXML
    private TextField tf_seccion;

    @FXML
    private Label lb_estado;

    private ObservableList<Articulo> articulos;
    private Articulo articulo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        ArticuloImplementar ai = new ArticuloImplementar();
        ObservableList<Articulo> items = ai.getArticulos();

        this.tbl_articulos.setItems(items);
        this.tbl_articulos.refresh();

        this.col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.col_codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        this.col_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.col_seccion.setCellValueFactory(new PropertyValueFactory<>("seccion"));
        this.col_descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        this.col_precio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        this.col_fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        this.col_stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        this.col_origen.setCellValueFactory(new PropertyValueFactory<>("origen"));

        /*

        // Añade un listener a la lista para refrescar la tabla cuando se añadan nuevos datos

        items.addListener((ListChangeListener.Change<? extends Articulo> c) ->{
            while(c.next()){
                if(c.wasAdded() || c.wasRemoved()){

                    this.tbl_articulos.refresh();

                }
            }
        });

        this.tbl_articulos.setItems(items);

         */

    }
    public void initAtributos(ObservableList<Articulo> articulos){
        this.articulos = articulos;

    }

    @FXML
    public void agregarArticulo(ActionEvent event) throws IOException {
       /*
        if(codigo.equals("")){

            AlertUtils.mostrarError("El codigo es un campo obligatorio");
            return;
        } */
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
        Repositorio<Articulo> repo = new ArticuloImplementar();
        //Debemos instanciar el objeto items para poder recorrerlo y ver si ya hay un objeto existente dentro
        ArticuloImplementar ai = new ArticuloImplementar();
        ObservableList<Articulo> items = ai.getArticulos();

        try {
            boolean idExiste = false;
            //Recorremos la Lista de los items
            for (Articulo articulo: items) {
                //Comprobamos si las ides que tenemos en Lista coincide con la id introducida por el usuario.
                if(articulo.getId() == a.getId()){
                    //Si se cumple:
                    idExiste = true; //Activamos la bandera
                    break; //Salimos de la comprobación.
                }

            }
            if(!idExiste && !items.contains(a) ){ //Comprobamos que no coincide ni la id ni el nuevo articulo agregado
                repo.insertarArticulo(a);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Informacion");
                alert.setContentText("Se ha añadido correctamente");
                alert.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                if(idExiste){
                    alert.setContentText("El ID del Articulo ya está en uso");
                }else{
                    alert.setContentText("El articulo ya existe");
                }

                alert.showAndWait();
            }

           // lb_estado.setText("Articulo insertado en la BBDD con éxito.");


            this.tbl_articulos.refresh();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    public void closeWindows() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));

        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setTitle("Inserción productos:");

        stage.setScene(scene);
        stage.show();

        Stage myStage = (Stage) this.tbl_articulos.getScene().getWindow();

        myStage.close();



    }
}


