package vista;

import dao.TramiteDAO;
import dao.VehiculoDAO;
import java.sql.Date;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Tramite;
import modelo.Vehiculo;

public class VentanaPrincipal {

    private VehiculoDAO vehiculoDAO;
    private TramiteDAO tramiteDAO;

    public VentanaPrincipal() {
        vehiculoDAO = new VehiculoDAO();
        tramiteDAO = new TramiteDAO();
    }

    public void iniciar() {
        String[] opciones = {
            "1. Gestión de Vehículos (CRUD)",
            "2. Gestión de Trámites (CRUD)",
            "3. Consulta Multitabla (JOIN 3FN)",
            "4. Salir"
        };

        boolean salir = false;

        while (!salir) {
            String seleccion = (String) JOptionPane.showInputDialog(
                null,
                "Selecciona una opción para continuar:",
                "SISTEMA DE REEMPLACAMIENTO VEHICULAR - YUCATÁN",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
            );

            if (seleccion == null || seleccion.equals("4. Salir")) {
                salir = true;
                JOptionPane.showMessageDialog(null, "¡Gracias por usar el sistema!");
                break;
            }

            switch (seleccion) {
                case "1. Gestión de Vehículos (CRUD)":
                    menuVehiculos();
                    break;
                case "2. Gestión de Trámites (CRUD)":
                    menuTramites();
                    break;
                case "3. Consulta Multitabla (JOIN 3FN)":
                    mostrarConsultaJOIN();
                    break;
            }
        }
    }

