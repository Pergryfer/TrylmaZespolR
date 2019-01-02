package klient;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;


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


    public Controller(){

    }

    @FXML
    public void initialize() {
        zasady.setOnAction(event -> {

        });

        stworz.setOnAction(event -> {
            lGraczy = (int) Math.round(gracze.getValue());
            lBotow = (int) Math.round(boty.getValue());
           /* try {
                Klient.ustawLiczbeGraczy(lGraczy, lBotow);
            } catch (IOException e) {
                e.printStackTrace();
            } */
        });
    }

    public int getlGraczy() {
        return lGraczy;
    }

    public int getlBotow() {
        return lBotow;
    }

}
