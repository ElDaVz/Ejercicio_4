import java.util.ArrayList;

public class Explorador extends Combatiente{
    private ArrayList<Item> inventario;

    Explorador(String nombre, int vidaMaxima, int ataqueBase) {
        super(nombre, vidaMaxima, ataqueBase);
        this.inventario = new ArrayList<>();
    }

    public String decirFraseInicio() {
        return frase("¡Es hora de la aventura!");
    }

    public String decirFraseMuerte() {
        return frase("¡La aventura llega hasta aquí! :(");
    }

    public ArrayList<Item> getInventario() {
        return inventario;
    }

    public Item usarItem(int opcion) {
        int indice = opcion - 1;

        if (inventario.isEmpty() || indice < 0 || indice >= inventario.size() ) {
            return null;
        }

        Item i = inventario.get(indice);
        inventario.remove(indice);
        return i;
    }


}
