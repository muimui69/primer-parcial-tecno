package business;

import data.DSaleDetails;
import java.sql.SQLException;
import java.util.List;

public class BSaleDetails {
    private final DSaleDetails dSaleDetails;

    public BSaleDetails() {
        this.dSaleDetails = new DSaleDetails();
    }

    public String save(int saleId, int productId, int quantity, double price) {
        try {
            return dSaleDetails.save(saleId, productId, quantity, price);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El detalle de venta no se pudo guardar: " + e.getMessage();
        }
    }

    public String update(int saleId, int productId, int newQuantity, double newPrice) {
        try {
            return dSaleDetails.update(saleId, productId, newQuantity, newPrice);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El detalle de venta no se pudo actualizar: " + e.getMessage();
        }
    }

    public String delete(int saleId, int productId) {
        try {
            return dSaleDetails.delete(saleId, productId);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El detalle de venta no se pudo eliminar: " + e.getMessage();
        }
    }

    public List<String[]> findAll() {
        try {
            return dSaleDetails.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String[] findOne(int saleId, int productId) {
        try {
            return dSaleDetails.findOne(saleId, productId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
