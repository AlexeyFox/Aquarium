

import java.awt.Color;

public interface AbstractSeaFactory {
	public SeaCreature produceSeaCreature(String type,Color col,int size, int x,int y,int frequency,AquaPanel panel);
}
