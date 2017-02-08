package creatures;

import javafx.scene.paint.Color;
import javafx.scene.image.Image;

public abstract class Creature {

    private Color color = Color.GRAY;
    private Creature[][] creatures;
    private int x;
    private int y;
    private int speed = 0;
    private boolean hasActed;
    private Image image = null;
    
    public Creature() {}
    
    public Creature(int x, int y) {
	this.x = x;
	this.y = y;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getX() {
	return x;
    }

    public int getY() {
	return y;
    }

    public void setActed(boolean hasActed) {
	this.hasActed = hasActed;
    }

    public boolean hasActed() {
	return hasActed;
    }

    public Color getColor() {
	return color;
    }

    public void setColor(Color color) {
	this.color = color;
    }

    public boolean kill(int x, int y) {
	if (isValidLocation(this.x + x, this.y + y) && creatures[this.x + x][this.y + y] != null) {
	    creatures[this.x + x][this.y + y] = null;
	    return true;
	} else {
	    return false;
	}
    }

    public abstract void act();

    public boolean move(int x, int y) {
	if (isValidLocation(this.x + x, this.y + y) && creatures[this.x + x][this.y + y] == null) {
	    creatures[this.x][this.y] = null;
	    this.x += x;
	    this.y += y;
	    creatures[this.x][this.y] = this;
	    return true;
	} else {
	    return false;
	}
    }

    public boolean spawn(String creatureName, int x, int y) {
	if (isValidLocation(this.x + x, this.y + y) && creatures[this.x + x][this.y + y] == null) {
	    Creature creature;
	    try {
		creature = (Creature) Class.forName("creatures." + creatureName).newInstance();
		creature.setActed(true);
		creature.setLocation(this.x + x, this.y + y);
		creatures[this.x + x][this.y + y] = creature;
	    } catch (Exception e){
		return false;
	    }
	    return true;
	} else {
	    return false;
	}
    }
    
    private void setLocation(int x, int y) {
	this.x = x;
	this.y = y;
    }
    
    private boolean isValidLocation(int x, int y) {
	return x >= 0 && x < creatures.length && y >= 0 && y < creatures[0].length;
    }

    public void giveCurrentCreatures(Creature[][] creatures) {
	this.creatures = creatures;
    }

    protected void setSpeed(int speed) {
	this.speed = speed;
    }

    public int getSpeed() {
	return speed;
    }
    
    protected int getGridWidth() {
	return creatures.length;
    }
    
    protected int getGridHeight() {
	return creatures[0].length;
    }
    
    protected String getCreatureName(int x, int y) {
	if (isValidLocation(this.x + x, this.y + y) && creatures[this.x + x][this.y + y] != null) {
	    return creatures[this.x + x][this.y + y].getClass().getSimpleName();
	} else {
	    return null;
	}
    }

}