    // --- MENÚ DE VEHÍCULOS ---
    private void menuVehiculos() {
        String[] subOpciones = {"1. Registrar", "2. Consultar", "3. Actualizar Placa", "4. Eliminar", "5. Volver"};
        String op = (String) JOptionPane.showInputDialog(null, "Gestión de Vehículos:", "VEHÍCULOS",
                JOptionPane.PLAIN_MESSAGE, null, subOpciones, subOpciones[0]);

        if (op == null || op.equals("5. Volver")) return;

        switch (op) {
            case "1. Registrar":
                String numSerie = JOptionPane.showInputDialog("Número de Serie (VIN):");
                if (numSerie == null || numSerie.trim().isEmpty()) break;
                
                String placa = JOptionPane.showInputDialog("Placa Actual:");
                String motor = JOptionPane.showInputDialog("Número de Motor:");
                int anio = Integer.parseInt(JOptionPane.showInputDialog("Año del Vehículo:"));
                int idProp = Integer.parseInt(JOptionPane.showInputDialog("ID del Propietario (ej. 1):"));
                int idMod = Integer.parseInt(JOptionPane.showInputDialog("ID del Modelo (ej. 1):"));

                Vehiculo v = new Vehiculo(numSerie, placa, motor, anio, idProp, idMod);
                if (vehiculoDAO.crear(v)) {
                    JOptionPane.showMessageDialog(null, "¡Vehículo registrado con éxito!");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrar vehículo.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            case "2. Consultar":
                List<Vehiculo> listaVeh = vehiculoDAO.listar();
                if (listaVeh.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hay vehículos registrados en el sistema.", "Sin datos", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                
                StringBuilder sbVeh = new StringBuilder("--- PADRÓN VEHICULAR ---\n\n");
                for (Vehiculo veh : listaVeh) {
                    sbVeh.append("VIN: ").append(veh.getNumSerie())
                         .append(" | Placa: ").append(veh.getPlacaActual())
                         .append(" | Año: ").append(veh.getAnio())
                         .append(" | Prop. ID: ").append(veh.getIdPropietario())
                         .append(" | Mod. ID: ").append(veh.getIdModelo()).append("\n");
                }
                JOptionPane.showMessageDialog(null, sbVeh.toString());
                break;

            case "3. Actualizar Placa":
                String vinAct = JOptionPane.showInputDialog("Ingrese el VIN del vehículo a actualizar:");
                if (vinAct == null || vinAct.trim().isEmpty()) break;
                
                String nuevaPlaca = JOptionPane.showInputDialog("Ingrese la nueva Placa:");
                int nuevoAnio = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el Año:"));

                Vehiculo vAct = new Vehiculo();
                vAct.setNumSerie(vinAct);
                vAct.setPlacaActual(nuevaPlaca);
                vAct.setAnio(nuevoAnio);

                if (vehiculoDAO.actualizar(vAct)) {
                    JOptionPane.showMessageDialog(null, "¡Vehículo actualizado correctamente!");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo actualizar el vehículo.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            case "4. Eliminar":
                String vinDel = JOptionPane.showInputDialog("Ingrese el VIN del vehículo a eliminar:");
                if (vinDel == null || vinDel.trim().isEmpty()) break;
                
                if (vehiculoDAO.eliminar(vinDel)) {
                    JOptionPane.showMessageDialog(null, "¡Vehículo eliminado!");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar el vehículo.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    }

    // --- MENÚ DE TRÁMITES ---
    private void menuTramites() {
        String[] subOpciones = {"1. Registrar Trámite", "2. Consultar Trámites", "3. Cambiar Estado", "4. Eliminar Trámite", "5. Volver"};
        String op = (String) JOptionPane.showInputDialog(null, "Gestión de Trámites:", "TRÁMITES",
                JOptionPane.PLAIN_MESSAGE, null, subOpciones, subOpciones[0]);

        if (op == null || op.equals("5. Volver")) return;

        switch (op) {
            case "1. Registrar Trámite":
                String folio = JOptionPane.showInputDialog("Folio del Trámite (ej. TR-001):");
                if (folio == null || folio.trim().isEmpty()) break;
                
                String tipo = JOptionPane.showInputDialog("Tipo de Trámite (ej. Reemplacamiento 2026):");
                String estado = JOptionPane.showInputDialog("Estado inicial (ej. Pendiente):");
                String vin = JOptionPane.showInputDialog("Número de Serie del Vehículo (VIN):");
                Date fecha = new Date(System.currentTimeMillis());

                Tramite t = new Tramite(folio, tipo, estado, fecha, vin);
                if (tramiteDAO.crear(t)) {
                    JOptionPane.showMessageDialog(null, "¡Trámite creado exitosamente!");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al crear trámite.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            case "2. Consultar Trámites":
                List<Tramite> listaTra = tramiteDAO.listar();
                if (listaTra.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hay trámites registrados en el sistema.", "Sin datos", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }

                StringBuilder sbTra = new StringBuilder("--- HISTORIAL DE TRÁMITES ---\n\n");
                for (Tramite tr : listaTra) {
                    sbTra.append("Folio: ").append(tr.getFolioTramite())
                         .append(" | Tipo: ").append(tr.getTipoTramite())
                         .append(" | Estado: ").append(tr.getEstadoTramite())
                         .append(" | VIN: ").append(tr.getNumSerie()).append("\n");
                }
                JOptionPane.showMessageDialog(null, sbTra.toString());
                break;

            case "3. Cambiar Estado":
                String folAct = JOptionPane.showInputDialog("Folio del trámite:");
                if (folAct == null || folAct.trim().isEmpty()) break;
                
                String nvoEst = JOptionPane.showInputDialog("Nuevo Estado (ej. Concluido):");

                if (tramiteDAO.actualizarEstado(folAct, nvoEst)) {
                    JOptionPane.showMessageDialog(null, "¡Estado del trámite actualizado!");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al actualizar trámite.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            case "4. Eliminar Trámite":
                String folDel = JOptionPane.showInputDialog("Folio del trámite a eliminar:");
                if (folDel == null || folDel.trim().isEmpty()) break;
                
                if (tramiteDAO.eliminar(folDel)) {
                    JOptionPane.showMessageDialog(null, "¡Trámite eliminado!");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar trámite.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    }

    // --- CONSULTA MULTITABLA (JOIN) ---
    private void mostrarConsultaJOIN() {
        List<String[]> resultados = vehiculoDAO.obtenerVehiculosDetallados();
        
        if (resultados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se encontraron vehículos registrados para realizar la consulta multitabla.", "Sin datos", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder("--- DETALLE DE VEHÍCULOS (JOIN 3FN) ---\n\n");
        for (String[] fila : resultados) {
            sb.append("VIN: ").append(fila[0])
              .append(" | Placa: ").append(fila[1])
              .append(" | Marca: ").append(fila[2])
              .append(" | Modelo: ").append(fila[3])
              .append(" | Año: ").append(fila[4])
              .append(" | Propietario: ").append(fila[5]).append("\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString());
    }
}
