package model;

public class Categoria {
    private int idCategoria;
    private String nombre;
    private String subcategoria;
    private String tipo;
    private String descripcion;

    public Categoria(int idCategoria, String nombre, String subcategoria, String tipo, String descripcion) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.subcategoria = subcategoria;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public int getIdCategoria() { return idCategoria; }
    public String getNombre() { return nombre; }
    public String getSubcategoria() { return subcategoria; }
    public String getTipo() { return tipo; }
    public String getDescripcion() { return descripcion; }

    @Override
    public String toString() {
        return nombre + " - " + subcategoria;
    }
}
