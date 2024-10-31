package data;

import config.ConfigDB;
import config.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DPurchaseDetails {
    private final DatabaseConnection db;
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

    public String update(int purchaseId, int productId, int quantity, double price) throws SQLException {
        String query = "UPDATE purchase_details SET quantity=?, price=? WHERE purchase_id=? AND product_id=?";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ps.setInt(1, quantity);
        ps.setDouble(2, price);
        ps.setInt(3, purchaseId);
        ps.setInt(4, productId);
        if (ps.executeUpdate() == 0) {
            System.err.println("class DPurchaseDetails.java dice:" + "El detalle de compra no se pudo actualizar");
            throw new SQLException();
        }
        return "El detalle de compra se actualizó con éxito";
    }

    public String delete(int purchaseId, int productId) throws SQLException {
        String query = "DELETE FROM purchase_details WHERE purchase_id=? AND product_id=?";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ps.setInt(1, purchaseId);
        ps.setInt(2, productId);
        if (ps.executeUpdate() == 0) {
            System.err.println("class DPurchaseDetails.java dice:" + "El detalle de compra no se pudo eliminar");
            throw new SQLException();
        }
        return "El detalle de compra se eliminó con éxito";
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

    public String[] findOne(int purchaseId, int productId) throws SQLException {
        String[] purchaseDetail = null;
        String query = "SELECT * FROM purchase_details WHERE purchase_id=? AND product_id=?";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ps.setInt(1, purchaseId);
        ps.setInt(2, productId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            purchaseDetail = new String[] {
                    String.valueOf(rs.getInt("purchase_id")),
                    String.valueOf(rs.getInt("product_id")),
                    String.valueOf(rs.getInt("quantity")),
                    String.valueOf(rs.getDouble("price"))
            };
        }
        return purchaseDetail;
    }

    public void closeConnection() {
        if (db != null) db.closeConnection();
    }
}
