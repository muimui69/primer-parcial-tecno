package command;

import business.BProduct;
import java.util.List;

public class HandleProduct {

    public static String save(String params) {
        String[] partsOfParams = params.split(",\\s*");

        if (partsOfParams.length == 4) {
            try {
                String name = partsOfParams[0];
                double price = Double.parseDouble(partsOfParams[1]);
                String description = partsOfParams[2];
                int stock = Integer.parseInt(partsOfParams[3]);

                BProduct product = new BProduct();
                return product.save(name, price, description, stock);
            } catch (NumberFormatException e) {
                return "Error: El precio y el stock deben ser valores numéricos válidos. " + e.getMessage();
            } catch (Exception e) {
                return "Error al guardar el producto: " + e.getMessage();
            }
        } else {
            return "Error en HandleProduct: Número de parámetros incorrecto para save. Esperados: 4, Recibidos: " + partsOfParams.length;
        }
    }

    public static String update(String params) {
        String[] partsOfParams = params.split(",\\s*");

        if (partsOfParams.length == 5) {
            try {
                int id = Integer.parseInt(partsOfParams[0]);
                String name = partsOfParams[1];
                double price = Double.parseDouble(partsOfParams[2]);
                String description = partsOfParams[3];
                int stock = Integer.parseInt(partsOfParams[4]);

                BProduct product = new BProduct();
                return product.update(id, name, price, description, stock);
            } catch (NumberFormatException e) {
                return "Error: ID, precio y stock deben ser valores numéricos válidos. " + e.getMessage();
            } catch (Exception e) {
                return "Error al actualizar el producto: " + e.getMessage();
            }
        } else {
            return "Error en HandleProduct: Número de parámetros incorrecto para update. Esperados: 5, Recibidos: " + partsOfParams.length;
        }
    }

    public static String delete(String idStr) {
        try {
            int productId = Integer.parseInt(idStr.trim());
            BProduct product = new BProduct();
            return product.delete(productId);
        } catch (NumberFormatException e) {
            return "Error en HandleProduct: El ID debe ser numérico. " + e.getMessage();
        }
    }

    public static String findOne(String idStr) {
        try {
            int productId = Integer.parseInt(idStr.trim());
            BProduct product = new BProduct();
            String[] productDetails = product.findOne(productId);
            if (productDetails == null || productDetails.length == 0) {
                return "No se encontró el producto con ID: " + idStr;
            }
            return String.join(", ", productDetails);
        } catch (NumberFormatException e) {
            return "Error: El ID debe ser numérico. " + e.getMessage();
        }
    }

    public static String findAll() {
        BProduct product = new BProduct();
        try {
            List<String[]> products = product.findAll();
            if (products.isEmpty()) {
                return "No hay productos registrados.";
            }
            StringBuilder sb = new StringBuilder();
            for (String[] productInfo : products) {
                sb.append("Producto: ").append(String.join(", ", productInfo)).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            return "Error al recuperar productos: " + e.getMessage();
        }
    }
}
