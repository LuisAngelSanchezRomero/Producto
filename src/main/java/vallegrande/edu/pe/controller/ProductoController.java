package vallegrande.edu.pe.controller;

import vallegrande.edu.pe.model.Producto;
import vallegrande.edu.pe.model.ProductoDAO;
import java.util.List;

public class ProductoController {

    private final ProductoDAO dao = new ProductoDAO();

    public List<Producto> obtenerProductos(){
        return dao.listar();
    }

    public void agregarProducto(String nombre, double precio, String categoria, String procedencia, boolean disponible){
        Producto p = new Producto();
        p.setNombre(nombre);
        p.setPrecio(precio);
        p.setCategoria(categoria);
        p.setProcedencia(procedencia);
        p.setDisponible(disponible);
        dao.insertar(p);
    }

    public void modificarProducto(int id, String nombre, double precio, String categoria, String procedencia, boolean disponible){
        Producto p = new Producto();
        p.setId(id);
        p.setNombre(nombre);
        p.setPrecio(precio);
        p.setCategoria(categoria);
        p.setProcedencia(procedencia);
        p.setDisponible(disponible);
        dao.modificar(p);
    }

    public void eliminarProducto(int id){
        dao.eliminar(id);
    }
}
