package es.masanz.ut9.pruebas.concesionario;

public class Coche {
    private String imagen;
    private int precio;
    private String modelo;
    private String color;

    public Coche(String imagen, int precio, String modelo, String color) {
        this.imagen = imagen;
        this.precio = precio;
        this.modelo = modelo;
        this.color = color;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
