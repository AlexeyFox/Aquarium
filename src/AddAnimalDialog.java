/**@version HomeWork 4
 * @author Ekaterina Lezepikov 324623602
 * @author Kassandra Amerzoyev 314129636
 */


import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;

//import AquaFrame.AquaPanel;

public class AddAnimalDialog extends JDialog implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JPanel panel1;
	private JButton button;
	private String[] colors = {"Red","Green", "Cyan","Magenta","Orange","Pink","Black"};
	private String[] animals = {"Fish","Jellyfish"};
	private String[] buttons = {"Save","Change"};
	private JLabel ltype;
	private JLabel lsize;
	private JLabel vspeed;
	private JLabel hspeed;
	private JLabel lcolor;
	private JLabel lfrequency;
	private JSlider  size;
	private JSlider verticalspeed;
	private JSlider horizontalspeed;
	private JSlider frequency;
	private JComboBox color;
	private JComboBox type;
	AquaPanel obj;
	Swimmable animal;

	AddAnimalDialog(AquaPanel obj, Swimmable animal){
		this.animal = animal;
		this.obj = obj;
		this.setModal(true);
		///Panel///
		panel1=new JPanel();
		panel1.setLayout(new GridLayout(7,5,1,1));
		panel1.setSize(500,300);	
		panel1.setBackground(Color.lightGray);
		setLayout(new BorderLayout());	
		this.add("Center", panel1);
		
		///Type///
		ltype = new JLabel("Type");
		type = new JComboBox(animals);
		ltype.setVisible(true);
		type.setVisible(true);
		type.setBackground(Color.lightGray);
		if(animal == null){
			ltype.setVisible(true);
			type.setVisible(true);
		}
		else{
			ltype.setVisible(false);
			type.setVisible(false);
		}
		
		///Size///
		lsize = new JLabel("Size");
		size = new JSlider(20,320);
		size.setMajorTickSpacing(100);
		size.setMinorTickSpacing(5);
		size.setPaintTicks(true);
		size.setPaintLabels(true);
		lsize.setVisible(true);
		size.setVisible(true);
		size.setBackground(Color.lightGray);
		
		///Vertical Speed///
		vspeed = new JLabel("Vertical Speed");
		verticalspeed = new JSlider(1,10);
		vspeed.setVisible(true);
		verticalspeed.setVisible(true);
		verticalspeed.setMajorTickSpacing(1);
		verticalspeed.setMinorTickSpacing(5);
		verticalspeed.setPaintTicks(true);
		verticalspeed.setPaintLabels(true);
		verticalspeed.setBackground(Color.lightGray);
		
		///Horizontal Speed///
		hspeed = new JLabel("Horizontal Speed");
		horizontalspeed = new JSlider(1,10);
		hspeed.setVisible(true);
		horizontalspeed.setVisible(true);
		horizontalspeed.setMajorTickSpacing(1);
		horizontalspeed.setMinorTickSpacing(5);
		horizontalspeed.setPaintTicks(true);
		horizontalspeed.setPaintLabels(true);
		horizontalspeed.setBackground(Color.lightGray);
		
		///Color///
		lcolor = new JLabel("Color");
		color = new JComboBox(colors);
		lcolor.setVisible(true);
		color.setVisible(true);
		color.setBackground(Color.lightGray);
		
		///Save///
		if(animal == null)
			button = new JButton(buttons[0]);	
		else
			button = new JButton(buttons[1]);
		button.addActionListener(this);
		button.setBackground(Color.lightGray);

		//frequency
		lfrequency = new JLabel("Frequensy");
		frequency = new JSlider(100,1000);
		frequency.setMajorTickSpacing(100);
		frequency.setMinorTickSpacing(500);
		frequency.setPaintTicks(true);
		frequency.setPaintLabels(true);
		frequency.setBackground(Color.lightGray);
		if(animal == null){
			lfrequency.setVisible(true);
			frequency.setVisible(true);
		}
		else{
			lfrequency.setVisible(false);
			frequency.setVisible(false);
		}
		
		///Add all to panel///
		panel1.add(ltype);
		panel1.add(type);
		panel1.add(lsize);
		panel1.add(size);
		panel1.add(vspeed);
		panel1.add(verticalspeed);
		panel1.add(hspeed);
		panel1.add(horizontalspeed);
		panel1.add(lcolor);
		panel1.add(color);
		panel1.add(lfrequency);
		panel1.add(frequency);
		panel1.add(button);		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		   if(e.getSource()==button){
			   if(button.getText() == buttons[0])
				   Save();
			   else
				   Change(); 
		   }
	}
	
	
	public void Change(){
		animal.setSize(size.getValue());
		animal.setColor(getColor(color.getSelectedItem().toString()));
		animal.setHorSpeed(horizontalspeed.getValue());
		animal.setVerSpeed(verticalspeed.getValue());
		dispose ();
	}
	
	public void Save()
	{	
		obj.setAnimal("Animal",type.getSelectedItem().toString(),getColor(color.getSelectedItem().toString()),size.getValue(),horizontalspeed.getValue(),verticalspeed.getValue(),frequency.getValue());
		dispose ();
	}
	
	private Color getColor(String col){
		///Choose color///
		if (col == colors[0])
			return Color.red;
		else if(col == colors[1])
			return Color.green;
		else if(col == colors[2])
			return Color.cyan;
		else if(col == colors[3])
			return Color.magenta;
		else if(col == colors[4])
			return Color.orange;
		else if(col == colors[5])
			return Color.pink;
		return Color.black;
	}	
}
