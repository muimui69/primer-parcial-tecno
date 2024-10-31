package command;

import java.util.HashMap;
import java.util.Map;

public class CommandInterpreter {

    private static final Map<String, String[]> COMMANDS = new HashMap<>();

    static {
        COMMANDS.put("user", new String[]{"save", "update", "findOne", "findAll", "delete"});
        COMMANDS.put("product", new String[]{"save", "update", "findOne", "findAll", "delete"});
        COMMANDS.put("purchase", new String[]{"save", "update", "findOne", "findAll", "delete"});
        COMMANDS.put("inventory", new String[]{"save", "update", "findOne", "findAll", "delete"});
        COMMANDS.put("promotion", new String[]{"save", "update", "findOne", "findAll", "delete"});
        COMMANDS.put("sale", new String[]{"save", "update", "findOne", "findAll", "delete"});
        COMMANDS.put("payment", new String[]{"save", "update", "findOne", "findAll", "delete"});
    }

    public static String interpret(String subject) {
        subject = subject.replaceAll("[^a-zA-Z0-9\\s\\(\\),./@]", "");
        subject = subject.replaceAll("\\s+", " ").trim();

        System.out.println("Subject luego de formatear: " + subject);
        if (subject.equals("help")) {
            return getHelpMessage();
        }

        String pattern = "(\\w+)\\s+(\\w+)\\s*\\((.*)\\)";
        java.util.regex.Pattern regex = java.util.regex.Pattern.compile(pattern);
        java.util.regex.Matcher matcher = regex.matcher(subject);

        if (!matcher.matches()) {
            return "Comando no reconocido. Por favor, asegúrate de seguir la estructura: {caso_de_uso} comando (parametros)";
        }

        String useCase = matcher.group(1).trim();
        String command = matcher.group(2).trim();
        String params = matcher.group(3).trim();

        if (!COMMANDS.containsKey(useCase)) {
            return "Caso de uso '" + useCase + "' no reconocido. Usa 'HELP' para ver la lista de casos de uso y comandos.";
        }

        boolean commandExists = false;
        for (String validCommand : COMMANDS.get(useCase)) {
            if (validCommand.equals(command)) {
                commandExists = true;
                break;
            }
        }

        if (!commandExists) {
            return "Comando '" + command + "' no reconocido para el caso de uso '" + useCase + "'. Usa 'HELP' para ver la lista de comandos.";
        }

        // Empezar el switch
        switch (useCase.toLowerCase()) {
            case "user":
                if (command.equals("save")) {
                    return HandleUser.save(params);
                } else if (command.equals("update")) {
                    return HandleUser.update(params);
                } else if (command.equals("delete")) {
                    return HandleUser.delete(params);
                } else if (command.equals("findAll")) {
                    return HandleUser.findAll();
                } else if (command.equals("findOne")) {
                    return HandleUser.findOne(params);
                }
                break;

            case "product":
                if (command.equals("save")) {
                    return HandleProduct.save(params);
                } else if (command.equals("update")) {
                    return HandleProduct.update(params);
                } else if (command.equals("delete")) {
                    return HandleProduct.delete(params);
                } else if (command.equals("findAll")) {
                    return HandleProduct.findAll();
                } else if (command.equals("findOne")) {
                    return HandleProduct.findOne(params);
                }
                break;

            case "purchase":
                if (command.equals("save")) {
                    return HandlePurchase.save(params);
                } else if (command.equals("update")) {
                    return HandlePurchase.update(params);
                } else if (command.equals("delete")) {
                    return HandlePurchase.delete(params);
                } else if (command.equals("findAll")) {
                    return HandlePurchase.findAll();
                } else if (command.equals("findOne")) {
                    return HandlePurchase.findOne(params);
                }
                break;

            case "inventory":
                if (command.equals("save")) {
                    return HandleInventory.save(params);
                } else if (command.equals("update")) {
                    return HandleInventory.update(params);
                } else if (command.equals("delete")) {
                    return HandleInventory.delete(params);
                } else if (command.equals("findAll")) {
                    return HandleInventory.findAll();
                } else if (command.equals("findOne")) {
                    return HandleInventory.findOne(params);
                }
                break;

            case "promotion":
                if (command.equals("save")) {
                    return HandlePromotion.save(params);
                } else if (command.equals("update")) {
                    return HandlePromotion.update(params);
                } else if (command.equals("delete")) {
                    return HandlePromotion.delete(params);
                } else if (command.equals("findAll")) {
                    return HandlePromotion.findAll();
                } else if (command.equals("findOne")) {
                    return HandlePromotion.findOne(params);
                }
                break;

            case "sale":
                if (command.equals("save")) {
                    return HandleSale.save(params);
                } else if (command.equals("update")) {
                    return HandleSale.update(params);
                } else if (command.equals("delete")) {
                    return HandleSale.delete(params);
                } else if (command.equals("findAll")) {
                    return HandleSale.findAll();
                } else if (command.equals("findOne")) {
                    return HandleSale.findOne(params);
                }
                break;

            case "payment":
                if (command.equals("save")) {
                    return HandlePayment.save(params);
                } else if (command.equals("update")) {
                    return HandlePayment.update(params);
                } else if (command.equals("delete")) {
                    return HandlePayment.delete(params);
                } else if (command.equals("findAll")) {
                    return HandlePayment.findAll();
                } else if (command.equals("findOne")) {
                    return HandlePayment.findOne(params);
                }
                break;

            default:
                return "No se reconoce " + useCase + " como un caso de uso";
        }
        return "Comando ejecutado correctamente.";
    }

