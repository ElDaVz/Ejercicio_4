public class Item {
    private String nombre;
    private String tipo;
    private String descripcion;
    private int efecto;

    public Item(String nombre, String tipo, String descripcion, int efecto) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.efecto = efecto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getEfecto() {
        return efecto;
    }

}
