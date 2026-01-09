package controlador;

import Carretes.Carrete1;
import Carretes.Carrete2;
import Carretes.Carrete3;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import vista.VistaInicio;
import vista.VistaUnete;
import vista.VistaTragamonedas;
import vista.VistaRegistro;
import modelo.Modelo;

public class ControladorTragamonedas implements ActionListener {
    private final Modelo modelo;
    private final VistaInicio vistaInicio;
    private final VistaUnete vistaUnete;
    private final VistaTragamonedas juego;
    private final VistaRegistro formulario;
    private Carrete1 carrete1;
    private Carrete2 carrete2;
    private Carrete3 carrete3;
    private int velocidad;
    protected Clip clip;
    protected ImageIcon iconogano; 
    protected ImageIcon iconoperdio;

    public ControladorTragamonedas(Modelo modelo, VistaInicio vistaInicio, VistaTragamonedas juego, VistaRegistro formulario, VistaUnete vistaUnete) {
        iconogano = new ImageIcon(getClass().getResource("/Imagenes/welcome.png"));
        iconoperdio = new ImageIcon(getClass().getResource("/Imagenes/lose.jpeg"));
        this.modelo = modelo;
        this.vistaInicio = vistaInicio;
        this.vistaUnete = vistaUnete;
        this.juego = juego;
        this.formulario = formulario;

        this.vistaInicio.itemsalir.addActionListener(this);
        this.vistaInicio.botonplay.addActionListener(this);
        this.vistaInicio.btnregistro.addActionListener(this);
        this.vistaInicio.btnunete.addActionListener(this);
        this.juego.btnC1.addActionListener(this);
        this.juego.btnC2.addActionListener(this);
        this.juego.btnC3.addActionListener(this);
        this.juego.cbmodo.addActionListener(this);
        this.juego.btnregreso.addActionListener(this);
        this.juego.btngirar.addActionListener(this);

        this.velocidad = 800;
        SonidoInicio();

        this.carrete1 = new Carrete1(velocidad, modelo);
        this.carrete2 = new Carrete2(velocidad, modelo);
        this.carrete3 = new Carrete3(velocidad, modelo);

        vistaInicio.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    vistaInicio.botonplay.doClick();
                }
            }
        });

        vistaInicio.setFocusable(true);
        vistaInicio.requestFocusInWindow();

        juego.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    juego.btnregreso.doClick();
                }
            }
        });
        juego.setFocusable(true);
        juego.requestFocusInWindow();
        vistaInicio.setVisible(true);
        juego.setVisible(false);
        formulario.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaInicio.itemsalir) {
            salir();
        } else if (e.getSource() == vistaInicio.btnregistro) {
            vistaInicio.setVisible(false);
            formulario.setVisible(true);
        } else if(e.getSource() == vistaInicio.btnunete){
           vistaUnete.setVisible(true);
           vistaInicio.setVisible(false);
        } else if (e.getSource() == vistaInicio.botonplay) {
            vistaInicio.setVisible(false);
            juego.setVisible(true);
            modelo.iniciarJuego();
        } else if (e.getSource() == juego.btnC1) {
            carrete1.stopCarrete();
            verificarWin();
        } else if (e.getSource() == juego.btnC2) {
            carrete2.stopCarrete();
            verificarWin();
        } else if (e.getSource() == juego.btnC3) {
            carrete3.stopCarrete();
            verificarWin();
        } else if (e.getSource() == juego.cbmodo) {
            velocidadCarretes();
        } else if (e.getSource() == juego.btnregreso) {
            regresar();
        } else if(e.getSource() == vistaUnete){
            regresar2();
        } else if (e.getSource() == juego.btngirar) {
            girarCarretes();
        }
    }

    private void girarCarretes() {
        sonidoCarretesgirando();
        if (!carrete1.isAlive()) {
            carrete1 = new Carrete1(velocidad, modelo);
            carrete1.start();
        }
        if (!carrete2.isAlive()) {
            carrete2 = new Carrete2(velocidad, modelo);
            carrete2.start();
        }
        if (!carrete3.isAlive()) {
            carrete3 = new Carrete3(velocidad, modelo);
            carrete3.start();
        }
    }

    private void verificarWin() {
        if (!carrete1.isRunning() && !carrete2.isRunning() && !carrete3.isRunning()) {
        if (clip != null && clip.isRunning()) { //Para detener el sonido cuando paren los 3 carretes.
            clip.stop();
            if (carrete1.getImagen() == carrete2.getImagen() && carrete1.getImagen() == carrete3.getImagen()) {
                sonidoWinner();
                JOptionPane.showMessageDialog(null, "¡Ganaste!", "Winner", JOptionPane.INFORMATION_MESSAGE, iconogano);
            } else {
                sonidoGAMEOVER();
                JOptionPane.showMessageDialog(null, "¡Perdiste!", "YOU LOSE", JOptionPane.INFORMATION_MESSAGE, iconoperdio);
            }
        }
        }
    }

    private void SonidoInicio() {
        try {
            URL urlaudio = getClass().getResource("/sonidos/InicialSong.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(urlaudio);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al reproducir la música: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sonidoCarretesgirando() {
        try {
            URL audio2 = getClass().getResource("/sonidos/tragamonedas.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audio2);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY); 
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al reproducir la música: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
     private void sonidoWinner() {
        try {
            URL yahoo = getClass().getResource("/sonidos/MarioWin.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(yahoo);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al reproducir la música: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sonidoGAMEOVER() {
        try {
            URL gameo = getClass().getResource("/sonidos/GameOver.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(gameo);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al reproducir la música: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void salir() {
        JOptionPane.showMessageDialog(vistaInicio, "¡Hasta luego!", "Saliendo...", JOptionPane.INFORMATION_MESSAGE);
        vistaInicio.dispose();
        System.exit(0);
    }

    private void velocidadCarretes() {
        String modoJuego = (String) juego.cbmodo.getSelectedItem();
        switch (modoJuego) {
            case "EASY": velocidad = 1000; break;
            case "NORMAL": velocidad = 50; break;
            case "VERY HARD": velocidad = 10; break;
        }
    }

    private void regresar() {
        juego.setVisible(false);
        vistaInicio.setVisible(true);
    }
    private void regresar2(){
      vistaUnete.setVisible(false);
      vistaInicio.setVisible(true);
    }
}
