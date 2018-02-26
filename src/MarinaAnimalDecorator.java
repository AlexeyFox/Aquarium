


import java.awt.Color;

public class MarinaAnimalDecorator implements MarineAnimal{
	MarineAnimal animal;
	
	MarinaAnimalDecorator(MarineAnimal animal){this.animal = animal;}	
	@Override
	public void PaintFish(Color col) {animal.PaintFish(col);}
}
