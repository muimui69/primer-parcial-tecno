package data;

import config.ConfigDB;
import config.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DInventory {
    private DatabaseConnection db;
    ConfigDB configDB = new ConfigDB();

    public DInventory() {
        this.db = new DatabaseConnection(
                configDB.getUser(),
                configDB.getPassword(),
                configDB.getHost(),
                configDB.getPort(),
                configDB.getDbName()
        );
    }

    public String save(int productId, String movementType, int quantity, String date) throws SQLException {
        String query = "INSERT INTO inventory (product_id, movement_type, quantity, date) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ps.setInt(1, productId);
        ps.setString(2, movementType);
        ps.setInt(3, quantity);
        ps.setString(4, date);
        if (ps.executeUpdate() == 0) {
            System.err.println("class DInventory.java dice:" + "El movimiento de inventario no se pudo insertar");
            throw new SQLException();
        }
        return "El movimiento de inventario se insertó con éxito";
    }

    public List<String[]> findAll() throws SQLException {
        List<String[]> inventoryList = new ArrayList<>();
        String query = "SELECT * FROM inventory";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            inventoryList.add(new String[] {
                    String.valueOf(rs.getInt("id")),
                    String.valueOf(rs.getInt("product_id")),
                    rs.getString("movement_type"),
                    String.valueOf(rs.getInt("quantity")),
                    rs.getString("date")
            });
        }
        return inventoryList;
    }

    public void closeConnection() {
        db.closeConnection();
    }
}
