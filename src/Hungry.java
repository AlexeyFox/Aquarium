

import javax.swing.JOptionPane;

public class Hungry implements HungerState{

	public void doAction(Context context) {context.setState(this);}
	public String toString(){return "Hungry"; }
}
