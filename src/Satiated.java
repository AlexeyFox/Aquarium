/**@version HomeWork 4
 * @author Ekaterina Lezepikov 324623602
 * @author Kassandra Amerzoyev 314129636
 */


import javax.swing.JOptionPane;

public class Satiated implements HungerState{

	public void doAction(Context context) { context.setState(this);	}
	public String toString(){return "Satiated";}
}
