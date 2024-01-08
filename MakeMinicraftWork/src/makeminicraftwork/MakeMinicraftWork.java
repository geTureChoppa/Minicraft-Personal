/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package makeminicraftwork;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 *
 * @author yvc2327
 */
public class MakeMinicraftWork {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //IM NOT SURE WHAT SCROLL DOES< 
        int scroll = 10;
        
        int WIDTH = 10;
        int HEIGHT = 15;
        
        
        
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

        // fill the initial buffer with pixel values
        for (int i = 0; i < WIDTH * HEIGHT; i++) {
            pixels[i] = 0xFF0000; // replace with your desired color
        }

        while (true) {
            // update the pixels based on scroll speed
            for (int y = 0; y < HEIGHT; y++) {
                for (int x = 0; x < WIDTH; x++) {
                    int offset = y * WIDTH + x;
                    int oldOffset = ((y - scroll) % HEIGHT) * WIDTH + x;
//                    pixels[offset] = pixels[oldOffset];
                }
            }

            // render the updated buffer to the screen
            Graphics g = image.getGraphics();
            g.drawImage(image, 0, 0, null);
            g.dispose();

            // pause for a short amount of time to control the frame rate
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}

