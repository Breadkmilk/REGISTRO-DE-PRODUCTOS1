
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LuisYucra
 */
public class ProductoDAO {
   public List<Producto> listar() {
        List<Producto> lista = new ArrayList<>();

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM producto");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
                lista.add(p);
            }

        } catch (Exception e) { e.printStackTrace(); }

        return lista;
    }

    public void agregar(Producto p) {
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(
                     "INSERT INTO producto(nombre,precio) VALUES (?,?)")) {

            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.executeUpdate();

        } catch (Exception e) { e.printStackTrace(); }
    }

    public Producto obtener(int id) {
        Producto p = null;

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(
                     "SELECT * FROM producto WHERE id=?")) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                p = new Producto();
                p.setId(id);
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
            }

        } catch (Exception e) { e.printStackTrace(); }

        return p;
    }

    public void actualizar(Producto p) {
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(
                     "UPDATE producto SET nombre=?, precio=? WHERE id=?")) {

            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setInt(3, p.getId());
            ps.executeUpdate();

        } catch (Exception e) { e.printStackTrace(); }
    }

    public void eliminar(int id) {
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(
                     "DELETE FROM producto WHERE id=?")) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) { e.printStackTrace(); }
    } 
}
