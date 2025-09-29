import java.util.ArrayList;
import java.util.Scanner;

public class ControladorBatalla {
    private Batalla batalla;
    private Vista vista;
    private Scanner input;
    private ArrayList<Item> itemsDisponibles;

    public ControladorBatalla() {
        this.batalla = new Batalla();
        this.vista = new Vista();
        this.input = new Scanner(System.in);
        this.itemsDisponibles = CargadorCSV.cargarItems("items.csv");
    }

    public void iniciarJuego() {
        vista.mostrarTitulo();

        // Crear jugador
        Combatiente jugador = crearJugador();

        // Asignar items al jugador si es Guerrero o Explorador
        asignarItems(jugador);

        // Iniciar batalla
        batalla.iniciarBatalla(jugador);

        // Ejecutar batalla
        ejecutarBatalla();

        // Mostrar resultado
        boolean jugadorGano = !batalla.getJugadores().isEmpty();
        vista.mostrarResultadoBatalla(jugadorGano);

        input.close();
    }

    private Combatiente crearJugador() {
        vista.mostrarMenuSeleccionRol();

        int opcion = 0;
        while (opcion < 1 || opcion > 2) {
            try {
                opcion = input.nextInt();
            } catch (Exception e) {
                input.nextLine();
            }
        }

        input.nextLine(); // Limpiar buffer

        vista.mostrarMensaje("Ingresa tu nombre: ");
        String nombre = input.nextLine();

        if (opcion == 1) {
            return new Guerrero(nombre, 250, 30);
        } else {
            return new Explorador(nombre, 80, 20);
        }
    }

    private void asignarItems(Combatiente jugador) {
        int maxItems = 0;

        if (jugador instanceof Guerrero) {
            maxItems = 3;
        } else if (jugador instanceof Explorador) {
            maxItems = 6;
        }

        // Asignar items aleatorios
        for (int i = 0; i < maxItems && i < itemsDisponibles.size(); i++) {
            Item item = itemsDisponibles.get(i);

            if (jugador instanceof Guerrero) {
                ((Guerrero) jugador).getInventario().add(item);
            } else if (jugador instanceof Explorador) {
                ((Explorador) jugador).getInventario().add(item);
            }
        }
    }

    private void ejecutarBatalla() {
        while (batalla.isBatallaActiva()) {
            // Mostrar estado
            vista.limpiarPantalla();
            vista.mostrarEstadoCombatientes(batalla.getJugadores(), batalla.getEnemigos());
            vista.mostrarRegistroAcciones(batalla.getRegistroAcciones());

            // Turno de jugadores
            procesarTurnoBando(batalla.getJugadores(), batalla.getEnemigos());

            if (batalla.verificarFinBatalla()) {
                break;
            }

            // Turno de enemigos
            procesarTurnoBando(batalla.getEnemigos(), batalla.getJugadores());

            batalla.verificarFinBatalla();
        }
    }

    private void procesarTurnoBando(ArrayList<Combatiente> bando, ArrayList<Combatiente> objetivos) {
        for (Combatiente combatiente : bando) {
            if (!combatiente.estaVivo()) {
                continue;
            }

            vista.mostrarMensaje("\n--- Turno de " + combatiente.getNombre() + " ---");

            // Generar y mostrar menu
            String menuTexto = generarMenu(combatiente);
            vista.mostrarMensaje(menuTexto);

            // Procesar opcion
            Menu menu = obtenerMenu(combatiente);
            int opcion = menu.procesarOpcion(input);

            ejecutarAccion(combatiente, opcion, objetivos);

            if (batalla.verificarFinBatalla()) {
                break;
            }
        }
    }

    private String generarMenu(Combatiente combatiente) {
        Menu menu = obtenerMenu(combatiente);
        return menu.generarMenu();
    }

    private Menu obtenerMenu(Combatiente combatiente) {
        if (combatiente instanceof Guerrero) {
            return new MenuGuerrero();
        } else if (combatiente instanceof Explorador) {
            return new MenuExplorador();
        } else if (combatiente instanceof Orco) {
            Orco orco = (Orco) combatiente;
            return new MenuOrco(orco.getEsJefe());
        } else if (combatiente instanceof Esqueleto) {
            Esqueleto esqueleto = (Esqueleto) combatiente;
            return new MenuEsqueleto(esqueleto.getEsJefe());
        }
        return null;
    }

