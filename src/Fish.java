

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;



public class Fish extends Swimmable implements MarineAnimal{

	private String name;
	private Color col;
	private int size;
	private int x_front,y_front,x_dir,y_dir;
	boolean suspended;
	public int frequency,startFrequency;
	CyclicBarrier barrier;
	private int counter;
	public int messageCount;
	public Context context;
	
	///////////////////////////////////Constructor////////////////////////////////////////
	Fish(String name,Color col,int size, int x,int y,int frequency ,AquaPanel panel){ 	
		super(x,y);
		context = new Context();
		Satiated satiated = new Satiated();
		satiated.doAction(context);
		this.name = name;
		this.col = col;
		this.size = size;
		this.x_front = size+size/4;
		this.y_front = 10+size/2;
		this.frequency = frequency;
		this.startFrequency = frequency;
		this.panel = panel;
		this.messageCount = 0;
		x_dir = y_dir = 1;
	}
	////////////////////////////////Copy Constractor//////////////////////////////////////
	public Fish(Fish fish) {
		super(fish.getHorSpeed(),fish.getVerSpeed());
		context = new Context();
		if(fish.context.getState().toString().equals("Hungry")){
			Hungry hungry = new Hungry();
			hungry.doAction(context);
		}
		else{
			Satiated satiated = new Satiated();
			satiated.doAction(context);
		}
		this.name = fish.name;
		this.col = fish.col;
		this.size = fish.size;
		this.x_front = fish.size+fish.size/4;
		this.y_front = 10+fish.size/2;
		this.frequency = fish.frequency;
		this.startFrequency = fish.startFrequency;
		this.panel = fish.panel;
		this.messageCount = fish.messageCount;
		x_dir = fish.x_dir;
		y_dir = fish.y_dir;
	}
	/////////////////////////////////////////Set Fuctions//////////////////////////////////////////
	@Override
	public void setSuspend() {suspended = true;}
	@Override
	public void setResume() {
		suspended = false;
		synchronized(this){  //Wake up all animals
			notifyAll();
		}
	}
	@Override
	public void setBarrier(CyclicBarrier b) { barrier = b;}
	public void setFrequency(int num){
		if(num == 1)
			frequency-=num;
		else
			frequency = num;
		if(frequency < 1 && messageCount == 0){
			Hungry hungry = new Hungry();
			hungry.doAction(context);
			panel.notifyUser(getColor(),"Fish",context);
			messageCount++;
		}
	}
	public void setSize(int size){ this.size = size;}	
	public void setColor(String col) {
		if (col.equals("Red"))
			this.col = Color.red;
		else if(col.equals("Green"))
			this.col =  Color.green;
		else if(col.equals("Cyan"))
			this.col =  Color.cyan;
		else if(col.equals("Magenta"))
			this.col =  Color.magenta;
		else if(col.equals("Orange"))
			this.col =  Color.orange;
		else if(col.equals("Pink"))
			this.col =  Color.pink;
		else if(col.equals(Color.black))
			this.col =  Color.black;	
	else{
		String first = null,second = null,third = null;
		int counter = 0;
		for(int i = 0 ; i< col.length()-1;i++){
			if(!col.substring(i,i+1).equals("(") && !col.substring(i,i+1).equals(")")){
				if(col.substring(i,i+1).equals(","))
					counter++;
				else if(counter == 0){
					if(first != null)
						first = first+col.substring(i,i+1);
					else
						first = col.substring(i,i+1);
				}
				else if(counter == 1){
					if(second != null)
						second = second+col.substring(i,i+1);
					else
						second = col.substring(i,i+1);
				}
				else{
					if(third != null)
						third = third+col.substring(i,i+1);
					else
						third = col.substring(i,i+1);
				}
			}
		}
		Color color = new Color( Integer.parseInt(first), Integer.parseInt(second), Integer.parseInt(third));
		this.col = color;
	}		
	}
	@Override
	public void setX(int x) {this.x_front = x;}
	@Override
	public void setY(int Y) {this.y_front = Y;}
	@Override
	public void setColor(Color col) {this.col = col;}
	/////////////////////////////////////////Get Fuctions///////////////////////////////////////////
	@Override
	public String getAnimalName() { return name;}
	@Override
	public int getSize() { return size; }
	@Override
	public int getEatCount() {return counter;}
	@Override
	public String getColor() { 
		if (col == Color.red)
			return "Red";
		else if(col == Color.green)
			return "Green";
		else if(col == Color.cyan)
			return "Cyan";
		else if(col == Color.magenta)
			return "Magenta" ;
		else if(col == Color.orange)
			return "Orange";
		else if(col == Color.pink)
			return "Pink";
		else if(col.equals(Color.black))
			return "Black";
		else return getColorName(col);
	}
	@Override
	public int getX() {return x_front;}
	@Override
	public int getY() {return y_front;}
	public String getColorName(Color c) { return "("+c.getRed()+","+c.getGreen()+","+c.getBlue()+")";}
	///////////////////////////////////////////Draw////////////////////////////////////////////////
	@Override
	public void drawCreature(Graphics g) {
		 g.setColor(col);    
		 if(x_dir == 1) // fish swims to right side 
		 {
			// Body of fish  
			g.fillOval(x_front - size, y_front - size/4, size, size/2); 

			// Tail of fish  
			int[] x_t={x_front-size-size/4,x_front-size-size/4,x_front-size};  
			int [] y_t = {y_front - size/4, y_front + size/4, y_front};  
			Polygon t = new Polygon(x_t,y_t,3);    
			g.fillPolygon(t); 
			
			// Eye of fish  
			Graphics2D g2 = (Graphics2D) g;  
			g2.setColor(new Color(255-col.getRed(),255-col.getGreen(),255- col.getBlue()));  
			g2.fillOval(x_front-size/5, y_front-size/10, size/10, size/10);
			
		    // Mouth of fish  
			if(size>70)   
				g2.setStroke(new BasicStroke(3));  
			else if(size>30)   
				g2.setStroke(new BasicStroke(2));  
			else   
				g2.setStroke(new BasicStroke(1));       
			g2.drawLine(x_front, y_front, x_front-size/10, y_front+size/10);       
			g2.setStroke(new BasicStroke(1)); 
			if(frequency < 1){
				g2.drawString("Hungry", x_front, y_front);
			}
		 }
		 else
		 {  
			 // Body of fish  
			 g.fillOval(x_front, y_front - size/4, size, size/2);  
			 
			 // Tail of fish  
			 int[] x_t={x_front+size+size/4,x_front+size+size/4,x_front+size};  
			 int [] y_t = {y_front - size/4, y_front + size/4, y_front};  
			 Polygon t = new Polygon(x_t,y_t,3);    
			 g.fillPolygon(t);
			 
			// Eye of fish  
			 Graphics2D g2 = (Graphics2D) g;  
			 g2.setColor(new Color(255-col.getRed(),255-col.getGreen(),255-col.getBlue()));  
			 g2.fillOval(x_front+size/10, y_front-size/10, size/10, size/10);  
			 
			 // Mouth of fish  
			 if(size>70)   
				 g2.setStroke(new BasicStroke(3));  
			 else if(size>30)   
					 g2.setStroke(new BasicStroke(2));  
			 else   
					 g2.setStroke(new BasicStroke(1));       
			 g2.drawLine(x_front, y_front, x_front+size/10, y_front+size/10);       
			 g2.setStroke(new BasicStroke(1)); 
			 if(frequency < 1){
				 g2.drawString("Hungry", x_front-40, y_front);
			 }
		 }
	}
	
	
	/////////////////////////////////////////////Eat////////////////////////////////////
	@Override
	public void eatInc() { 
		Satiated  satiated = new Satiated();
		satiated.doAction(context);
		counter++ ;
		messageCount=0;
		setFrequency(startFrequency);
	}
	
	
	public void Eat()
    {
		///Synchronize hunting with all animals///
        if ( context.getState().equals("Hungry"))
			try {
				barrier.await();  //Wait if there is another animals
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        barrier = null;
        
        ///New direction///
        double old_speed = Math.sqrt(horSpeed*horSpeed + verSpeed*verSpeed);
        double k;  
        if((x_front - panel.getWidth()/2) != 0)
        	k = Math.abs((y_front - panel.getHeight()/2) / (x_front - panel.getWidth()/2));  
        else
        	k = Math.abs((y_front - panel.getHeight()/2));     
        double new_h_speed = old_speed / Math.sqrt(k*k + 1);
        double new_v_speed = new_h_speed * k;
        NewSwimming(new_v_speed,new_h_speed);

        // Check if the fish can eat the worm//
        if ((Math.abs(y_front-panel.getHeight()/2) < 6) && (Math.abs(x_front-panel.getWidth()/2) < 6))
        	panel.updateIsFood(this);       
    }
	//////////////////////////////////////////////Run//////////////////////////////////////////
	public void run() 
    {
        while(true){ 
          	try { Thread.sleep(30);} 
        	catch (InterruptedException e) { System.out.println("Sleep ERROR!");}
          	
        	if(suspended) { ///Check if the fish sleeps
        		synchronized(this){
        			try {
        				wait();
        			} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}	
                }
                else{  //If the fish wake up and if there is worm
                	if(panel.isFood && context.getState().toString().equals("Hungry"))
                		Eat();
                	else
                		RegularSwimming();
                }   	 
		    panel.repaint();
      }        
   }

	/////////////////////////////////////////////Swiming/////////////////////////////////////////
	public void NewSwimming(double v_speed,double h_Speed){
        if(x_front > panel.getWidth()/2)
            x_dir = -1; //left
        else
            x_dir = 1; //right
        
        if(y_front > panel.getHeight()/2)
            y_dir = -1; //up 
        else
            y_dir = 1; //go       
        x_front += (int)h_Speed*x_dir;
        y_front += (int)v_speed*y_dir;
	}
	
	 public void RegularSwimming()
	 {
		 // Check if fish in the edge of the Aquarium //
		 if (x_front <= 0+size+size/6)
			 x_dir = 1; 
		 else if (x_front >= panel.getWidth()-(size+size/6))
			 x_dir = -1; 
		
		 if (y_front >= panel.getHeight()- size/4)
			 y_dir = -1;	 
		 else if(y_front <= 0 + size/4)
			 y_dir = 1;
		 x_front += horSpeed*x_dir;
		 y_front += verSpeed*y_dir;	
		 
		 ///////Set frequency////
		 setFrequency(1);
	 } 
	 /////////////////////////////////////////////Clone////////////////////////////////
	@Override
	public Object clone() {return new Fish(this);}
	@Override
	public Swimmable copy() {
		Fish animal = null;
		animal = (Fish)this.clone();
		return animal;
	}

	///////////////////////////////////////////Paint/////////////////////////////////
	@Override
	public void PaintFish(Color col) {setColor(col);}
}
