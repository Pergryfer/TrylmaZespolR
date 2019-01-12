package klient;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import sample.MyScene;
import sample.PlanszaGwiazda;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Klient extends Application implements Serializable {
    private static Socket s = null;
    private static ObjectInputStream in;
    private static ObjectOutputStream out;
    public static Stage primaryStage;
    public static PlanszaKlient planszaKlient;
    static Scene scenaCzekania;
    static Stage stage;

 //   static ArrayList<MyPane> pola = new ArrayList<MyPane>();
//    static ArrayList<HBox> lista = new ArrayList<HBox>();

    public Klient(){

    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.polaczDoSerwera();
        Parent root = FXMLLoader.load(getClass().getResource("oknoStartowe.fxml"));
        primaryStage.setTitle("Hello Trylma");
        primaryStage.setScene(new Scene(root, 400, 135));
        primaryStage.show();

    }

    private void polaczDoSerwera() throws IOException {
        s = new Socket(InetAddress.getLocalHost(), 9092);
        out = new ObjectOutputStream(s.getOutputStream());
        in = new ObjectInputStream(new BufferedInputStream(s.getInputStream()));
     //   wyslijWiadomosc("dolacz");
    }

    public static boolean ustawLiczbeGraczy(int lG, int lB) throws IOException {
        if(wyslijWiadomosc("iloscGraczy " + lG + " " + lB).equals("wykonano")){
            planszaKlient = new PlanszaKlient(lG+lB);


            OknoPlanszy oknoPlanszy = new OknoPlanszy();
            MyScene scene = new MyScene(oknoPlanszy.pane, 1000, 680);
            stage = new Stage();
            stage.setScene(scene);
            //Klient.wyslijWiadomosc(scene);
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            return true;

        }
        return false;

    }

    public static String wyslijWiadomosc(String wiadomosc) throws IOException{
        try {
            out.writeObject(wiadomosc);
            out.flush();
            Object odpowiedz = in.readObject();
            if(odpowiedz instanceof String){
                System.out.println("Klient: " + wiadomosc);
                System.out.println("Serwer: " + odpowiedz);
                return (String) odpowiedz;
            } else if(odpowiedz instanceof Pane){
                //Obługa wlaczania Pane'a
                return "Pane dostarczony";
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
        return "Blad";
    }

    public static boolean ruszPionek(int rzad1, int kol1, int rzad2, int kol2){
        try {
            if(wyslijWiadomosc("ruch" + " " + rzad1 + " " + kol1 + " " + rzad2 + " " + kol2).equals("poprawny")) {
                return true;
            }else{
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void dolacz(){
        try {
            wyslijWiadomosc("dolacz");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void koniecTury(){
        new Thread(() -> Platform.runLater(() -> {
            String odpowiedz = null;
            try {
                odpowiedz = wyslijWiadomosc("koniecTury");

                stage.setScene(instancjaScenyCzekania());

                if(odpowiedz.equals("czekaj")) {
                    //okno czekania
                    wyslijWiadomosc("czekam"); // odpowiedz to bedzie Pane

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        })).start();

    }

    public static void main(String[] args) throws IOException{
        launch(args);
        System.exit(0);
    }

    public static PlanszaKlient getPlanszaKlient(){
        return planszaKlient;
    }

    private static Scene instancjaScenyCzekania(){
        if(scenaCzekania == null) {
            Pane pane = new Pane();
            pane.setPrefSize(1000,680);
            Text text = new Text("Oczekiwanie na twoją kolej...");
            pane.getChildren().add(text);
            text.setFont(Font.font(46));
            text.setLayoutY(359);
            text.setLayoutX(212);
            scenaCzekania = new Scene(pane);
            return scenaCzekania;
        } else {
            return scenaCzekania;
        }
    }
}
