package business;

import data.DPayment;
import java.sql.SQLException;
import java.util.List;

public class BPayment {
    private final DPayment dPayment;

    public BPayment() {
        this.dPayment = new DPayment();
    }

    public String save(int saleId, String paymentDate, double amount, String method) {
        try {
            return dPayment.save(saleId, paymentDate, amount, method);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El pago no se pudo guardar: " + e.getMessage();
        }
    }

    public String update(int id, int saleId, String paymentDate, double amount, String method) {
        try {
            return dPayment.update(id, saleId, paymentDate, amount, method);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El pago no se pudo actualizar: " + e.getMessage();
        }
    }

    public String delete(int id) {
        try {
            return dPayment.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return "El pago no se pudo eliminar: " + e.getMessage();
        }
    }

    public List<String[]> findAll() {
        try {
            return dPayment.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String[] findOne(int id) {
        try {
            return dPayment.findOne(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
