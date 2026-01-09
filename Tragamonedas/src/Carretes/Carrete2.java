package Carretes;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import modelo.Modelo;
import vista.VistaTragamonedas;

public class Carrete2 extends Thread {

    private int tiempo;
    private Modelo modelo;
    private volatile boolean running = true;
    private int numero;
    public Carrete2(int tiempo, Modelo modelo) {
        this.tiempo = tiempo;
        this.modelo = modelo;
    }

    public void stopCarrete() {
        running = false;
    }
     public boolean isRunning(){
    return running;    
    }

    @Override
    public void run() {
        while (running) {
            if (!modelo.estaEnJuego()) {
                modelo.detenerGiro();
                break;
            }

            numero = (int) (Math.random() * 7 + 1);
            String figura = "/Figuras/" + numero + ".jpeg";

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                        ImageIcon icono = new ImageIcon(getClass().getResource(figura));
                    VistaTragamonedas.C2.setIcon(icono);
                }
            });

            try {
                Thread.sleep(tiempo);
            } catch (InterruptedException e) {
                modelo.detenerJuego();
                break;
            }
        }
    }

     public int getImagen() {
    return numero;
    }
}
