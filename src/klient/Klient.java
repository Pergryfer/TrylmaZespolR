package klient;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Klient extends Application {
    private Socket s = null;
    private PrintWriter out = null;
    private BufferedReader in = null;

 //   static ArrayList<MyPane> pola = new ArrayList<MyPane>();
//    static ArrayList<HBox> lista = new ArrayList<HBox>();

    public Klient(){
        try {
            this.polaczDoSerwera();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
         Parent root = FXMLLoader.load(getClass().getResource("oknoStartowe.fxml"));
         primaryStage.setTitle("Hello Trylma");
         primaryStage.setScene(new Scene(root, 400, 140));
         primaryStage.show();
    }

    private void polaczDoSerwera() throws IOException {
        this.s = new Socket(InetAddress.getLocalHost(), 9091);
        this.in = new BufferedReader (
                new InputStreamReader(s.getInputStream()));
        this.out = new PrintWriter (
                new OutputStreamWriter(s.getOutputStream()), true);
     //   wyslijWiadomosc("dolacz");
    }
    public  void ustawLiczbeGraczy(int lG, int lB) throws IOException {
        wyslijWiadomosc("iloscGraczy " + lG + " " + lB);
    }
    public void wyslijWiadomosc(String wiadomosc) throws IOException{
        out.println(wiadomosc);
        String odpowiedz = in.readLine();
        System.out.println("Klient: " + wiadomosc);
        System.out.println("Serwer: " + odpowiedz);
    }



    public static void main(String[] args) throws IOException{
        Klient klient = new Klient();
        klient.ustawLiczbeGraczy(5,1);
        launch(args);
        System.exit(0);
    }
}
