package controller;

import model.Movimiento;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MovimientoController {

    // Ver todos los movimientos del mes actual
    public List<Movimiento> getTodos() {
        List<Movimiento> lista = new ArrayList<>();
        int mes = LocalDate.now().getMonthValue();
        int anio = LocalDate.now().getYear();
        try {
            Connection con = Conexion.getConexion();
            String sql = "SELECT * FROM movimiento WHERE MONTH(fecha) = ? AND YEAR(fecha) = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, mes);
            ps.setInt(2, anio);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Movimiento(
                        rs.getInt("id_movimiento"),
                        rs.getInt("id_miembro"),
                        rs.getInt("id_categoria"),
                        rs.getDouble("importe"),
                        rs.getString("fecha"),
                        rs.getString("descripcion"),
                        rs.getInt("es_fijo")
                ));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return lista;
    }

    // Insertar movimiento con fecha automática
    public void insertar(int idMiembro, int idCategoria, double importe, String descripcion, int esFijo) {
        try {
            Connection con = Conexion.getConexion();
            String fecha = LocalDate.now().toString();
            String sql = "INSERT INTO movimiento (id_miembro, id_categoria, importe, fecha, descripcion, es_fijo) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idMiembro);
            ps.setInt(2, idCategoria);
            ps.setDouble(3, importe);
            ps.setString(4, fecha);
            ps.setString(5, descripcion);
            ps.setInt(6, esFijo);
            ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Eliminar movimiento
    public void eliminar(int idMovimiento) {
        try {
            Connection con = Conexion.getConexion();
            String sql = "DELETE FROM movimiento WHERE id_movimiento = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idMovimiento);
            ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Calcular total ingresos del mes
    public double getTotalIngresos() {
        double total = 0;
        int mes = LocalDate.now().getMonthValue();
        int anio = LocalDate.now().getYear();
        try {
            Connection con = Conexion.getConexion();
            String sql = "SELECT SUM(mo.importe) FROM movimiento mo JOIN categoria c ON mo.id_categoria = c.id_categoria WHERE c.tipo = 'ingreso' AND MONTH(mo.fecha) = ? AND YEAR(mo.fecha) = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, mes);
            ps.setInt(2, anio);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) total = rs.getDouble(1);
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return total;
    }
    // Obtener saldo acumulado total
    public double getSaldoAcumulado() {
        double saldo = 0;
        try {
            Connection con = Conexion.getConexion();
            String sql = "SELECT SUM(ahorros_mes) FROM saldo_acumulado";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) saldo = rs.getDouble(1);
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return saldo;
    }

    // Guardar ahorros del mes actual
    public void guardarAhorrosMes() {
        int mes = LocalDate.now().getMonthValue();
        int anio = LocalDate.now().getYear();
        double ahorrosMes = getTotalIngresos() - getTotalGastos();
        double saldoTotal = getSaldoAcumulado() + ahorrosMes;
        try {
            Connection con = Conexion.getConexion();
            // Si ya existe ese mes lo actualiza, si no lo crea
            String sqlCheck = "SELECT id FROM saldo_acumulado WHERE mes = ? AND anio = ?";
            PreparedStatement psCheck = con.prepareStatement(sqlCheck);
            psCheck.setInt(1, mes);
            psCheck.setInt(2, anio);
            ResultSet rs = psCheck.executeQuery();
            if (rs.next()) {
                String sqlUpdate = "UPDATE saldo_acumulado SET ahorros_mes = ?, saldo_total = ? WHERE mes = ? AND anio = ?";
                PreparedStatement psUpdate = con.prepareStatement(sqlUpdate);
                psUpdate.setDouble(1, ahorrosMes);
                psUpdate.setDouble(2, saldoTotal);
                psUpdate.setInt(3, mes);
                psUpdate.setInt(4, anio);
                psUpdate.executeUpdate();
            } else {
                String sqlInsert = "INSERT INTO saldo_acumulado (mes, anio, ahorros_mes, saldo_total) VALUES (?, ?, ?, ?)";
                PreparedStatement psInsert = con.prepareStatement(sqlInsert);
                psInsert.setInt(1, mes);
                psInsert.setInt(2, anio);
                psInsert.setDouble(3, ahorrosMes);
                psInsert.setDouble(4, saldoTotal);
                psInsert.executeUpdate();
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Calcular total gastos del mes
    public double getTotalGastos() {
        double total = 0;
        int mes = LocalDate.now().getMonthValue();
        int anio = LocalDate.now().getYear();
        try {
            Connection con = Conexion.getConexion();
            String sql = "SELECT SUM(mo.importe) FROM movimiento mo JOIN categoria c ON mo.id_categoria = c.id_categoria WHERE c.tipo = 'gasto' AND MONTH(mo.fecha) = ? AND YEAR(mo.fecha) = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, mes);
            ps.setInt(2, anio);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) total = rs.getDouble(1);
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return total;
    }
}