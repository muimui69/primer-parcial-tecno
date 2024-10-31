package data;

import config.ConfigDB;
import config.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DSale {
    private DatabaseConnection db;
    ConfigDB configDB = new ConfigDB();

    public DSale() {
        this.db = new DatabaseConnection(
                configDB.getUser(),
                configDB.getPassword(),
                configDB.getHost(),
                configDB.getPort(),
                configDB.getDbName()
        );
    }

    public String save(int userId, double total, String saleDate) throws SQLException {
        String query = "INSERT INTO sales (user_id, total, sale_date) VALUES (?, ?, ?)";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ps.setInt(1, userId);
        ps.setDouble(2, total);
        ps.setString(3, saleDate);
        if (ps.executeUpdate() == 0) {
            System.err.println("class DSale.java dice:" + "La venta no se pudo insertar");
            throw new SQLException();
        }
        return "La venta se insertó con éxito";
    }

    public List<String[]> findAll() throws SQLException {
        List<String[]> sales = new ArrayList<>();
        String query = "SELECT * FROM sales";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            sales.add(new String[] {
                    String.valueOf(rs.getInt("id")),
                    String.valueOf(rs.getInt("user_id")),
                    String.valueOf(rs.getDouble("total")),
                    rs.getString("sale_date")
            });
        }
        return sales;
    }

    public void closeConnection() {
        db.closeConnection();
    }
}
