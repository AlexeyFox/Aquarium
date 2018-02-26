/**@version HomeWork 4
 * @author Ekaterina Lezepikov 324623602
 * @author Kassandra Amerzoyev 314129636
 */


import java.awt.Color;

public class PlantFactory implements AbstractSeaFactory{

	@Override
	public SeaCreature produceSeaCreature(String type, Color col, int size, int x, int y,int frequency, AquaPanel panel) {
		SeaCreature plant = null;
		if(type.equals("Zostera"))
			plant = new Zostera(type,col,size,x,y,panel);
		else if(type.equals("Laminaria"))
			plant = new Laminaria(type,col,size,x,y,panel);		
		return plant;
	}

}
