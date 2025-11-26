package dao;

import dao.Conexion;
import Modelo.Viaje;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViajeDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // ==========================
    // 1️⃣ LISTAR TODOS LOS VIAJES
    // ==========================
    public List<Viaje> listar() {
        List<Viaje> lista = new ArrayList<>();
        String sql = "SELECT * FROM viaje";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Viaje v = new Viaje();
                v.setIdViaje(rs.getInt("idViaje"));
                v.setIdRuta(rs.getInt("idRuta"));
                v.setSalida(rs.getTimestamp("salida"));
                v.setLlegada(rs.getTimestamp("llegada"));
                v.setIdEquipo(rs.getInt("idEquipo"));
                v.setEstado(rs.getString("estado"));
                v.setIdDestino(rs.getInt("idDestino"));
                lista.add(v);
            }
        } catch (Exception e) {
            System.out.println("❌ Error al listar viajes: " + e);
        }
        return lista;
    }
    // ========================================
    // 2️⃣ LISTAR VIAJES POR DESTINO (MUY ÚTIL)
    // ========================================
    public List<Viaje> listarPorDestino(int idDestino) {
        List<Viaje> lista = new ArrayList<>();
        String sql = "SELECT * FROM viaje WHERE idDestino = ?";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idDestino);
            rs = ps.executeQuery();
            while (rs.next()) {
                Viaje v = new Viaje();
                v.setIdViaje(rs.getInt("idViaje"));
                v.setIdRuta(rs.getInt("idRuta"));
                v.setSalida(rs.getTimestamp("salida"));
                v.setLlegada(rs.getTimestamp("llegada"));
                v.setIdEquipo(rs.getInt("idEquipo"));
                v.setEstado(rs.getString("estado"));
                v.setIdDestino(rs.getInt("idDestino"));
                lista.add(v);
            }
        } catch (Exception e) {
            System.out.println("❌ Error al listar viajes por destino: " + e);
        }
        return lista;
    }

    // ==========================
    // 3️⃣ AGREGAR NUEVO VIAJE
    // ==========================
    public boolean agregar(Viaje v) {
        String sql = "INSERT INTO viaje (idRuta, salida, llegada, idEquipo, estado, idDestino) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, v.getIdRuta());
            ps.setTimestamp(2, v.getSalida());
            ps.setTimestamp(3, v.getLlegada());
            ps.setInt(4, v.getIdEquipo());
            ps.setString(5, v.getEstado());
            ps.setInt(6, v.getIdDestino());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("❌ Error al agregar viaje: " + e);
            return false;
        }
    }

    // ==========================
    // 4️⃣ ACTUALIZAR VIAJE
    // ==========================
    public boolean actualizar(Viaje v) {
        String sql = "UPDATE viaje SET idRuta=?, salida=?, llegada=?, idEquipo=?, estado=?, idDestino=? WHERE idViaje=?";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, v.getIdRuta());
            ps.setTimestamp(2, v.getSalida());
            ps.setTimestamp(3, v.getLlegada());
            ps.setInt(4, v.getIdEquipo());
            ps.setString(5, v.getEstado());
            ps.setInt(6, v.getIdDestino());
            ps.setInt(7, v.getIdViaje());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("❌ Error al actualizar viaje: " + e);
            return false;
        }
    }

    // ==========================
    // 5️⃣ ELIMINAR VIAJE
    // ==========================
    public boolean eliminar(int idViaje) {
        String sql = "DELETE FROM viaje WHERE idViaje = ?";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idViaje);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("❌ Error al eliminar viaje: " + e);
            return false;
        }
    }
}