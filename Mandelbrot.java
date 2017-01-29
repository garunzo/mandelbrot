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

        for (int row = 0; row < height; row++) {  // Loop over every position on screen / image
            for (int col = 0; col < width; col++) {
                int iteration_counter = 0;
                ComplexNumber C = params.translateScreenPosToComplexNumberPlane(col, row, width, height);
                ComplexNumber Z = new ComplexNumber(0.0, 0.0); // 0 + 0i

                /* Iterate while magnitude of Z < 2 (or Z^2 < 4; avoids performing a slow square root operation) */
                while (Z.magnitudeSquared() < 4 && iteration_counter < max) {
                    Z.squareComplex().addComplex(C); // f(Z) = Z^2 + C
                    iteration_counter++;
                }
                // If iteration < max, then not in set, so set a color
                if (iteration_counter < max) image.setRGB(col, row, cm.getColor(iteration_counter));
                // Otherwise, point is in set, so set color to black
                else image.setRGB(col, row, cm.black());
            }
        }
        ImageIO.write(image, "png", new File(params.pathName(args)));
    }
}
