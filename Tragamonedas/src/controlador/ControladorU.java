package controlador;
import modelo.ModeloU;
import vista.VistaUnete;
import dao.DAO2;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import vista.VistaInicio;

public class ControladorU implements ActionListener {
    
    private final ModeloU modelo2;
    private final VistaUnete vistaU;
    private final VistaInicio vistaInicio;
    private final DAO2 daos;

    public ControladorU(ModeloU modelo, VistaUnete vistaU, VistaInicio vistaInicio) {
        this.modelo2 = modelo;
        this.vistaU = vistaU;
        this.daos = new DAO2();
        this.vistaInicio = vistaInicio;
        this.vistaU.btnbackU.addActionListener(this);
        this.vistaU.btnRUnete.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaU.btnRUnete) {
            String nombre = vistaU.campoN.getText();
            String apellido = vistaU.campoA.getText();
            String sexo = vistaU.cbsexo.getSelectedItem().toString();
            int edad = (int) vistaU.spiedad.getValue();
            String correo = vistaU.campoR.getText();
            String RFC = vistaU.campoRFC.getText();
 
            if (nombre.isEmpty() || apellido.isEmpty() || sexo.isEmpty() || correo.isEmpty() || RFC.isEmpty()) {
                JOptionPane.showMessageDialog(null, "¡Debe completar todos los campos!", "AVISO", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            modelo2.setNombre(nombre);
            modelo2.setApellido(apellido);
            modelo2.setSexo(sexo);
            modelo2.setEdad(edad);
            modelo2.setCorreo(correo);
            modelo2.setRFC(RFC);

            if (daos.agregar(modelo2)) {
                JOptionPane.showMessageDialog(null, "¡Registro guardado exitosamente!", "EXITO", JOptionPane.INFORMATION_MESSAGE);
            } else {
                SonidoError();
                JOptionPane.showMessageDialog(null, "¡Upps! Error al registrar cliente", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == vistaU.btnbackU) {
            vistaU.dispose();
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
