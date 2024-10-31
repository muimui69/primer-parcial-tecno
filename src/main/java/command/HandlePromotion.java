package command;

import business.BPromotion;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class HandlePromotion {

    public static String save(String params) {
        String[] partsOfParams = params.split(",\\s*");

        if (partsOfParams.length == 4) {
            try {
                String name = partsOfParams[0];
                double discountPercentage = Double.parseDouble(partsOfParams[1]);
                LocalDate startDate = LocalDate.parse(partsOfParams[2]);
                LocalDate endDate = LocalDate.parse(partsOfParams[3]);

                BPromotion promotion = new BPromotion();
                return promotion.save(name, discountPercentage, Date.valueOf(startDate), Date.valueOf(endDate));
            } catch (NumberFormatException e) {
                return "Error: El porcentaje de descuento debe ser un valor numérico válido. " + e.getMessage();
            } catch (Exception e) {
                return "Error al guardar la promoción: " + e.getMessage();
            }
        } else {
            return "Error en HandlePromotion: Número de parámetros incorrecto para save. Esperados: 4, Recibidos: " + partsOfParams.length;
        }
    }

    public static String update(String params) {
        String[] partsOfParams = params.split(",\\s*");

        if (partsOfParams.length == 5) {
            try {
                int id = Integer.parseInt(partsOfParams[0]);
                String name = partsOfParams[1];
                double discountPercentage = Double.parseDouble(partsOfParams[2]);
                LocalDate startDate = LocalDate.parse(partsOfParams[2]);
                LocalDate endDate = LocalDate.parse(partsOfParams[3]);

                BPromotion promotion = new BPromotion();
                return promotion.update(id, name, discountPercentage, Date.valueOf(startDate), Date.valueOf(endDate));
            } catch (NumberFormatException e) {
                return "Error: El ID y el porcentaje de descuento deben ser valores numéricos válidos. " + e.getMessage();
            } catch (Exception e) {
                return "Error al actualizar la promoción: " + e.getMessage();
            }
        } else {
            return "Error en HandlePromotion: Número de parámetros incorrecto para update. Esperados: 5, Recibidos: " + partsOfParams.length;
        }
    }

    public static String delete(String idStr) {
        try {
            int promotionId = Integer.parseInt(idStr.trim());
            BPromotion promotion = new BPromotion();
            return promotion.delete(promotionId);
        } catch (NumberFormatException e) {
            return "Error en HandlePromotion: El ID debe ser numérico. " + e.getMessage();
        }
    }

    public static String findOne(String idStr) {
        try {
            int promotionId = Integer.parseInt(idStr.trim());
            BPromotion promotion = new BPromotion();
            String[] promotionDetails = promotion.findOne(promotionId);
            if (promotionDetails == null || promotionDetails.length == 0) {
                return "No se encontró la promoción con ID: " + idStr;
            }
            return String.join(", ", promotionDetails);
        } catch (NumberFormatException e) {
            return "Error: El ID debe ser numérico. " + e.getMessage();
        }
    }

    public static String findAll() {
        BPromotion promotion = new BPromotion();
        try {
            List<String[]> promotions = promotion.findAll();
            if (promotions.isEmpty()) {
                return "No hay promociones registradas.";
            }
            StringBuilder sb = new StringBuilder();
            for (String[] promotionInfo : promotions) {
                sb.append("Promoción: ").append(String.join(", ", promotionInfo)).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            return "Error al recuperar las promociones: " + e.getMessage();
        }
    }
}
