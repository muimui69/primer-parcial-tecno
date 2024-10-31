package command;

import java.util.List;
import business.BUser;

public class HandleUser {

    public static String save(String params) {
        String[] partsOfParams = params.split(",\\s*");

        if (partsOfParams.length == 4) {
            try {
                String name = partsOfParams[0];
                String email = partsOfParams[1];
                String password = partsOfParams[2];
                String role = partsOfParams[3];

                BUser user = new BUser();
                return user.save(name, email, password, role);
            } catch (Exception e) {
                return "Error al guardar el usuario: " + e.getMessage();
            }
        } else {
            return "Error en HandleUser: Número de parámetros incorrecto para save. Esperados: 4, Recibidos: " + partsOfParams.length;
        }
    }

    public static String update(String params) {
        String[] partsOfParams = params.split(",\\s*");

        if (partsOfParams.length == 5) {
            try {
                int id = Integer.parseInt(partsOfParams[0]);
                String name = partsOfParams[1];
                String email = partsOfParams[2];
                String password = partsOfParams[3];
                String role = partsOfParams[4];

                BUser user = new BUser();
                return user.update(id, name, email, password, role);
            } catch (NumberFormatException e) {
                return "Error: ID debe ser un número válido. " + e.getMessage();
            } catch (Exception e) {
                return "Error al actualizar el usuario: " + e.getMessage();
            }
        } else {
            return "Error en HandleUser: Número de parámetros incorrecto para update. Esperados: 5, Recibidos: " + partsOfParams.length;
        }
    }

    public static String delete(String idStr) {
        try {
            int userId = Integer.parseInt(idStr.trim());
            BUser user = new BUser();
            return user.delete(userId);
        } catch (NumberFormatException e) {
            return "Error en HandleUser: El ID debe ser numérico. " + e.getMessage();
        }
    }

    public static String findOne(String idStr) {
        try {
            int userId = Integer.parseInt(idStr.trim());
            BUser user = new BUser();
            String[] userDetails = user.findOne(userId);
            if (userDetails == null || userDetails.length == 0) {
                return "No se encontró el usuario con ID: " + idStr;
            }
            return String.join(", ", userDetails);
        } catch (NumberFormatException e) {
            return "Error: El ID debe ser numérico. " + e.getMessage();
        }
    }

    public static String findAll() {
        BUser user = new BUser();
        try {
            List<String[]> users = user.findAll();
            if (users.isEmpty()) {
                return "No hay usuarios registrados.";
            }
            StringBuilder sb = new StringBuilder();
            for (String[] userInfo : users) {
                sb.append("Usuario: ").append(String.join(", ", userInfo)).append("\n");
            }
            return sb.toString();
        } catch (Exception e) {
            return "Error al recuperar usuarios: " + e.getMessage();
        }
    }
}
