package modelo;

public class Vehiculo {
    private String numSerie;
    private String placaActual;
    private String numMotor;
    private int anio;
    private int idPropietario;
    private int idModelo;

    public Vehiculo() {
    }

    public Vehiculo(String numSerie, String placaActual, String numMotor, int anio, int idPropietario, int idModelo) {
        this.numSerie = numSerie;
        this.placaActual = placaActual;
        this.numMotor = numMotor;
        this.anio = anio;
        this.idPropietario = idPropietario;
        this.idModelo = idModelo;
    }

    //getters
    public String getNumSerie() {
        return numSerie;
    }

    public String getPlacaActual() {
        return placaActual;
    }

    public String getNumMotor() {
        return numMotor;
    }

    public int getAnio() {
        return anio;
    }

    public int getIdPropietario() {
        return idPropietario;
    }

    public int getIdModelo() {
        return idModelo;
    }

    //setters
    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public void setPlacaActual(String placaActual) {
        this.placaActual = placaActual;
    }

    public void setNumMotor(String numMotor) {
        this.numMotor = numMotor;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public void setIdPropietario(int idPropietario) {
        this.idPropietario = idPropietario;
    }

    public void setIdModelo(int idModelo) {
        this.idModelo = idModelo;
    }



}