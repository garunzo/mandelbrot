import java.awt.Color;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class MandelbrotColor {

    public static void main(String[] args) throws Exception {
        int pixels = 1;
        double scale = 1.0;
        double offsetX = 1.25, offsetY = 0.0;

        if (args.length == 4) {
           scale = Double.parseDouble(args[0]);
           offsetX = Double.parseDouble(args[1]);
           offsetY = Double.parseDouble(args[2]);
           pixels = Integer.parseInt(args[3]);
        } else {
           System.out.println("Usage: MandelbrotColor <scale> <x-offset> <y-offset> <pixels>");
           System.out.println("Example: MandelbrotColor 4.0 0.0 0.0 1");
           return;
        }

        int width = 1920*pixels, height = 1080*pixels, max = 1000;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int black = 0;
        int[] colors = new int[max];
        for (int i = 0; i<max; i++) {
            colors[i] = Color.HSBtoRGB(i/256f, 1, i/(i+8f));
            //System.out.println(i + " : " + colors[i]);
        }

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {

             /* Translate screen position to complex number */
             double c_re = (col - width/2)*scale/width - offsetX;
             double c_im = (row - height/2)*scale/width + offsetY;
             ComplexNumber C = new ComplexNumber(c_re, c_im);

             int iteration = 0;
             ComplexNumber Z = new ComplexNumber(0.0, 0.0); // 0 + 0i

             /* Iterate while magnitude of Z is less than 2
              * or that magnitudeSquared of Z is less than 4
              * (avoids performing a slow square root operation)
              */
             while (Z.magnitudeSquared() < 4 && iteration < max) {
                /* Calculate Mandelbrot function : f(Z) = Z^2 + C */
                Z.squareComplex().addComplex(C); // Z^2 + C
                iteration++;
             }
             if (iteration < max) image.setRGB(col, row, colors[iteration]);
             else image.setRGB(col, row, black /*colors[5]*/);
            }
        }
        ImageIO.write(image, "png", new File("data/mandelbrot-scale-"+args[0]+"-offset-"+args[1]+".png"));
    }
}
