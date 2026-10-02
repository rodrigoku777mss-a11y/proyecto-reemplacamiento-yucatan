package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Vehiculo;

public class VehiculoDAO {

    // 1. CREATE - Insertar Vehículo
    public boolean crear(Vehiculo v) {
        String sql = "INSERT INTO VEHICULO (num_serie, placa_actual, num_motor, anio, id_propietario, id_modelo) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, v.getNumSerie());
            ps.setString(2, v.getPlacaActual());
            ps.setString(3, v.getNumMotor());
            ps.setInt(4, v.getAnio());
            ps.setInt(5, v.getIdPropietario());
            ps.setInt(6, v.getIdModelo());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al crear vehículo: " + e.getMessage());
            return false;
        }
    }

    // 2. READ - Consultar todos los Vehículos
    public List<Vehiculo> listar() {
        List<Vehiculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM VEHICULO";
        try (Connection con = Conexion.obtenerConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Vehiculo v = new Vehiculo(
                    rs.getString("num_serie"),
                    rs.getString("placa_actual"),
                    rs.getString("num_motor"),
                    rs.getInt("anio"),
                    rs.getInt("id_propietario"),
                    rs.getInt("id_modelo")
                );
                lista.add(v);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar vehículos: " + e.getMessage());
        }
        return lista;
    }

    // 3. UPDATE - Actualizar Placa y Año
    public boolean actualizar(Vehiculo v) {
        String sql = "UPDATE VEHICULO SET placa_actual = ?, anio = ? WHERE num_serie = ?";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, v.getPlacaActual());
            ps.setInt(2, v.getAnio());
            ps.setString(3, v.getNumSerie());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar vehículo: " + e.getMessage());
            return false;
        }
    }

    // 4. DELETE - Eliminar por número de serie
    public boolean eliminar(String numSerie) {
        String sql = "DELETE FROM VEHICULO WHERE num_serie = ?";
        try (Connection con = Conexion.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, numSerie);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar vehículo: " + e.getMessage());
            return false;
        }
    }

    // 5. CONSULTA JOIN 
    public List<String[]> obtenerVehiculosDetallados() {
        List<String[]> resultados = new ArrayList<>();
        String sql = "SELECT v.num_serie, v.placa_actual, m.marca, m.nombre_modelo, v.anio, " +
                     "CONCAT(p.nombre, ' ', p.primer_apellido) AS propietario " +
                     "FROM VEHICULO v " +
                     "INNER JOIN MODELO m ON v.id_modelo = m.id_modelo " +
                     "INNER JOIN PROPIETARIO p ON v.id_propietario = p.id_propietario";
                     
        try (Connection con = Conexion.obtenerConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                String[] fila = new String[6];
                fila[0] = rs.getString("num_serie");
                fila[1] = rs.getString("placa_actual");
                fila[2] = rs.getString("marca");
                fila[3] = rs.getString("nombre_modelo");
                fila[4] = String.valueOf(rs.getInt("anio"));
                fila[5] = rs.getString("propietario");
                resultados.add(fila);
            }
        } catch (SQLException e) {
            System.err.println("Error en la consulta JOIN: " + e.getMessage());
        }
        return resultados;
    }
}