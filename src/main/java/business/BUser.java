package business;

import java.sql.SQLException;
import java.util.List;
import data.DUser;

public class BUser {
    private final DUser dUser;

    public BUser() {
        this.dUser = new DUser();
    }

    public String save(String name, String email, String password, String role) {
        try {
            return dUser.save(name, email, password, role);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El usuario no se pudo guardar: " + e.getMessage();
        }
    }

    public String update(int id, String name, String email, String password, String role) {
        try {
            return dUser.update(id, name, email, password, role);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El usuario no se pudo actualizar: " + e.getMessage();
        }
    }

    public String delete(int id) {
        try {
            return dUser.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El usuario no se pudo eliminar: " + e.getMessage();
        }
    }

    public List<String[]> findAll() {
        try {
            return dUser.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String[] findOne(int id) {
        try {
            return dUser.findOne(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
