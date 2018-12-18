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
import java.net.Socket;
import java.util.ArrayList;

public class Klient extends Application {

 //   static ArrayList<MyPane> pola = new ArrayList<MyPane>();
//    static ArrayList<HBox> lista = new ArrayList<HBox>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("klientFXML.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1000, 680));
        primaryStage.show();
    }

    public Klient(){

    }
    public static void polaczDoSerwera() throws IOException{
        String serverAdress = JOptionPane.showInputDialog("Logowanie do serwera \n" +
                "na porcie 9090:");
        Socket s = new Socket(serverAdress, 9090);
        BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String answer = input.readLine();
        JOptionPane.showMessageDialog(null, answer);
    }



    public static void main(String[] args) throws IOException{
        polaczDoSerwera();
        launch(args);

        System.exit(0);
    }
}
