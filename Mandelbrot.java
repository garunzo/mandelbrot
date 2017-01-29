import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Mandelbrot {
    public static void main(String[] args) throws Exception {
        int iterations;

        PlottingParameters params = new PlottingParameters(args);
        if (!params.valid) return;

        int width = 1920*params.zoom, height = 1080*params.zoom, max = 1000;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        ColorMap cm = new ColorMap(max);

        for (int row = 0; row < height; row++) {  // Loop over every position on screen / image
            for (int col = 0; col < width; col++) {
                ComplexNumber C = params.translateScreenPosToComplexNumberPlane(col, row, width, height);
                ComplexNumber Z = new ComplexNumber(0.0, 0.0); // 0 + 0i

                /* Iterate while magnitude of Z < 2 (or Z^2 < 4; avoids slow square root operation) */
                for (iterations = 0; Z.magnitudeSquared() < 4 && iterations < max; iterations++) {
                    Z.squareComplex().addComplex(C); // f(Z) = Z^2 + C
                }
                // If iteration < max, then not in set, so set a color, otherwise black
                if (iterations < max) image.setRGB(col, row, cm.getColor(iterations));
                else image.setRGB(col, row, cm.black());
            }
        }
        ImageIO.write(image, "png", new File(params.pathName(args)));
    }
}
