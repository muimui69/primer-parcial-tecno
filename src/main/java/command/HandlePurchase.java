package command;

import business.BPurchase;
import java.util.List;

public class HandlePurchase {

    public static String save(String params) {
        String[] partsOfParams = params.split(",\\s*");

        if (partsOfParams.length == 2) {
            try {
                int userId = Integer.parseInt(partsOfParams[0]);
                double total = Double.parseDouble(partsOfParams[1]);

                BPurchase purchase = new BPurchase();
                return purchase.save(userId, total);
            } catch (NumberFormatException e) {
                return "Error: El ID de usuario y el total deben ser valores numéricos válidos. " + e.getMessage();
            } catch (Exception e) {
                return "Error al guardar la compra: " + e.getMessage();
            }
        } else {
            return "Error en HandlePurchase: Número de parámetros incorrecto para save. Esperados: 2, Recibidos: " + partsOfParams.length;
        }
    }

    public static String update(String params) {
        String[] partsOfParams = params.split(",\\s*");

        if (partsOfParams.length == 3) {
            try {
                int id = Integer.parseInt(partsOfParams[0]);
                int userId = Integer.parseInt(partsOfParams[1]);
                double total = Double.parseDouble(partsOfParams[2]);

                BPurchase purchase = new BPurchase();
                return purchase.update(id, userId, total);
            } catch (NumberFormatException e) {
                return "Error: El ID, el ID de usuario y el total deben ser valores numéricos válidos. " + e.getMessage();
            } catch (Exception e) {
                return "Error al actualizar la compra: " + e.getMessage();
            }
        } else {
            return "Error en HandlePurchase: Número de parámetros incorrecto para update. Esperados: 3, Recibidos: " + partsOfParams.length;
        }
    }

    public static String delete(String idStr) {
        try {
            int purchaseId = Integer.parseInt(idStr.trim());
            BPurchase purchase = new BPurchase();
            return purchase.delete(purchaseId);
        } catch (NumberFormatException e) {
            return "Error en HandlePurchase: El ID debe ser numérico. " + e.getMessage();
        }
    }

    public static String findOne(String idStr) {
        try {
            int purchaseId = Integer.parseInt(idStr.trim());
            BPurchase purchase = new BPurchase();
            String[] purchaseDetails = purchase.findOne(purchaseId);
            if (purchaseDetails == null || purchaseDetails.length == 0) {
                return "No se encontró la compra con ID: " + idStr;
            }
            return String.join(", ", purchaseDetails);
        } catch (NumberFormatException e) {
            return "Error: El ID debe ser numérico. " + e.getMessage();
        }
    }

    public static String findAll() {
        BPurchase purchase = new BPurchase();
        try {
            List<String[]> purchases = purchase.findAll();
            if (purchases.isEmpty()) {
                return "No hay compras registradas.";
            }
            StringBuilder sb = new StringBuilder();
            for (String[] purchaseInfo : purchases) {
                sb.append("Compra: ").append(String.join(", ", purchaseInfo)).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            return "Error al recuperar las compras: " + e.getMessage();
        }
    }
}
