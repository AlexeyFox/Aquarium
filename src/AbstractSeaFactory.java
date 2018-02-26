/**@version HomeWork 4
 * @author Ekaterina Lezepikov 324623602
 * @author Kassandra Amerzoyev 314129636
 */


import java.awt.Color;

public interface AbstractSeaFactory {
	public SeaCreature produceSeaCreature(String type,Color col,int size, int x,int y,int frequency,AquaPanel panel);
}
