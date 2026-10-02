package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Tramite;



public class TramiteDAO {

    // 1. CREATE - Registrar Trámite
    public boolean crear(Tramite t) {
        String sql = "INSERT INTO TRAMITE (folio_tramite, tipo_tramite, estado_tramite, fecha_inicio, num_serie) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, t.getFolioTramite());
            ps.setString(2, t.getTipoTramite());
            ps.setString(3, t.getEstadoTramite());
            ps.setDate(4, t.getFechaInicio());
            ps.setString(5, t.getNumSerie());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al crear trámite: " + e.getMessage());
            return false;
        }
    }

    // 2. READ - Listar Trámites
    public List<Tramite> listar() {
        List<Tramite> lista = new ArrayList<>();
        String sql = "SELECT * FROM TRAMITE";
        try (Connection con = Conexion.obtenerConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Tramite t = new Tramite(
                    rs.getString("folio_tramite"),
                    rs.getString("tipo_tramite"),
                    rs.getString("estado_tramite"),
                    rs.getDate("fecha_inicio"),
                    rs.getString("num_serie")
                );
                lista.add(t);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar trámites: " + e.getMessage());
        }
        return lista;
    }

    // 3. UPDATE - Cambiar estado del Trámite
    public boolean actualizarEstado(String folio, String nuevoEstado) {
        String sql = "UPDATE TRAMITE SET estado_tramite = ? WHERE folio_tramite = ?";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nuevoEstado);
            ps.setString(2, folio);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar estado del trámite: " + e.getMessage());
            return false;
        }
    }

    // 4. DELETE - Eliminar Trámite
    public boolean eliminar(String folio) {
        String sql = "DELETE FROM TRAMITE WHERE folio_tramite = ?";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, folio);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar trámite: " + e.getMessage());
            return false;
        }
    }
}