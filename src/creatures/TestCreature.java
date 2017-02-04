package creatures;

import javafx.scene.image.Image;

public class TestCreature extends Creature {

    public TestCreature() {
	setImage(new Image("https://www.smashingmagazine.com/wp-content/uploads/2015/06/10-dithering-opt.jpg"));
    }

    @Override
    public void act() {
	System.out.println(getCreatureName(0,1));
    }

}
