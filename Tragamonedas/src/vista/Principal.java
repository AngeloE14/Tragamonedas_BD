package vista;

import controlador.ControladorTragamonedas;
import controlador.ControladorRegistro;
import modelo.Modelo;
import conexion.Conexion;
import controlador.ControladorU;
import modelo.ModeloU;

/**
 * Ventana Inicial del tragamonedas
 *
 * @author Angelo
 */
public class Principal extends Thread {

    public static void main(String[] args) {
        Modelo modelo = new Modelo();
        ModeloU modeloU = new ModeloU();
        Conexion conexion = new Conexion();
        VistaInicio inicio = new VistaInicio();
        VistaTragamonedas juego = new VistaTragamonedas();
        VistaRegistro registro = new VistaRegistro();
        VistaUnete vu = new VistaUnete();
        ControladorTragamonedas controlador = new ControladorTragamonedas(modelo, inicio, juego, registro, vu);
        ControladorRegistro controlRegistro = new ControladorRegistro(modelo,registro,inicio);
        ControladorU controlu = new ControladorU(modeloU,vu,inicio);
        
    }
    }
