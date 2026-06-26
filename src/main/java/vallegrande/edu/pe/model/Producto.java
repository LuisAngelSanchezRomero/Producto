package vallegrande.edu.pe.model;

public class Producto {

    private int id;
    private String nombre;
    private double precio;
    private String categoria;    // Para el JComboBox
    private String procedencia;  // Para el JRadioButton
    private boolean disponible;  // Para el JCheckBox

    public Producto(){}

    public Producto(int id, String nombre, double precio, String categoria, String procedencia, boolean disponible){
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.procedencia = procedencia;
        this.disponible = disponible;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public String getCategoria() { return categoria; }
    public String getProcedencia() { return procedencia; }
    public boolean isDisponible() { return disponible; }

    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public void setProcedencia(String procedencia) { this.procedencia = procedencia; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
}
