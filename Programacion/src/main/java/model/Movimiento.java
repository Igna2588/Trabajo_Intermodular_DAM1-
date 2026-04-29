package model;

public class Movimiento {

    private int idMovimiento;
    private int idMiembro;
    private int idCategoria;
    private double importe;
    private String fecha;
    private String descripcion;
    private int esFijo;

    public Movimiento(int idMovimiento, int idMiembro, int idCategoria, double importe, String fecha, String descripcion, int esFijo) {
        this.idMovimiento = idMovimiento;
        this.idMiembro = idMiembro;
        this.idCategoria = idCategoria;
        this.importe = importe;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.esFijo = esFijo;
    }

    public int getIdMovimiento() {
        return idMovimiento; }
    public int getIdMiembro() {
        return idMiembro; }
    public int getIdCategoria() {
        return idCategoria; }
    public double getImporte() {
        return importe; }
    public String getFecha() { return fecha; }
    public String getDescripcion() { return descripcion; }
    public int getEsFijo() { return esFijo; }

    @Override
    public String toString() {
        String fijo = esFijo == 1 ? " " : "";
        return fecha + " - " + descripcion + " - " + importe + "€" + fijo;
    }
}