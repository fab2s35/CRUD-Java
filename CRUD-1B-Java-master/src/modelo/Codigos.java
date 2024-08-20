package modelo;

import java.sql.*;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import vista.frmCodigos;


public class Codigos {
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
    
         
    
    //Metodo de mostrar para que salga en la tabla de la vista/interface
    //Recuerda siempre importar librerias
    public void Mostrar(JTable tabla) {
 
        //Creamos una variable de la clase de conexion
        Connection conexion = ClaseConexion.getConexion();
 
        //Definimos el modelo de la tabla
        DefaultTableModel modeloDeDatos = new DefaultTableModel();
 
        modeloDeDatos.setColumnIdentifiers(new Object[]{"UUID_codigo", "Nombre_Estudiante", "Tipo_codigo", "carnet_estudiante"});
 
        try {
 
            //Creamos un Statement
            Statement statement = conexion.createStatement();
 
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet 
            ResultSet rs = statement.executeQuery("SELECT * FROM tbCodigos");
 
            //Recorremos el ResultSet 
            while (rs.next()) {
 
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modeloDeDatos.addRow(new Object[]{rs.getString("UUID_codigo"),
 
                    rs.getString("Nombre_Estudiante"),
                    rs.getString("Tipo_codigo"),
                    rs.getInt("carnet_estudiante")});
 
            }
 
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modeloDeDatos);
 
        } catch (Exception e) {
 
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
 
        }
        
    }


    //Para eliminar
    public void Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
 
        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada
        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        //borramos 
        try {
            PreparedStatement deleteEstudiante = conexion.prepareStatement("delete from tbCodigos where UUID_codigo = ?");
            deleteEstudiante.setString(1, miId);
            deleteEstudiante.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }    
    
    
    //metodo para mostrar datos de fila
    public void cargarDatosTabla(frmCodigos vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.jtbCodigos.getSelectedRow();
 
        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUIDDeTb = vista.jtbCodigos.getValueAt(filaSeleccionada, 0).toString();
            String NombreDeTB = vista.jtbCodigos.getValueAt(filaSeleccionada, 1).toString();
            String TipoCodigo = vista.jtbCodigos.getValueAt(filaSeleccionada, 2).toString();
            String carnet_estudianteTB = vista.jtbCodigos.getValueAt(filaSeleccionada, 3).toString();
 
            // Establece los valores en los campos de texto
            vista.txtNombre.setText(NombreDeTB);
            vista.txtTipoCodigo.setText(TipoCodigo);
            vista.txtCarnet.setText(carnet_estudianteTB);
        }
    }
    
    
    
     //(Clase2) Metodo para actualizar
      public void Actualizar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();
            try { 
                //Ejecutamos la Query
                PreparedStatement updateUser = conexion.prepareStatement("update tbCodigos set nombre_estudiante = ?, tipo_codigo = ?, carnet_estudiante = ? where UUID_codigo = ?");
                updateUser.setString(1, getNombre_estudiante());
                updateUser.setString(2, getTipo_Codigo());
                updateUser.setInt(3, getCarnet_estudiante());
                updateUser.setString(4, miUUId);
                updateUser.executeUpdate();
 
            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
     
      }
      
      
      public void Buscar(JTable tabla, JTextField txtBuscar) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"UUID_Codigo", "Nombre_estudiante", "Tipo_codigo", "carnet_estudiante"});
        try {
            PreparedStatement deleteEstudiante = conexion.prepareStatement("SELECT * FROM tbCodigos WHERE nombre_estudiante LIKE ? || '%'");
            deleteEstudiante.setString(1, txtBuscar.getText());
            ResultSet rs = deleteEstudiante.executeQuery();
 
             while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modelo.addRow(new Object[]{rs.getString("UUID_codigo"), 
                    rs.getString("Nombre_Estudiante"), 
                    rs.getString("Tipo_codigo"), 
                    rs.getInt("carnet_estudiante")});
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modelo);
 
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo de buscar " + e);
        }
    }
}
