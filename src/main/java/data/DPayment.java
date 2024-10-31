package data;

import config.ConfigDB;
import config.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DPayment {
    private final DatabaseConnection db;
    ConfigDB configDB = new ConfigDB();

    public DPayment() {
        this.db = new DatabaseConnection(
                configDB.getUser(),
                configDB.getPassword(),
                configDB.getHost(),
                configDB.getPort(),
                configDB.getDbName()
        );
    }

    public String save(int saleId, String paymentDate, double amount, String method) throws SQLException {
        String query = "INSERT INTO payments (sale_id, payment_date, amount, method) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ps.setInt(1, saleId);
        ps.setString(2, paymentDate);
        ps.setDouble(3, amount);
        ps.setString(4, method);
        if (ps.executeUpdate() == 0) {
            System.err.println("class DPayment.java dice:" + "El pago no se pudo insertar");
            throw new SQLException();
        }
        return "El pago se insertó con éxito";
    }

    public String update(int id, int saleId, String paymentDate, double amount, String method) throws SQLException {
        String query = "UPDATE payments SET sale_id=?, payment_date=?, amount=?, method=? WHERE id=?";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ps.setInt(1, saleId);
        ps.setString(2, paymentDate);
        ps.setDouble(3, amount);
        ps.setString(4, method);
        ps.setInt(5, id);
        if (ps.executeUpdate() == 0) {
            System.err.println("class DPayment.java dice:" + "El pago no se pudo actualizar");
            throw new SQLException();
        }
        return "El pago se actualizó con éxito";
    }

    public String delete(int id) throws SQLException {
        String query = "DELETE FROM payments WHERE id=?";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ps.setInt(1, id);
        if (ps.executeUpdate() == 0) {
            System.err.println("class DPayment.java dice:" + "El pago no se pudo eliminar");
            throw new SQLException();
        }
        return "El pago se eliminó con éxito";
    }

    public List<String[]> findAll() throws SQLException {
        List<String[]> payments = new ArrayList<>();
        String query = "SELECT * FROM payments";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            payments.add(new String[] {
                    String.valueOf(rs.getInt("id")),
                    String.valueOf(rs.getInt("sale_id")),
                    rs.getString("payment_date"),
                    String.valueOf(rs.getDouble("amount")),
                    rs.getString("method")
            });
        }
        return payments;
    }

    public String[] findOne(int id) throws SQLException {
        String[] payment = null;
        String query = "SELECT * FROM payments WHERE id=?";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            payment = new String[] {
                    String.valueOf(rs.getInt("id")),
                    String.valueOf(rs.getInt("sale_id")),
                    rs.getString("payment_date"),
                    String.valueOf(rs.getDouble("amount")),
                    rs.getString("method")
            };
        }
        return payment;
    }

    public void closeConnection() {
        if (db != null) db.closeConnection();
    }
}
