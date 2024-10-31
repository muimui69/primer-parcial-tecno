package business;

import data.DProductPromotion;
import java.sql.SQLException;
import java.util.List;

public class BProductPromotion {
    private final DProductPromotion dProductPromotion;

    public BProductPromotion() {
        this.dProductPromotion = new DProductPromotion();
    }

    public String save(int promotionId, int productId) {
        try {
            return dProductPromotion.save(promotionId, productId);
        } catch (SQLException e) {
            e.printStackTrace();
            return "La promoción del producto no se pudo guardar: " + e.getMessage();
        }
    }

    public String update(int promotionId, int productId, int newPromotionId, int newProductId) {
        try {
            return dProductPromotion.update(promotionId, productId, newPromotionId, newProductId);
        } catch (SQLException e) {
            e.printStackTrace();
            return "La promoción del producto no se pudo actualizar: " + e.getMessage();
        }
    }

    public String delete(int promotionId, int productId) {
        try {
            return dProductPromotion.delete(promotionId, productId);
        } catch (SQLException e) {
            e.printStackTrace();
            return "La promoción del producto no se pudo eliminar: " + e.getMessage();
        }
    }

    public List<String[]> findAll() {
        try {
            return dProductPromotion.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String[] findOne(int promotionId, int productId) {
        try {
            return dProductPromotion.findOne(promotionId, productId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
