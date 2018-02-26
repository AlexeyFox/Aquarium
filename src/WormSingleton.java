import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class WormSingleton {

	 private static WormSingleton instance = null;
	 /* A private Constructor prevents any other
	 * class from instantiating.*/
	 private WormSingleton() { }
	 
	 /* Static 'instance' method */
	 public static WormSingleton getInstance( ){
		 if(instance == null)
			 instance = new WormSingleton();
		 return instance;
	 }
	 
	 public static boolean isFood(boolean food){ 
		 if (food) return true;
		 return false;
	 }
	 
	 public void paintComponent(Graphics g) {		    	
		 ///Draw worm///
		 Graphics2D g2 = (Graphics2D) g;
		 g2.setStroke(new BasicStroke(3));
		 g2.setColor(Color.red);
		 g2.drawArc(492, 512/2-5, 10, 10, 30, 210);	   
		 g2.drawArc(492, 512/2+5, 10, 10, 180, 270);	
		 g2.setStroke(new BasicStroke(1));
	 }
}
