

import java.awt.Color;

public class AnimalFactory implements AbstractSeaFactory{

	@Override
	public SeaCreature produceSeaCreature(String type,Color col,int size, int x,int y,int frequency ,AquaPanel panel) {
		SeaCreature animal = null;
		if(type.equals("Fish"))
			animal = new Fish(type,col,size,x,y,frequency,panel);
		else if(type.equals("Jellyfish"))
			animal = new Jellyfish(type,col,size,x,y,frequency,panel);		
		return animal;
	}

}
