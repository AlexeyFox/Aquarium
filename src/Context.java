

public class Context {
	private HungerState hungerstate ;
	
	Context(){ hungerstate = null;}
	public void setState(HungerState hungerstate){this.hungerstate = hungerstate;}
	public HungerState getState(){return hungerstate;}
}
