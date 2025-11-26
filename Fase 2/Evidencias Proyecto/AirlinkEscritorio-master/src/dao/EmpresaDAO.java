package dao;

import Modelo.Empresa;
import java.sql.*;
import java.util.*;

public class EmpresaDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();

    // Listar todas las empresas
    public List<Empresa> listar() {
        List<Empresa> lista = new ArrayList<>();
        String sql = "SELECT * FROM empresa ORDER BY idEmpresa DESC";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Empresa e = new Empresa();
                e.setIdEmpresa(rs.getInt("idEmpresa"));
                e.setNombreEmpresa(rs.getString("nombreEmpresa"));
                e.setTipoEmpresa(rs.getString("tipoEmpresa"));
                e.setLogo(rs.getString("logo"));
                e.setDescripcion(rs.getString("descripcion"));
                e.setSitioWeb(rs.getString("sitio_web"));
                e.setActivo(rs.getBoolean("activo"));
                lista.add(e);
            }
        } catch (SQLException ex) {
            System.out.println("❌ Error al listar empresas: " + ex.getMessage());
        }
        return lista;
    }

    // Agregar empresa
    public boolean agregar(Empresa e) {
        String sql = "INSERT INTO empresa (nombreEmpresa, tipoEmpresa, logo, descripcion, sitio_web, activo) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, e.getNombreEmpresa());
            ps.setString(2, e.getTipoEmpresa());
            ps.setString(3, e.getLogo());
            ps.setString(4, e.getDescripcion());
            ps.setString(5, e.getSitioWeb());
            ps.setBoolean(6, e.isActivo());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("❌ Error al agregar empresa: " + ex.getMessage());
            return false;
        }
    }

    // Actualizar empresa
    public boolean actualizar(Empresa e) {
        String sql = "UPDATE empresa SET nombreEmpresa=?, tipoEmpresa=?, logo=?, descripcion=?, sitio_web=?, activo=? WHERE idEmpresa=?";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, e.getNombreEmpresa());
            ps.setString(2, e.getTipoEmpresa());
            ps.setString(3, e.getLogo());
            ps.setString(4, e.getDescripcion());
            ps.setString(5, e.getSitioWeb());
            ps.setBoolean(6, e.isActivo());
            ps.setInt(7, e.getIdEmpresa());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("❌ Error al actualizar empresa: " + ex.getMessage());
            return false;
        }
    }

    // Eliminar empresa
    public boolean eliminar(int idEmpresa) {
        String sql = "DELETE FROM empresa WHERE idEmpresa=?";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idEmpresa);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("❌ Error al eliminar empresa: " + ex.getMessage());
            return false;
        }
    }
}
