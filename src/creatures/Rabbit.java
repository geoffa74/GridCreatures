package creatures;

import javafx.scene.image.Image;

public class Rabbit extends Creature {
    
    public Rabbit() {
	setImage(new Image("http://pix.iemoji.com/images/emoji/apple/ios-9/256/rabbit.png"));
    }

    @Override
    public void act() {
	move((int) (Math.random() * 3) - 1, (int) (Math.random() * 3) - 1);
	int x = (int) (Math.random() * 3) - 1;
	int y = (int) (Math.random() * 3) - 1;
	String creatureName = getCreatureName(x, y);
	if(!(x == 0 && y == 0) && creatureName != null && creatureName.equals("Rabbit")) {
	    spawn("Rabbit",(int) (Math.random() * 3) - 1, (int) (Math.random() * 3) - 1);
	}
    }

}
