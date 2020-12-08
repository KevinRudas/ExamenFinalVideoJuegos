package com.example.examenfinalvideojuegos;

public class ClasePokeCapturar {

    String nombre;
    String tipo;
    String imagen;
    Double latitude;
    Double longitude;

    public ClasePokeCapturar() {
    }

    public ClasePokeCapturar(String nombre, String tipo, String imagen, Double latitude, Double longitude) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.imagen = imagen;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String get_imagen() {
        return imagen;
    }

    public void set_imagen(String url_imagen) {
        this.imagen = url_imagen;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
