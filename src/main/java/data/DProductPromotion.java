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
        String query = "INSERT INTO product_promotions (promotion_id, product_id) VALUES (?, ?)";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ps.setInt(1, promotionId);
        ps.setInt(2, productId);
        if (ps.executeUpdate() == 0) {
            System.err.println("class DProductPromotion.java dice:" + "La promoción del producto no se pudo insertar");
            throw new SQLException();
        }
        return "La promoción del producto se insertó con éxito";
    }

    public List<String[]> findAll() throws SQLException {
        List<String[]> productPromotions = new ArrayList<>();
        String query = "SELECT * FROM product_promotions";
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

    public void closeConnection() {
        db.closeConnection();
    }
}
