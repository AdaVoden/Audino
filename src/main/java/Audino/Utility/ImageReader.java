import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Math;

public class ImageReader {
    
    public static void getColor(BufferedImage image) {
        
        /* 
         *  Getting pixel color by position x and y for each pixel 
         *  and taking a sum of all values read.
         */   
        Color black = new Color(0,0,0);
        Color white = new Color(255,255,255);
    	
    	
    	ArrayList<Color> colorList = new ArrayList<Color>();
        ArrayList<Integer> colorFreq = new ArrayList<Integer>();

        colorList.add(black);
        colorFreq.add(1);
        colorList.add(white);
        colorFreq.add(1);
        
        /* 
         *  Iterate over all pixels. 
         *  This loop populates colorList and colorFreq.
         */
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                
                Color pixel = new Color(image.getRGB(x, y));
                
                /*
                 *  Iterate over all color currently stored in colorList
                 */
                for (int i = 0; i <= colorList.size() - 1; i++) {
                    
                    // Check for color similarity.
                    if (Math.abs(pixel.getRed() - colorList.get(i).getRed()) < 40 &&
                    	Math.abs(pixel.getGreen() - colorList.get(i).getGreen()) < 40 &&
                    	Math.abs(pixel.getBlue() - colorList.get(i).getBlue()) < 40 ) {
                    	
                    	// Increment similar color in colorFreq.
                    	int value = colorFreq.get(i); 
                    	value += 1;
                    	colorFreq.remove(i);
                    	colorFreq.add(i, value);
                    	                  	                   	
                    	i = colorList.size();  // exit loop.           	               
                    }
                    // If there's no match by the end of the loop, add a new color.
                    else if (i == colorList.size() - 1) {
                    	colorList.add(pixel);
                    	colorFreq.add(0);
                    }                                 	
                }
            }                
        }
        
        
        /*
         *  Remove any colors from the lists if they do occur in >= 1000 pixels.
         */
        for (int i = 0; i < colorFreq.size(); i++) {
        	if (colorFreq.get(i) <= 1000) {
        		colorFreq.remove(i);
        		colorList.remove(i);
        		
        		i -= 1;
        	}
        }
        
                
        /*
         *  Print out the values of colorList and associated frequencies.
         */
        for (int i = 0; i < colorList.size(); i++) {
        	System.out.println("Color: "     + colorList.get(i));
        	System.out.println("Frequency: " + colorFreq.get(i));
        	System.out.println();
        }
    }
    
    public static void main(String args[]) throws IOException{
    
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter a directory path to an image file: ");
        String filename  = keyboard.next();
    
        File file = new File(filename);
        BufferedImage image = ImageIO.read(file);
        
        getColor(image);
        
        keyboard.close();
    }
    
    
}
