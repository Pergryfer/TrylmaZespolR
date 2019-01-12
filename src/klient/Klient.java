package klient;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Kolor;
import sample.MyScene;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Optional;

public class Klient extends Application implements Serializable {
    private static Socket s = null;
    private static ObjectInputStream in;
    private static ObjectOutputStream out;
    public static Stage primaryStage;
    public static PlanszaKlient planszaKlient;
    static Scene scenaCzekania;
    static Stage stage;
    static ArrayList<ArrayList> listaPionkow;
    private static OknoPlanszy oknoPlanszy;
    static Scene scene;

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

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                primaryStage.setScene(new Scene(root, 400, 135));
                primaryStage.show();
            }
        });

    }


    private void polaczDoSerwera() throws IOException {
        s = new Socket(InetAddress.getLocalHost(), 9091);
        out = new ObjectOutputStream(s.getOutputStream());
        in = new ObjectInputStream(new BufferedInputStream(s.getInputStream()));
        //   wyslijWiadomosc("dolacz");
    }

    public static boolean ustawLiczbeGraczy(int lG, int lB) throws IOException {
        if(wyslijWiadomosc("iloscGraczy " + lG + " " + lB).equals("wykonano")){
            planszaKlient = new PlanszaKlient(lG+lB);
            //zamykanie okna startowego, inicjalizacja planszy
            primaryStage.close();

            oknoPlanszy = new OknoPlanszy();
            scene = new Scene(oknoPlanszy.pane, 1000, 680);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    stage = instancjaStage();
                    stage.setScene(scene);
                    stage.show();
                }
            });

            return true;

        }
        return false;

    }

    public synchronized static String wyslijWiadomosc(Object wiadomosc) throws IOException{
        try {
            out.writeObject(wiadomosc);
            out.flush();
            Object odpowiedz = in.readObject();
            if (odpowiedz instanceof ArrayList) {

                //Tutaj serwer wysyla w odpowiedzi Arrayliste.

                listaPionkow = (ArrayList<ArrayList>) odpowiedz;

                oknoPlanszy.setListaPionkow(listaPionkow);

                for (int i = -1; i < 17; i++) {
                    for (int j = 0; j < 25; j++) {
                        if (i == -1) {
                            if (j < 10) {
                                System.out.print("  " + j + " ");
                            } else {
                                System.out.print(" " + j + " ");
                            }
                        } else {
                            if (PlanszaKlient.pola[i][j] == 2) {
                                System.out.print("    ");
                            } else {
                                if (PlanszaKlient.pola[i][j] == 0) {
                                    System.out.print("[__]");
                                } else {
                                    System.out.print("[" + PlanszaKlient.pola[i][j] + "]");
                                }
                            }
                        }
                    }
                    System.out.println(i);
                }


                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        stage = (Stage)instancjaScenyCzekania().getWindow();
                        scene.setRoot(oknoPlanszy.pane);
                        stage.setScene(scene);
                        stage.show();
                    }
                });


                return "Array dostarczony";

            } else if(odpowiedz instanceof String){
                System.out.println("Klient: " + wiadomosc);
                System.out.println("Serwer: " + odpowiedz);
                return (String) odpowiedz;
            } else if(odpowiedz instanceof MyScene){
                //Obługa wlaczania sceny
                stage.setScene((MyScene) odpowiedz);
                stage.show();
                return "Scene dostarczony";
            } else if (odpowiedz instanceof Kolor) {
                Kolor kolor = (Kolor) odpowiedz;

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("KONIEC GRY");
                alert.setHeaderText(null);
                alert.setContentText("Wygrał gracz z kolorem : "+ kolor.toString());

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    System.exit(0);
                }

                return "koniec";


            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
        return "Blad";
    }

    public synchronized static boolean ruszPionek(int rzad1, int kol1, int rzad2, int kol2, Kolor kolor){
        try {
            if(wyslijWiadomosc("ruch" + " " + rzad1 + " " + kol1 + " " + rzad2 + " " + kol2 + " " + kolor.toString()).equals("poprawny")) {
                PlanszaKlient.pola[rzad2][kol2] = PlanszaKlient.pola[rzad1][kol1];
                PlanszaKlient.pola[rzad1][kol1] = 0;
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

                String odp = wyslijWiadomosc("przeslijIloscGraczy");
                int z = -1;

                try{
                    z = Integer.parseInt(odp);
                } catch (Exception e){
                    e.printStackTrace();
                }
                assert z != -1;

                planszaKlient = new PlanszaKlient(z);
                oknoPlanszy = new OknoPlanszy();
                scene = new Scene(oknoPlanszy.pane);

                primaryStage.close();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        stage = instancjaStage();
                        stage.setScene(instancjaScenyCzekania());
                        stage.show();
                    }
                });
                czekaj();

                //czekaj();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized static void koniecTury(){
        String odpowiedz = null;

        try {
            odpowiedz = wyslijWiadomosc("koniecTury");

            //zapisywanie stanu pionkow do listy, pozniej wyslanie do serwera i czekanie na swoja ture

            listaPionkow = new ArrayList<>();

            for (int i = 0; i < PlanszaKlient.pola.length; i++){
                ArrayList<Integer> list = new ArrayList<>();
                for (int j = 0; j < PlanszaKlient.pola[0].length; j++){
                    list.add(PlanszaKlient.pola[i][j]);
                }
                listaPionkow.add(list);
            }

            String odp = wyslijWiadomosc(listaPionkow);

            System.out.println("\n\n\n"+odp+"\n\n\n");



            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    instancjaStage().setScene(instancjaScenyCzekania());
                }
            });


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
                    while(!wyslijWiadomosc("czekam").equals("Array dostarczony")) {
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

    private static Stage instancjaStage(){
        if(stage == null){
            stage = new Stage();
            return stage;
        } else {
            return stage;
        }
    }

}