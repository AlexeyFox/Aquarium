


import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.Iterator;
import java.util.List;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.CyclicBarrier;

public class AquaPanel extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private AquaFrame myframe;
	private JPanel p1;//button panel
	private JPanel p2;//chart panel
	private JButton[] b_num;
	private String[] names = {"Add Animal","Add Plant","Duplicate Animal","Sleep","Wake up", "Reset","Food","Decorator","Info","Exit"};
	private static Image[] img;
	public HashSet<Swimmable> animals;
	public HashSet<Immobile> plants;
	Iterator <Swimmable> animalsIterator;
	Iterator <Immobile> plantsIterator;
	boolean isFood;
	CyclicBarrier barrier;
	boolean isInfo=true;
	JTable chart;
	DefaultTableModel chartm = new DefaultTableModel();
	String[] criterion = {"Animal","Color","Size","Hor. speed","Ver. speed","Eat counter"};
	int worms = 0;
	List<Object> state = new ArrayList<Object>();
	CareTaker careTaker;
	
	WormSingleton Food = WormSingleton.getInstance();;

	
	
	ArrayList<Swimmable> animalsArraytemp = new ArrayList<Swimmable>();
	ArrayList<Immobile> plantsArraytemp = new ArrayList<Immobile>();
	
	class CustomMouseListener implements MouseListener{
		/** inner class to help distinguish an even button click from and odd button click*/
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount()%2==0){
				chartm.setRowCount(0);
				worms=0;}
		}
		public void mousePressed(MouseEvent e) {
		}
		public void mouseReleased(MouseEvent e) {
		}
		public void mouseEntered(MouseEvent e) {
		}
		public void mouseExited(MouseEvent e) {
		}
	}


	AquaPanel(AquaFrame myframe){/*building the frame*/
		super();
		careTaker = new CareTaker();
		///////////////////////Panel////////////////////
		p1=new JPanel();
		p1.setLayout(new GridLayout(1,7,5,5));
		p1.setBackground(Color.lightGray);
		p1.addMouseListener(new CustomMouseListener());
		p2=new JPanel();
		p2.setLayout(new FlowLayout());
		p2.setVisible(false);
		p2.setBackground(Color.lightGray);
	
		//////////////////////Buttons///////////////////
		b_num=new JButton[names.length];
		for(int i=0;i<names.length;i++)
		{
		    b_num[i]=new JButton(names[i]);
		    b_num[i].addActionListener(this);
		    b_num[i].setBackground(Color.white);		    	
		    p1.add(b_num[i]);	
		    
		 }
		b_num[5].addMouseListener(new CustomMouseListener());
		
		//////////////////////Chart /////////////
	    chart = new JTable(chartm);	    
		for( int i=0; i < criterion.length ; i++)	//adding columns   	
	    	chartm.addColumn(criterion[i]);	
        //////////////////////Frame/////////////////////
		setBackground(Color.lightGray);
		this.myframe = myframe;
		setLayout(new BorderLayout());
		myframe.add("South", p1 );
		myframe.add("North", p2 );
		/////////////////////Animals///////////////////
		animals = new HashSet<Swimmable>();
		plants = new HashSet<Immobile>();
		isFood = false;
	}
	
	public void actionPerformed(ActionEvent e)
	{/*connecting between actions and buttons*/
		   if(e.getSource()==b_num[0]) 		// Add_Animal
			   Add_Animal();
		   else if(e.getSource()==b_num[1]) // Add_Plant
			   Add_Plant();
		   else if(e.getSource()==b_num[2]) // Duplicate_Animal
			   Duplicate_Animal();
		   else if(e.getSource()==b_num[3]) // Sleep
			   Sleep();
		   else if(e.getSource()==b_num[4]) // Wake_up
			   Wake_up();
		   else if(e.getSource()==b_num[5]) // Reset
			   Reset();
		   else if(e.getSource()==b_num[6]) // Food
			   Food();
		   else if(e.getSource()==b_num[7]){ // Decorator			  
			   Decorator();}
		   else if(e.getSource()==b_num[8]){ // Info			  
			   Info();}
		   else if(e.getSource()==b_num[9]) // Exit
			   Exit();
	}
	
	public void mouseClicked(MouseEvent e){
        if(e.getClickCount()==1)
        	isInfo=false;	
	}
	
	//////////////////////////////////////Buttons Realization////////////////////////
	public void Add_Animal()
	{
		/////Open new window for adding animal information/////
		AddAnimalDialog window = new AddAnimalDialog(this, null); 
		window.setTitle("Add Animal");
		window.setLocation(140, 140);
		window.setSize(515, 339);
		window.setVisible(true);
	}
	
	public void Add_Plant(){
		AddPlantDialog window = new AddPlantDialog(this); 
		window.setTitle("Add Plant");
		window.setLocation(140, 140);
		window.setSize(515, 339);
		window.setVisible(true);
	}
	
	public void Duplicate_Animal(){
		try {
			PrintDialog dialog = new PrintDialog(animals, this);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
				e.printStackTrace();
			}
		
	}
	
	public void Clone_Animal(Swimmable animal){
		if(animals.size() < 5){
			Swimmable newanimal = null;
			newanimal = (Swimmable) animal.copy();			
			int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Like to Chance some parameters of animal?","Warning",0);
			if(dialogResult == JOptionPane.YES_OPTION){
				animalsIterator = animals.iterator();
				AddAnimalDialog window = new AddAnimalDialog(this,newanimal); 
				window.setTitle("Add Animal");
				window.setLocation(140, 140);
				window.setSize(515, 339);
				window.setVisible(true);
				animals.add(newanimal);
			}
			else
				animals.add(newanimal);
			newanimal.start();
		}
		else{ JOptionPane.showMessageDialog(this, "You can't add more than five animals.");}
	}
	
	public void Sleep()
	{  
		///For all animals update their suspend to "true" causing them to freeze/sleep ///
		animalsIterator = animals.iterator();			
		synchronized (animals) {
			while (animalsIterator.hasNext()){
	        	 Swimmable animal = animalsIterator.next();
	        	 animal.setSuspend();
			}
	     }
	}
	
	synchronized public void Wake_up()
	{
		///For all animals update their suspend to "false" and notify them so they will unfreeze///
		animalsIterator = animals.iterator();			
		while (animalsIterator.hasNext()){
			Swimmable animal = animalsIterator.next();
			animal.setResume();
		}
	}

	public void Reset()
	{
		///Stop all threads and clean the animals list///
		animalsIterator = animals.iterator();	
		synchronized (animals) {
			while (animalsIterator.hasNext()){
	        	 Swimmable animal = animalsIterator.next();
	        	 if(animal instanceof Fish){
	        		 
	        	 }
	        	 animal.stop();
			}
	     }
		careTaker.Destroy();
		animals.clear();
		plants.clear();
		animalsIterator = animals.iterator();	
		plantsIterator = plants.iterator();
		isFood = false;
		repaint();
	}
	
	public void Food()
	{
		///If the worm does't exist and there is animals in the panel ,add a worm///
		if(!isFood){
			try{
				barrier = new CyclicBarrier(animals.size());
				animalsIterator = animals.iterator();			
				while (animalsIterator.hasNext()){
					Swimmable animal = animalsIterator.next();
					animal.setBarrier(barrier);    //Haunting synchronization of all animals
				}
				isFood = true;
				repaint();
			}catch (IllegalArgumentException message){ JOptionPane.showMessageDialog(this, "There is no fishes.");}
		} else{ JOptionPane.showMessageDialog(this, "The worm exists.");}
	}
	
	public void Decorator(){
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(600,300);
		animalsIterator = animals.iterator();
		JPanel panel=new JPanelDecorator(animalsIterator,frame);
		frame.add(panel);
	}
	
	public void Info()
	{	/* displaying a chart which contains info about the animals and eaten worms count*/	
		animalsIterator = animals.iterator();		
		while (animalsIterator.hasNext()){ // adding info about each animal to chart
			Swimmable animal = animalsIterator.next();
			String type = animal.getAnimalName();
			String color = animal.getColor();
			String size = String.valueOf(animal.getSize());
			String horSpeed = String.valueOf(animal.getHorSpeed());
			String verSpeed = String.valueOf(animal.getVerSpeed());
			String counter = String.valueOf(animal.getEatCount());
			worms += Integer.parseInt(counter);		
			String[] row = {type, color, size, horSpeed, verSpeed, counter};
			chartm.addRow(row); 
		}		
		String[] r={"Total","","","","",String.valueOf(worms)}; 
		chartm.addRow(r);
		
		chart.setPreferredScrollableViewportSize(new Dimension(this.getWidth(),96));
		chart.setFillsViewportHeight(true);
	    JScrollPane scrollPane = new JScrollPane(chart);
	    p2.add(scrollPane);   
	    
	    //open or close chart by click
	    if(isInfo)
		{
			p2.setVisible(true);
			isInfo=false;
		}
		else
		{	chartm.setRowCount(0);	
			p2.setVisible(false);
			isInfo=true;				
			worms=0;
		}	
	}
	
	public void Exit(){ System.exit(0);}  //Exit from the system
	
	
	//////////////////////////////////////////////Memento////////////////////////////////////////
	
	///Frame///
	public void saveState(){

		animalsIterator = animals.iterator();
		plantsIterator = plants.iterator();
		ArrayList<Swimmable> animalsArray = new ArrayList<Swimmable>();
		ArrayList<Immobile> plantsArray = new ArrayList<Immobile>();
		
		while(animalsIterator.hasNext())
		{   
			Swimmable animal = animalsIterator.next();
			animalsArray.add(animal);
		}
		while(plantsIterator.hasNext())
		{  
			Immobile plant = plantsIterator.next();
			plantsArray.add(plant);
		}
		final Object headers[] = {"Animal", "Size","Color"};
		final Object[][] rows = new Object[(animalsArray.size() + plantsArray.size())][3];
	
		for(int i=0; i< (animalsArray.size() + plantsArray.size()) ;i++){
				if(i<animalsArray.size()){
					if(animalsArray.get(i) instanceof Fish ) rows[i][0] = "Fish";
					else rows[i][0] = "Jellyfish";
					rows[i][1] = animalsArray.get(i).getSize();
					rows[i][2] = animalsArray.get(i).getColor();
				}
				else{
					if(plantsArray.get(i-animalsArray.size()) instanceof Laminaria ){
						rows[i][0] = "Laminaria";
						rows[i][1] = ((Laminaria)plantsArray.get(i-animalsArray.size())).getSize();
					}
					else{
						rows[i][0] = "Zostera";
						rows[i][1] = ((Zostera)plantsArray.get(i-animalsArray.size())).getSize();
					}
					rows[i][2] = "Green";
				}
		}	
		animalsArraytemp = animalsArray;
		plantsArraytemp = plantsArray;

		final JFrame frame = new JFrame("Table Printing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JTable table = new JTable(rows, headers);
		JScrollPane scrollPane = new JScrollPane(table);
		frame.add(scrollPane, BorderLayout.CENTER);
		JButton button = new JButton("Ok");
		ActionListener printAction = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if(index > -1){
					if(index < animalsArraytemp.size()){
						List<Object> temp = new ArrayList<Object>();
						temp.add(animalsArraytemp.get(index).getAnimalName());
						temp.add(animalsArraytemp.get(index).getColor());
						temp.add(animalsArraytemp.get(index).getSize());
						temp.add(animalsArraytemp.get(index).getName());
						temp.add(animalsArraytemp.get(index).getX());
						temp.add(animalsArraytemp.get(index).getY());
						temp.add(animalsArraytemp.get(index).horSpeed);
						temp.add(animalsArraytemp.get(index).verSpeed);
						setState(temp);
					}
					else{
						List<Object> temp = new ArrayList<Object>();
						temp.add(plantsArraytemp.get(index-animalsArraytemp.size()).name);
						temp.add("Green");
						temp.add(plantsArraytemp.get(index-animalsArraytemp.size()).getSize());
						temp.add(plantsArraytemp.get(index-animalsArraytemp.size()).getX());
						temp.add(plantsArraytemp.get(index-animalsArraytemp.size()).getY());
						setState(temp);
					}
					careTaker.add(saveToMemento());	
					frame.dispose();
				}
				else { JOptionPane.showMessageDialog(null, "You need to choose some animal.");} 
			}
		};
		button.addActionListener(printAction);
		frame.add(button, BorderLayout.SOUTH);
		frame.setSize(300, 150);
		frame.setVisible(true);		
	}

	public void restoreState(){
		
		final Object[][] rows = new Object[careTaker.getSize()][3];
		for(int i=0; i< careTaker.getSize() ;i++){
			rows[i][0] = (careTaker.get(i).getState()).get(0);
			rows[i][1] = (careTaker.get(i).getState()).get(1);
			rows[i][2] = (careTaker.get(i).getState()).get(2);
		}
		final Object headers[] = {"Animal", "Size","Color"};
		
		final JFrame frame = new JFrame("Table Printing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JTable table = new JTable(rows, headers);
		JScrollPane scrollPane = new JScrollPane(table);
		frame.add(scrollPane, BorderLayout.CENTER);
		JButton button = new JButton("Change");
		ActionListener printAction = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if(index > -1){
					getStateFromMemento(careTaker.get(index));
					if(state.get(0) == "Fish" || state.get(0) == "Jellyfish"){
						animalsIterator = animals.iterator();
						while(animalsIterator.hasNext()){
							Swimmable animal = animalsIterator.next();
							if(animal.getName().equals(state.get(3))){				
								animal.setSize((int)state.get(2));
								animal.setColor((String)state.get(1));
								animal.setX((int)state.get(4));
								animal.setY((int)state.get(5));
								animal.setHorSpeed((int)state.get(6));
								animal.setVerSpeed((int)state.get(7));
							}
						}
					}
					else{
						plantsIterator = plants.iterator();
						while(animalsIterator.hasNext()){
							Immobile plant = plantsIterator.next();
							if(plant.name.equals(state.get(0)))	{			
								plant.setSize((int)state.get(2));
								plant.setColor((String)state.get(1));
								plant.setX((int)state.get(3));
								plant.setY((int)state.get(4));
							}
						}
					}
					
					frame.dispose();
				}
				else { JOptionPane.showMessageDialog(null, "You need to choose some animal.");} 
			}
		};
		button.addActionListener(printAction);
		frame.add(button, BorderLayout.SOUTH);
		frame.setSize(300, 150);
		frame.setVisible(true);
	}
	
	///Get/Set///
	public void setState(List<Object> state){ this.state = state;}
	public List<Object> getState(){return state;}
	
	///Memento save/get///
	public Memento saveToMemento(){ return new Memento(state);}
	public void getStateFromMemento(Memento memento){ state = memento.getState();}
	
	/////////////////////////////////////////////Help Functions//////////////////////////////////////////
	
	public void setAnimal(String type,String name,Color col,int size, int x_front,int y_front,int frequency){
		///Add new animal to list/// 
			if(type.equals("Animal")){
				if(animals.size() < 5){
					AnimalFactory obj1 = new AnimalFactory();
					SeaCreature animal = obj1.produceSeaCreature(name, col, size, x_front, y_front, frequency,this);
					animals.add((Swimmable) animal);
					((Swimmable) animal).start();
				}
				else{ JOptionPane.showMessageDialog(this, "You can't add more than five animals.");}
			}
			else{
				if(plants.size() < 5){
					PlantFactory obj2 = new PlantFactory();
					SeaCreature plant = obj2.produceSeaCreature(name, col, size, x_front, y_front,0, this);
					plants.add((Immobile) plant);
					repaint();
				}
				else{ JOptionPane.showMessageDialog(this, "You can't add more than five plants.");}
			}
	}
	

	///////////////////////////////////Background Image////////////////////////////////
	void setBackgr(int i){
		/*this func to set the background*/
		if(i == 0){  //reset background
			img = null;
			p1.setBackground(Color.lightGray);
			this.setBackground(Color.lightGray);
		}
		else if(i == 1){ //Blue
			img = null;
			p1.setBackground(Color.blue);
			this.setBackground(Color.blue);
		}
		else if(i == 2){  //Add image
			Load_Image();
		}
	}
	
	public void Load_Image(){
		///Get image from PC of user///
		FileDialog fd = new FileDialog(myframe,"Choose a file", FileDialog.LOAD);
		fd.setDirectory("C:\\");
		fd.setFile("*.jpg");
		fd.setVisible(true);
		String filename = fd.getFile();
		if (filename == null)
		  System.out.println("You cancelled the choice");
		else
		  System.out.println("You chose " + filename);
		System.out.println(fd.getDirectory() + filename); 
		img = new Image[1];
		try { 
			img[0] = ImageIO.read(new File(fd.getDirectory() + filename)); } 
		catch (IOException d) { System.out.println("Cannot load image"); }			
		repaint();
	}

	/////////////////////////////////////////////Food/////////////////////////////////////////
	
	public void updateIsFood(Swimmable animal)
	{
		///Update of "isFood" status if the worm was eaten///
		synchronized (animal) {
			if (isFood){
				if(animal instanceof Fish){
					if(((Fish)animal).context.getState().toString().equals("Hungry"))
						animal.eatInc();
					isFood = false;
				}
				else{
					if(((Jellyfish)animal).context.getState().toString().equals("Hungry"))
						animal.eatInc();
					isFood = false;
				}
				
			}
		}
	}
	
	public void notifyUser(String color, String name, Context con){ 
		if(!isFood){
			JOptionPane.showMessageDialog(null, "" + name + " " + color + " is hungry :("  + con.getState().toString());}	
		}
	
	//////////////////////////////////////Painting///////////////////////////////////
	
	public void paintComponent(Graphics g) {
			
		    super.paintComponent(g);		    
		    Dimension dm = getSize();
		    ///Draw image///
			if(img != null)
			    g.drawImage(img[0], 5, 5, dm.width-10, dm.height-15, this);			
			///Draw animals///
			animalsIterator = animals.iterator();
			while(animalsIterator.hasNext()){
				Swimmable animal = animalsIterator.next();
				animal.drawCreature(g);
			}
			///Draw Plants///
			plantsIterator = plants.iterator();
			while(plantsIterator.hasNext()){
				Immobile plant = plantsIterator.next();
				plant.drawCreature(g);
			}		
			///Draw worm///
			if(isFood)
				Food.paintComponent(g);
	}  	   
}
