package business;

import data.DPurchaseDetails;
import java.sql.SQLException;
import java.util.List;

public class BPurchaseDetails {
    private final DPurchaseDetails dPurchaseDetails;

    public BPurchaseDetails() {
        this.dPurchaseDetails = new DPurchaseDetails();
    }

    public String save(int purchaseId, int productId, int quantity, double price) {
        try {
            return dPurchaseDetails.save(purchaseId, productId, quantity, price);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El detalle de compra no se pudo guardar: " + e.getMessage();
        }
    }

    public String update(int purchaseId, int productId, int newQuantity, double newPrice) {
        try {
            return dPurchaseDetails.update(purchaseId, productId, newQuantity, newPrice);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El detalle de compra no se pudo actualizar: " + e.getMessage();
        }
    }

    public String delete(int purchaseId, int productId) {
        try {
            return dPurchaseDetails.delete(purchaseId, productId);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El detalle de compra no se pudo eliminar: " + e.getMessage();
        }
    }

    public List<String[]> findAll() {
        try {
            return dPurchaseDetails.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String[] findOne(int purchaseId, int productId) {
        try {
            return dPurchaseDetails.findOne(purchaseId, productId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
