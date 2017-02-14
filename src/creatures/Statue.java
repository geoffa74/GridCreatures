package creatures;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Statue extends Creature {


    public Statue(){
        setPicture(true);
    }

    private void setPicture(boolean picture){
        if (picture) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream("Pictures\\weeping angel.png");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            setImage(new Image(fis));

            setColor(Color.WHITE);
        }
    }

    @Override
    public void act() {
	
    }

}
