package structure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GridCreaturesUI extends Application {

    private List<String> creatureTypes;
    private String currentCreatureType;
    private Grid grid;
    private int gridWidth = DEFAULT_GRID_WIDTH;
    private int gridHeight = DEFAULT_GRID_HEIGHT;
    private int windowWidth;
    private int windowHeight;
    private static final int DEFAULT_GRID_WIDTH = 10;
    private static final int DEFAULT_GRID_HEIGHT = 10;

    @Override
    public void start(Stage primaryStage) {
	
	readConfig();

	Canvas canvas = new Canvas(windowWidth, windowHeight);
	canvas.setOnMouseReleased(new EventHandler<MouseEvent>() {

	    @Override
	    public void handle(MouseEvent e) {
		grid.addCreature(e.getX(), e.getY(), currentCreatureType);
	    }

	});
	GraphicsContext g = canvas.getGraphicsContext2D();
	grid = new Grid(g, canvas.getWidth(), canvas.getHeight(), gridWidth, gridHeight);

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
	    }

	});
	
	MenuItem runItem = new MenuItem("Run");
	runItem.setOnAction(new EventHandler<ActionEvent>() {

	    @Override
	    public void handle(ActionEvent event) {
		TextInputDialog dialog = new TextInputDialog("walter");
		dialog.setTitle("Interval");
		dialog.setHeaderText("Interval Input");
		dialog.setContentText("Please enter an interval in ms: ");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
		    grid.run(Integer.parseInt(result.get()));
		}
	    }

	});
	
	MenuItem stopItem = new MenuItem("Stop");
	stopItem.setOnAction(new EventHandler<ActionEvent>() {

	    @Override
	    public void handle(ActionEvent event) {
		grid.stop();
	    }

	});
	
	MenuItem resetItem = new MenuItem("Reset");
	resetItem.setOnAction(new EventHandler<ActionEvent>() {

	    @Override
	    public void handle(ActionEvent event) {
		grid.reset();
	    }

	});

	gridMenu.getItems().addAll(actItem, runItem, stopItem, resetItem);

	Menu creatureMenu = new Menu("Creature");
	List<MenuItem> creatureMenuItems = new LinkedList<MenuItem>();
	MenuItem temp;
	for (String name : creatureTypes) {
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

	Scene scene = new Scene(root, windowWidth, windowHeight);

	primaryStage.setTitle("Test");
	primaryStage.setScene(scene);
	primaryStage.show();

    }
    
    private void readConfig() {
	try {
	    BufferedReader reader = new BufferedReader(new FileReader("config.txt"));
	    String line = reader.readLine();
	    while(line != null) {
		parseParameter(line);
		line = reader.readLine();
	    }
	} catch (FileNotFoundException e) {
	    
	} catch (IOException e) {
	    
	}
    }
    
    private void parseParameter(String line) {
	String[] data = line.split(":");
	switch(data[0].trim()) {
	case "windowWidth":
	    windowWidth = Integer.parseInt(data[1].trim());
	    break;
	case "windowHeight":
	    windowHeight = Integer.parseInt(data[1].trim());
	    break;
	case "gridWidth":
	    gridWidth = Integer.parseInt(data[1].trim());
	    break;
	case "gridHeight":
	    gridHeight = Integer.parseInt(data[1].trim());
	    break;
	case "creatureList":
	    String[] types = data[1].split(",");
	    creatureTypes = new LinkedList<String>();
	    for(String type: types) {
		creatureTypes.add(type.trim());
	    }
	    break;
	}
    }

}