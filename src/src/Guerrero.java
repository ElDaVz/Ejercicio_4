import java.util.ArrayList;
public class Guerrero extends Combatiente {

    private ArrayList<Item> inventario;

        Guerrero(String nombre, int vidaMaxima, int ataqueBase) {
            super(nombre, vidaMaxima, ataqueBase);
            this.inventario = new ArrayList<>();
        }

        public String decirFraseInicio() {
            return frase("¡Por la gloria y el honor!");
        }

        public String decirFraseMuerte() {
            return frase("He caído en batalla...");
        }

        public ArrayList<Item> getInventario() {
            return inventario;
        }

        public Item usarItem(int opcion) {
            int indice = opcion - 1;

            if (inventario.isEmpty() || indice < 0 || indice >= inventario.size()) {
                return null;
            }

            Item i = inventario.get(indice);
            inventario.remove(indice);
            return i;
        }
    }

