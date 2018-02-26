


import java.util.concurrent.CyclicBarrier;

public abstract class Immobile implements SeaCreature  { 
	public String name; 
	
	abstract public int getSize();
	abstract public int getX();
	abstract public int getY();
	
	abstract public void setSize(int size);
	abstract public void setX(int x);
	abstract public void setY(int Y);
	abstract public void setColor(String col);
}
