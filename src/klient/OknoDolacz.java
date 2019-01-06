package klient;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class OknoCzekania {

    private Pane pane;

    public OknoCzekania(){
        pane = stworzOkno();
    }

    Pane stworzOkno(){
        Pane pane = new Pane();
        pane.setPrefSize(400, 140);
        HBox hBox = new HBox();
        hBox.setPrefSize(400,140);
        hBox.setAlignment(Pos.CENTER);
        Button button = new Button("Dołącz");
        hBox.getChildren().add(button);
        pane.getChildren().add(hBox);


        return pane;
    }

    public Pane getPane() {
        return pane;
    }
}
