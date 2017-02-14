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
    private boolean isInitialized;

    public final Image getImage() {
        return image;
    }

    protected final void setImage(Image image) {
        this.image = image;
    }

    protected final int getX() {
	return x;
    }

    protected final int getY() {
	return y;
    }

    public final void setActed(boolean hasActed) {
	this.hasActed = hasActed;
    }

    public final boolean hasActed() {
	return hasActed;
    }

    public final Color getColor() {
	return color;
    }

    public final void setColor(Color color) {
	this.color = color;
    }

    public final boolean kill(int x, int y) {
	if (isValidLocation(this.x + x, this.y + y) && creatures[this.x + x][this.y + y] != null) {
	    creatures[this.x + x][this.y + y] = null;
	    return true;
	} else {
	    return false;
	}
    }

    public abstract void act();

    public final boolean move(int x, int y) {
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

    public final boolean spawn(String creatureName, int x, int y) {
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
    
    public final void initializeLocation(int x, int y) {
	if(!isInitialized) {
	    this.x = x;
	    this.y = y;
	    isInitialized = true;
	}
    }
    
    private final void setLocation(int x, int y) {
	this.x = x;
	this.y = y;
    }
    
    private final boolean isValidLocation(int x, int y) {
	return x >= 0 && x < creatures.length && y >= 0 && y < creatures[0].length;
    }

    public final void giveCurrentCreatures(Creature[][] creatures) {
	this.creatures = creatures;
    }

    protected final void setSpeed(int speed) {
	this.speed = speed;
    }

    public final int getSpeed() {
	return speed;
    }
    
    protected final int getGridWidth() {
	return creatures.length;
    }
    
    protected final int getGridHeight() {
	return creatures[0].length;
    }
    
    protected final String getCreatureName(int x, int y) {
	if (isValidLocation(this.x + x, this.y + y) && creatures[this.x + x][this.y + y] != null) {
	    return creatures[this.x + x][this.y + y].getClass().getSimpleName();
	} else {
	    return null;
	}
    }

}
