package command;

import business.BInventory;
import java.util.List;

public class HandleInventory {

    public static String save(String params) {
        String[] partsOfParams = params.split(",\\s*");

        if (partsOfParams.length == 3) {
            try {
                int productId = Integer.parseInt(partsOfParams[0]);
                String movementType = partsOfParams[1];
                int quantity = Integer.parseInt(partsOfParams[2]);

                BInventory inventory = new BInventory();
                return inventory.save(productId, movementType, quantity);
            } catch (NumberFormatException e) {
                return "Error: El ID del producto y la cantidad deben ser valores numéricos válidos. " + e.getMessage();
            } catch (Exception e) {
                return "Error al guardar el movimiento de inventario: " + e.getMessage();
            }
        } else {
            return "Error en HandleInventory: Número de parámetros incorrecto para save. Esperados: 3, Recibidos: " + partsOfParams.length;
        }
    }

    public static String update(String params) {
        String[] partsOfParams = params.split(",\\s*");

        if (partsOfParams.length == 4) {
            try {
                int id = Integer.parseInt(partsOfParams[0]);
                int productId = Integer.parseInt(partsOfParams[1]);
                String movementType = partsOfParams[2];
                int quantity = Integer.parseInt(partsOfParams[3]);

                BInventory inventory = new BInventory();
                return inventory.update(id, productId, movementType, quantity);
            } catch (NumberFormatException e) {
                return "Error: El ID, el ID del producto y la cantidad deben ser valores numéricos válidos. " + e.getMessage();
            } catch (Exception e) {
                return "Error al actualizar el movimiento de inventario: " + e.getMessage();
            }
        } else {
            return "Error en HandleInventory: Número de parámetros incorrecto para update. Esperados: 4, Recibidos: " + partsOfParams.length;
        }
    }

    public static String delete(String idStr) {
        try {
            int inventoryId = Integer.parseInt(idStr.trim());
            BInventory inventory = new BInventory();
            return inventory.delete(inventoryId);
        } catch (NumberFormatException e) {
            return "Error en HandleInventory: El ID debe ser numérico. " + e.getMessage();
        }
    }

    public static String findOne(String idStr) {
        try {
            int inventoryId = Integer.parseInt(idStr.trim());
            BInventory inventory = new BInventory();
            String[] inventoryDetails = inventory.findOne(inventoryId);
            if (inventoryDetails == null || inventoryDetails.length == 0) {
                return "No se encontró el movimiento de inventario con ID: " + idStr;
            }
            return String.join(", ", inventoryDetails);
        } catch (NumberFormatException e) {
            return "Error: El ID debe ser numérico. " + e.getMessage();
        }
    }

    public static String findAll() {
        BInventory inventory = new BInventory();
        try {
            List<String[]> inventories = inventory.findAll();
            if (inventories.isEmpty()) {
                return "No hay movimientos de inventario registrados.";
            }
            StringBuilder sb = new StringBuilder();
            for (String[] inventoryInfo : inventories) {
                sb.append("Inventario: ").append(String.join(", ", inventoryInfo)).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            return "Error al recuperar los movimientos de inventario: " + e.getMessage();
        }
    }
}
