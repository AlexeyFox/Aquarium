
import javax.swing.JOptionPane;

public class Satiated implements HungerState{

	public void doAction(Context context) { context.setState(this);	}
	public String toString(){return "Satiated";}
}
