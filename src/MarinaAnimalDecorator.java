/**@version HomeWork 4
 * @author Ekaterina Lezepikov 324623602
 * @author Kassandra Amerzoyev 314129636
 */


import java.awt.Color;

public class MarinaAnimalDecorator implements MarineAnimal{
	MarineAnimal animal;
	
	MarinaAnimalDecorator(MarineAnimal animal){this.animal = animal;}	
	@Override
	public void PaintFish(Color col) {animal.PaintFish(col);}
}
