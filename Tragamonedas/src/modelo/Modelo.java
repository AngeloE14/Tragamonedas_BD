package modelo;

import java.util.ArrayList;
import java.util.List;

public class Modelo {
    protected boolean juegoActivo;
    protected boolean carretesGirando; 
    private int id;
    private String nombre;
    private String apellido;
    private String sexo;
    private int edad;
    private String correo;
    private String telefono;

    public Modelo(int id, String nombre, String apellido, String sexo, int edad, String correo, String telefono) {
        this.juegoActivo = false; 
        this.carretesGirando = false; 
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.edad = edad;
        this.correo = correo;
        this.telefono = telefono;
    }
    public Modelo(){
        
    }

    public synchronized void iniciarJuego() {
        this.juegoActivo = true; 
        this.carretesGirando = true; 
        notifyAll(); 
    }

    public synchronized void detenerJuego() {
        this.juegoActivo = false; 
        this.carretesGirando = false; 
    }

    public synchronized boolean estaEnJuego() {
        return juegoActivo; 
    }

    public synchronized boolean estanCarretesGirando() {
        return carretesGirando; 
    }

    public synchronized void esperarGiro() throws InterruptedException {
        if (!carretesGirando) {
            wait(); 
        }
    }

    public synchronized void detenerGiro() {
        this.carretesGirando = false; 
    }

    public synchronized void reiniciarGiro() {
        this.carretesGirando = true;
        notifyAll(); 
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    
}
