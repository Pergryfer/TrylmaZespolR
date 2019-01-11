package sample;

import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.Serializable;

public class MyScene extends Scene implements Serializable {
    public MyScene(Parent root, double width, double height){
        super(root, width, height);
    }
}
