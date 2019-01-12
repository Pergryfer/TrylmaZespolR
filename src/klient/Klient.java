package klient;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
            } else if(odpowiedz instanceof MyScene){
                //Obługa wlaczania sceny
                stage.setScene((MyScene) odpowiedz);
                stage.show();
                return "Scene dostarczony";
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
            if(wyslijWiadomosc("dolacz").equals("dolaczono")){
                //tutaj okno czekania czy tam grania zobacz czy dobrze uruchamiam powinnno być tu coś z OknoPlansza?
                stage = new Stage();
                stage.setScene(instancjaScenyCzekania());
                stage.show();
                czekaj();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void koniecTury(){
        String odpowiedz = null;

        try {
            odpowiedz = wyslijWiadomosc("koniecTury");
            stage.setScene(instancjaScenyCzekania());
            if(odpowiedz.equals("czekaj")) {
                //okno czekania
                czekaj();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void czekaj(){
        Task<Void> czekacz = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    while(!wyslijWiadomosc("czekam").equals("Scene dostarczony")) {
                        Thread.sleep(1000);
                    }

                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        new Thread(czekacz).start();
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
