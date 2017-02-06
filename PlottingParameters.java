import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class PlottingParameters {

   public int zoom;       // amount of additional zooming
   public double scale;   // scaling range
   public double offsetX; // x translation
   public double offsetY; // y translation
   public boolean valid;
   public int width;
   public int height;
   public int col = 0;
   public int row = 0;
   public int max = 1000;
   public ColorMap cm;
   public BufferedImage image;
   public String[] orig_args;



   public PlottingParameters(String[] args, int m) {
      if (args.length == 4) {
          try {
              scale = Double.parseDouble(args[0]);
              offsetX = Double.parseDouble(args[1]);
              offsetY = Double.parseDouble(args[2]);
              zoom = Integer.parseInt(args[3]);
              width = 1920 * zoom;
              height = 1080 * zoom;
              cm = new ColorMap(max);
              image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
              max = m;
              orig_args = args;
          } catch (NumberFormatException e) {
              System.out.println("Scale and offsets must be floating point.");
              System.out.println("Zoom must be integer.");
              System.exit(1);
          }

      } else {
         System.out.println("Usage: Mandelbrot <scale> <x-offset> <y-offset> <zoom>");
         System.out.println("Example: Mandelbrot 4.0 0.0 0.0 1");
         valid = false;
         width = 0; // being lazy
         height = 0;
         System.exit(1);
      }
      valid = true;
   }

   public ComplexNumber translateScreenPosToComplexNumberPlane( int col, int row, int width, int height) {
      double c_re = (col - width/2)*this.scale/width - this.offsetX;
      double c_im = (row - height/2)*this.scale/width + this.offsetY;
      return new ComplexNumber(c_re, c_im);
   }

    public ComplexNumber currentScreenPosToComplexNumber() {
        double c_re = (col - width/2)*scale/width - offsetX;
        double c_im = (row - height/2)*scale/width + offsetY;
        return new ComplexNumber(c_re, c_im);
    }

   public String pathName(String[] args) {
      return "images/mandelbrot-S-"
              + args[0] + "-X-" + args[1]
              + "-Y-" + args[2] + "-P-" + this.zoom + ".png";
   }

   public boolean nextScreenPos() {
       col++;
       if (col == width) {
           row++;
           col = 0;
       }
       return (row < height);
   }

   public void setColorCurrentScreenPos(int iterations) {
       image.setRGB(col, row, (iterations < max) ? cm.getColor(iterations) : cm.black());
   }

   public void writeImageToFile() throws Exception {
       ImageIO.write(image, "png", new File(pathName(orig_args)));
   }

}
