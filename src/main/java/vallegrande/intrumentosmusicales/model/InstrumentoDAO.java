package vallegrande.intrumentosmusicales.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InstrumentoDAO {

    public boolean registrar(Instrumento inst) {
        String sql = "INSERT INTO instrumentos (nombre, marca, tipo_instrumento, modelo, precio, stock, proveedor) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, inst.getNombre());
            ps.setString(2, inst.getMarca());
            ps.setString(3, inst.getTipoInstrumento());
            ps.setString(4, inst.getModelo());
            ps.setDouble(5, inst.getPrecio());
            ps.setInt(6, inst.getStock());
            ps.setString(7, inst.getProveedor());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al registrar: " + e.getMessage());
            return false;
        }
    }

    public List<Instrumento> listar() {
        List<Instrumento> lista = new ArrayList<>();
        String sql = "SELECT * FROM instrumentos";
        try (Connection con = Conexion.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Instrumento inst = new Instrumento(
                        rs.getInt("id_producto"),
                        rs.getString("nombre"),
                        rs.getString("marca"),
                        rs.getString("tipo_instrumento"),
                        rs.getString("modelo"),
                        rs.getDouble("precio"),
                        rs.getInt("stock"),
                        rs.getString("proveedor")
                );
                lista.add(inst);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }

    public boolean actualizar(Instrumento inst) {
        String sql = "UPDATE instrumentos SET nombre=?, marca=?, tipo_instrumento=?, modelo=?, precio=?, stock=?, proveedor=? WHERE id_producto=?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, inst.getNombre());
            ps.setString(2, inst.getMarca());
            ps.setString(3, inst.getTipoInstrumento());
            ps.setString(4, inst.getModelo());
            ps.setDouble(5, inst.getPrecio());
            ps.setInt(6, inst.getStock());
            ps.setString(7, inst.getProveedor());
            ps.setInt(8, inst.getIdProducto());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int idProducto) {
        String sql = "DELETE FROM instrumentos WHERE id_producto=?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idProducto);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }
}