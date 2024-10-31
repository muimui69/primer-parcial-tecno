package business;

import data.DInventory;
import java.sql.SQLException;
import java.util.List;

public class BInventory {
    private final DInventory dInventory;

    public BInventory() {
        this.dInventory = new DInventory();
    }

    public String save(int productId, String movementType, int quantity) {
        try {

            return dInventory.save(productId, movementType, quantity);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El movimiento de inventario no se pudo guardar: " + e.getMessage();
        }
    }

    public String update(int id, int productId, String movementType, int quantity) {
        try {
            return dInventory.update(id, productId, movementType, quantity);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El movimiento de inventario no se pudo actualizar: " + e.getMessage();
        }
    }

    public String delete(int id) {
        try {
            return dInventory.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El movimiento de inventario no se pudo eliminar: " + e.getMessage();
        }
    }

    public List<String[]> findAll() {
        try {
            return dInventory.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String[] findOne(int id) {
        try {
            return dInventory.findOne(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
