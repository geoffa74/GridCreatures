import javafx.scene.paint.Color;

public class RandomCreature extends Creature {

    public RandomCreature() {
	setColor(new Color(Math.random(), Math.random(), Math.random(), 1));
    }
   
    @Override
    public void act() {
	setColor(new Color(Math.random(), Math.random(), Math.random(), 1));
	move((int)(Math.random() * 3) - 1, (int)(Math.random() * 3) - 1);
    }
}
