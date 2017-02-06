# mandelbrot

Plots the mandelbrot set with a color scheme.

The primary objective is to make the mandelbrot.java class / code so easy to understand by hiding all other complexity that even a person unfamiliar with Java can get a basic understanding of the process.

Usage: Mandelbrot scale x-offset y-offset zoom

scale     - sort of zooms into a particular area of the graph.  
x-offset  - horizontal translation (along the real axis)  
y-offset  - vertical translation (along the imaginary axis)  
zoom      - increases the number of pixels so that image can be zoomed in more in a viewer.  
            this can greatly increase the amount of time to create an image.  

Borrowed initial code from http://codegists.com/snippet/java/mandelbrotcolorjava_rockdrigo_java

Modifications include:

Moved a lot of calculation complexity into other classes/methods.  
Added ComplexNumbers class to perform basic complex number operations.  
Added ability to relocate center of image and zoom.  
Added command line parameters to easily alter image.  
Changed file naming to include initial parameters.  
