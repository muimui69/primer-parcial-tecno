package data;

import config.ConfigDB;
import config.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DSaleDetails {
    private final DatabaseConnection db;
    ConfigDB configDB = new ConfigDB();

    public DSaleDetails() {
        this.db = new DatabaseConnection(
                configDB.getUser(),
                configDB.getPassword(),
                configDB.getHost(),
                configDB.getPort(),
                configDB.getDbName()
        );
    }

    public String save(int saleId, int productId, int quantity, double price) throws SQLException {
        String query = "INSERT INTO sale_detail (sale_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ps.setInt(1, saleId);
        ps.setInt(2, productId);
        ps.setInt(3, quantity);
        ps.setDouble(4, price);
        if (ps.executeUpdate() == 0) {
            System.err.println("class DSaleDetails.java dice:" + "El detalle de venta no se pudo insertar");
            throw new SQLException();
        }
        return "El detalle de venta se insertó con éxito";
    }

    public String update(int saleId, int productId, int quantity, double price) throws SQLException {
        String query = "UPDATE sale_detail SET quantity=?, price=? WHERE sale_id=? AND product_id=?";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ps.setInt(1, quantity);
        ps.setDouble(2, price);
        ps.setInt(3, saleId);
        ps.setInt(4, productId);
        if (ps.executeUpdate() == 0) {
            System.err.println("class DSaleDetails.java dice:" + "El detalle de venta no se pudo actualizar");
            throw new SQLException();
        }
        return "El detalle de venta se actualizó con éxito";
    }

    public String delete(int saleId, int productId) throws SQLException {
        String query = "DELETE FROM sale_detail WHERE sale_id=? AND product_id=?";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ps.setInt(1, saleId);
        ps.setInt(2, productId);
        if (ps.executeUpdate() == 0) {
            System.err.println("class DSaleDetails.java dice:" + "El detalle de venta no se pudo eliminar");
            throw new SQLException();
        }
        return "El detalle de venta se eliminó con éxito";
    }

    public List<String[]> findAll() throws SQLException {
        List<String[]> saleDetails = new ArrayList<>();
        String query = "SELECT * FROM sale_detail";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            saleDetails.add(new String[] {
                    String.valueOf(rs.getInt("sale_id")),
                    String.valueOf(rs.getInt("product_id")),
                    String.valueOf(rs.getInt("quantity")),
                    String.valueOf(rs.getDouble("price"))
            });
        }
        return saleDetails;
    }

    public String[] findOne(int saleId, int productId) throws SQLException {
        String[] saleDetail = null;
        String query = "SELECT * FROM sale_detail WHERE sale_id=? AND product_id=?";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ps.setInt(1, saleId);
        ps.setInt(2, productId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            saleDetail = new String[] {
                    String.valueOf(rs.getInt("sale_id")),
                    String.valueOf(rs.getInt("product_id")),
                    String.valueOf(rs.getInt("quantity")),
                    String.valueOf(rs.getDouble("price"))
            };
        }
        return saleDetail;
    }

    public void closeConnection() {
        if (db != null) db.closeConnection();
    }
}
