import javafx.scene.paint.Color;

public class TestCreature extends Creature {
    
    public TestCreature() {
	setColor(Color.BLUE);
    }
   
    @Override
    public void act() {
	spawn(new StatueCreature(),1,0);
	move(0,1);
    }

}