    private static String getHelpMessage() {
        String helpMessage = "**************** SISTEMA DE TKM JEANS ****************\r\n" + //
                "\r\n" + //
                "        Usa 'help' para ver la lista de comandos\r\n" + //
                "\r\n" + //
                "        La estructura para enviar comandos es: casoDeUso metodo (parametros)\r\n" + //
                "        Lista de casos de uso y comandos disponibles:\r\n" + //
                "\r\n" + //
                "        CU1: user\r\n" + //
                "        - save (name, email, password, role)\r\n" + //
                "        - update (id, name, email, password, role)\r\n" + //
                "        - findOne (idToFind)\r\n" + //
                "        - findAll (all)\r\n" + //
                "        - delete (idToDelete)\r\n" + //
                "\r\n" + //
                "        CU2: product\r\n" + //
                "        - save (name, price, description, stock)\r\n" + //
                "        - update (id, name, price, description, stock)\r\n" + //
                "        - findOne (idToFind)\r\n" + //
                "        - findAll (all)\r\n" + //
                "        - delete (idToDelete)\r\n" + //
                "\r\n" + //
                "        CU3: purchase\r\n" + //
                "        - save (user_id, total, purchase_date)\r\n" + //
                "        - update (id, user_id, total, purchase_date)\r\n" + //
                "        - findOne (idToFind)\r\n" + //
                "        - findAll (all)\r\n" + //
                "        - delete (idToDelete)\r\n" + //
                "\r\n" + //
                "        CU4: inventory\r\n" + //
                "        - save (product_id, movement_type, quantity, date)\r\n" + //
                "        - update (id, product_id, movement_type, quantity, date)\r\n" + //
                "        - findOne (id)\r\n" + //
                "        - findAll (all)\r\n" + //
                "\r\n" + //
                "        CU5: promotion\r\n" + //
                "        - save (name, discount_percentage, start_date, end_date)\r\n" + //
                "        - update (id, name, discount_percentage, start_date, end_date)\r\n" + //
                "        - findOne (idToFind)\r\n" + //
                "        - findAll (all)\r\n" + //
                "        - delete (idToDelete)\r\n" + //
                "\r\n" + //
                "        CU6: sale\r\n" + //
                "        - save (user_id, total, sale_date)\r\n" + //
                "        - update (id, user_id, total, sale_date)\r\n" + //
                "        - findOne (idToFind)\r\n" + //
                "        - findAll (all)\r\n" + //
                "\r\n" + //
                "        CU7: payment\r\n" + //
                "        - save (sale_id, payment_date, amount, method)\r\n" + //
                "        - update (id, sale_id, payment_date, amount, method)\r\n" + //
                "        - findOne (id)\r\n" + //
                "        - findAll (all)\r\n" + //
                "        ";
        return helpMessage;
    }
}
