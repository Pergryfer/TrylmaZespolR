package sample;

import javafx.scene.layout.VBox;

public class MyVBox extends VBox {
    public MyVBox() {
        this.setPrefSize(2,40);
    }
    public MyVBox(double width){
        this.setPrefSize(width, 40);
    }
}
