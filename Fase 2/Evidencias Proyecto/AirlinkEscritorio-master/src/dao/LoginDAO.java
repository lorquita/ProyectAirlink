package dao;

import Modelo.Usuario;
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

public class LoginDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

public Usuario log(String correo, String passIngresada) {
    Usuario usuario = null;
    String sql = "SELECT * FROM usuario WHERE email = ?";

    try {
        con = Conexion.getConexion();
        ps = con.prepareStatement(sql);
        ps.setString(1, correo);
        rs = ps.executeQuery();

        if (rs.next()) {
            String hashGuardado = rs.getString("contrasena");

            System.out.println("📩 Correo ingresado: " + correo);
            System.out.println("🔑 Contraseña ingresada: " + passIngresada);
            System.out.println("💾 Hash guardado en BD: " + hashGuardado);

            boolean coincide = false;

            // caso 1: hash bcrypt
            if (hashGuardado != null && hashGuardado.startsWith("$2")) {
                try {
                    coincide = BCrypt.checkpw(passIngresada, hashGuardado);
                    System.out.println("✅ BCrypt comparado -> " + coincide);
                } catch (Exception ex) {
                    System.out.println("⚠️ Error al comparar BCrypt: " + ex.getMessage());
                }
            } else {
                // caso 2: texto plano
                coincide = passIngresada.equals(hashGuardado);
                System.out.println("⚙️ Comparación texto plano -> " + coincide);
            }

            if (coincide) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("idUsuario"));
                usuario.setNombre(rs.getString("nombreUsuario"));
                usuario.setCorreo(rs.getString("email"));
                usuario.setContraseña(hashGuardado);
                usuario.setRol(String.valueOf(rs.getInt("idRol")));
                System.out.println("🎉 Login exitoso para " + usuario.getNombre());
            } else {
                System.out.println("❌ Contraseña incorrecta para " + correo);
            }
        } else {
            System.out.println("🚫 No existe usuario con correo: " + correo);
        }

    } catch (SQLException e) {
        System.out.println("💥 Error en LoginDAO.log(): " + e.getMessage());
    }
    return usuario;
}

    
}
