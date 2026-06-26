package vallegrande.edu.pe.view;

import vallegrande.edu.pe.controller.ProductoController; // Importación añadida

import javax.swing.*;
import java.awt.*;

public class InicioView extends JFrame {

    public InicioView() {
        setTitle("Sistema de Inventario");
        setSize(400, 280);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JLabel titulo = new JLabel("BIENVENIDO", JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        panel.add(titulo, BorderLayout.NORTH);

        JLabel descripcion = new JLabel(
                "<html><div style='text-align:center;'>"
                        + "Este sistema permite gestionar productos<br>"
                        + "utilizando el patrón MVC."
                        + "</div></html>",
                JLabel.CENTER
        );
        descripcion.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        JButton btnProductos = new JButton("Ir a Productos");
        btnProductos.setFocusPainted(false);

        // Modificado: Se inyecta el controlador requerido por ProductoView
        btnProductos.addActionListener(e -> {
            ProductoController controller = new ProductoController();
            new ProductoView(controller).setVisible(true);
            dispose();
        });

        JPanel centro = new JPanel(new GridLayout(2, 1, 10, 10));
        centro.setBackground(Color.WHITE);
        centro.add(descripcion);
        centro.add(btnProductos);

        panel.add(centro, BorderLayout.CENTER);
        add(panel);
    }
}
