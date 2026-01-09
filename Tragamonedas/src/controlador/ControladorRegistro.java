package controlador;

import modelo.Modelo;
import vista.VistaRegistro;
import dao.DAO;
import vista.VistaInicio;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class ControladorRegistro implements ActionListener {
    private final Modelo modelo;
    private final VistaRegistro vista;
    private final DAO dao;
    private final VistaInicio vistaInicio;

    public ControladorRegistro(Modelo modelo, VistaRegistro vista, VistaInicio vistaInicio) {
        this.modelo = modelo;
        this.vista = vista;
        this.dao = new DAO();
        this.vistaInicio = vistaInicio;
        
        this.vista.btnregistrar.addActionListener(this);
        this.vista.btnback.addActionListener(this);
        this.vista.itemsalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                salir();
            }
        });
        this.vista.checkTC.addActionListener(this);
    }

    public void salir() {
        JOptionPane.showMessageDialog(vista, "Hasta luego!", "Saliendo...", JOptionPane.INFORMATION_MESSAGE);
        vista.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnregistrar) {
            String nombre = vista.campoNombre.getText();
            String apellido = vista.campoApellido.getText();
            String sexo = vista.combosexo.getSelectedItem().toString();
            int edad = (int) vista.spinneredad.getValue();
            String correo = vista.campoCorreo.getText();
            String telefono = vista.campoTelefono.getText();

            // Verifica que todos los campos estén llenos
            if (nombre.isEmpty() || apellido.isEmpty() || sexo.isEmpty() || correo.isEmpty() || telefono.isEmpty()) {
                SonidoAdvertencia();
                JOptionPane.showMessageDialog(null, "¡Debe completar todos los campos!", "AVISO", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!vista.checkTC.isSelected()) {
                SonidoError();
                JOptionPane.showMessageDialog(null, "¡Debe aceptar los términos y condiciones!", "Oh no!", JOptionPane.WARNING_MESSAGE);
                return;
            }

            modelo.setNombre(nombre);
            modelo.setApellido(apellido);
            modelo.setSexo(sexo);
            modelo.setEdad(edad);
            modelo.setCorreo(correo);
            modelo.setTelefono(telefono);

            if (dao.agregar(modelo)) {
                JOptionPane.showMessageDialog(null, "¡Registro guardado exitosamente!", "EXITO", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "¡Upps! Error al registrar cliente", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == vista.btnback) {
            vista.dispose();
            vistaInicio.setVisible(true);
        }
    }

    private void SonidoError() {
        try {
            URL error = getClass().getResource("/sonidos/error.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(error);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al reproducir la música: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void SonidoAdvertencia() {
        try {
            URL ad = getClass().getResource("/sonidos/E1.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(ad);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al reproducir la música: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
