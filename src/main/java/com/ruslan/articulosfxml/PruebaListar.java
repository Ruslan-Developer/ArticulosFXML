package com.ruslan.articulosfxml;

import com.ruslan.articulosfxml.modelo.Articulo;


public class PruebaListar {


    public static void main(String[] args)  {
        Repositorio<Articulo> repo = new ArticuloImplementar();
        repo.listar().forEach(System.out::println);



    }
}
