package Modelo;

public class Empresa {
    private int idEmpresa;
    private String nombreEmpresa;
    private String tipoEmpresa;
    private String logo;
    private String descripcion;
    private String sitioWeb;
    private boolean activo;

    public Empresa() {}

    public Empresa(int idEmpresa, String nombreEmpresa, String tipoEmpresa, String logo, String descripcion, String sitioWeb, boolean activo) {
        this.idEmpresa = idEmpresa;
        this.nombreEmpresa = nombreEmpresa;
        this.tipoEmpresa = tipoEmpresa;
        this.logo = logo;
        this.descripcion = descripcion;
        this.sitioWeb = sitioWeb;
        this.activo = activo;
    }

    public int getIdEmpresa() { return idEmpresa; }
    public void setIdEmpresa(int idEmpresa) { this.idEmpresa = idEmpresa; }

    public String getNombreEmpresa() { return nombreEmpresa; }
    public void setNombreEmpresa(String nombreEmpresa) { this.nombreEmpresa = nombreEmpresa; }

    public String getTipoEmpresa() { return tipoEmpresa; }
    public void setTipoEmpresa(String tipoEmpresa) { this.tipoEmpresa = tipoEmpresa; }

    public String getLogo() { return logo; }
    public void setLogo(String logo) { this.logo = logo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getSitioWeb() { return sitioWeb; }
    public void setSitioWeb(String sitioWeb) { this.sitioWeb = sitioWeb; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}
