package data;

import config.ConfigDB;
import config.DatabaseConnection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DInventory {
    private final DatabaseConnection db;
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

    public String save(int productId, String movementType, int quantity) throws SQLException {
        String query = "INSERT INTO inventory (product_id, movement_type, quantity, date) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        LocalDate now = LocalDate.now();
        ps.setInt(1, productId);
        ps.setString(2, movementType);
        ps.setInt(3, quantity);
        ps.setDate(4, Date.valueOf(now));
        if (ps.executeUpdate() == 0) {
            System.err.println("class DInventory.java dice:" + "El movimiento de inventario no se pudo insertar");
            throw new SQLException();
        }
        return "El movimiento de inventario se insertó con éxito";
    }

    public String update(int id, int productId, String movementType, int quantity) throws SQLException {
        String query = "UPDATE inventory SET product_id=?, movement_type=?, quantity=?, date=? WHERE id=?";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        LocalDate now = LocalDate.now();
        ps.setInt(1, productId);
        ps.setString(2, movementType);
        ps.setInt(3, quantity);
        ps.setDate(4, Date.valueOf(now));
        ps.setInt(5, id);
        if (ps.executeUpdate() == 0) {
            System.err.println("class DInventory.java dice:" + "El movimiento de inventario no se pudo actualizar");
            throw new SQLException();
        }
        return "El movimiento de inventario se actualizó con éxito";
    }

    public String delete(int id) throws SQLException {
        String query = "DELETE FROM inventory WHERE id=?";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ps.setInt(1, id);
        if (ps.executeUpdate() == 0) {
            System.err.println("class DInventory.java dice:" + "El movimiento de inventario no se pudo eliminar");
            throw new SQLException();
        }
        return "El movimiento de inventario se eliminó con éxito";
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

    public String[] findOne(int id) throws SQLException {
        String[] inventoryItem = null;
        String query = "SELECT * FROM inventory WHERE id=?";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            inventoryItem = new String[] {
                    String.valueOf(rs.getInt("id")),
                    String.valueOf(rs.getInt("product_id")),
                    rs.getString("movement_type"),
                    String.valueOf(rs.getInt("quantity")),
                    rs.getString("date")
            };
        }
        return inventoryItem;
    }

    public void closeConnection() {
        if (db != null) db.closeConnection();
    }
}
