public class MenuOrco extends Menu {
    private boolean esJefe;

    public MenuOrco(boolean esJefe) {
        super();
        this.esJefe = esJefe;
        this.opciones.add(1, "Rugido Salvaje");

        if (esJefe) {
            this.opciones.add("Furia Berserker");
        }

        this.generarMenu();
    }

    public String generarMenu() {
        String titulo = esJefe ? "MenuOrcoJefe" : "MenuOrco";
        return formatearOpciones(titulo);
    }
}
