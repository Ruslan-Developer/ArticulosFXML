package com.ruslan.articulosfxml;

import com.ruslan.articulosfxml.modelo.Articulo;
import com.ruslan.articulosfxml.util.ConexionBBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticuloImplementar implements Repositorio<Articulo> {

    private static Articulo crearProducto(ResultSet rs) throws SQLException {
        Articulo articulo = new Articulo();
        articulo.setId(rs.getInt("id"));
        articulo.setCodigo(rs.getString("codigo"));
        articulo.setNombre(rs.getString("nombre"));
        articulo.setSeccion(rs.getString("seccion"));
        articulo.setDescripcion(rs.getString("descripcion"));
        articulo.setPrecio(rs.getDouble("precio"));
        articulo.setFecha(rs.getString("fecha"));
        articulo.setStock(rs.getInt("stock"));
        articulo.setOrigen(rs.getString("origen"));


        return articulo;

    }

    @Override
    public List<Articulo> listar() {
        List<Articulo> articulos = new ArrayList<>();


        try (Connection conexion = ConexionBBDD.conectar(); Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM productos")) {


            while (rs.next()) {
                Articulo articulo = crearProducto(rs);
                articulos.add(articulo);
            }

        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return articulos;
    }

    public ObservableList<Articulo> getArticulos() {
        ObservableList<Articulo> obs = FXCollections.observableArrayList();

        try (Connection conexion = ConexionBBDD.conectar()) {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM productos");


            while (rs.next()) {
                Articulo articulo = crearProducto(rs);
                obs.add(articulo);
            }

        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return obs;


    }


    @Override
    public void insertarArticulo(Articulo articulo) throws IOException, ClassNotFoundException, SQLException {


        try (Connection conexion = ConexionBBDD.conectar(); PreparedStatement stmt = conexion.prepareStatement("INSERT INTO productos (id,codigo, nombre, seccion, descripcion, precio, fecha, stock, origen) VALUES (?,?,?,?,?,?,?,?,?)")) {

            stmt.setInt(1, articulo.getId());
            stmt.setString(2, articulo.getCodigo());
            stmt.setString(3, articulo.getNombre());
            stmt.setString(4, articulo.getSeccion());
            stmt.setString(5, articulo.getDescripcion());
            stmt.setDouble(6, articulo.getPrecio());
            stmt.setString(7, articulo.getFecha());
            stmt.setInt(8, articulo.getStock());
            stmt.setString(9, articulo.getOrigen());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }


    }

    @Override
    public int modificarArticulo(Articulo articulo) throws SQLException, IOException, ClassNotFoundException {

        try (Connection conexion = ConexionBBDD.conectar(); PreparedStatement stmt = conexion.prepareStatement("UPDATE productos SET nombre= ?, seccion= ?, descripcion= ?, precio= ?, fecha= ?, stock= ?, origen= ? WHERE codigo = ?")) {


            stmt.setString(1, articulo.getNombre());
            stmt.setString(2, articulo.getSeccion());
            stmt.setString(3, articulo.getDescripcion());
            stmt.setDouble(4, articulo.getPrecio());
            stmt.setString(5, articulo.getFecha());
            stmt.setInt(6, articulo.getStock());
            stmt.setString(7, articulo.getOrigen());
            stmt.setString(8, articulo.getCodigo());

             int filasModificadas = stmt.executeUpdate();
             if(filasModificadas == 0){ //Si devuelve un 0 significa que ningún registro en la base de datos coincidió con la condición de la consulta, en este caso codigo
                return -1; //Articulo con dicho codigo no existe
             }else{
                 return 1; //Articulo modificado correctamente
             }


        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }


    }

    @Override
    public boolean eliminarArticulo(int id) throws SQLException, IOException, ClassNotFoundException {

        try (Connection conexion = ConexionBBDD.conectar(); PreparedStatement stmt = conexion.prepareStatement("DELETE FROM productos WHERE id = ?")) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return false;
    }


}
