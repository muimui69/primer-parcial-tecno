package command;

import business.BPayment;
import java.util.List;

public class HandlePayment {

    public static String save(String params) {
        String[] partsOfParams = params.split(",\\s*");

        if (partsOfParams.length == 3) {
            try {
                int saleId = Integer.parseInt(partsOfParams[0]);
                double amount = Double.parseDouble(partsOfParams[1]);
                String method = partsOfParams[2];

                BPayment payment = new BPayment();
                return payment.save(saleId, amount, method);
            } catch (NumberFormatException e) {
                return "Error: El ID de la venta y el monto deben ser valores numéricos válidos. " + e.getMessage();
            } catch (Exception e) {
                return "Error al guardar el pago: " + e.getMessage();
            }
        } else {
            return "Error en HandlePayment: Número de parámetros incorrecto para save. Esperados: 4, Recibidos: " + partsOfParams.length;
        }
    }

    public static String update(String params) {
        String[] partsOfParams = params.split(",\\s*");

        if (partsOfParams.length == 4) {
            try {
                int id = Integer.parseInt(partsOfParams[0]);
                int saleId = Integer.parseInt(partsOfParams[1]);
                double amount = Double.parseDouble(partsOfParams[2]);
                String method = partsOfParams[3];

                BPayment payment = new BPayment();
                return payment.update(id, saleId, amount, method);
            } catch (NumberFormatException e) {
                return "Error: El ID, el ID de venta y el monto deben ser valores numéricos válidos. " + e.getMessage();
            } catch (Exception e) {
                return "Error al actualizar el pago: " + e.getMessage();
            }
        } else {
            return "Error en HandlePayment: Número de parámetros incorrecto para update. Esperados: 5, Recibidos: " + partsOfParams.length;
        }
    }

    public static String delete(String idStr) {
        try {
            int paymentId = Integer.parseInt(idStr.trim());
            BPayment payment = new BPayment();
            return payment.delete(paymentId);
        } catch (NumberFormatException e) {
            return "Error en HandlePayment: El ID debe ser numérico. " + e.getMessage();
        }
    }

    public static String findOne(String idStr) {
        try {
            int paymentId = Integer.parseInt(idStr.trim());
            BPayment payment = new BPayment();
            String[] paymentDetails = payment.findOne(paymentId);
            if (paymentDetails == null || paymentDetails.length == 0) {
                return "No se encontró el pago con ID: " + idStr;
            }
            return String.join(", ", paymentDetails);
        } catch (NumberFormatException e) {
            return "Error: El ID debe ser numérico. " + e.getMessage();
        }
    }

    public static String findAll() {
        BPayment payment = new BPayment();
        try {
            List<String[]> payments = payment.findAll();
            if (payments.isEmpty()) {
                return "No hay pagos registrados.";
            }
            StringBuilder sb = new StringBuilder();
            for (String[] paymentInfo : payments) {
                sb.append("Pago: ").append(String.join(", ", paymentInfo)).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            return "Error al recuperar los pagos: " + e.getMessage();
        }
    }
}
