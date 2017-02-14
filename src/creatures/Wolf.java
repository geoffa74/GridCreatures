package creatures;

public class Wolf extends Creature {

    @Override
    public void act() {
	move((int) (Math.random() * 3) - 1, (int) (Math.random() * 3) - 1);
	int x = (int) (Math.random() * 3) - 1;
	int y = (int) (Math.random() * 3) - 1;
	String creatureName = getCreatureName(x, y);
	if(!(x == 0 && y == 0) && creatureName != null && creatureName.equals("Rabbit")) {
	    kill(x, y);
	}
    }
    
    

}
