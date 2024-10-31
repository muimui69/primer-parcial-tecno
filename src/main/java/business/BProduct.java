package business;

import java.sql.SQLException;
import java.util.List;
import data.DProduct;

public class BProduct {
    private final DProduct dProduct;

    public BProduct() {
        this.dProduct = new DProduct();
    }

    public String save(String name, double price, String description, int stock) {
        try {
            return dProduct.save(name, price, description, stock);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El producto no se pudo guardar: " + e.getMessage();
        }
    }

    public String update(int id, String name, double price, String description, int stock) {
        try {
            return dProduct.update(id, name, price, description, stock);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El producto no se pudo actualizar: " + e.getMessage();
        }
    }

    public String delete(int id) {
        try {
            return dProduct.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El producto no se pudo eliminar: " + e.getMessage();
        }
    }

    public List<String[]> findAll() {
        try {
            return dProduct.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String[] findOne(int id) {
        try {
            return dProduct.findOne(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
