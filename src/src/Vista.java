import java.util.ArrayList;

public class Vista {

    public void mostrarTitulo() {
        System.out.println("\n=====================================");
        System.out.println("    SIMULADOR DE BATALLA RPG");
        System.out.println("=====================================\n");
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarEstadoCombatientes(ArrayList<Combatiente> jugadores, ArrayList<Combatiente> enemigos) {
        System.out.println("\n--- Estado de la Batalla ---");

        System.out.println("\nJugadores:");
        for (Combatiente c : jugadores) {
            if (c.estaVivo()) {
                System.out.println("  " + c.getNombre() + " - HP: " + c.getVidaActual() + "/" + c.getVidaMaxima());
            }
        }

        System.out.println("\nEnemigos:");
        for (Combatiente c : enemigos) {
            if (c.estaVivo()) {
                System.out.println("  " + c.getNombre() + " - HP: " + c.getVidaActual() + "/" + c.getVidaMaxima());
            }
        }
        System.out.println();
    }

    public void mostrarRegistroAcciones(ArrayList<String> registro) {
        System.out.println("\n--- Últimas 3 acciones ---");

        int inicio = Math.max(0, registro.size() - 3);
        for (int i = inicio; i < registro.size(); i++) {
            System.out.println("  " + registro.get(i));
        }
        System.out.println();
    }

    public void mostrarMenuSeleccionRol() {
        System.out.println("\n=== Selecciona tu rol ===");
        System.out.println("1. Guerrero (Alta vida, alto ataque, pocos items)");
        System.out.println("2. Explorador (Vida normal, ataque normal, muchos items)");
        System.out.print("Selecciona una opción: ");
    }

    public void mostrarResultadoBatalla(boolean jugadorGano) {
        System.out.println("\n=====================================");
        if (jugadorGano) {
            System.out.println("    ¡VICTORIA!");
            System.out.println("  Has derrotado a tus enemigos");
        } else {
            System.out.println("    DERROTA");
            System.out.println("  Has sido derrotado en batalla");
        }
        System.out.println("=====================================\n");
    }

    public void mostrarSeleccionObjetivo(ArrayList<Combatiente> objetivos) {
        System.out.println("\nSelecciona un objetivo:");
        for (int i = 0; i < objetivos.size(); i++) {
            Combatiente c = objetivos.get(i);
            System.out.println((i + 1) + ". " + c.getNombre() + " (HP: " + c.getVidaActual() + "/" + c.getVidaMaxima() + ")");
        }
        System.out.print("Objetivo: ");
    }

    public void mostrarInventario(ArrayList<Item> inventario) {
        System.out.println("\n--- Inventario ---");
        if (inventario.isEmpty()) {
            System.out.println("  Inventario vacío");
        } else {
            for (int i = 0; i < inventario.size(); i++) {
                Item item = inventario.get(i);
                System.out.println((i + 1) + ". " + item.getNombre() + " - " + item.getDescripcion());
            }
        }
        System.out.print("Selecciona un item: ");
    }

    public void limpiarPantalla() {
        // Simplificado para prototipo
        System.out.println("\n\n");
    }
}
