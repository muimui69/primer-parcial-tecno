package data;

import config.ConfigDB;
import config.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DUser {
    private final DatabaseConnection db;
    ConfigDB configDB = new ConfigDB();

    public DUser() {
        this.db = new DatabaseConnection(
                configDB.getUser(),
                configDB.getPassword(),
                configDB.getHost(),
                configDB.getPort(),
                configDB.getDbName()
        );
    }

    public String save(String name, String email, String password, String role) throws SQLException {
        String query = "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ps.setString(1, name);
        ps.setString(2, email);
        ps.setString(3, password);
        ps.setString(4, role);
        if (ps.executeUpdate() == 0) {
            System.err.println("class DUser.java dice:" + "El usuario no se pudo insertar");
            throw new SQLException();
        }
        return "El usuario se insertó con éxito";
    }

    public String update(int id, String name, String email, String password, String role) throws SQLException {
        String query = "UPDATE users SET name=?, email=?, password=?, role=? WHERE id=?";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ps.setString(1, name);
        ps.setString(2, email);
        ps.setString(3, password);
        ps.setString(4, role);
        ps.setInt(5, id);
        if (ps.executeUpdate() == 0) {
            System.err.println("class DUser.java dice:" + "El usuario no se pudo modificar");
            throw new SQLException();
        }
        return "El usuario se modificó con éxito";
    }

    public String delete(int id) throws SQLException {
        String query = "DELETE FROM users WHERE id=?";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ps.setInt(1, id);
        if (ps.executeUpdate() == 0) {
            System.err.println("class DUser.java dice:" + "El usuario no se pudo eliminar");
            throw new SQLException();
        }
        return "El usuario se eliminó con éxito";
    }

    public List<String[]> findAll() throws SQLException {
        List<String[]> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            users.add(new String[] {
                    String.valueOf(rs.getInt("id")),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("role")
            });
        }
        return users;
    }

    public String[] findOne(int id) throws SQLException {
        String[] user = null;
        String query = "SELECT * FROM users WHERE id=?";
        PreparedStatement ps = db.openConnection().prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            user = new String[] {
                    String.valueOf(rs.getInt("id")),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("role")
            };
        }
        return user;
    }

    public void closeConnection() {
        if (db != null) db.closeConnection();
    }
}
