import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Mandelbrot {

   public static void main(String[] args) throws Exception {

      PlottingParameters params = new PlottingParameters(args);
      if (!params.valid) return;

      int width = 1920*params.zoom, height = 1080*params.zoom, max = 1000;
      BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

      ColorMap cm = new ColorMap(max);

      // Loop over every point on screen / image
      for (int row = 0; row < height; row++) {
         for (int col = 0; col < width; col++) {

          // Translate screen points to complex number plane
          double c_re = (col - width/2)*params.scale/width - params.offsetX;
          double c_im = (row - height/2)*params.scale/width + params.offsetY;
          ComplexNumber C = new ComplexNumber(c_re, c_im);

          int iteration = 0;
          ComplexNumber Z = new ComplexNumber(0.0, 0.0); // 0 + 0i

          /* Iterate while magnitude of Z is less than 2
           * or that magnitudeSquared of Z is less than 4
           * (avoids performing a slow square root operation)
           */
          while (Z.magnitudeSquared() < 4 && iteration < max) {
             Z.squareComplex().addComplex(C); // f(Z) = Z^2 + C
             iteration++;
          }
          // If iteration < max, then not in set, so set a color
          if (iteration < max) image.setRGB(col, row, cm.getColor(iteration));
          // Otherwise, point is in set, so set color to black
          else image.setRGB(col, row, cm.black());
         }
      }
      // Write the image to a file
      ImageIO.write(image, "png",
         new File("data/mandelbrot-S-"
                  + args[0] + "-X-" + args[1]
                  + "-Y-" + args[2] + "-P-" + params.zoom + ".png"));
   }
}
