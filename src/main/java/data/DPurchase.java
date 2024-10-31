package data;

import config.ConfigDB;
import config.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DPurchase {
    private final DatabaseConnection db;
    ConfigDB configDB = new ConfigDB();

    public DPurchase() {
        this.db = new DatabaseConnection(
                configDB.getUser(),
                configDB.getPassword(),
                configDB.getHost(),
                configDB.getPort(),
                configDB.getDbName()
        );
    }

    public String save(int userId, double total, String purchaseDate) throws SQLException {
        String query = "INSERT INTO purchases (user_id, total, purchase_date) VALUES (?, ?, ?)";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ps.setInt(1, userId);
        ps.setDouble(2, total);
        ps.setString(3, purchaseDate);
        if (ps.executeUpdate() == 0) {
            System.err.println("class DPurchase.java dice:" + "La compra no se pudo insertar");
            throw new SQLException();
        }
        return "La compra se insertó con éxito";
    }

    public String update(int id, int userId, double total, String purchaseDate) throws SQLException {
        String query = "UPDATE purchases SET user_id=?, total=?, purchase_date=? WHERE id=?";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ps.setInt(1, userId);
        ps.setDouble(2, total);
        ps.setString(3, purchaseDate);
        ps.setInt(4, id);
        if (ps.executeUpdate() == 0) {
            System.err.println("class DPurchase.java dice:" + "La compra no se pudo modificar");
            throw new SQLException();
        }
        return "La compra se modificó con éxito";
    }

    public String delete(int id) throws SQLException {
        String query = "DELETE FROM purchases WHERE id=?";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ps.setInt(1, id);
        if (ps.executeUpdate() == 0) {
            System.err.println("class DPurchase.java dice:" + "La compra no se pudo eliminar");
            throw new SQLException();
        }
        return "La compra se eliminó con éxito";
    }

    public List<String[]> findAll() throws SQLException {
        List<String[]> purchases = new ArrayList<>();
        String query = "SELECT * FROM purchases";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            purchases.add(new String[] {
                    String.valueOf(rs.getInt("id")),
                    String.valueOf(rs.getInt("user_id")),
                    String.valueOf(rs.getDouble("total")),
                    rs.getString("purchase_date")
            });
        }
        return purchases;
    }

    public String[] findOne(int id) throws SQLException {
        String[] purchase = null;
        String query = "SELECT * FROM purchases WHERE id=?";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            purchase = new String[] {
                    String.valueOf(rs.getInt("id")),
                    String.valueOf(rs.getInt("user_id")),
                    String.valueOf(rs.getDouble("total")),
                    rs.getString("purchase_date")
            };
        }
        return purchase;
    }

    public void closeConnection() {
        if (db != null) db.closeConnection();
    }
}
