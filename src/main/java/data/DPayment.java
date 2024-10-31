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

    public void closeConnection() {
        db.closeConnection();
    }
}
