package sample;

import javafx.scene.shape.Circle;

public class Pionek extends Circle{
    public int id;
    public int x;
    public int y;
    public static int kliknietyPionek;
    Kolor kolor;



    public Pionek( int id, double promien, Kolor kolor) {
        super(promien);
        this.id = id;
        this.kolor = kolor;
        this.setOnMouseClicked((event) -> {
            kliknietyPionek = this.id;
            System.out.println(this.kolor);
        });
    }


    public Pionek getIt(){
        return this;
    }

    public int getIdd(){
        return id;
    }

    public Kolor getKolor(){
        return kolor;
    }
}
