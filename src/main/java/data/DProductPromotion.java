package data;

import config.ConfigDB;
import config.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DProductPromotion {
    private final DatabaseConnection db;
    ConfigDB configDB = new ConfigDB();

    public DProductPromotion() {
        this.db = new DatabaseConnection(
                configDB.getUser(),
                configDB.getPassword(),
                configDB.getHost(),
                configDB.getPort(),
                configDB.getDbName()
        );
    }

    public String save(int promotionId, int productId) throws SQLException {
        String query = "INSERT INTO product_promotion (promotion_id, product_id) VALUES (?, ?)";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ps.setInt(1, promotionId);
        ps.setInt(2, productId);
        if (ps.executeUpdate() == 0) {
            System.err.println("class DProductPromotion.java dice:" + "La promoción del producto no se pudo insertar");
            throw new SQLException();
        }
        return "La promoción del producto se insertó con éxito";
    }

    public String update(int promotionId, int productId, int newPromotionId, int newProductId) throws SQLException {
        String query = "UPDATE product_promotion SET promotion_id=?, product_id=? WHERE promotion_id=? AND product_id=?";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ps.setInt(1, newPromotionId);
        ps.setInt(2, newProductId);
        ps.setInt(3, promotionId);
        ps.setInt(4, productId);
        if (ps.executeUpdate() == 0) {
            System.err.println("class DProductPromotion.java dice:" + "La promoción del producto no se pudo actualizar");
            throw new SQLException();
        }
        return "La promoción del producto se actualizó con éxito";
    }

    public String delete(int promotionId, int productId) throws SQLException {
        String query = "DELETE FROM product_promotion WHERE promotion_id=? AND product_id=?";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ps.setInt(1, promotionId);
        ps.setInt(2, productId);
        if (ps.executeUpdate() == 0) {
            System.err.println("class DProductPromotion.java dice:" + "La promoción del producto no se pudo eliminar");
            throw new SQLException();
        }
        return "La promoción del producto se eliminó con éxito";
    }

    public List<String[]> findAll() throws SQLException {
        List<String[]> productPromotions = new ArrayList<>();
        String query = "SELECT * FROM product_promotion";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            productPromotions.add(new String[] {
                    String.valueOf(rs.getInt("promotion_id")),
                    String.valueOf(rs.getInt("product_id"))
            });
        }
        return productPromotions;
    }

    public String[] findOne(int promotionId, int productId) throws SQLException {
        String[] productPromotion = null;
        String query = "SELECT * FROM product_promotion WHERE promotion_id=? AND product_id=?";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ps.setInt(1, promotionId);
        ps.setInt(2, productId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            productPromotion = new String[] {
                    String.valueOf(rs.getInt("promotion_id")),
                    String.valueOf(rs.getInt("product_id"))
            };
        }
        return productPromotion;
    }

    public void closeConnection() {
        if (db != null) db.closeConnection();
    }
}
