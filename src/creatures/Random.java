package creatures;

import javafx.scene.paint.Color;

public class Random extends Creature {

    public Random() {
	setColor(new Color(Math.random(), Math.random(), Math.random(), 1));
    }

    @Override
    public void act() {
	setColor(new Color(Math.random(), Math.random(), Math.random(), 1));
	move((int) (Math.random() * 3) - 1, (int) (Math.random() * 3) - 1);
    }
}
