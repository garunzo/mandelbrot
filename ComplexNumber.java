public class ComplexNumber {
  double real;
  double imaginary;

  public ComplexNumber(double re, double im) {
     real = re;
     imaginary = im;
  }

  /* Calculate the magnitide of the complex number */
  public double magnitude() {
     return Math.pow(real*real + imaginary*imaginary, 0.5);
  }
  public double magnitudeSquared() {
     return real*real + imaginary*imaginary;
  }
  /* Square a complex number
   * (a + bi)^2 = a^2 + 2abi - b^2
   * real = a^2 - b^2
   * imaginary = 2ab
   */
  public ComplexNumber squareComplex() {
     double newReal = real*real - imaginary*imaginary;
     imaginary = 2 * real * imaginary;
     real = newReal;
     return this;
  }

  public ComplexNumber addComplex(ComplexNumber z) {
     real = real + z.real;
     imaginary = imaginary + z.imaginary;
     return this;
  }
}
