/**@version HomeWork 4
 * @author Ekaterina Lezepikov 324623602
 * @author Kassandra Amerzoyev 314129636
 */

public class Context {
	private HungerState hungerstate ;
	
	Context(){ hungerstate = null;}
	public void setState(HungerState hungerstate){this.hungerstate = hungerstate;}
	public HungerState getState(){return hungerstate;}
}
