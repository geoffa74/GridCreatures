package creatures;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by cumminsmt on 2/2/2017.
 */
public class Potato extends Creature {

    public Potato() {
      setPicture(true);
    }

    private void setPicture(boolean picture){
        if (picture) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream("Pictures\\EvilPotato.png");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            setImage(new Image(fis));

            setColor(Color.WHITE);
        }
    }

    @Override
    public void act() {
        int x=0-getX();
        int y=1;
        if (getY()<getGridHeight()-1) {
            for (int i = 0; i < getGridWidth(); i++) {
                String creatureName = getCreatureName(x,y);
                if (!"Potato".equals(creatureName)) {
                    kill(x, y);
                }
                x++;
            }
            move(0,1);
        }else {
            kill(0,0);
        }
    }
}