    private void ejecutarAccion(Combatiente combatiente, int opcion, ArrayList<Combatiente> objetivos) {
        if (combatiente instanceof Guerrero) {
            ejecutarAccionGuerrero((Guerrero) combatiente, opcion, objetivos);
        } else if (combatiente instanceof Explorador) {
            ejecutarAccionExplorador((Explorador) combatiente, opcion, objetivos);
        } else if (combatiente instanceof Orco) {
            ejecutarAccionOrco((Orco) combatiente, opcion, objetivos);
        } else if (combatiente instanceof Esqueleto) {
            ejecutarAccionEsqueleto((Esqueleto) combatiente, opcion, objetivos);
        }
    }

    private void ejecutarAccionGuerrero(Guerrero guerrero, int opcion, ArrayList<Combatiente> objetivos) {
        switch (opcion) {
            case 1: // Atacar
                Combatiente objetivo = batalla.seleccionarObjetivo(objetivos, input, vista);
                batalla.procesarAtaque(guerrero, objetivo);
                break;
            case 2: // Golpe Poderoso
                objetivo = batalla.seleccionarObjetivo(objetivos, input, vista);
                int danoPoderoso = guerrero.atacar() * 2;
                objetivo.recibirDanio(danoPoderoso);
                batalla.agregarAccion(guerrero.getNombre() + " usa Golpe Poderoso en " + objetivo.getNombre() + " causando " + danoPoderoso + " de daño");
                break;
            case 4: // Usar Item
                usarItemJugador(guerrero, objetivos);
                break;
            case 3: // Pasar turno
                batalla.agregarAccion(guerrero.getNombre() + " pasa su turno");
                break;
        }
    }

    private void ejecutarAccionExplorador(Explorador explorador, int opcion, ArrayList<Combatiente> objetivos) {
        switch (opcion) {
            case 1: // Atacar
                Combatiente objetivo = batalla.seleccionarObjetivo(objetivos, input, vista);
                batalla.procesarAtaque(explorador, objetivo);
                break;
            case 3: //usar Item
                usarItemJugador(explorador, objetivos);
                break;
            case 2: // Pasar turno
                batalla.agregarAccion(explorador.getNombre() + " pasa su turno");
                break;
        }
    }

    private void ejecutarAccionOrco(Orco orco, int opcion, ArrayList<Combatiente> objetivos) {
        switch (opcion) {
            case 1: // Atacar
                Combatiente objetivo = batalla.seleccionarObjetivo(objetivos, input, vista);
                batalla.procesarAtaque(orco, objetivo);
                break;
            case 2: // Rugido Salvaje
                int reduccion = orco.rugidoSalvaje();
                batalla.agregarAccion(orco.getNombre() + " usa Rugido Salvaje reduciendo la defensa enemiga en " + reduccion);
                break;
            case 4: // Furia Berserker (solo jefe)
                if (orco.getEsJefe()) {
                    objetivo = batalla.seleccionarObjetivo(objetivos, input, vista);
                    int danoFuria = orco.atacar() * 3;
                    objetivo.recibirDanio(danoFuria);
                    batalla.agregarAccion(orco.getNombre() + " usa Furia Berserker en " + objetivo.getNombre() + " causando " + danoFuria + " de daño");
                } else {
                    batalla.agregarAccion(orco.getNombre() + " pasa su turno");
                }
                break;
            case 3: // Pasar turno
                batalla.agregarAccion(orco.getNombre() + " pasa su turno");
                break;
        }
    }

