
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Zostera extends Immobile{

	AquaPanel panel;
	private int size;
	private Color colorr;
	private int x,y;
	
	/////////////////////////////////////////////////////////Constractor/////////////////////////////////////
	Zostera(String name,Color colorr, int size, int x, int y, AquaPanel panel){
		this.name = name;
		this.panel = panel;
		this.size = size;
		this.x = x;
		this.y = y;
		this.colorr = colorr;
	}
	//////////////////////////////////////////////////////Get Fuctions////////////////////////////////////
	public int getSize(){return size;}
	@Override
	public int getX() {return x;}
	@Override
	public int getY() {return y;}
	
	////////////////////////////////////////////////////Set Functions//////////////////////////////////////
	@Override
	public void setSize(int size) {this.size = size;}
	@Override
	public void setX(int x) {this.x = x;}
	@Override
	public void setY(int Y) {this.y = Y;}
	@Override
	public void setColor(String col) {this.colorr = Color.green;}
	////////////////////////////////////////////////////Draw//////////////////////////////////////////////
	public void drawCreature(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		g2.setColor(colorr);
		g.drawLine(x, y, x, y-size);
		g.drawLine(x-2, y, x-10, y-size*9/10);
		g.drawLine(x+2, y, x+10, y-size*9/10);
		g.drawLine(x-4, y, x-20, y-size*4/5);
		g.drawLine(x+4, y, x+20, y-size*4/5);
		g.drawLine(x-6, y, x-30, y-size*7/10);
		g.drawLine(x+6, y, x+30, y-size*7/10);
		g.drawLine(x-8, y, x-40, y-size*4/7);
		g.drawLine(x+8, y, x+40, y-size*4/7);
		g2.setStroke(new BasicStroke(1));
	}
}
