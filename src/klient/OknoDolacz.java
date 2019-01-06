package klient;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class OknoDolacz {

    private Pane pane;

    public OknoDolacz(){
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

        button.setOnAction(event -> {
            Klient.dolacz();

            OknoPlanszy oknoPlanszy = new OknoPlanszy();
            Scene scene = new Scene(oknoPlanszy.pane, 1000, 680);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        });
        return pane;
    }

    public Pane getPane() {
        return pane;
    }
}
