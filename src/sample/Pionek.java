package sample;

import javafx.scene.shape.Circle;

public class Pionek extends Circle{
    int id;
    public static int


    public Pionek(int id) {
        super();
        this.id = id;
    }

    public Pionek( int id, double promien) {
        super(promien);
        this.id = id;
        this.setOnMouseClicked((event) -> {
            System.out.println(this);
        });
    }
}
