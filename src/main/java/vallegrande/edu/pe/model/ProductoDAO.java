package vallegrande.edu.pe.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    public List<Producto> listar() {
        List<Producto> lista = new ArrayList<>();
        try {
            Connection con = ConexionBD.getConexion();
            String sql = "SELECT p.id, p.nombre, p.precio, c.nombre AS categoria, p.procedencia, p.disponible " +
                    "FROM producto p " +
                    "INNER JOIN categoria c ON p.categoria_id = c.id";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
                p.setCategoria(rs.getString("categoria"));
                p.setProcedencia(rs.getString("procedencia"));
                p.setDisponible(rs.getBoolean("disponible"));
                lista.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void insertar(Producto p) {
        try {
            Connection con = ConexionBD.getConexion();
            String sql = "INSERT INTO producto(nombre, precio, categoria_id, procedencia, disponible) " +
                    "VALUES (?, ?, (SELECT id FROM categoria WHERE nombre = ?), ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setString(3, p.getCategoria());
            ps.setString(4, p.getProcedencia());
            ps.setBoolean(5, p.isDisponible());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modificar(Producto p) {
        try {
            Connection con = ConexionBD.getConexion();
            String sql = "UPDATE producto SET nombre = ?, precio = ?, " +
                    "categoria_id = (SELECT id FROM categoria WHERE nombre = ?), " +
                    "procedencia = ?, disponible = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setString(3, p.getCategoria());
            ps.setString(4, p.getProcedencia());
            ps.setBoolean(5, p.isDisponible());
            ps.setInt(6, p.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminar(int id) {
        try {
            Connection con = ConexionBD.getConexion();
            String sql = "DELETE FROM producto WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
