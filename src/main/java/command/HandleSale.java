package command;

import business.BSale;
import java.util.List;

public class HandleSale {

    public static String save(String params) {
        String[] partsOfParams = params.split(",\\s*");

        if (partsOfParams.length == 2) {
            try {
                int userId = Integer.parseInt(partsOfParams[0]);
                double total = Double.parseDouble(partsOfParams[1]);

                BSale sale = new BSale();
                return sale.save(userId, total);
            } catch (NumberFormatException e) {
                return "Error: El ID de usuario y el total deben ser valores numéricos válidos. " + e.getMessage();
            } catch (Exception e) {
                return "Error al guardar la venta: " + e.getMessage();
            }
        } else {
            return "Error en HandleSale: Número de parámetros incorrecto para save. Esperados: 3, Recibidos: " + partsOfParams.length;
        }
    }

    public static String update(String params) {
        String[] partsOfParams = params.split(",\\s*");

        if (partsOfParams.length == 3) {
            try {
                int id = Integer.parseInt(partsOfParams[0]);
                int userId = Integer.parseInt(partsOfParams[1]);
                double total = Double.parseDouble(partsOfParams[2]);

                BSale sale = new BSale();
                return sale.update(id, userId, total);
            } catch (NumberFormatException e) {
                return "Error: El ID, el ID de usuario y el total deben ser valores numéricos válidos. " + e.getMessage();
            } catch (Exception e) {
                return "Error al actualizar la venta: " + e.getMessage();
            }
        } else {
            return "Error en HandleSale: Número de parámetros incorrecto para update. Esperados: 4, Recibidos: " + partsOfParams.length;
        }
    }

    public static String delete(String idStr) {
        try {
            int saleId = Integer.parseInt(idStr.trim());
            BSale sale = new BSale();
            return sale.delete(saleId);
        } catch (NumberFormatException e) {
            return "Error en HandleSale: El ID debe ser numérico. " + e.getMessage();
        }
    }

    public static String findOne(String idStr) {
        try {
            int saleId = Integer.parseInt(idStr.trim());
            BSale sale = new BSale();
            String[] saleDetails = sale.findOne(saleId);
            if (saleDetails == null || saleDetails.length == 0) {
                return "No se encontró la venta con ID: " + idStr;
            }
            return String.join(", ", saleDetails);
        } catch (NumberFormatException e) {
            return "Error: El ID debe ser numérico. " + e.getMessage();
        }
    }

    public static String findAll() {
        BSale sale = new BSale();
        try {
            List<String[]> sales = sale.findAll();
            if (sales.isEmpty()) {
                return "No hay ventas registradas.";
            }
            StringBuilder sb = new StringBuilder();
            for (String[] saleInfo : sales) {
                sb.append("Venta: ").append(String.join(", ", saleInfo)).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            return "Error al recuperar las ventas: " + e.getMessage();
        }
    }
}
