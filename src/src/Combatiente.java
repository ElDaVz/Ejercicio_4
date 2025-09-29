abstract class Combatiente {
    private String nombre;
    private int vidaMaxima;
    private int vidaActual;
    private int ataqueBase;

    Combatiente(String nombre, int vidaMaxima, int ataqueBase) {
        this.nombre = nombre;
        this.vidaMaxima = vidaMaxima;
        this.vidaActual = vidaMaxima;
        this.ataqueBase = ataqueBase;

    }

    public String getNombre() {
        return nombre;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getVidaActual() {
        return vidaActual;
    }

    public void setVidaActual(int vidaActual) {
        this.vidaActual = vidaActual;
    }

    public boolean estaVivo() {
        return this.vidaActual > 0;
    }

    public String frase(String palabras) {
        return palabras;
    }

    public int atacar() {
        return ataqueBase;
    }

    public int recibirDanio(int danio) {
        this.vidaActual -= danio;
        return this.vidaActual;
    }

    public String toString() {
        return nombre;
    }
}
