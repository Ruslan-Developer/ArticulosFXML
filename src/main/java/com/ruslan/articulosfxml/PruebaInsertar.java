package com.ruslan.articulosfxml;

import com.ruslan.articulosfxml.modelo.Articulo;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class PruebaInsertar {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);

        Repositorio<Articulo> repo = new ArticuloImplementar();
        Articulo a = new Articulo();

        System.out.println("Insertar id");
        a.setId(sc.nextInt());
        System.out.println("Insertar codigo");
        a.setCodigo(sc.next());
        System.out.println("nombre");
        a.setNombre(sc.nextLine());
        System.out.println("seccion");
        a.setSeccion(sc.nextLine());

        repo.insertarArticulo(a);
        System.out.println("Articulo insertado");

    }








}
