public class PlottingParameters {

   public int zoom;       // amount of additional zooming
   public double scale;   // scaling range
   public double offsetX; // x translation
   public double offsetY; // y translation
   public boolean valid;
   public final int width;
   public final int height;
   public int col = 0;
   public int row = 0;


   public PlottingParameters(String[] args) {
      if (args.length == 4) {
         scale = Double.parseDouble(args[0]);
         offsetX = Double.parseDouble(args[1]);
         offsetY = Double.parseDouble(args[2]);
         zoom = Integer.parseInt(args[3]);
         width = 1920*zoom;
         height = 1080*zoom;

      } else {
         System.out.println("Usage: Mandelbrot <scale> <x-offset> <y-offset> <pixels>");
         System.out.println("Example: Mandelbrot 4.0 0.0 0.0 1");
         valid = false;
         width = 0; // being lazy
         height = 0;
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
}
