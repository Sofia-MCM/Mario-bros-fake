package mis_juegos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/*
 * la libreria awt encuentra los eventos los cuales manejamos para el uso del teclado.
 * la libreria swing la utilizamos para el uso de la clase JPanel y as como sus componentes.
 */

// Se esta heredando de la clase JPanel para hacer uso de sus componentes como botones, lista desplegable, casillas de verificación, cajas
// dibujar en pantalla como figuras 2D
public class MarioBros extends JPanel { 
private Image personaje;     // Imagen actual 
private Image quieto;       // Imagen cuando está parado 
private Image saltando;    // Imagen cuando salta 
private Image correrrev; 
private Image correr; 
private Image abajo; 
private Image fondo;      // Imagen fondo / mapa del juego      
private int x = 400, y = 430; // estas coordenadas son la posición inicial de la imagen en la ventana

		public MarioBros() { 
		// Cargar imágenes 
			saltando = new ImageIcon("imagenes_mario/saltando.gif").getImage(); // ruta de tu imagen 
			quieto = new ImageIcon("imagenes_mario/stop.gif").getImage(); // ruta de tu imagen 
			fondo = new ImageIcon("mapas/fondo.gif").getImage();  // fondo 
			correr = new ImageIcon("imagenes_mario/correr2.gif").getImage(); //corriendo 
	        abajo = new ImageIcon("imagenes_mario/abajo.gif").getImage(); 
	        correrrev = new ImageIcon("imagenes_mario/correr2.gif").getImage(); 

        personaje = quieto; // Empieza en quieto

        // Escuchar las teclas
        setFocusable(true); // Con este metodo estamos agregando al panel que va a recibir acciones por el teclado 
        // se inicializa en true activar el teclado.
        addKeyListener(new KeyAdapter() {
        	
            @Override
            //
            public void keyPressed(KeyEvent e) {
            	if (e.getKeyCode() == KeyEvent.VK_RIGHT) { 
                    personaje = correr; // cambia la imagen 
                    x += 10; 
                    repaint();//se vuelve a pintar la imagen en el panel 
                } 
                 
                 if (e.getKeyCode() == KeyEvent.VK_LEFT) { 
                    personaje = correrrev; // cambia la imagen 
                    x -= 10; 
                    repaint();//se vuelve a pintar la imagen en el panel 
                } 
                  
                 if (e.getKeyCode() == KeyEvent.VK_UP) { 
                     personaje = saltando; // cambia la imagen 
                     y-= 5; 
                     repaint();//se vuelve a pintar la imagen en el panel 
                 } 
                  
                 if (e.getKeyCode() == KeyEvent.VK_DOWN) { 
                     personaje = abajo; // cambia la imagen 
                     y = 5; 
                     repaint();//se vuelve a pintar la imagen en el panel 
                 } 
            }

            
            @Override
            public void keyReleased(KeyEvent e) {
                // Cuando se suelta la tecla, detener movimiento
                int tecla = e.getKeyCode();
                if (tecla == KeyEvent.VK_LEFT || tecla == KeyEvent.VK_RIGHT) {
                	x = 400;
                	personaje = quieto; // vuelve a la original
                    repaint();
                }
                if (tecla == KeyEvent.VK_UP || tecla == KeyEvent.VK_DOWN) {
                	y = 430;
                	personaje = quieto; // vuelve a la original
                    repaint();
                }
            }
        });
    }
    
  

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        g.drawImage(personaje, x, y, this);
    }

    public static void main(String[] args) {
        JFrame ventana = new JFrame("Juego de Mario Bros FAKE");
        MarioBros juego = new MarioBros();
        ventana.add(juego);
        ventana.setSize(1000, 700);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null); //CENTRAR LA VENTANA
        ventana.setVisible(true);
    }
}
