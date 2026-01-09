package dao;
import modelo.ModeloU;
import conexion.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.ModeloU;

public class DAO2 implements DAOGeneral2<Integer, ModeloU> {
    private Conexion conexion;

    public DAO2() {
        conexion = new Conexion();
    }

    @Override
    public boolean agregar(ModeloU elemento) {
        if (conexion.abrir()) {
        String sql = "INSERT INTO empleados(nombre, apellido, correo, edad, sexo , RFC) VALUES(?, ?, ?, ? ,? ,?)";
        Connection enlace = conexion.obtener();
        try {
                PreparedStatement pstm = enlace.prepareStatement(sql);
                pstm.setString(1, elemento.getNombre());
                pstm.setString(2,elemento.getApellido());
                pstm.setString(3, elemento.getCorreo());
                pstm.setInt(4, elemento.getEdad());
                pstm.setString(5, elemento.getSexo());
                pstm.setString(6, elemento.getRFC());
                pstm.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            } finally {
                conexion.cerrar();
            }
        }
        return false;
    }
    @Override
public List<ModeloU> consultar() {
    List<ModeloU> lista = new ArrayList<>();
    if (conexion.abrir()) {
        String sql = "SELECT * FROM personas";
        Connection enlace = conexion.obtener();
        try {
            PreparedStatement pstm = enlace.prepareStatement(sql);
            ResultSet resultados = pstm.executeQuery();
            while (resultados.next()) {
                ModeloU modelo = new ModeloU();
                modelo.setId(resultados.getInt("id"));
                modelo.setNombre(resultados.getString("nombre"));
                modelo.setApellido(resultados.getString("apellido"));
                modelo.setSexo(resultados.getString("sexo"));
                modelo.setEdad(resultados.getInt("edad"));
                modelo.setCorreo(resultados.getString("correo"));
                modelo.setRFC(resultados.getString("RFC"));
                lista.add(modelo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conexion.cerrar();
        }
    }
    return lista;
}


    @Override
public boolean actualizar(Integer id, ModeloU nuevo) {
    if (conexion.abrir()) {
        String sql = "UPDATE personas SET nombre = ?, apellido = ?, sexo = ?, edad = ?, correo = ?, telefono = ? WHERE id = ?";
        Connection con = conexion.obtener();
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nuevo.getNombre());
            stmt.setString(2, nuevo.getApellido());
            stmt.setString(3, nuevo.getSexo());
            stmt.setInt(4, nuevo.getEdad());
            stmt.setString(5, nuevo.getCorreo());
            stmt.setString(6, nuevo.getRFC());
            stmt.setInt(7, id);
            int filasActualizadas = stmt.executeUpdate();
            if (filasActualizadas > 0) {
                con.commit(); 
                return true;
            } else {
                con.rollback(); 
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el registro en la base de datos", e);
        } finally {
            conexion.cerrar();
        }
    }
    return false;
}


    @Override
    public boolean eliminar(Integer id) {
        if (conexion.abrir()) {
            String sql = "DELETE FROM modelo WHERE id=?";
            Connection con = conexion.obtener();
            try {
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setInt(1, id);
                statement.executeUpdate();
                return true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                conexion.cerrar();
            }
        }
        return false;
    }
}
