package dao;

import java.sql.*;
import java.util.*;
import Modelo.Usuario;
import org.mindrot.jbcrypt.BCrypt;

public class UsuarioDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // --- LISTAR ---
    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT u.*, r.nombreRol FROM usuario u JOIN rol r ON u.idRol = r.idRol";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("idUsuario"));
                u.setNombre(rs.getString("nombreUsuario"));
                u.setCorreo(rs.getString("email"));
                u.setContraseña(rs.getString("contrasena"));
                u.setRol(rs.getString("nombreRol"));
                lista.add(u);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar usuarios: " + e.getMessage());
        }
        return lista;
    }

    // --- AGREGAR ---
    public boolean agregar(Usuario u) {
        String sql = "INSERT INTO usuario (nombreUsuario, email, contrasena, idRol) VALUES (?, ?, ?, ?)";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);

            // Encriptar la contraseña antes de guardarla
            String hash = BCrypt.hashpw(u.getContraseña(), BCrypt.gensalt());

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getCorreo());
            ps.setString(3, hash);
            ps.setInt(4, Integer.parseInt(u.getRol()));
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al agregar usuario: " + e.getMessage());
            return false;
        }
    }

    // --- ACTUALIZAR ---
    public boolean actualizar(Usuario u) {
        String sql = "UPDATE usuario SET nombreUsuario=?, email=?, contrasena=?, idRol=? WHERE idUsuario=?";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);

            // si la contraseña no está cifrada, cifrarla
            String contrasena = u.getContraseña();
            if (!contrasena.startsWith("$2b$")) {
                contrasena = BCrypt.hashpw(contrasena, BCrypt.gensalt());
            }

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getCorreo());
            ps.setString(3, contrasena);
            ps.setInt(4, Integer.parseInt(u.getRol()));
            ps.setInt(5, u.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al actualizar usuario: " + e.getMessage());
            return false;
        }
    }

    // --- ELIMINAR ---
    public boolean eliminar(int id) {
        String sql = "DELETE FROM usuario WHERE idUsuario=?";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }

    // --- LOGIN ---
    public Usuario login(String correo, String contrasenaIngresada) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE email = ?";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            rs = ps.executeQuery();

            if (rs.next()) {
                String hashGuardado = rs.getString("contrasena");

                // compara la contraseña normal (texto) con el hash guardado
                if (BCrypt.checkpw(contrasenaIngresada, hashGuardado)) {
                    usuario = new Usuario();
                    usuario.setId(rs.getInt("idUsuario"));
                    usuario.setNombre(rs.getString("nombreUsuario"));
                    usuario.setCorreo(rs.getString("email"));
                    usuario.setRol(String.valueOf(rs.getInt("idRol")));
                } else {
                    System.out.println("Contraseña incorrecta para: " + correo);
                }
            } else {
                System.out.println("Usuario no encontrado: " + correo);
            }

        } catch (SQLException e) {
            System.out.println("Error al validar login: " + e.getMessage());
        }

        return usuario;
    }
}
