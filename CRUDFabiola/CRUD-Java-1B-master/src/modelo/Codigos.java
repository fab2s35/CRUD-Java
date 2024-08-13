package modelo;

import java.awt.event.MouseListener;
import java.sql.*;
import java.util.UUID;

public class Codigos{
    //1-Parametros
    private String UUID_Codigo;
    private String Nombre_estudiante;
    private String Tipo_Codigo;
    private int carnet_estudiante;
    
    //2- Getters y Setters 
    public String getUUID_Codigo() {
        return UUID_Codigo;
    }

    public void setUUID_Codigo(String UUID_Codigo) {
        this.UUID_Codigo = UUID_Codigo;
    }

    public String getNombre_estudiante() {
        return Nombre_estudiante;
    }

    public void setNombre_estudiante(String Nombre_estudiante) {
        this.Nombre_estudiante = Nombre_estudiante;
    }

    public String getTipo_Codigo() {
        return Tipo_Codigo;
    }

    public void setTipo_Codigo(String Tipo_Codigo) {
        this.Tipo_Codigo = Tipo_Codigo;
    }

    public int getCarnet_estudiante() {
        return carnet_estudiante;
    }

    public void setCarnet_estudiante(int carnet_estudiante) {
        this.carnet_estudiante = carnet_estudiante;
    }
    
    //3- Funciones (Insert, Update, delete, select)
    public void Guardar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement addProducto = conexion.prepareStatement("INSERT INTO tbCodigos(UUID_Codigo, Nombre_estudiante, Tipo_Codigo, carnet_estudiante) VALUES (?, ?, ?, ?)");
            //Establecer valores de la consulta SQL
            addProducto.setString(1, UUID.randomUUID().toString());
            addProducto.setString(2, getNombre_estudiante());
            addProducto.setString(3, getTipo_Codigo());
            addProducto.setInt(4, getCarnet_estudiante());
 
            //Ejecutar la consulta
            addProducto.executeUpdate();
 
        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }
    
    
}
