import java.awt.Color;

public class ColorMap {

   int[] colorMap;
   int max;

   public ColorMap(int m) {
      max = m;
      colorMap = new int[max];
      for (int i = 0; i<max; i++) {
          colorMap[i] = Color.HSBtoRGB(i/256f, 1, i/(i+8f));
      }
   }
   public int getColor(int i) {
      return (i>= 0 && i<max) ? colorMap[i] : 0;
   }
   public int black() {
      return 0;
   }
}
