package creatures;

import javafx.scene.paint.Color;

public class GOL extends Creature {
    
    public GOL() {
	setColor(Color.BLACK);
    }
    
    @Override
    public void act() {
	setStates(getNextStates(getStates()));
    }
    
    private boolean[][] getStates() {
	boolean[][] states = new boolean[getGridWidth()][getGridHeight()];
	for(int i = 0; i < getGridWidth(); i++) {
	    for(int j = 0; j < getGridHeight(); j++) {
		if(getCreatureName(i - getX(), j - getY()) != null && getCreatureName(i - getX(), j - getY()).equals("GOL")) {
		    states[i][j] = true;
		} else {
		    states[i][j] = false;
		}
	    }
	}
	return states;
    }
    
    private int getNumberOfNeighbors(boolean[][] states, int x, int y) {
	int numberOfNeighbors = 0;
	if(isValidLocation(x + 1, y) && states[x + 1][y]) numberOfNeighbors++;
	if(isValidLocation(x, y + 1) && states[x][y + 1]) numberOfNeighbors++;
	if(isValidLocation(x + 1, y + 1) && states[x + 1][y + 1]) numberOfNeighbors++;
	if(isValidLocation(x - 1, y) && states[x - 1][y]) numberOfNeighbors++;
	if(isValidLocation(x, y - 1) && states[x][y - 1]) numberOfNeighbors++;
	if(isValidLocation(x - 1, y - 1) && states[x - 1][y - 1]) numberOfNeighbors++;
	if(isValidLocation(x - 1, y + 1) && states[x - 1][y + 1]) numberOfNeighbors++;
	if(isValidLocation(x + 1,y - 1) && states[x + 1][y - 1]) numberOfNeighbors++;
	return numberOfNeighbors;
    }
    
    private boolean isValidLocation(int x, int y) {
	return (x >= 0 && x < getGridWidth() && y >= 0 && y < getGridHeight());
    }
    
    private boolean[][] getNextStates(boolean[][] states) {
	boolean[][] nextStates = new boolean[getGridWidth()][getGridHeight()];
	int numberOfNeighbors;
	for(int i = 0; i < getGridWidth(); i++) {
	    for(int j = 0; j < getGridHeight(); j++) {
		numberOfNeighbors = getNumberOfNeighbors(states, i, j);
		if(numberOfNeighbors < 2 || numberOfNeighbors > 3) {
		    nextStates[i][j] = false;
		} else if(numberOfNeighbors == 3) {
		    nextStates[i][j] = true; 
		} else {
		    nextStates[i][j] = states[i][j];
		}
	    }
	}
	return nextStates;
    }
    
    private void setStates(boolean[][] nextStates) {
	int count = 0;
	for(int i = 0; i < getGridWidth(); i++) {
	    for(int j = 0; j < getGridHeight(); j++) {
		if(!(i == getX() && j == getY())) {
		    count++;
		    kill(i - getX(), j - getY());
		    if(nextStates[i][j]) {
			spawn("GOL", i - getX(), j - getY());
		    }
		}
	    }
	}
	if(!nextStates[getX()][getY()]) {
	    kill(0, 0);
    	}
    }
}
