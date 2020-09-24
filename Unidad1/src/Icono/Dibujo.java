/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Icono;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author APL
 */
public class Dibujo extends JPanel {
    // ventana
    private JFrame ventana;
    // contenedor
    private Container contenedor;
    
    // figura representada en hexadecimal
    private final int [] FIGURA = {
        0x00080,
        0x000c0,
        0x000e0,
        0x000f0,
        0xffff8,
        0xffffc,
        0xffffe,
        0xfffff,
        0xffffe,
        0xffffc,
        0xffff8,
        0x000f0,
        0x000e0,
        0x000c0,
        0x00080
    };
    
    // máscara
    private final int MASCARA = 0x80000;
    
    // ancho en bits
    private final int ANCHO_BITS = 20;
    
    // coordenadas
    private int coordenada_x, coordenada_y;
    
    // declarar hilo de ejecución
    private Thread hilo;

    public Dibujo() {
        // inicializar la ventana
        ventana = new JFrame("Dibujando icono");
        // definir tamaño a ventana
        ventana.setSize(800, 600);
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        
        contenedor = ventana.getContentPane();
        contenedor.setSize(800, 600);
        // agregar la ventana en el contenedor
        contenedor.add(this, BorderLayout.CENTER);
        
        // definir el hilo
        this.hilo = new Thread();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        
        // iterador de la figura
        for (int i = 0; i < this.FIGURA.length; i++) {
            // iterador para el ancho en bits de la figura
            for (int j = 0; j < this.ANCHO_BITS; j++) {
                int temp = this.FIGURA[i] & (this.MASCARA  >> j);
                
                if (temp != 0) {
                    graphics.drawLine(
                            this.coordenada_y + j,
                            this.coordenada_x + i,
                            this.coordenada_y + j,
                            this.coordenada_x + i
                    );
                }
            }
        }
    }
    
    public void dibujar() {
        this.coordenada_x = (int)(Math.random()*500);
        this.coordenada_y = (int)(Math.random()*500);
        
        for (;;) {
            try {
                this.coordenada_x = this.coordenada_x - 10;
                this.hilo.sleep(1000); // un segundo
                paint(getGraphics());
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
