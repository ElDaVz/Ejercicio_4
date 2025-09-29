import java.awt.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

abstract class Menu {
    protected ArrayList<String> opciones;

    public Menu(){
        this.opciones = new ArrayList<>();
        this.opciones.add("Atacar");
        this.opciones.add("Pasar Turno");
    }

    abstract String generarMenu();

    public int procesarOpcion(Scanner sc) {
        int maxOpciones = opciones.size();
        int opcionActual = 0;

        while(opcionActual < 1 || opcionActual > maxOpciones) {
            try {
                opcionActual = sc.nextInt();
            } catch (InputMismatchException e) {
                sc.nextLine();
                opcionActual = 0;
            }
        }

        return opcionActual;
    }

    public String formatearOpciones(String encabezado)
    {
        StringBuilder menu = new StringBuilder();
        menu.append("$$$ ").append(encabezado).append("$$$\n");

        menu.append("\n-- Opciones ---\n");

        for (int i = 0; i < this.opciones.size(); i++) {
            menu.append((i + 1)).append(". ").append(this.opciones.get(i)).append("\n");
        }
        menu.append("Seleccione una opción: ");
        return menu.toString();
    }



}
