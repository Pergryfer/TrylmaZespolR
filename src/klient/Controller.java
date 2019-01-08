package klient;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import sample.PlanszaGwiazda;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class Controller {
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
        zasady.setOnAction(event -> {

        });

        stworz.setOnAction(event -> {
            lGraczy = (int) Math.round(gracze.getValue());
            lBotow = (int) Math.round(boty.getValue());
            try {
                if(Klient.ustawLiczbeGraczy(lGraczy, lBotow)) {
                    Klient.primaryStage.close();
                    planszaKlient = new PlanszaKlient(lGraczy+lBotow);

                    OknoPlanszy oknoPlanszy = new OknoPlanszy();
                    Scene scene = new Scene(oknoPlanszy.pane, 1000, 680);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } else {
                    System.out.println("Nie wchodzi");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public int getlGraczy() {
        return lGraczy;
    }

    public int getlBotow() {
        return lBotow;
    }

    public static PlanszaKlient getPlanszaKlient() {
        return planszaKlient;
    }
}
