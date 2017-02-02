package structure;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GridCreaturesUI extends Application {
    
    private List<String> creatureTypes;
    private String currentCreatureType;
    private Grid grid;
    private static final int DEFAULT_GRID_WIDTH = 10;
    private static final int DEFAULT_GRID_HEIGHT = 10;
    
    @Override
    public void start(Stage primaryStage) {
        
	Canvas canvas = new Canvas(1000, 800);
	canvas.setOnMouseReleased(new EventHandler<MouseEvent>() {

	    @Override
	    public void handle(MouseEvent e) {
		grid.addCreature(e.getX(), e.getY(), currentCreatureType);
		grid.draw();
	    }

	    
	});
	GraphicsContext g = canvas.getGraphicsContext2D();
	grid = new Grid(g, canvas.getWidth(), canvas.getHeight(), DEFAULT_GRID_WIDTH, DEFAULT_GRID_HEIGHT);
	grid.draw();
	
        BorderPane root = new BorderPane();
        root.setCenter(canvas); 
                
        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        root.setTop(menuBar);
        
        Menu gridMenu = new Menu("Grid");
        
        MenuItem actItem = new MenuItem("Act");
        actItem.setOnAction(new EventHandler<ActionEvent>() {

	    @Override
	    public void handle(ActionEvent event) {
		grid.act();
		grid.draw();
	    }
            
        });
        
        gridMenu.getItems().add(actItem);
        
        generateCreatureList();
        
        Menu creatureMenu = new Menu("Creature");
        List<MenuItem> creatureMenuItems = new LinkedList<MenuItem>();
        MenuItem temp;
        for(String name: creatureTypes) {
            temp = new MenuItem(name);
            temp.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
		    currentCreatureType = name;
		}
        	
            });
            creatureMenuItems.add(temp);
        }
        creatureMenu.getItems().addAll(creatureMenuItems);
        menuBar.getMenus().addAll(gridMenu, creatureMenu);
        
        Scene scene = new Scene(root, 1000, 800);

        primaryStage.setTitle("Test");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    private void generateCreatureList() {
	creatureTypes = new LinkedList<String>();
	creatureTypes.add("Statue");
	creatureTypes.add("Random");
	creatureTypes.add("Test");
    }
    
    

}