package conexion;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public final class Conexion {
    
private static final String bd ="tragamonedas";  
private static final String usuario ="root";   
private static final String password ="admin";   
private static final String host ="localhost";   
private static final String puerto ="3306";   
private static final String driver="com.mysql.cj.jdbc.Driver";
private final String url= "jdbc:mysql://localhost:3306/" + bd;

private Connection conexion;

    public Conexion() {    
    try{
    Class.forName(driver);
    conexion = DriverManager.getConnection(url, usuario, password);
    //JOptionPane.showMessageDialog(null, "Conexion establecida correctamente");
    } catch (SQLException | ClassNotFoundException e){
    JOptionPane.showMessageDialog(null, "Error al cerrar la conexion"+e.getMessage());     
    }  
    }
    public boolean abrir(){
    try{
    Class.forName("com.mysql.cj.jdbc.Driver");
    conexion= DriverManager.getConnection(url, usuario, password);
    return true;
    }catch(SQLException | ClassNotFoundException e){     
    return false;    
    }
    
}
    public Connection obtener(){
    return conexion;    
    }
    
    public boolean cerrar(){
    try{
    conexion.close();
    return true;    
    }catch (SQLException e){
    return false;    
    }
    }
}