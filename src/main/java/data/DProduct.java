package data;

import config.ConfigDB;
import config.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DProduct {

    private DatabaseConnection db;
    ConfigDB ConfigDb = new ConfigDB();

    public DProduct() {
        this.db = new DatabaseConnection(
                ConfigDb.getUser(),
                ConfigDb.getPassword(),
                ConfigDb.getHost(),
                ConfigDb.getPort(),
                ConfigDb.getDbName()
        );
    }

    public String save(String name, double price, String description, int stock) throws SQLException {
        String query = "INSERT INTO products(name, price, description, stock) values(?, ?, ?, ?)";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ps.setString(1, name);
        ps.setDouble(2, price);
        ps.setString(3, description);
        ps.setInt(4, stock);
        if (ps.executeUpdate() == 0) {
            System.err.println("class DProduct.java dice:" + "El producto no se pudo insertar");
            throw new SQLException();
        }
        return "El producto se insertó con éxito";
    }

    public String update(int id, String name, double price, String description, int stock) throws SQLException {
        String query = "UPDATE products SET name=?, price=?, description=?, stock=? WHERE id=?";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ps.setString(1, name);
        ps.setDouble(2, price);
        ps.setString(3, description);
        ps.setInt(4, stock);
        ps.setInt(5, id);
        if (ps.executeUpdate() == 0) {
            System.err.println("class DProduct.java dice:" + "El producto no se pudo modificar");
            throw new SQLException();
        }
        return "El producto se modificó con éxito";
    }

    public String delete(int id) throws SQLException {
        String query = "DELETE FROM products WHERE id=?";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ps.setInt(1, id);
        if (ps.executeUpdate() == 0) {
            System.err.println("class DProduct.java dice:" + "El producto no se pudo eliminar");
            throw new SQLException();
        }
        return "El producto se eliminó con éxito";
    }

    public List<String[]> findAll() throws SQLException {
        List<String[]> products = new ArrayList<>();
        String query = "SELECT * FROM products";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ResultSet set = ps.executeQuery();
        while (set.next()) {
            products.add(new String[] {
                    String.valueOf(set.getInt("id")),
                    set.getString("name"),
                    String.valueOf(set.getDouble("price")),
                    set.getString("description"),
                    String.valueOf(set.getInt("stock"))
            });
        }
        return products;
    }

    public String[] findOne(int id) throws SQLException {
        String[] product = null;
        String query = "SELECT * FROM products WHERE id=?";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet set = ps.executeQuery();
        if (set.next()) {
            product = new String[] {
                    String.valueOf(set.getInt("id")),
                    set.getString("name"),
                    String.valueOf(set.getDouble("price")),
                    set.getString("description"),
                    String.valueOf(set.getInt("stock"))
            };
        }
        return product;
    }

    public void closeConnection() {
        db.closeConnection();
    }
}
