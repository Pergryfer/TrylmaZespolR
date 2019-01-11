package serwer;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import sample.Kolor;
import sample.PlanszaGwiazda;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class Serwer {
    private ServerSocket serverSocket = null;
    private ArrayList<KlientWatek> klienci = new ArrayList<>();
    private ArrayList<Rozgrywka>  gry = new ArrayList<>();
    private Random losowa = new Random();


    public Serwer( ServerSocket ss){
        this.serverSocket = ss;
        System.out.println("Serwer Uruchomiony");
        System.out.println("Port: " + ss.getLocalPort());
        System.out.println("bind address: " + ss.getInetAddress());
    }

    private void nasluchiwanie() throws IOException {
        boolean serwerDziala = true;
        try {
            while (serwerDziala) {
                Socket socket = serverSocket.accept();
                KlientWatek klient = new KlientWatek(socket);
                klienci.add(klient);
                //klienci.get(klienci.lastIndexOf(klient)).setD;
                System.out.println("Klient " + klienci.size() + " sie polaczyl");
            }
        } finally {
            serverSocket.close();
        }
    }

    private  class KlientWatek extends Thread implements Serializable {
        private Socket socket;
        private ObjectInputStream in;
        private ObjectOutputStream out;
        private int indexGry;
        private Kolor kolor;
        private volatile boolean mojaKolej = false;

        public KlientWatek(Socket socket){
            System.out.println("Tworze nowy watek");
            this.socket = socket;
        }


        private String obslugaWiadomosci(String wiadomosc){

            String[] rozdzielonaWiadomosc = wiadomosc.trim().split("\\s+");

                switch (rozdzielonaWiadomosc[0]){
                case "iloscGraczy":
                    System.out.println("Komenda: " + rozdzielonaWiadomosc[0]);

                    int lGraczy = Integer.parseInt(rozdzielonaWiadomosc[1]);
                    int lBotow = Integer.parseInt(rozdzielonaWiadomosc[2]);

                    if((lGraczy + lBotow == 6  || lGraczy + lBotow == 4 || lGraczy + lBotow == 2) && lGraczy > 0) {
                        Rozgrywka gra = new Rozgrywka(lGraczy, lBotow);
                        gry.add(gra);
                        this.indexGry = gry.indexOf(gra);
                        return "wykonano";
                    }
                    return "nieWykonano";

                case "ruch": // kolejnosc rzad1 kol1 rzad2 kol2
                    System.out.println("Komenda: " + rozdzielonaWiadomosc[0]);
                    int rzad1 = Integer.parseInt(rozdzielonaWiadomosc[1]);
                    int kol1 = Integer.parseInt(rozdzielonaWiadomosc[2]);
                    int rzad2 = Integer.parseInt(rozdzielonaWiadomosc[3]);
                    int kol2 = Integer.parseInt(rozdzielonaWiadomosc[4]);
                    if(gry.get(indexGry).wykonajRuch(rzad1, kol1, rzad2, kol2, klienci.indexOf(this))){
                        return "poprawny";
                    } else {
                        return "niePoprawny";
                    }
                case "dolacz": // Proba dolaczenia
                    System.out.println("Komenda: " + rozdzielonaWiadomosc[0]);
                    for(Rozgrywka gra : gry ){
                        if(gra.czyPelnaGra()){
                            gra.dolacz(this);
                            return "dolaczono";
                        }
                    }
                    return "brak Miejsc";

                case "wyjdz":
                    System.out.println("Komenda: " + rozdzielonaWiadomosc[0]);
                    gry.get(indexGry).opuscRozgrywke(this);
                    klienci.remove(this);
                    return "wykonano";

                case "koniecTury":
                    System.out.println("Komenda: " + rozdzielonaWiadomosc[0]);
                    //blokowanie innym ruchu w FX
                    gry.get(indexGry).koniecTury(kolor);
                    return "czekaj";
                case "czekam":
                    System.out.println("Komenda: " + rozdzielonaWiadomosc[0]);

                    //czekanie aż będzie pane do zwrócenia
                    while (!mojaKolej) {
                        Thread.onSpinWait();
                    }
                    return "wyslijScene";

                default:
                    System.out.println("Brak Komendy: " + rozdzielonaWiadomosc[0]);
                    return "blad";
            }
        }

        public void rozpocznijGre(){
            for(int i=0; i<losowa.nextInt(6);i++){
              //  obudzNastepnyWatek(i%(lGraczy+lBotow));
            }
        }

        public void run(){
            try{
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                while(true) {
                    try {
                       Object wiadomosc = in.readObject();
                       if(wiadomosc instanceof String){
                           String odpowiedz = obslugaWiadomosci((String) wiadomosc);
                           if(odpowiedz.equals("wyslijScene")){
                               out.writeObject(gry.get(indexGry).scene);
                               out.flush();
                           }
                           out.writeObject(odpowiedz);
                           out.flush();
                       } else if(wiadomosc instanceof Scene){
                           gry.get(indexGry).scene = (Scene) wiadomosc;

                       }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                }
            }catch(IOException e){
                e.printStackTrace();
            } finally {
                try{
                    System.out.println("Zamykam Socket");
                    socket.close();
                    klienci.remove(this);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            System.out.println("Koniec watku klienta ");
            klienci.remove(this);
        }
    }

    private class Rozgrywka     {
        private ArrayList<KlientWatek> gracze = new ArrayList<>();
        private int lGraczy;
        private int lBotow;
        private PlanszaGwiazda plansza;
        private Scene scene;


        public Rozgrywka(int lGraczy, int lBotow) {
            this.lGraczy = lGraczy;
            this.lBotow = lBotow;
            plansza = new PlanszaGwiazda(lGraczy + lBotow);
        }

        public void koniecTury(Kolor kolor){
            for(int i = 0; i < gracze.size()-1; i++){
                if(gracze.get(i).kolor == kolor){
                    gracze.get(i).mojaKolej = false;
                    gracze.get(i+1).mojaKolej = true;
                    plansza.nowaTura();
                }
            }
        }

        public boolean czyPelnaGra(){
            if(gracze.size()<lGraczy){
                return false;
            } else {
                return true;
            }
        }
        public boolean wykonajRuch(int rzad1, int kol1, int rzad2, int kol2, int indexGracza){
            if(plansza.ruszPionek(rzad1, kol1, rzad2, kol2)){
                return true;
            } else{
                return false;
            }
        }

        public void opuscRozgrywke(KlientWatek klient){
            gracze.remove(klient);
        }

        public void dolacz(KlientWatek klient){
            gracze.add(klient);
        }
    }

    public static void main(String[] args) throws IOException{
   //     ServerSocket serverSocket = new ServerSocket(9091);
        Serwer serwer = new Serwer(new ServerSocket(9092));
        serwer.nasluchiwanie();
    }
}
