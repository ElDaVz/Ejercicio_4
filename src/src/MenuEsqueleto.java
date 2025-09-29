public class MenuEsqueleto extends Menu {
    private boolean esJefe;

    public MenuEsqueleto(boolean esJefe) {
        super();
        this.esJefe = esJefe;
        this.opciones.add(1, "Drenar Vida");

        if (esJefe) {
            this.opciones.add("Tormenta Necrótica");
        }

        this.generarMenu();
    }

    public String generarMenu() {
        String titulo = esJefe ? "MenuEsqueletoJefe" : "MenuEsqueleto";
        return formatearOpciones(titulo);
    }
}
