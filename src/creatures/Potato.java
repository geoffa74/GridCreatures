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
        //setImage(new Image("https://lh3.googleusercontent.com/-qRmDvvvSw70/V1JQ54e1lyI/AAAAAAAAAXs/INTs7fIUgug9O8UmuKeeUy9xJgE4TnkGw/w426-h409/Potato.png"));
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("D:/MyDocs/Downloads/MurderousPotato.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        setImage(new Image(fis));

        setColor(Color.WHITE);
    }

    @Override
    public void act() {
        int x=0-getX();
        int y=1;
        System.out.println(x);
        System.out.println(getX());
        System.out.println(y);
        for (int i=0;(i,y+2);i++){
            kill(x,y);
            x++;
        }
        move(0, 1);
    }
}
