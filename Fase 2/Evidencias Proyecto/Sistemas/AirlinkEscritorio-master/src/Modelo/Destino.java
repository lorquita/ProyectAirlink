package Modelo;

public class Destino {
    private int idDestino;
    private String nombre;
    private double precio;
    private String ciudad;
    private String pais;
    private String imagen;
    private String descripcion;
    private boolean destacado;
    private boolean activo;

    // Constructor vacío
    public Destino() {}

    // Getters y Setters
    public int getIdDestino() { return idDestino; }
    public void setIdDestino(int idDestino) { this.idDestino = idDestino; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }
    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public boolean isDestacado() { return destacado; }
    public void setDestacado(boolean destacado) { this.destacado = destacado; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    @Override
    public String toString() {
        return nombre; // Para que el comboBox muestre el nombre
    }
}
