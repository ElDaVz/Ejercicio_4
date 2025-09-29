import java.util.ArrayList;

public class MenuExplorador extends Menu{

    public MenuExplorador() {
        super();
        this.opciones.add("Usar Item");
        this.generarMenu();
    }

    public String generarMenu(){
        return formatearOpciones("MenuExplorador");
    }

}
