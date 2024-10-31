//package com.primer_parcial_tecno;
//
//import business.BUser;
//import business.BProduct;
//import business.BPromotion;
//import business.BSale;
//import business.BSaleDetails;
//import business.BPayment;
//import utils.DateString;
//
//import java.sql.Date;
//import java.text.ParseException;
//import java.util.List;
//
//public class Main {
//    public static void main(String[] args) {
//        // Ejemplo de negocio para la entidad User
//        BUser bUser = new BUser();
//        System.out.println("== Gestionando Usuarios ==");
//        String resultUser = bUser.save("Alice Smith", "alice@example.com", "password123", "CLIENT");
//        System.out.println(resultUser);
//
//        // Consultar y mostrar usuarios
//        List<String[]> users = bUser.findAll();
//        System.out.println("\nUsuarios en la base de datos:");
//        for (String[] user : users) {
//            System.out.println("ID: " + user[0] + ", Name: " + user[1] + ", Email: " + user[2] + ", Role: " + user[3]);
//        }
//
//        // Ejemplo de negocio para la entidad Product
//        BProduct bProduct = new BProduct();
//        System.out.println("\n== Gestionando Productos ==");
//        String resultProduct = bProduct.save("Skinny Jeans", 39.99, "Skinny fit jeans", 50);
//        System.out.println(resultProduct);
//
//        // Consultar y mostrar productos
//        List<String[]> products = bProduct.findAll();
//        System.out.println("\nProductos en la base de datos:");
//        for (String[] product : products) {
//            System.out.println("ID: " + product[0] + ", Name: " + product[1] + ", Price: " + product[2] + ", Stock: " + product[4]);
//        }
//
//        // Ejemplo de negocio para la entidad Promotion
//        BPromotion bPromotion = new BPromotion();
//        System.out.println("\n== Gestionando Promociones ==");
//
//        try {
//            Date startDate = DateString.StringToDateSQL("2024-12-01");
//            Date endDate = DateString.StringToDateSQL("2024-12-31");
//            String resultPromotion = bPromotion.save("Winter Sale", 20.0, startDate, endDate);
//            System.out.println(resultPromotion);
//        } catch (ParseException e) {
//            System.out.println("Error al convertir la fecha de la promoción: " + e.getMessage());
//        }
//
//        // Consultar y mostrar promociones
//        List<String[]> promotions = bPromotion.findAll();
//        System.out.println("\nPromociones en la base de datos:");
//        for (String[] promotion : promotions) {
//            System.out.println("ID: " + promotion[0] + ", Name: " + promotion[1] + ", Discount: " + promotion[2]);
//        }
//
//        // Ejemplo de negocio para realizar una Venta
//        BSale bSale = new BSale();
//        System.out.println("\n== Gestionando Ventas ==");
//
//        String resultSale = bSale.save(1, 39.99);  // Usuario ID = 1
//        System.out.println(resultSale);
//
//        // Añadir detalles a la venta
//        BSaleDetails bSaleDetails = new BSaleDetails();
//        String resultSaleDetail = bSaleDetails.save(1, 1, 1, 39.99); // Sale ID = 1, Product ID = 1
//        System.out.println(resultSaleDetail);
//
//        // Registrar un pago para la venta
//        BPayment bPayment = new BPayment();
//        String resultPayment = bPayment.save(1, 39.99, "Credit Card");
//        System.out.println(resultPayment);
//
//        // Consultar y mostrar ventas
//        List<String[]> sales = bSale.findAll();
//        System.out.println("\nVentas en la base de datos:");
//        for (String[] sale : sales) {
//            System.out.println("ID: " + sale[0] + ", UserID: " + sale[1] + ", Total: " + sale[2] + ", Sale Date: " + sale[3]);
//        }
//    }
//}


//command ejemplo
//package com.primer_parcial_tecno;
//
//import command.CommandInterpreter;
//
//public class Main {
//    public static void main(String[] args) {
//        // Ejemplos de comandos para probar el sistema
//        System.out.println("== Pruebas con el CommandInterpreter ==");
//
//        // Prueba para guardar un usuario
//        String result = CommandInterpreter.interpret("user save(Alice Johnson, alice.johnson@example.com, password123, CLIENT)");
//        System.out.println("Resultado guardar usuario: " + result);
//
//        // Prueba para actualizar un usuario
//        result = CommandInterpreter.interpret("user update(1, Alice Johnson, alice.johnson@example.com, newpassword, CLIENT)");
//        System.out.println("Resultado actualizar usuario: " + result);
//
//        // Prueba para obtener todos los usuarios
//        result = CommandInterpreter.interpret("user findAll()");
//        System.out.println("Resultado obtener todos los usuarios: " + result);
//
//        // Prueba para encontrar un usuario específico
//        result = CommandInterpreter.interpret("user findOne(1)");
//        System.out.println("Resultado encontrar usuario: " + result);
//
//        // Prueba para eliminar un usuario
//        result = CommandInterpreter.interpret("user delete(1)");
//        System.out.println("Resultado eliminar usuario: " + result);
//
//        // Prueba para guardar un producto
//        result = CommandInterpreter.interpret("product save(Skinny Jeans, 39.99, Skinny fit jeans, 50)");
//        System.out.println("Resultado guardar producto: " + result);
//
//        // Prueba para actualizar un producto
//        result = CommandInterpreter.interpret("product update(1, Skinny Jeans, 45.99, Skinny fit jeans, 60)");
//        System.out.println("Resultado actualizar producto: " + result);
//
//        // Prueba para obtener todos los productos
//        result = CommandInterpreter.interpret("product findAll()");
//        System.out.println("Resultado obtener todos los productos: " + result);
//
//        // Prueba para guardar una promoción
//        result = CommandInterpreter.interpret("promotion save(Winter Sale, 20.0, 2024-12-01, 2024-12-31)");
//        System.out.println("Resultado guardar promoción: " + result);
//
//        // Prueba para obtener todas las promociones
//        result = CommandInterpreter.interpret("promotion findAll()");
//        System.out.println("Resultado obtener todas las promociones: " + result);
//
//        // Prueba para guardar una venta
//        result = CommandInterpreter.interpret("sale save(1, 100.0, 2024-12-15)");
//        System.out.println("Resultado guardar venta: " + result);
//
//        // Prueba para obtener todas las ventas
//        result = CommandInterpreter.interpret("sale findAll()");
//        System.out.println("Resultado obtener todas las ventas: " + result);
//
//        // Prueba para guardar un pago
//        result = CommandInterpreter.interpret("payment save(1, 2024-12-15, 100.0, Credit Card)");
//        System.out.println("Resultado guardar pago: " + result);
//
//        // Prueba para obtener todos los pagos
//        result = CommandInterpreter.interpret("payment findAll()");
//        System.out.println("Resultado obtener todos los pagos: " + result);
//    }
//}
//
