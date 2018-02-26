/**@version HomeWork 4
 * @author Ekaterina Lezepikov 324623602
 * @author Kassandra Amerzoyev 314129636
 */


import java.util.ArrayList;
import java.util.List;

public class CareTaker {
	
	List<Memento> list;
	
	CareTaker(){ list = new ArrayList<Memento>();}
	
	public void add(Memento state){	list.add(state);}	
	public int getSize(){
		if(list!= null)
			return list.size();
		else return 0;
	}
	public Memento get(int id){	
		if( id < list.size())
			return list.get(id);
		return null;
	}
	
	public void Destroy(){list.clear();}
}
