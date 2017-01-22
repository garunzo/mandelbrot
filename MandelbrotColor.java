import java.awt.Color;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class MandelbrotColor {
    public static void main(String[] args) throws Exception {
        int pixels = 8;
        int width = 1920*pixels, height = 1080*pixels, max = 1000;
        double scale = 1.0;
        double offset = 1.25;
        if (args.length == 2) {
           scale = Double.parseDouble(args[0]);
           offsetX = Double.parseDouble(args[1]);
           offsetY = Double.parseDouble(args[2]);
        } else {
           System.out.println("Usage: Mandelbrot <scale> <x-offset> <y-offset>");
           System.out.println("Example: Mandelbrot 4.0 0.0 0.0");
        }
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int black = 0;
        int[] colors = new int[max];
        for (int i = 0; i<max; i++) {
            colors[i] = Color.HSBtoRGB(i/256f, 1, i/(i+8f));
            //System.out.println(i + " : " + colors[i]);
        }

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                double c_re = (col - width/2)*scale/width - offset;
                double c_im = (row - height/2)*scale/width;
                double x = 0, y = 0;
                double r2;
                int iteration = 0;
                while (x*x+y*y < 4 && iteration < max) {
                    double x_new = x*x-y*y+c_re;
                    y = 2*x*y+c_im;
                    x = x_new;
                    iteration++;
                }
                if (iteration < max) image.setRGB(col, row, colors[iteration]);
                else image.setRGB(col, row, black /*colors[5]*/);
            }
        }

        ImageIO.write(image, "png", new File("mandelbrot-scale-"+args[0]+"-offset-"+args[1]+".png"));
    }
}
