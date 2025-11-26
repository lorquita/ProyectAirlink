package dao;

import Modelo.Destino;
import java.sql.*;
import java.util.*;

public class DestinoDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // ============================
    // LISTAR
    // ============================
    public List<Destino> listar() {
        List<Destino> lista = new ArrayList<>();
        String sql = "SELECT * FROM destino";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Destino d = new Destino();
                d.setIdDestino(rs.getInt("idDestino"));
                d.setNombre(rs.getString("nombre"));
                d.setPrecio(rs.getDouble("precio"));
                d.setCiudad(rs.getString("ciudad"));
                d.setPais(rs.getString("pais"));
                d.setImagen(rs.getString("imagen"));
                d.setDescripcion(rs.getString("descripcion"));
                try {
                    d.setDestacado(rs.getBoolean("destacado"));
                } catch (SQLException ex) {
                    d.setDestacado(false);
                }
                lista.add(d);
            }
        } catch (Exception e) {
            System.out.println("Error al listar destinos: " + e);
        }
        return lista;
    }

    // ============================
    // AGREGAR
    // ============================
    public boolean agregar(Destino d) {
        String sql = "INSERT INTO destino (nombre, precio, ciudad, pais, imagen, descripcion, destacado) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, d.getNombre());
            ps.setDouble(2, d.getPrecio());
            ps.setString(3, d.getCiudad());
            ps.setString(4, d.getPais());
            ps.setString(5, d.getImagen());
            ps.setString(6, d.getDescripcion());
            ps.setBoolean(7, d.isDestacado());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al agregar destino: " + e);
            return false;
        }
    }

    // ============================
    // ACTUALIZAR
    // ============================
    public boolean actualizar(Destino d) {
        String sql = "UPDATE destino SET nombre=?, precio=?, ciudad=?, pais=?, imagen=?, descripcion=?, destacado=? WHERE idDestino=?";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, d.getNombre());
            ps.setDouble(2, d.getPrecio());
            ps.setString(3, d.getCiudad());
            ps.setString(4, d.getPais());
            ps.setString(5, d.getImagen());
            ps.setString(6, d.getDescripcion());
            ps.setBoolean(7, d.isDestacado());
            ps.setInt(8, d.getIdDestino());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al actualizar destino: " + e);
            return false;
        }
    }

    // ============================
    // ELIMINAR
    // ============================
    public boolean eliminar(int id) {
        String sql = "DELETE FROM destino WHERE idDestino=?";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar destino: " + e);
            return false;
        }
    }
}
