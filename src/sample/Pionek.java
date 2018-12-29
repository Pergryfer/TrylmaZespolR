package sample;

import javafx.scene.shape.Circle;

public class Pionek extends Circle{
    public int id;
    public int x;
    public int y;
    public static int kliknietyPionek;


    public Pionek(int id) {
        super();
        this.id = id;
    }

    public Pionek( int id, double promien) {
        super(promien);
        this.id = id;
        this.setOnMouseClicked((event) -> {
            //System.out.println(this.x + " " + this.y);
            kliknietyPionek = this.id;
        });
    }


    public Pionek getIt(){
        return this;
    }
}
