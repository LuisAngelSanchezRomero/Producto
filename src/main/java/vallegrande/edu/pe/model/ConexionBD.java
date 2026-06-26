package vallegrande.edu.pe.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBD {

    private static final String ENDPOINT = "productos-db.cfniaacllq9n.us-east-1.rds.amazonaws.com";
    private static final String PORT = "3306";
    private static final String DATABASE = "productos";
    private static final String USER = "root";
    private static final String PASSWORD = "luis2025";

    public static Connection getConexion() {
        try {
            String url = "jdbc:mysql://" + ENDPOINT + ":" + PORT + "/" + DATABASE;

            Connection con = DriverManager.getConnection(url, USER, PASSWORD);
            System.out.println(" Conexión exitosa a AWS RDS - Productos");
            return con;
        } catch (Exception e) {
            System.out.println(" ERROR REAL DE CONEXIÓN A AWS RDS:");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
