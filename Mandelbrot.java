public class Mandelbrot {
    public static void main(String[] args) throws Exception {
        int iterations;
        PlottingParameters pp = new PlottingParameters(args, 1000);

        do {
            ComplexNumber C = pp.currentScreenPosToComplexNumber();
            ComplexNumber Z = new ComplexNumber(0.0, 0.0); // 0 + 0i

            /* Iterate while magnitude of Z < 2 (or Z^2 < 4; to avoid slow square root operation) */
            for (iterations = 0; Z.magnitudeSquared() < 4 && iterations < pp.max; iterations++) {

                Z.squareComplex().addComplex(C); // Z = Z^2 + C
            }
            pp.setColorCurrentScreenPos(iterations);
        } while (pp.nextScreenPos());

        pp.writeImageToFile();
    }
}