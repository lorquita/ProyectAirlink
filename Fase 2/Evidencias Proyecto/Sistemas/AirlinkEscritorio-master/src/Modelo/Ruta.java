package Modelo;

public class Ruta {
    private int idRuta;
    private int idTerminalOrigen;
    private int idTerminalDestino;
    private String origen;
    private String destino;
    private double distanciaKm;
    private int duracionEstimadaMin;
    private boolean activo;

    // Getters y Setters
    public int getIdRuta() { return idRuta; }
    public void setIdRuta(int idRuta) { this.idRuta = idRuta; }

    public int getIdTerminalOrigen() { return idTerminalOrigen; }
    public void setIdTerminalOrigen(int idTerminalOrigen) { this.idTerminalOrigen = idTerminalOrigen; }

    public int getIdTerminalDestino() { return idTerminalDestino; }
    public void setIdTerminalDestino(int idTerminalDestino) { this.idTerminalDestino = idTerminalDestino; }

    public String getOrigen() { return origen; }
    public void setOrigen(String origen) { this.origen = origen; }

    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }

    public double getDistanciaKm() { return distanciaKm; }
    public void setDistanciaKm(double distanciaKm) { this.distanciaKm = distanciaKm; }

    public int getDuracionEstimadaMin() { return duracionEstimadaMin; }
    public void setDuracionEstimadaMin(int duracionEstimadaMin) { this.duracionEstimadaMin = duracionEstimadaMin; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    @Override
    public String toString() {
        return origen + " → " + destino;
    }
}
