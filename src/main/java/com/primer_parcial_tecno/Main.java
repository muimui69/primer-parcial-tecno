package com.primer_parcial_tecno;

import data.DProduct;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando prueba de SELECT...");

        DProduct dProduct = new DProduct();

        try {
            List<String[]> products = dProduct.findAll();

            System.out.println("Productos encontrados:");
            for (String[] product : products) {
                System.out.println("ID: " + product[0] + ", Nombre: " + product[1] + ", Precio: " + product[2] +
                        ", Descripción: " + product[3] + ", Stock: " + product[4]);
            }

        } catch (Exception e) {
            System.err.println("Error al realizar SELECT: " + e.getMessage());
            e.printStackTrace();
        } finally {
            dProduct.closeConnection();
        }
    }
}
