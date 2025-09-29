public class Esqueleto extends Combatiente {
    private boolean esJefe;

    Esqueleto(String nombre, int vidaMaxima, int ataqueBase, boolean esJefe) {
        super(nombre, vidaMaxima, ataqueBase);
        this.esJefe = esJefe;
    }

    public boolean getEsJefe() {
        return esJefe;
    }

    public String decirFraseInicio() {
        if (esJefe) {
            return frase("¡Soy el señor de los muertos vivientes!");
        }
        return frase("Tu fin está cerca!");
    }

    public String decirFraseMuerte() {
        if (esJefe) {
            return frase("Volveré desde las sombras...");
        }
        return frase("Mi muerte no impedirá que pierdas esta batalla");
    }

    // Habilidad especial: Drenar Vida
    public int drenarVida(Combatiente objetivo) {
        int dano = 10;
        if (esJefe) {
            dano = 15;
        }
        objetivo.recibirDanio(dano);
        // Curar al esqueleto
        int nuevaVida = this.getVidaActual() + dano;
        if (nuevaVida > this.getVidaMaxima()) {
            nuevaVida = this.getVidaMaxima();
        }
        this.setVidaActual(nuevaVida);
        return dano;
    }
}
