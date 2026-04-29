package model;

public class Miembro {

    private int idMiembro;
    private String nombre;
    private String apellido;
    private String rol;
    private String email;

    public Miembro(int idMiembro, String nombre, String apellido, String rol, String email) {
        this.idMiembro = idMiembro;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
        this.email = email;
    }

    public int getIdMiembro() { return idMiembro; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getRol() { return rol; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return nombre + " " + apellido + " (" + rol + ")";
    }
}
