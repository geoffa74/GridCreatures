import java.util.Map;

import javafx.scene.paint.Color;

public abstract class Creature {
    
    private Color color = Color.GRAY;
    private Creature[][] creatures;
    private int x;
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private int y;
    private int speed;
    boolean hasActed;
    
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
	if(isValidLocation(this.x + x, this.y + y) && creatures[this.x + x][this.y + y] != null) {
	    creatures[this.x + x][this.y + y] = null;
	    return true;
	} else {
	    return false;
	}
    }
    
    public abstract void act();
    
    public boolean move(int x, int y) {
	if(isValidLocation(this.x + x, this.y + y) && creatures[this.x + x][this.y + y] == null) {
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
	if(isValidLocation(this.x + x, this.y + y) && creatures[this.x + x][this.y + y] == null) {
	    creature.setX(this.x + x);
	    creature.setY(this.y + y);
	    creatures[this.x + x][this.y + y] = creature;
	    return true;
	} else {
	    return false;
	}
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
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
    
}
