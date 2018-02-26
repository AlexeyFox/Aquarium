
import java.util.ArrayList;
import java.util.List;

public class Memento {
	
	private List<Object> state = new ArrayList<Object>();
	
	Memento(List<Object> state){this.state = state;}	
	///Get State///	
	public List<Object> getState(){return state;}
}
