package klient;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import sample.MyScene;

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
    private static Scene plansza;

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
            return true;
        }
        return false;

    }

    public static String wyslijWiadomosc(Object wiadomosc) throws IOException{
        try {

            out.writeObject(wiadomosc);
            out.flush();
            Object odpowiedz = in.readObject();
            if(odpowiedz instanceof String){
                System.out.println("Klient: " + wiadomosc);
                System.out.println("Serwer: " + odpowiedz);
                return (String) odpowiedz;
            } else if(odpowiedz instanceof MyScene){
                //Obługa wlaczania Pane'a



                plansza = (MyScene)odpowiedz;
                primaryStage.setScene(plansza);



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
        try{
            String odpowiedz = wyslijWiadomosc("koniecTury");
            if(odpowiedz.equals("czekaj")){
                //okno czekania
                wyslijWiadomosc("czekam"); // odpowiedz to bedzie Pane
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException{
        launch(args);
        System.exit(0);
    }
}
