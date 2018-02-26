/**@version HomeWork 4
 * @author Ekaterina Lezepikov 324623602
 * @author Kassandra Amerzoyev 314129636
 */


import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JPanelDecorator extends JPanel{

	private static final long serialVersionUID = 1L;
	Iterator <Swimmable> animalsIterator;
	ArrayList<Swimmable> animalsArray;
	private Object Fish;
	
	JPanelDecorator(Iterator <Swimmable> animalsIterator,final JFrame frame){
		this.animalsIterator = animalsIterator;
		animalsArray = new ArrayList();

		setVisible(true);
		setBounds(100, 100, 564, 241);
		setLayout(null);
		
		// ScrollPane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(62, 27, 440, 93);
		add(scrollPane);

		// Table
		final JTable table = new JTable();
		scrollPane.setViewportView(table);

		// Model for Table
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		model.addColumn("Name");
		model.addColumn("Color");
		model.addColumn("Size");
		model.addColumn("Horizontal Speed");
		model.addColumn("Vertical Speed");

		int row = 0;
		while(animalsIterator.hasNext())
		{   
			Swimmable animal = animalsIterator.next();
			animalsArray.add(animal);
			model.addRow(new Object[0]);
			model.setValueAt(animal.getAnimalName(), row, 0);
			if(animal instanceof Jellyfish){
				model.setValueAt(((Jellyfish)animal).getColor(), row, 1);
			}
			else
				model.setValueAt(((Fish)animal).getColor(), row, 1);
			model.setValueAt(animal.getSize(), row, 2);
			model.setValueAt(animal.getHorSpeed(), row, 3);
			model.setValueAt(animal.getVerSpeed(), row, 4);
			row++;
		}
	
		// Button  OK
		JButton btnOk = new JButton("Change Color");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if(index > -1){
					ChooseColor(animalsArray.get(index));
					frame.dispose();
				}
				else { JOptionPane.showMessageDialog(null, "You need to choose some animal.");}
		}
		});
		btnOk.setBounds(176, 151, 83, 23);
		add(btnOk);
		
		// Button Cancel
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				}
			});
		btnCancel.setBounds(292, 151, 89, 23);
		add(btnCancel);
	}
	
	public void ChooseColor(Swimmable animal){
		
		MarineAnimal obj;
		
		 if(animal instanceof Jellyfish){
			 Jellyfish jellyfish = ((Jellyfish) animal);
			 obj = new MarinaAnimalDecorator(jellyfish);
		 }
		 else{
			 Fish fish = ((Fish)animal);
			 obj = new MarinaAnimalDecorator(fish);
		 }
		 Color initialBackground = getBackground();
		 Color background = JColorChooser.showDialog(null, "Change Animal Background",initialBackground);
	        if (background != null) { 
	        	obj.PaintFish(background);
	        }
	}
}
