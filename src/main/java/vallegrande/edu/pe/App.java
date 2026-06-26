package vallegrande.edu.pe;

import vallegrande.edu.pe.controller.ProductoController;
import vallegrande.edu.pe.view.ProductoView;
import javax.swing.SwingUtilities;

/**
 * Clase principal que inicia la aplicación de Productos.
 */
public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Unimos las capas pasando el controlador directo a la vista
            var controller = new ProductoController();
            var view = new ProductoView(controller);
            view.setVisible(true);
        });
    }
}

