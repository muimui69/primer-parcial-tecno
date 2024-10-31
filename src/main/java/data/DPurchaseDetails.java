package data;

import config.ConfigDB;
import config.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DPurchaseDetails {
    private DatabaseConnection db;
    ConfigDB configDB = new ConfigDB();

    public DPurchaseDetails() {
        this.db = new DatabaseConnection(
                configDB.getUser(),
                configDB.getPassword(),
                configDB.getHost(),
                configDB.getPort(),
                configDB.getDbName()
        );
    }

    public String save(int purchaseId, int productId, int quantity, double price) throws SQLException {
        String query = "INSERT INTO purchase_details (purchase_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ps.setInt(1, purchaseId);
        ps.setInt(2, productId);
        ps.setInt(3, quantity);
        ps.setDouble(4, price);
        if (ps.executeUpdate() == 0) {
            System.err.println("class DPurchaseDetails.java dice:" + "El detalle de compra no se pudo insertar");
            throw new SQLException();
        }
        return "El detalle de compra se insertó con éxito";
    }

    public List<String[]> findAll() throws SQLException {
        List<String[]> purchaseDetails = new ArrayList<>();
        String query = "SELECT * FROM purchase_details";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            purchaseDetails.add(new String[] {
                    String.valueOf(rs.getInt("purchase_id")),
                    String.valueOf(rs.getInt("product_id")),
                    String.valueOf(rs.getInt("quantity")),
                    String.valueOf(rs.getDouble("price"))
            });
        }
        return purchaseDetails;
    }

    public void closeConnection() {
        db.closeConnection();
    }
}
