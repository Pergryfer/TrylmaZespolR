package klient;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.MyScene;

import java.io.IOException;


public class Controller {
    @FXML
    private Button dolacz;
    @FXML
    private Button zasady;
    @FXML
    private Button stworz;
    @FXML
    private Slider gracze;
    @FXML
    private Slider boty;

    private int lGraczy;
    private int lBotow;
    private static PlanszaKlient planszaKlient;


    public Controller(){

    }

    @FXML
    public void initialize() {

        dolacz.setOnAction(event -> {
            Klient.dolacz();
        });

        zasady.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Zasady");
            alert.setHeaderText(null);
            alert.setContentText("Zasady znajdziesz tutaj : https://www.wikihow.com/Play-Chinese-Checkers");

            alert.showAndWait();
        });

        stworz.setOnAction(event -> {
            lGraczy = (int) Math.round(gracze.getValue());
            lBotow = (int) Math.round(boty.getValue());
            try {
                if(Klient.ustawLiczbeGraczy(lGraczy, lBotow)) {
                    Klient.primaryStage.close();
                    planszaKlient = new PlanszaKlient(lGraczy+lBotow);


                    OknoPlanszy oknoPlanszy = new OknoPlanszy();
                    MyScene scene = new MyScene(oknoPlanszy.pane, 1000, 680);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    Klient.wyslijWiadomosc(scene);
                    //stage.initStyle(StageStyle.UNDECORATED);
                    stage.show();


                } else {
                    System.out.println("Nie wchodzi");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static PlanszaKlient getPlanszaKlient() {
        return planszaKlient;
    }
}
