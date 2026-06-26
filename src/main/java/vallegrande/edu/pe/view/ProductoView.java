package vallegrande.edu.pe.view;

import vallegrande.edu.pe.controller.ProductoController;
import vallegrande.edu.pe.model.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ProductoView extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;
    private ProductoController controller;

    private JComboBox<String> cbCategoria;
    private JRadioButton rbImportado;
    private JRadioButton rbNacional;
    private ButtonGroup grupoProcedencia;
    private JCheckBox chkDisponible;

    public ProductoView(ProductoController controller) {
        this.controller = controller;

        setTitle("Mantenimiento de Productos - Valle Grande");
        setSize(850, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Titulo (JLabel)
        JLabel titulo = new JLabel("GESTIÓN Y LISTADO DE PRODUCTOS", JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setForeground(new Color(0, 120, 215));
        panelPrincipal.add(titulo, BorderLayout.NORTH);

        // FORMULARIO IZQUIERDO (Organización de componentes obligatorios)
        JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 5, 10));
        panelFormulario.setBackground(Color.WHITE);
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Producto"));

        JTextField txtNombre = new JTextField(10);
        JTextField txtPrecio = new JTextField(6);

        // Inicializar JComboBox obligatorio
        cbCategoria = new JComboBox<>(new String[]{"General", "Alimentos", "Electrónica"});

        // Inicializar JRadioButton obligatorio y agruparlos
        rbImportado = new JRadioButton("Importado", true);
        rbNacional = new JRadioButton("Nacional");
        rbImportado.setBackground(Color.WHITE);
        rbNacional.setBackground(Color.WHITE);
        grupoProcedencia = new ButtonGroup();
        grupoProcedencia.add(rbImportado);
        grupoProcedencia.add(rbNacional);

        JPanel panelRadio = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelRadio.setBackground(Color.WHITE);
        panelRadio.add(rbImportado);
        panelRadio.add(rbNacional);

        // Inicializar JCheckBox obligatorio
        chkDisponible = new JCheckBox("Disponible en Stock");
        chkDisponible.setBackground(Color.WHITE);

        // Añadir elementos al contenedor izquierdo
        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(txtNombre);
        panelFormulario.add(new JLabel("Precio:"));
        panelFormulario.add(txtPrecio);
        panelFormulario.add(new JLabel("Categoría:"));
        panelFormulario.add(cbCategoria);
        panelFormulario.add(new JLabel("Procedencia:"));
        panelFormulario.add(panelRadio);
        panelFormulario.add(new JLabel("Estado:"));
        panelFormulario.add(chkDisponible);

        panelPrincipal.add(panelFormulario, BorderLayout.WEST);

        // TABLA DE PRODUCTOS (Centro)
        modelo = new DefaultTableModel(
                new Object[]{"ID", "Nombre", "Precio", "Categoría", "Procedencia", "Disponible"}, 0
        );

        tabla = new JTable(modelo);
        tabla.setRowHeight(22);
        tabla.setFillsViewportHeight(true);

        // Encabezado azul institucional
        tabla.getTableHeader().setDefaultRenderer(new javax.swing.table.DefaultTableCellRenderer() {
            {
                setOpaque(true);
                setBackground(new Color(0, 120, 215));
                setForeground(Color.WHITE);
                setHorizontalAlignment(JLabel.CENTER);
            }
        });

        JScrollPane scroll = new JScrollPane(tabla);
        panelPrincipal.add(scroll, BorderLayout.CENTER);

        // BOTONERA INFERIOR (JButton)
        JButton btnAgregar = new JButton("Agregar");
        JButton btnModificar = new JButton("Modificar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnVolver = new JButton("Volver");

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        panelBotones.setBackground(Color.WHITE);
        panelBotones.add(btnAgregar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnVolver);

        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        add(panelPrincipal);

        // ACCIÓN: AGREGAR PRODUCTO
        btnAgregar.addActionListener(e -> {
            try {
                String nombre = txtNombre.getText();
                double precio = Double.parseDouble(txtPrecio.getText());
                String categoria = cbCategoria.getSelectedItem().toString();
                String procedencia = rbImportado.isSelected() ? "Importado" : "Nacional";
                boolean disponible = chkDisponible.isSelected();

                controller.agregarProducto(nombre, precio, categoria, procedencia, disponible);

                modelo.setRowCount(0);
                cargarDatos();

                // Limpiar campos
                txtNombre.setText("");
                txtPrecio.setText("");
                cbCategoria.setSelectedIndex(0);
                rbImportado.setSelected(true);
                chkDisponible.setSelected(false);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Ingrese datos numéricos válidos en el precio.");
            }
        });

        // ACCIÓN: MODIFICAR PRODUCTO
        btnModificar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();

            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila para modificar");
                return;
            }

            try {
                int id = (int) modelo.getValueAt(fila, 0);
                String nombre = txtNombre.getText();
                double precio = Double.parseDouble(txtPrecio.getText());
                String categoria = cbCategoria.getSelectedItem().toString();
                String procedencia = rbImportado.isSelected() ? "Importado" : "Nacional";
                boolean disponible = chkDisponible.isSelected();

                controller.modificarProducto(id, nombre, precio, categoria, procedencia, disponible);

                modelo.setRowCount(0);
                cargarDatos();

                txtNombre.setText("");
                txtPrecio.setText("");
                cbCategoria.setSelectedIndex(0);
                rbImportado.setSelected(true);
                chkDisponible.setSelected(false);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Ingrese datos válidos para modificar");
            }
        });

        // ACCIÓN: ELIMINAR PRODUCTO
        btnEliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();

            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Seleccione una fila");
                return;
            }

            int id = (int) modelo.getValueAt(fila, 0);

            controller.eliminarProducto(id);

            modelo.setRowCount(0);
            cargarDatos();

            txtNombre.setText("");
            txtPrecio.setText("");
            cbCategoria.setSelectedIndex(0);
            rbImportado.setSelected(true);
            chkDisponible.setSelected(false);
        });

        // Evento al hacer clic en una fila: Auto-completar todos los componentes visuales
        tabla.getSelectionModel().addListSelectionListener(e -> {
            int fila = tabla.getSelectedRow();
            if (fila != -1) {
                txtNombre.setText(modelo.getValueAt(fila, 1).toString());
                txtPrecio.setText(modelo.getValueAt(fila, 2).toString());
                cbCategoria.setSelectedItem(modelo.getValueAt(fila, 3).toString());

                String proc = modelo.getValueAt(fila, 4).toString();
                if (proc.equals("Importado")) {
                    rbImportado.setSelected(true);
                } else {
                    rbNacional.setSelected(true);
                }

                String disp = modelo.getValueAt(fila, 5).toString();
                chkDisponible.setSelected(disp.equals("Sí"));
            }
        });

        // Botón Volver
        btnVolver.addActionListener(e -> {
            new InicioView().setVisible(true);
            dispose();
        });

        cargarDatos();
    }

    private void cargarDatos() {
        List<Producto> lista = controller.obtenerProductos();

        for (Producto p : lista) {
            modelo.addRow(new Object[]{
                    p.getId(),
                    p.getNombre(),
                    p.getPrecio(),
                    p.getCategoria(),
                    p.getProcedencia(),
                    p.isDisponible() ? "Sí" : "No"
            });
        }
    }
}
