import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Grid {

    private Creature[][] creatures;
    private double width;
    private double height;
    private int gridWidth;
    private int gridHeight;
    private GraphicsContext g;
    
    public Grid(GraphicsContext g, double width, double height, int gridWidth, int gridHeight) {
	this.width = width;
	this.height = height;
	this.gridHeight = gridHeight;
	this.gridWidth = gridWidth;
	this.g = g;
	creatures = new Creature[gridWidth][gridHeight];
	
    }
    
    public void addCreature(double x, double y, String creatureType) {
	int gridX = (int)Math.floor(x * gridWidth/width);
	int gridY = (int)Math.floor(y * gridHeight/height);
	creatures[gridX][gridY] = generateCreature(creatureType, gridX, gridY);
    }
    
    private Creature generateCreature(String creatureType, int x, int y) {
   	if(creatureType == null) {
   	    return null;
   	}
   	Creature creature;
   	switch(creatureType) {
   	case "Statue":
   	    creature = new StatueCreature();
   	    creature.setX(x);
   	    creature.setY(y);
   	    return creature;
   	case "Random":
   	    creature = new RandomCreature();
	    creature.setX(x);
	    creature.setY(y);
	    return creature;
   	case "Test":
   	    creature = new TestCreature();
	    creature.setX(x);
	    creature.setY(y);
	    return creature;
   	default:
   	    return null;
   	}
    }
    
    public void act() {
	Creature creature;
	do {
	    creature = getFastestCreature();
	    if(creature != null) {
		creature.giveCurrentCreatures(creatures);
	    	creature.act();
	    	creature.setActed(true);
	    }
	} while(creature != null);
	for(int i = 0; i < gridWidth; i++) {
	    for(int j = 0; j < gridHeight; j++) {
		if(creatures[i][j] != null) {
		    creatures[i][j].setActed(false);
		}
	    }
	}
    }
    
    private Creature getFastestCreature() {
	Creature fastest = null;
	for(int i = 0; i < gridWidth; i++) {
	    for(int j = 0; j < gridHeight; j++) {
		if(creatures[i][j] != null) {
		    if(!creatures[i][j].hasActed && fastest == null) {
			fastest = creatures[i][j];
		    } else {
			if(!creatures[i][j].hasActed && creatures[i][j].getSpeed() > fastest.getSpeed()) {
			    fastest = creatures[i][j];
			}
		    }
		}
	    }
	}
	return fastest;
    }
    
    public void run() {
	
    }
    
    public void changeGridSize(int gridWidth, int gridHeight) {
	this.gridWidth = gridWidth;
	this.gridHeight = gridHeight;
    }
    
    private void drawGrid() {
	g.setFill(Color.BLACK);
	for(int i = 0; i <= gridWidth; i++) {
	    double deltaX = width/gridWidth;
	    g.strokeLine(i * deltaX, 0, i * deltaX, height);
	}
	for(int j = 0; j <= gridHeight; j++) {
	    double deltaY = height/gridHeight;
	    g.strokeLine(0, j * deltaY, width, j * deltaY);
	}
    }
    
    private void drawCreatures() {
	double creatureWidth = width/gridWidth;
	double creatureHeight = height/gridHeight;
	for(int i = 0; i < gridWidth; i++) {
	    for(int j = 0; j < gridHeight; j++) {
		if(creatures[i][j] != null) {
		    g.setFill(creatures[i][j].getColor());
		    g.fillRect(i * creatureWidth, j * creatureHeight, creatureWidth, creatureHeight);
		}
	    }
	}
    }
    
    public void draw() {
	g.setFill(Color.WHITE);
	g.fillRect(0, 0, width, height);
	drawCreatures();
	drawGrid();
    }

}
