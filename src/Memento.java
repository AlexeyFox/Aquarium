/**@version HomeWork 4
 * @author Ekaterina Lezepikov 324623602
 * @author Kassandra Amerzoyev 314129636
 */


import java.util.ArrayList;
import java.util.List;

public class Memento {
	
	private List<Object> state = new ArrayList<Object>();
	
	Memento(List<Object> state){this.state = state;}	
	///Get State///	
	public List<Object> getState(){return state;}
}
