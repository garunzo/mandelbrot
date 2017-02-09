public class Mandelbrot {

    public static int iterationsUntilNotInMandelbrotSet(ComplexNumber C, int max) {
        int iterations;
        ComplexNumber Z = new ComplexNumber(0.0, 0.0); // Start Z at 0 + 0i
        /* Iterate while magnitude of Z < 2 (or Z^2 < 4; to avoid slow square root operation) */
        for (iterations = 0; Z.magnitudeSquared() < 4 && iterations < max; iterations++) {
            Z.squareComplex().addComplex(C); // Z = Z^2 + C
        }
        return iterations; // if iterations hit max, then C is considered in mandelbrot set
    }

    public static void main(String[] args) throws Exception {
        PlottingParameters pp = new PlottingParameters(args, 1000);
        do {
            ComplexNumber C = pp.currentScreenPosToComplexNumber(); // Pick a complex number, C
            pp.setColorCurrentScreenPos( iterationsUntilNotInMandelbrotSet(C, pp.max) );
        } while (pp.nextScreenPos());
        pp.writeImageToFile();
    }
}