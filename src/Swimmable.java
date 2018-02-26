import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.CyclicBarrier;

public abstract class Swimmable extends Thread implements SeaCreature, Cloneable{
	AquaPanel panel;
	protected int horSpeed;
	protected int verSpeed;
	
	public Swimmable() {
		horSpeed = 0;
		verSpeed = 0;
	}	
	public Swimmable(int hor, int ver) {
		horSpeed = hor;
		verSpeed = ver;
	}	
	public int getHorSpeed() { return horSpeed; }	
	public int getVerSpeed() { return verSpeed; }
	public void setHorSpeed(int hor) { horSpeed = hor; }
	public void setVerSpeed(int ver) { verSpeed = ver; }
	abstract public String getAnimalName();
	abstract public void setSuspend();
	abstract public void setResume();
	abstract public void setSize(int size);
	abstract public void setBarrier(CyclicBarrier b);
	abstract public int getSize();
	abstract public void eatInc();
	abstract public int getEatCount();
	abstract public String getColor();
	abstract public void setColor(Color col);
	abstract public Object clone();
	abstract public Swimmable copy();
	abstract public void setColor(String col);
	abstract public int getX();
	abstract public int getY();
	abstract public void setX(int x);
	abstract public void setY(int Y);
}
