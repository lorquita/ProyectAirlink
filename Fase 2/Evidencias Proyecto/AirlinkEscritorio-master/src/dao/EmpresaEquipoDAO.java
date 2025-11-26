package dao;

import Modelo.EmpresaEquipo;
import java.sql.*;
import java.util.*;

public class EmpresaEquipoDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List<EmpresaEquipo> listar() {
        List<EmpresaEquipo> lista = new ArrayList<>();
        String sql = "SELECT * FROM empresa_equipo WHERE activo = 1";
        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                EmpresaEquipo e = new EmpresaEquipo();
                e.setIdEquipo(rs.getInt("idEquipo"));
                e.setIdEmpresa(rs.getInt("idEmpresa"));
                e.setModelo(rs.getString("modelo"));
                e.setMatricula(rs.getString("matricula"));
                e.setCapacidad(rs.getInt("capacidad"));
                lista.add(e);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar equipos: " + ex);
        }
        return lista;
    }
}
