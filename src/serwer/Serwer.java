package serwer;

import sample.PlanszaGwiazda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class Serwer {
    private ServerSocket serverSocket = null;
    private int lGraczy;
    private int lBotow;
    private PlanszaGwiazda plansza = null;
    private ArrayList<KlientWatek> klienci = new ArrayList<>();
    private ArrayList<GraWatek>  gry = new ArrayList<>();
    private Random losowa = new Random();
    private boolean watekSpi = false;

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
                klienci.get(klienci.lastIndexOf(klient)).start();
                System.out.println("Klient " + klienci.size() + " sie polaczyl");
            }
        } finally {
            serverSocket.close();
        }
    }

    private  class KlientWatek extends Thread{
        private Socket socket;
        private BufferedReader in;
        private  PrintWriter out;

        public KlientWatek(Socket socket){
            System.out.println("Tworze nowy watek");
            this.socket = socket;
        }

        private String obslugaWiadomosci(String wiadomosc){

            String[] rozdzielonaWiadomosc = wiadomosc.trim().split("\\s+");

                switch (rozdzielonaWiadomosc[0]){
                case "iloscGraczy":
                    lGraczy = Integer.parseInt(rozdzielonaWiadomosc[1]);
                    lBotow = Integer.parseInt(rozdzielonaWiadomosc[2]);
                    System.out.println("Komenda: " + rozdzielonaWiadomosc[0]);
                    //utworzenie planszy zaleznie od ilosci graczy
                    /*if(lGraczy == 1 && lBotow ==1){
                        rozpocznijGre();
                    } */
                    if((lGraczy + lBotow == 6  || lGraczy + lBotow == 4 || lGraczy + lBotow == 2) && lGraczy>0) {
                        plansza = new PlanszaGwiazda(lGraczy + lBotow);
                    }
                    return "wykonano";

                case "ruch": // kolejnosc rzad1 kol1 rzad2 kol2
                    System.out.println("Komenda: " + rozdzielonaWiadomosc[0]);
                    int rzad1 = Integer.parseInt(rozdzielonaWiadomosc[1]);
                    int kol1 = Integer.parseInt(rozdzielonaWiadomosc[2]);
                    int rzad2 = Integer.parseInt(rozdzielonaWiadomosc[3]);
                    int kol2 = Integer.parseInt(rozdzielonaWiadomosc[4]);
                    if(plansza.ruszPionek(rzad1, kol1, rzad2, kol2)){
                        return "poprawny";
                    } else {
                        return "niePoprawny";
                    }
                case "dolacz": // po kliknieciu wlaczenia
                    System.out.println("Komenda: " + rozdzielonaWiadomosc[0]);
                    //warunek trzeba gdy za duzo ludzi
                    if(lGraczy == klienci.size()){
                        //rozpocznij Gre
                        rozpocznijGre();
                    }
                    return "wykonano";
                case "czyPierwszy":
                    System.out.println("Komenda: " + rozdzielonaWiadomosc[0]);
                    if(klienci.indexOf(this) == 0) {
                        return "true";
                    } else {
                        return "false";
                    }
                case "wyjdz":
                    System.out.println("Komenda: " + rozdzielonaWiadomosc[0]);
                    klienci.remove(this);
                    return "wykonano";

                case "koniecTury":
                    System.out.println("Komenda: " + rozdzielonaWiadomosc[0]);
                    //blokowanie innym ruchu w FX
                    plansza.nowaTura();
                    return "wykonano";
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
                in = new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()));
                out = new PrintWriter(
                        socket.getOutputStream(), true);
                while(true) {
                    for (String linia; (linia = in.readLine()) != null; ) {
                        String odpowiedz = obslugaWiadomosci(linia);
                        out.println(odpowiedz);
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

    private class GraWatek extends Thread{

    }

    public static void main(String[] args) throws IOException{
   //     ServerSocket serverSocket = new ServerSocket(9091);
        Serwer serwer = new Serwer(new ServerSocket(9092));
        serwer.nasluchiwanie();
    }
}
