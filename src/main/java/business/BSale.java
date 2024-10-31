package business;

import java.sql.SQLException;
import java.util.List;
import data.DSale;

public class BSale {
    private DSale dSale;

    public BSale() {
        this.dSale = new DSale();
    }

    public String save(int userId, double total) {
        try {
            return dSale.save(userId, total);
        } catch (SQLException e) {
            e.printStackTrace();
            return "La venta no se pudo guardar: " + e.getMessage();
        }
    }

    public String update(int id, int userId, double total) {
        try {
            return dSale.update(id, userId, total);
        } catch (SQLException e) {
            e.printStackTrace();
            return "La venta no se pudo actualizar: " + e.getMessage();
        }
    }

    public String delete(int id) {
        try {
            return dSale.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return "La venta no se pudo eliminar: " + e.getMessage();
        }
    }

    public List<String[]> findAll() {
        try {
            return dSale.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String[] findOne(int id) {
        try {
            return dSale.findOne(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
