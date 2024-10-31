package data;

import config.ConfigDB;
import config.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DPromotion {
    private final DatabaseConnection db;
    ConfigDB configDB = new ConfigDB();

    public DPromotion() {
        this.db = new DatabaseConnection(
                configDB.getUser(),
                configDB.getPassword(),
                configDB.getHost(),
                configDB.getPort(),
                configDB.getDbName()
        );
    }

    public String save(String name, double discountPercentage, String startDate, String endDate) throws SQLException {
        String query = "INSERT INTO promotion (name, discount_percentage, start_date, end_date) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ps.setString(1, name);
        ps.setDouble(2, discountPercentage);
        ps.setString(3, startDate);
        ps.setString(4, endDate);
        if (ps.executeUpdate() == 0) {
            System.err.println("class DPromotion.java dice:" + "La promoción no se pudo insertar");
            throw new SQLException();
        }
        return "La promoción se insertó con éxito";
    }

    public String update(int id, String name, double discountPercentage, String startDate, String endDate) throws SQLException {
        String query = "UPDATE promotion SET name=?, discount_percentage=?, start_date=?, end_date=? WHERE id=?";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ps.setString(1, name);
        ps.setDouble(2, discountPercentage);
        ps.setString(3, startDate);
        ps.setString(4, endDate);
        ps.setInt(5, id);
        if (ps.executeUpdate() == 0) {
            System.err.println("class DPromotion.java dice:" + "La promoción no se pudo actualizar");
            throw new SQLException();
        }
        return "La promoción se actualizó con éxito";
    }

    public String delete(int id) throws SQLException {
        String query = "DELETE FROM promotion WHERE id=?";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ps.setInt(1, id);
        if (ps.executeUpdate() == 0) {
            System.err.println("class DPromotion.java dice:" + "La promoción no se pudo eliminar");
            throw new SQLException();
        }
        return "La promoción se eliminó con éxito";
    }

    public List<String[]> findAll() throws SQLException {
        List<String[]> promotion = new ArrayList<>();
        String query = "SELECT * FROM promotion";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            promotion.add(new String[] {
                    String.valueOf(rs.getInt("id")),
                    rs.getString("name"),
                    String.valueOf(rs.getDouble("discount_percentage")),
                    rs.getString("start_date"),
                    rs.getString("end_date")
            });
        }
        return promotion;
    }

    public String[] findOne(int id) throws SQLException {
        String[] promotion = null;
        String query = "SELECT * FROM promotion WHERE id=?";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            promotion = new String[] {
                    String.valueOf(rs.getInt("id")),
                    rs.getString("name"),
                    String.valueOf(rs.getDouble("discount_percentage")),
                    rs.getString("start_date"),
                    rs.getString("end_date")
            };
        }
        return promotion;
    }

    public void closeConnection() {
        if (db != null) db.closeConnection();
    }
}
