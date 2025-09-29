import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class CargadorCSV {

    // Cargar items desde CSV
    public static ArrayList<Item> cargarItems(String archivo) {
        ArrayList<Item> items = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;

            // Saltar la primera línea (encabezados)
            br.readLine();

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                Item item = crearItem(datos);
                if (item != null) {
                    items.add(item);
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error al cargar items: " + e.getMessage());
        }

        return items;
    }

    // Crear item desde datos del CSV
    private static Item crearItem(String[] datos) {
        try {
            String nombre = datos[0].trim();
            String tipo = datos[1].trim();
            int efecto = Integer.parseInt(datos[2].trim());
            String descripcion = datos[3].trim();

            return new Item(nombre, tipo, descripcion, efecto);
        } catch (Exception e) {
            return null;
        }
    }

    // Cargar combatientes desde CSV
    public static ArrayList<Combatiente> cargarCombatientes(String archivo) {
        ArrayList<Combatiente> combatientes = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;

            // Saltar encabezados
            br.readLine();

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                Combatiente combatiente = crearCombatiente(datos);
                if (combatiente != null) {
                    combatientes.add(combatiente);
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error al cargar combatientes: " + e.getMessage());
        }

        return combatientes;
    }

    // Crear combatiente desde datos del CSV
    private static Combatiente crearCombatiente(String[] datos) {
        try {
            String tipo = datos[0].trim();
            String nombre = datos[1].trim();
            int vida = Integer.parseInt(datos[2].trim());
            int ataque = Integer.parseInt(datos[3].trim());
            boolean esJefe = Boolean.parseBoolean(datos[4].trim());

            return switch (tipo) {
                case "Guerrero" -> new Guerrero(nombre, vida, ataque);
                case "Explorador" -> new Explorador(nombre, vida, ataque);
                case "Orco" -> new Orco(nombre, vida, ataque, esJefe);
                case "Esqueleto" -> new Esqueleto(nombre, vida, ataque, esJefe);
                default -> null;
            };
        } catch (Exception e) {
            return null;
        }
    }
}
