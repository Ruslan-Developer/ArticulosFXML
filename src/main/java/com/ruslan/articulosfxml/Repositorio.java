package com.ruslan.articulosfxml;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface Repositorio<T>{
    List<T> listar();
    void insertarArticulo(T t) throws SQLException, IOException, ClassNotFoundException;

    int modificarArticulo(T t) throws SQLException, IOException, ClassNotFoundException;

    boolean eliminarArticulo(int id) throws SQLException, IOException, ClassNotFoundException;


}
