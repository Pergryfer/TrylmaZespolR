package klient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sample.MyPane;
import sample.PlanszaGwiazda;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Klient extends Application {

 //   static ArrayList<MyPane> pola = new ArrayList<MyPane>();
//    static ArrayList<HBox> lista = new ArrayList<HBox>();
    private static int lGraczy;
    private static int lBotow;
    private static Socket s;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("oknoStartowe.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 400, 140));
        primaryStage.show();
    }

    public Klient(){

    }
    public static void polaczDoSerwera() throws IOException{
        s = new Socket(InetAddress.getLocalHost(), 9090);
        BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String answer = input.readLine();
    }

    public static void ustawLiczbeGraczy(int lG, int lB){
        lGraczy = lG;
        lBotow = lB;
    }



    public static void main(String[] args) throws IOException{
        polaczDoSerwera();
        launch(args);




        System.exit(0);
    }
}
