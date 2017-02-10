package creatures;

import javafx.scene.paint.Color;

public class Test extends Creature {

    public Test() {
	setColor(Color.BLACK);
    }

    @Override
    public void act() {
	int movements = (int) (Math.random() * getGridWidth());
	int x;
	int y;
	for(int i = 0; i < movements; i++) {
	    x = (int) (Math.random() * 3) - 1;
	    y = (int) (Math.random() * 3) - 1;
	    kill(x,y);
	    move(x,y);
	}
    }

}
