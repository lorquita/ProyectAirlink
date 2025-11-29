package Modelo;

import java.sql.Timestamp;

public class Viaje {
    private int idViaje;
    private int idRuta;
    private Timestamp salida;
    private Timestamp llegada;
    private int idEquipo;
    private String estado;
    private int idDestino; // <-- NUEVO

    // Getters y Setters
    public int getIdViaje() { return idViaje; }
    public void setIdViaje(int idViaje) { this.idViaje = idViaje; }
    public int getIdRuta() { return idRuta; }
    public void setIdRuta(int idRuta) { this.idRuta = idRuta; }
    public Timestamp getSalida() { return salida; }
    public void setSalida(Timestamp salida) { this.salida = salida; }
    public Timestamp getLlegada() { return llegada; }
    public void setLlegada(Timestamp llegada) { this.llegada = llegada; }
    public int getIdEquipo() { return idEquipo; }
    public void setIdEquipo(int idEquipo) { this.idEquipo = idEquipo; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public int getIdDestino() { return idDestino; }
    public void setIdDestino(int idDestino) { this.idDestino = idDestino; }
}
