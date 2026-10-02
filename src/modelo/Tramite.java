package modelo;
import java.sql.Date;

public class Tramite {
    private String folioTramite;
    private String tipoTramite;
    private String estadoTramite;
    private Date fechaInicio;
    private String numSerie;

    public Tramite(String folioTramite, String tipoTramite, String estadoTramite, Date fechaInicio, String numSerie) {
        this.folioTramite = folioTramite;
        this.tipoTramite = tipoTramite;
        this.estadoTramite = estadoTramite;
        this.fechaInicio = fechaInicio;
        this.numSerie = numSerie;
    }

    //getters
    public String getFolioTramite() {
        return folioTramite;
    }

    public String getTipoTramite() {
        return tipoTramite;
    }

    public String getEstadoTramite() {
        return estadoTramite;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public String getNumSerie() {
        return numSerie;
    }

    //setters

    public void setFolioTramite(String folioTramite) {
        this.folioTramite = folioTramite;
    }

    public void setTipoTramite(String tipoTramite) {
        this.tipoTramite = tipoTramite;
    }

    public void setEstadoTramite(String estadoTramite) {
        this.estadoTramite = estadoTramite;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

}

