import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Mandelbrot {
    public static void main(String[] args) throws Exception {
        int iterations;

        PlottingParameters pp = new PlottingParameters(args);
        if (!pp.valid) return;

        int  max = 1000;
        BufferedImage image = new BufferedImage(pp.width, pp.height, BufferedImage.TYPE_INT_RGB);

        ColorMap cm = new ColorMap(max);

        do {
                ComplexNumber C = pp.currentScreenPosToComplexNumber();
                ComplexNumber Z = new ComplexNumber(0.0, 0.0); // 0 + 0i

                /* Iterate while magnitude of Z < 2 (or Z^2 < 4; avoids slow square root operation) */
                for (iterations = 0; Z.magnitudeSquared() < 4 && iterations < max; iterations++) {
                    Z.squareComplex().addComplex(C); // f(Z) = Z^2 + C
                }
                image.setRGB(pp.col, pp.row, (iterations < max) ? cm.getColor(iterations) : cm.black());

        } while (pp.nextScreenPos());

        ImageIO.write(image, "png", new File(pp.pathName(args)));
    }
}
