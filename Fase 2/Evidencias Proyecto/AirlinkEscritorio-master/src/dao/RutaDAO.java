package dao;

import Modelo.Ruta;
import java.sql.*;
import java.util.*;

public class RutaDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // ===== LISTAR TODAS LAS RUTAS =====
    public List<Ruta> listar() {
        List<Ruta> lista = new ArrayList<>();
        String sql = """
            SELECT r.idRuta,
                   r.idTerminalOrigen,
                   r.idTerminalDestino,
                   t1.nombreTerminal AS origen,
                   t2.nombreTerminal AS destino,
                   r.distanciaKm,
                   r.duracionEstimadaMin,
                   r.activo
            FROM ruta r
            JOIN terminal t1 ON r.idTerminalOrigen = t1.idTerminal
            JOIN terminal t2 ON r.idTerminalDestino = t2.idTerminal;
            """;
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ruta r = new Ruta();
                r.setIdRuta(rs.getInt("idRuta"));
                r.setIdTerminalOrigen(rs.getInt("idTerminalOrigen"));
                r.setIdTerminalDestino(rs.getInt("idTerminalDestino"));
                r.setOrigen(rs.getString("origen"));
                r.setDestino(rs.getString("destino"));
                r.setDistanciaKm(rs.getDouble("distanciaKm"));
                r.setDuracionEstimadaMin(rs.getInt("duracionEstimadaMin"));
                r.setActivo(rs.getBoolean("activo"));
                lista.add(r);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar rutas: " + e);
        }
        return lista;
    }

    // ===== AGREGAR RUTA =====
    public boolean agregar(Ruta r) {
        String sql = "INSERT INTO ruta (idTerminalOrigen, idTerminalDestino, distanciaKm, duracionEstimadaMin, activo) VALUES (?, ?, ?, ?, ?)";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, r.getIdTerminalOrigen());
            ps.setInt(2, r.getIdTerminalDestino());
            ps.setDouble(3, r.getDistanciaKm());
            ps.setInt(4, r.getDuracionEstimadaMin());
            ps.setBoolean(5, r.isActivo());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al agregar ruta: " + e);
            return false;
        }
    }

    // ===== ACTUALIZAR RUTA =====
    public boolean actualizar(Ruta r) {
        String sql = "UPDATE ruta SET idTerminalOrigen=?, idTerminalDestino=?, distanciaKm=?, duracionEstimadaMin=?, activo=? WHERE idRuta=?";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, r.getIdTerminalOrigen());
            ps.setInt(2, r.getIdTerminalDestino());
            ps.setDouble(3, r.getDistanciaKm());
            ps.setInt(4, r.getDuracionEstimadaMin());
            ps.setBoolean(5, r.isActivo());
            ps.setInt(6, r.getIdRuta());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al actualizar ruta: " + e);
            return false;
        }
    }

    // ===== ELIMINAR RUTA =====
    public boolean eliminar(int idRuta) {
        String sql = "DELETE FROM ruta WHERE idRuta=?";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idRuta);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar ruta: " + e);
            return false;
        }
    }

    // ===== OBTENER RUTA POR ID =====
    public Ruta obtenerPorId(int idRuta) {
        String sql = """
            SELECT r.idRuta,
                   r.idTerminalOrigen,
                   r.idTerminalDestino,
                   t1.nombreTerminal AS origen,
                   t2.nombreTerminal AS destino,
                   r.distanciaKm,
                   r.duracionEstimadaMin,
                   r.activo
            FROM ruta r
            JOIN terminal t1 ON r.idTerminalOrigen = t1.idTerminal
            JOIN terminal t2 ON r.idTerminalDestino = t2.idTerminal
            WHERE r.idRuta = ?;
            """;
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idRuta);
            rs = ps.executeQuery();
            if (rs.next()) {
                Ruta r = new Ruta();
                r.setIdRuta(rs.getInt("idRuta"));
                r.setIdTerminalOrigen(rs.getInt("idTerminalOrigen"));
                r.setIdTerminalDestino(rs.getInt("idTerminalDestino"));
                r.setOrigen(rs.getString("origen"));
                r.setDestino(rs.getString("destino"));
                r.setDistanciaKm(rs.getDouble("distanciaKm"));
                r.setDuracionEstimadaMin(rs.getInt("duracionEstimadaMin"));
                r.setActivo(rs.getBoolean("activo"));
                return r;
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener ruta: " + e);
        }
        return null;
    }
}
