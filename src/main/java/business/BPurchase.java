package business;

import data.DPurchase;
import java.sql.SQLException;
import java.util.List;

public class BPurchase {
    private final DPurchase dPurchase;

    public BPurchase() {
        this.dPurchase = new DPurchase();
    }

    public String save(int userId, double total, String purchaseDate) {
        try {
            return dPurchase.save(userId, total, purchaseDate);
        } catch (SQLException e) {
            e.printStackTrace();
            return "La compra no se pudo guardar: " + e.getMessage();
        }
    }

    public String update(int id, int userId, double total, String purchaseDate) {
        try {
            return dPurchase.update(id, userId, total, purchaseDate);
        } catch (SQLException e) {
            e.printStackTrace();
            return "La compra no se pudo actualizar: " + e.getMessage();
        }
    }

    public String delete(int id) {
        try {
            return dPurchase.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return "La compra no se pudo eliminar: " + e.getMessage();
        }
    }

    public List<String[]> findAll() {
        try {
            return dPurchase.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String[] findOne(int id) {
        try {
            return dPurchase.findOne(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