    private void ejecutarAccionEsqueleto(Esqueleto esqueleto, int opcion, ArrayList<Combatiente> objetivos) {
        switch (opcion) {
            case 1: // Atacar
                Combatiente objetivo = batalla.seleccionarObjetivo(objetivos, input, vista);
                batalla.procesarAtaque(esqueleto, objetivo);
                break;
            case 2: // Drenar Vida
                objetivo = batalla.seleccionarObjetivo(objetivos, input, vista);
                int dano = esqueleto.drenarVida(objetivo);
                batalla.agregarAccion(esqueleto.getNombre() + " drena " + dano + " HP de " + objetivo.getNombre());
                break;
            case 3: // Tormenta Necrótica (solo jefe)
                if (esqueleto.getEsJefe()) {
                    int danoArea = 20;
                    for (Combatiente obj : objetivos) {
                        obj.recibirDanio(danoArea);
                    }
                    batalla.agregarAccion(esqueleto.getNombre() + " usa Tormenta Necrótica causando " + danoArea + " a todos");
                } else {
                    batalla.agregarAccion(esqueleto.getNombre() + " pasa su turno");
                }
                break;
            case 4: // Pasar turno
                batalla.agregarAccion(esqueleto.getNombre() + " pasa su turno");
                break;
        }
    }

    private void usarItemJugador(Combatiente jugador, ArrayList<Combatiente> objetivos) {
        ArrayList<Item> inventario = null;

        if (jugador instanceof Guerrero) {
            inventario = ((Guerrero) jugador).getInventario();
        } else if (jugador instanceof Explorador) {
            inventario = ((Explorador) jugador).getInventario();
        }

        if (inventario == null || inventario.isEmpty()) {
            batalla.agregarAccion(jugador.getNombre() + " no tiene items disponibles");
            return;
        }

        vista.mostrarInventario(inventario);

        int opcionItem = 0;
        while (opcionItem < 1 || opcionItem > inventario.size()) {
            try {
                opcionItem = input.nextInt();
            } catch (Exception e) {
                input.nextLine();
            }
        }

        Item item = null;
        if (jugador instanceof Guerrero) {
            item = ((Guerrero) jugador).usarItem(opcionItem);
        } else if (jugador instanceof Explorador) {
            item = ((Explorador) jugador).usarItem(opcionItem);
        }

        if (item != null) {
            aplicarItem(item, jugador, objetivos);
        }
    }

    private void aplicarItem(Item item, Combatiente usuario, ArrayList<Combatiente> objetivos) {
        String tipo = item.getTipo();
        int efecto = item.getEfecto();

        if (tipo.equals("curacion")) {
            int nuevaVida = usuario.getVidaActual() + efecto;
            if (nuevaVida > usuario.getVidaMaxima()) {
                nuevaVida = usuario.getVidaMaxima();
            }
            usuario.setVidaActual(nuevaVida);
            batalla.agregarAccion(usuario.getNombre() + " usa " + item.getNombre() + " y recupera " + efecto + " HP");

        } else if (tipo.equals("ataque")) {
            // Atacar a un objetivo con bonus de ataque
            Combatiente objetivo = batalla.seleccionarObjetivo(objetivos, input, vista);
            int danoTotal = usuario.atacar() + efecto;
            objetivo.recibirDanio(danoTotal);
            batalla.agregarAccion(usuario.getNombre() + " usa " + item.getNombre() + " y ataca a " + objetivo.getNombre() + " causando " + danoTotal + " de daño");

        } else if (tipo.equals("defensa")) {
            // Curar un poco simulando defensa
            int curacion = efecto * 2;
            int nuevaVida = usuario.getVidaActual() + curacion;
            if (nuevaVida > usuario.getVidaMaxima()) {
                nuevaVida = usuario.getVidaMaxima();
            }
            usuario.setVidaActual(nuevaVida);
            batalla.agregarAccion(usuario.getNombre() + " usa " + item.getNombre() + " y se protege recuperando " + curacion + " HP");

        } else if (tipo.equals("velocidad")) {
            // Atacar dos veces
            Combatiente objetivo = batalla.seleccionarObjetivo(objetivos, input, vista);
            int dano = usuario.atacar();
            objetivo.recibirDanio(dano);
            objetivo.recibirDanio(dano);
            batalla.agregarAccion(usuario.getNombre() + " usa " + item.getNombre() + " y ataca dos veces a " + objetivo.getNombre() + " causando " + (dano * 2) + " de daño total");
        }
    }
}
