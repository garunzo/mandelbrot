# mandelbrot

Plots the mandelbrot set with a color scheme.

Usage: Mandelbrot scale x-offset y-offset zoom

    scale     - sort of zooms into a particular area of the graph.
    x-offset  - horizontal translation (along the real axis)
    y-offset  - vertical translation (along the imaginary axis)
    zoom      - increases the number of pixels so that image can be zoomed in more in a viewer.
                this can greatly increase the amount of time to create an image.

Borrowed initial code from http://codegists.com/snippet/java/mandelbrotcolorjava_rockdrigo_java

Modifications include:
1. Moved a lot of calculation complexity into other methods.
1. Added ComplexNumbers class to perform basic complex number operations
1. Added ability to relocate center of image and zoom.
1. Added command line parameters to easily alter image
1. Changed file naming to include initial parameters.
