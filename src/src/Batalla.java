import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Batalla {
    private ArrayList<Combatiente> jugadores;
    private ArrayList<Combatiente> enemigos;
    private ArrayList<String> registroAcciones;
    private boolean batallaActiva;
    private ArrayList<Combatiente> poolEnemigos;

    public Batalla() {
        this.jugadores = new ArrayList<>();
        this.enemigos = new ArrayList<>();
        this.registroAcciones = new ArrayList<>();
        this.batallaActiva = false;

        // Cargar enemigos desde CSV
        this.poolEnemigos = CargadorCSV.cargarCombatientes("combatientes.csv");
    }

    public void iniciarBatalla(Combatiente jugador) {
        jugadores.add(jugador);
        generarEnemigosAleatorios();
        batallaActiva = true;

        // Mensajes de inicio
        agregarAccion(jugador.frase(getTextoInicio(jugador)));
        for (Combatiente e : enemigos) {
            agregarAccion(e.frase(getTextoInicio(e)));
        }
    }

    private void generarEnemigosAleatorios() {
        Random r = new Random();
        int cantidad = r.nextInt(3) + 1; // 1 a 3 enemigos

        ArrayList<Combatiente> enemigosDisponibles = new ArrayList<>();
        for (Combatiente c : poolEnemigos) {
            if (!(c instanceof Guerrero) && !(c instanceof Explorador)) {
                enemigosDisponibles.add(c);
            }
        }

        for (int i = 0; i < cantidad && i < enemigosDisponibles.size(); i++) {
            enemigos.add(enemigosDisponibles.get(i));
        }
    }

    public void procesarAtaque(Combatiente atacante, Combatiente objetivo) {
        int dano = atacante.atacar();
        objetivo.recibirDanio(dano);

        String accion = atacante.getNombre() + " ataca a " + objetivo.getNombre() + " causando " + dano + " de daño";
        agregarAccion(accion);

        if (!objetivo.estaVivo()) {
            agregarAccion(objetivo.getNombre() + " ha sido derrotado: " + getTextoMuerte(objetivo));
        }
    }

    public void limpiarMuertos() {
        jugadores.removeIf(c -> !c.estaVivo());
        enemigos.removeIf(c -> !c.estaVivo());
    }

    public boolean verificarFinBatalla() {
        limpiarMuertos();

        if (jugadores.isEmpty() || enemigos.isEmpty()) {
            batallaActiva = false;
            return true;
        }
        return false;
    }

    public Combatiente seleccionarObjetivo(ArrayList<Combatiente> objetivos, Scanner sc, Vista vista) {
        vista.mostrarSeleccionObjetivo(objetivos);

        int opcion = 0;
        while (opcion < 1 || opcion > objetivos.size()) {
            try {
                opcion = sc.nextInt();
            } catch (Exception e) {
                sc.nextLine();
            }
        }

        return objetivos.get(opcion - 1);
    }

    public void agregarAccion(String accion) {
        registroAcciones.add(accion);
    }

    public ArrayList<Combatiente> getJugadores() {
        return jugadores;
    }

    public ArrayList<Combatiente> getEnemigos() {
        return enemigos;
    }

    public ArrayList<String> getRegistroAcciones() {
        return registroAcciones;
    }

    public boolean isBatallaActiva() {
        return batallaActiva;
    }

    // Métodos auxiliares para obtener textos
    private String getTextoInicio(Combatiente c) {
        if (c instanceof Guerrero) {
            return ((Guerrero) c).decirFraseInicio();
        } else if (c instanceof Explorador) {
            return ((Explorador) c).decirFraseInicio();
        } else if (c instanceof Orco) {
            return ((Orco) c).decirFraseInicio();
        } else if (c instanceof Esqueleto) {
            return ((Esqueleto) c).decirFraseInicio();
        }
        return "";
    }

    private String getTextoMuerte(Combatiente c) {
        if (c instanceof Guerrero) {
            return ((Guerrero) c).decirFraseMuerte();
        } else if (c instanceof Explorador) {
            return ((Explorador) c).decirFraseMuerte();
        } else if (c instanceof Orco) {
            return ((Orco) c).decirFraseMuerte();
        } else if (c instanceof Esqueleto) {
            return ((Esqueleto) c).decirFraseMuerte();
        }
        return "";
    }
}
