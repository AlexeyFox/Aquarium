

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

public class AddPlantDialog extends JDialog implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JPanel panel1;
	private JButton button;
	private String[] plants = {"Zostera","Laminaria"};
	private JLabel ltype;
	private JLabel lsize;
	private JSlider  size;
	private JComboBox type;
	AquaPanel obj;

	AddPlantDialog(AquaPanel obj){
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
		type = new JComboBox(plants);
		ltype.setVisible(true);
		type.setVisible(true);
		type.setBackground(Color.lightGray);
		
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
				
		///Save///
		button = new JButton("Save");
		button.addActionListener(this);
		button.setBackground(Color.lightGray);
		
		///Add all to panel///
		panel1.add(ltype);
		panel1.add(type);
		panel1.add(lsize);
		panel1.add(size);
		panel1.add(button);		
	}

	public void actionPerformed(ActionEvent e)
	{
		   if(e.getSource()==button)
			   Save();
	}
	
	public void Save()
	{	
		obj.setAnimal("Plant",type.getSelectedItem().toString(),Color.green,size.getValue(),(int)(Math.random()*obj.getWidth()+10),obj.getHeight(),0);
		dispose ();
	}
}
