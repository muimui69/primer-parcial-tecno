package com.primer_parcial_tecno;

import business.BUser;
import business.BProduct;
import business.BPromotion;
import business.BSale;
import business.BSaleDetails;
import business.BPayment;
import java.util.List;

//TRUNCATE TABLE payment, sale_detail, sale, product_promotion, promotion, inventory, purchase_detail, purchase, product, users RESTART IDENTITY CASCADE;

public class Main {
    public static void main(String[] args) {
        // Ejemplo de negocio para la entidad User
        BUser bUser = new BUser();
        System.out.println("== Gestionando Usuarios ==");
        String resultUser = bUser.save("Alice Smith", "alice@example.com", "password123", "CLIENT");
        System.out.println(resultUser);

        // Consultar y mostrar usuarios
        List<String[]> users = bUser.findAll();
        System.out.println("\nUsuarios en la base de datos:");
        for (String[] user : users) {
            System.out.println("ID: " + user[0] + ", Name: " + user[1] + ", Email: " + user[2] + ", Role: " + user[3]);
        }

        // Ejemplo de negocio para la entidad Product
        BProduct bProduct = new BProduct();
        System.out.println("\n== Gestionando Productos ==");
        String resultProduct = bProduct.save("Skinny Jeans", 39.99, "Skinny fit jeans", 50);
        System.out.println(resultProduct);

        // Consultar y mostrar productos
        List<String[]> products = bProduct.findAll();
        System.out.println("\nProductos en la base de datos:");
        for (String[] product : products) {
            System.out.println("ID: " + product[0] + ", Name: " + product[1] + ", Price: " + product[2] + ", Stock: " + product[4]);
        }

        // Ejemplo de negocio para la entidad Promotion
        BPromotion bPromotion = new BPromotion();
        System.out.println("\n== Gestionando Promociones ==");
        String resultPromotion = bPromotion.save("Winter Sale", 20.0, "2024-12-01", "2024-12-31");
        System.out.println(resultPromotion);

        // Consultar y mostrar promociones
        List<String[]> promotions = bPromotion.findAll();
        System.out.println("\nPromociones en la base de datos:");
        for (String[] promotion : promotions) {
            System.out.println("ID: " + promotion[0] + ", Name: " + promotion[1] + ", Discount: " + promotion[2]);
        }

        // Ejemplo de negocio para realizar una Venta
        BSale bSale = new BSale();
        System.out.println("\n== Gestionando Ventas ==");
        String resultSale = bSale.save(1, 39.99, "2024-12-15");  // Usuario ID = 1
        System.out.println(resultSale);

        // Añadir detalles a la venta
        BSaleDetails bSaleDetails = new BSaleDetails();
        String resultSaleDetail = bSaleDetails.save(1, 1, 1, 39.99); // Sale ID = 1, Product ID = 1
        System.out.println(resultSaleDetail);

        // Registrar un pago para la venta
        BPayment bPayment = new BPayment();
        String resultPayment = bPayment.save(1, "2024-12-15", 39.99, "Credit Card");
        System.out.println(resultPayment);

        // Consultar y mostrar ventas
        List<String[]> sales = bSale.findAll();
        System.out.println("\nVentas en la base de datos:");
        for (String[] sale : sales) {
            System.out.println("ID: " + sale[0] + ", UserID: " + sale[1] + ", Total: " + sale[2] + ", Sale Date: " + sale[3]);
        }
    }
}
