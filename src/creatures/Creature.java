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
    private boolean isInitialized = false;

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

    public boolean spawn(Creature creature, int x, int y) {
	if (isValidLocation(this.x + x, this.y + y) && creatures[this.x + x][this.y + y] == null) {
	    creature.setLocation(this.x + x, this.y + y);
	    creatures[this.x + x][this.y + y] = creature;
	    return true;
	} else {
	    return false;
	}
    }
    
    private void setLocation(int x, int y) {
	this.x = x;
	this.y = y;
    }
    
    public void initializeLocation(int x, int y) {
	if(!isInitialized) {
	    this.x = x;
	    this.y = y;
	    isInitialized = true;
	}
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
