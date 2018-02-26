


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.concurrent.CyclicBarrier;

public class Laminaria extends Immobile {

	AquaPanel panel;
	private int size;
	private Color colorr;
	private int x,y;
	/////////////////////////////////////////////Constractor//////////////////////////////////////////
	Laminaria(String name,Color colorr, int size, int x, int y, AquaPanel panel){
		this.name = name;
		this.panel = panel;
		this.size = size;
		this.x = x;
		this.y = y;
		this.colorr = colorr;
	}
	/////////////////////////////////////////////Get Functions/////////////////////////////////////////
	public int getSize(){ return size;}
	@Override
	public int getX() {return x;}
	@Override
	public int getY() {return y;}
	/////////////////////////////////////////////Set Functions//////////////////////////////////////////
	@Override
	public void setSize(int size) {this.size = size;}
	@Override
	public void setX(int x) { this.x = x;}
	@Override
	public void setY(int Y) {this.y = Y;}
	@Override
	public void setColor(String col) {this.colorr = Color.green;}
	@Override
	////////////////////////////////////////////////Draw///////////////////////////////////////////////
	public void drawCreature(Graphics g) {
		g.setColor(colorr);		
		g.fillArc(x-size/20, y-size, size/10, size*4/5, 0, 360);
		g.fillArc(x-size*3/20, y-size*13/15, size/10, size*2/3, 0, 360);
		g.fillArc(x+size/20, y-size*13/15, size/10, size*2/3, 0, 360);
		g.drawLine(x, y, x, y-size/5);
		g.drawLine(x, y, x-size/10, y-size/5);
		g.drawLine(x, y, x+size/10, y-size/5);
	}
}
