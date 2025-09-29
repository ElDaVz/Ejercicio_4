public class Orco extends Combatiente {
    private boolean esJefe;

    Orco(String nombre, int vidaMaxima, int ataqueBase, boolean esJefe) {
        super(nombre, vidaMaxima, ataqueBase);
        this.esJefe = esJefe;
    }

    public boolean getEsJefe() {
        return esJefe;
    }

    public String decirFraseInicio() {
        if (esJefe) {
            return frase("¡Soy el jefe de esta horda! ¡Prepárate para morir!");
        }
        return frase("¡Sangre y guerra!");
    }

    public String decirFraseMuerte() {
        if (esJefe) {
            return frase("La horda... continuará sin mí...");
        }
        return frase("He sido derrotado...");
    }

    //Duda****
    // Habilidad especial: Rugido Salvaje
    public int rugidoSalvaje() {
        if (esJefe) {
            return 15; // El jefe reduce mas defensa
        }
        return 10;
    }
}