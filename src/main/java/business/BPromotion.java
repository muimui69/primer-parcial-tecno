package business;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import data.DPromotion;

public class BPromotion {
    private DPromotion dPromotion;

    public BPromotion() {
        this.dPromotion = new DPromotion();
    }

    public String save(String name, double discountPercentage, Date startDate, Date endDate) {
        try {
            return dPromotion.save(name, discountPercentage, startDate, endDate);
        } catch (SQLException e) {
            e.printStackTrace();
            return "La promoción no se pudo guardar: " + e.getMessage();
        }
    }

    public String update(int id, String name, double discountPercentage, Date startDate, Date endDate) {
        try {
            return dPromotion.update(id, name, discountPercentage, startDate, endDate);
        } catch (SQLException e) {
            e.printStackTrace();
            return "La promoción no se pudo actualizar: " + e.getMessage();
        }
    }

    public String delete(int id) {
        try {
            return dPromotion.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return "La promoción no se pudo eliminar: " + e.getMessage();
        }
    }

    public List<String[]> findAll() {
        try {
            return dPromotion.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String[] findOne(int id) {
        try {
            return dPromotion.findOne(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
