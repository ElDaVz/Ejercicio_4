public class MenuGuerrero extends Menu {

    public MenuGuerrero() {
        super();
        this.opciones.add(1, "Golpe Poderoso");
        this.opciones.add("Usar Item");
        this.generarMenu();
    }

    public String generarMenu() {
        return formatearOpciones("MenuGuerrero");
    }
}