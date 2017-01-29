public class PlottingParameters {

   public int zoom;       // amount of additional zooming
   public double scale;   // scaling range
   public double offsetX; // x translation
   public double offsetY; // y translation
   public boolean valid;

   public PlottingParameters(String[] args) {
      if (args.length == 4) {
         scale = Double.parseDouble(args[0]);
         offsetX = Double.parseDouble(args[1]);
         offsetY = Double.parseDouble(args[2]);
         zoom = Integer.parseInt(args[3]);
      } else {
         System.out.println("Usage: Mandelbrot <scale> <x-offset> <y-offset> <pixels>");
         System.out.println("Example: Mandelbrot 4.0 0.0 0.0 1");
         valid = false;
      }
      valid = true;
   }
}
