

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PrintDialog extends JDialog{

	private static final long serialVersionUID = 1L;
	AquaPanel obj;
	ArrayList<Swimmable> animalsArray;
	
	public PrintDialog(HashSet<Swimmable> animals, final AquaPanel obj){
		this.obj = obj;
		setBounds(100, 100, 564, 241);
		setTitle("Choose " + "Animal");
		getContentPane().setLayout(null);
		animalsArray = new ArrayList<Swimmable>();
		
		// ScrollPane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(62, 27, 440, 93);
		getContentPane().add(scrollPane);

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

		Iterator <Swimmable> animalsIterator = animals.iterator();

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
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if(index > -1){
					obj.Clone_Animal(animalsArray.get(index));
					dispose();
				}
				else { JOptionPane.showMessageDialog(obj, "You need to choose some animal.");}
		}
		});
		btnOk.setBounds(176, 151, 83, 23);
		getContentPane().add(btnOk);
		
		// Button Cancel
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				}
			});
		btnCancel.setBounds(292, 151, 89, 23);
		getContentPane().add(btnCancel);
	}
}
