package serwer;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import sample.Kolor;
import sample.MyScene;
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
                klienci.get(klienci.lastIndexOf(klient)).start();
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
        private int indexGracza;
        private Kolor kolorGracza;

        public KlientWatek(Socket socket){
            System.out.println("Tworze nowy watek");
            this.socket = socket;
        }

        private synchronized String obslugaWiadomosci(String wiadomosc){

            String[] rozdzielonaWiadomosc = wiadomosc.trim().split("\\s+");

            switch (rozdzielonaWiadomosc[0]){
                case "iloscGraczy":
                    System.out.println("Komenda: " + rozdzielonaWiadomosc[0]);

                    int lGraczy = Integer.parseInt(rozdzielonaWiadomosc[1]);
                    int lBotow = Integer.parseInt(rozdzielonaWiadomosc[2]);

                    if((lGraczy + lBotow == 6  || lGraczy + lBotow == 4 || lGraczy + lBotow == 2) && lGraczy > 0) {
                        Rozgrywka gra = new Rozgrywka(lGraczy, lBotow);
                        gry.add(gra);
                        indexGry = gry.indexOf(gra);
                        indexGracza = 0;
                        kolorGracza = Kolor.CZERWONY;
                        gra.gracze.add(this);
                        return "wykonano";
                    }
                    return "nieWykonano";

                case "ruch": // kolejnosc rzad1 kol1 rzad2 kol2
                    System.out.println("Komenda: " + rozdzielonaWiadomosc[0]);
                    int rzad1 = Integer.parseInt(rozdzielonaWiadomosc[1]);
                    int kol1 = Integer.parseInt(rozdzielonaWiadomosc[2]);
                    int rzad2 = Integer.parseInt(rozdzielonaWiadomosc[3]);
                    int kol2 = Integer.parseInt(rozdzielonaWiadomosc[4]);
                    Kolor kolor = Kolor.valueOf(rozdzielonaWiadomosc[5]);
                    if(kolor.equals(kolorGracza) && gry.get(indexGry).wykonajRuch(rzad1, kol1, rzad2, kol2, klienci.indexOf(this))){
                        return "poprawny";
                    } else {
                        return "niePoprawny";
                    }
                case "dolacz": // Proba dolaczenia
                    System.out.println("Komenda: " + rozdzielonaWiadomosc[0]);
                    for(int i = 0; i < gry.size(); i++){
                        System.out.println("Gra " + i + " ma " + gry.get(i).getAktualnaLiczbaGraczy() + " graczy");
                        if(!gry.get(i).czyPelnaGra()){
                            gry.get(i).dolacz(this);
                            indexGry = i;
                            if (gry.get(indexGry).ktoWyszedl.size() != 0){
                                indexGracza = gry.get(indexGry).ktoWyszedl.get(0);
                                gry.get(indexGry).ktoWyszedl.remove(0);
                            } else {
                                indexGracza = gry.get(i).aktualnaLiczbaGraczy - 1;
                            }
                            System.out.println(indexGracza);
                            int iloscGraczy = gry.get(i).getWszyscyGracze();
                            if( iloscGraczy == 2 ){
                                kolorGracza = Kolor.BLEKITNY;
                            } else if(iloscGraczy == 4){
                                if(indexGracza == 1){
                                    kolorGracza = Kolor.ROZOWY;
                                } else if(indexGracza == 2){
                                    kolorGracza = Kolor.BLEKITNY;
                                } else if(indexGracza == 3){
                                    kolorGracza = Kolor.NIEBIESKI;
                                }
                            } else if(iloscGraczy == 6){
                                if(indexGracza == 1){
                                    kolorGracza = Kolor.ZOLTY;
                                } else if(indexGracza == 2){
                                    kolorGracza = Kolor.ROZOWY;
                                } else if(indexGracza == 3){
                                    kolorGracza = Kolor.BLEKITNY;
                                } else if(indexGracza == 4){
                                    kolorGracza = Kolor.ZIELONY;
                                } else if(indexGracza == 5){
                                    kolorGracza = Kolor.NIEBIESKI;
                                }
                            }
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
                    gry.get(indexGry).koniecTury(); //tu bedzie kolor
                    return "czekaj";

                case "czekam":

                    if(gry.get(indexGry).getTuraGracza() != indexGracza) {
                        return "czekajDalej";
                    } else {
                        return "wyslijArray";
                    }
                case "przeslijIloscGraczy":
                    return Integer.toString(gry.get(indexGry).lGraczy+gry.get(indexGry).lBotow);
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
                            if(odpowiedz.equals("wyslijArray")){




                                out.writeObject(gry.get(indexGry).getListaPionkow());
                                out.flush();


                            } else {
                                out.writeObject(odpowiedz);
                                out.flush();
                            }
                        } else if(wiadomosc instanceof MyScene){
                            gry.get(indexGry).scene = (MyScene) wiadomosc;
                            out.writeObject("wykonano");
                            out.flush();
                        } else if(wiadomosc instanceof ArrayList){

                            //tutaj ustawianie dla rozgrywki aktualnego arraya z pionkami

                            gry.get(indexGry).setListaPionkow((ArrayList<ArrayList<Integer>>) wiadomosc);



                            Kolor kolor = czyKoniec();
                            if (kolor != null){
                                out.writeObject(kolor);
                                out.flush();
                            } else {
                                out.writeObject("wykonano");
                                out.flush();
                            }

                        } else if (wiadomosc instanceof Kolor) {
                            out.writeObject(wiadomosc);
                            out.flush();
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

        private synchronized Kolor czyKoniec() {
            int czerwone = 0, blekitne = 0, rozowe = 0, niebieskie = 0, zolte = 0, zielone = 0;
            if (!gry.get(indexGry).blekitnyKoniec) {

                for (int i = 0; i < 4; i++) {
                    for (int j = 9; j < 16; j++) {
                        if (gry.get(indexGry).listaPionkow.get(i).get(j) > 19 && gry.get(indexGry).listaPionkow.get(i).get(j) < 30) {
                            blekitne++;
                        }
                    }
                }
                if (blekitne == 10) {
                    gry.get(indexGry).setKoniec(Kolor.BLEKITNY);
                    return Kolor.BLEKITNY;
                }
            }

            if(!gry.get(indexGry).czerwonyKoniec) {
                for (int i = 13; i < 17; i++) {
                    for (int j = 9; j < 16; j++) {
                        if (gry.get(indexGry).listaPionkow.get(i).get(j) > 9 && gry.get(indexGry).listaPionkow.get(i).get(j) < 20) {
                            czerwone++;
                        }
                    }
                }
                if (czerwone == 10) {
                    gry.get(indexGry).setKoniec(Kolor.CZERWONY);
                    return Kolor.CZERWONY;
                }
            }

            if(gry.get(indexGry).getAktualnaLiczbaGraczy() > 2) {
                if (!gry.get(indexGry).rozowyKoniec) {
                    for (int i = 4; i < 8; i++) {
                        for (int j = 0; j < 7; j++) {
                            if (gry.get(indexGry).listaPionkow.get(i).get(j) > 39 && gry.get(indexGry).listaPionkow.get(i).get(j) < 50) {
                                rozowe++;
                            }
                        }
                    }
                    if (rozowe == 10) {
                        gry.get(indexGry).setKoniec(Kolor.ROZOWY);
                        return Kolor.ROZOWY;
                    }
                }
                if (!gry.get(indexGry).niebieskiKoniec) {
                    for (int i = 9; i < 13; i++) {
                        for (int j = 18; j < 25; j++) {
                            if (gry.get(indexGry).listaPionkow.get(i).get(j) > 29 && gry.get(indexGry).listaPionkow.get(i).get(j) < 40) {
                                niebieskie++;
                            }
                        }
                    }
                    if (niebieskie == 10) {
                        gry.get(indexGry).setKoniec(Kolor.NIEBIESKI);
                        return Kolor.NIEBIESKI;
                    }
                }
            }

            if (gry.get(indexGry).aktualnaLiczbaGraczy > 4) {
                if (!gry.get(indexGry).zielonyKoniec) {
                    for (int i = 4; i < 8; i++) {
                        for (int j = 18; j < 25; j++) {
                            if (gry.get(indexGry).listaPionkow.get(i).get(j) > 59 && gry.get(indexGry).listaPionkow.get(i).get(j) < 70) {
                                zielone++;
                            }
                        }
                    }
                    if (zielone == 10) {
                        gry.get(indexGry).setKoniec(Kolor.ZIELONY);
                        return Kolor.ZIELONY;
                    }
                }
                if (!gry.get(indexGry).zoltyKoniec) {
                    for (int i = 9; i < 13; i++) {
                        for (int j = 0; j < 7; j++) {
                            if (gry.get(indexGry).listaPionkow.get(i).get(j) > 49 && gry.get(indexGry).listaPionkow.get(i).get(j) < 60) {
                                zolte++;
                            }
                        }
                    }
                    if (zolte == 10) {
                        gry.get(indexGry).setKoniec(Kolor.ZOLTY);
                        return Kolor.ZOLTY;
                    }
                }
            }
            return null;
        }
    }

    private class Rozgrywka {
        private ArrayList<KlientWatek> gracze = new ArrayList<>();
        private ArrayList<Bot> boty = new ArrayList<>();
        private int aktualnaLiczbaGraczy;
        private int turaGracza = 0;
        private int lGraczy;
        private int lBotow;
        private PlanszaGwiazda plansza = null;
        private MyScene scene;
        private ArrayList<ArrayList<Integer>> listaPionkow;
        boolean czerwonyKoniec = false;
        boolean blekitnyKoniec = false;
        boolean rozowyKoniec = false;
        boolean niebieskiKoniec = false;
        boolean zoltyKoniec = false;
        boolean zielonyKoniec = false;
        ArrayList<Integer> ktoWyszedl;


        public Rozgrywka(int lGraczy, int lBotow) {
            this.lGraczy = lGraczy;
            this.lBotow = lBotow;
            this.plansza = new PlanszaGwiazda(lGraczy + lBotow);
            this.aktualnaLiczbaGraczy = 1;
            ktoWyszedl = new ArrayList<>();

            //tworzenie botow
            if(lBotow != 0) {
                if (lGraczy + lBotow == 2) {
                    boty.add(new Bot(1,Kolor.BLEKITNY));
                    aktualnaLiczbaGraczy += 1;
                } else if(lGraczy + lBotow == 4) {
                    switch (lBotow){
                        case 1:
                            boty.add(new Bot(1, Kolor.ROZOWY));
                            aktualnaLiczbaGraczy += 1;
                            break;
                        case 2:
                            boty.add(new Bot(1, Kolor.ROZOWY));
                            boty.add(new Bot(2, Kolor.BLEKITNY));
                            aktualnaLiczbaGraczy += 2;
                            break;
                        case 3:
                            boty.add(new Bot(1, Kolor.ROZOWY));
                            boty.add(new Bot(2, Kolor.BLEKITNY));
                            boty.add(new Bot(3, Kolor.NIEBIESKI));
                            aktualnaLiczbaGraczy += 3;
                            break;
                    }
                } else { //6 osobowa gra
                    switch (lBotow){
                        case 1:
                            boty.add(new Bot(1, Kolor.ZOLTY));
                            aktualnaLiczbaGraczy += 1;
                            break;
                        case 2:
                            boty.add(new Bot(1,Kolor.ZOLTY));
                            boty.add(new Bot(2,Kolor.ROZOWY));
                            aktualnaLiczbaGraczy += 2;
                            break;
                        case 3:
                            boty.add(new Bot(1, Kolor.ZOLTY));
                            boty.add(new Bot(2, Kolor.ROZOWY));
                            boty.add(new Bot(3, Kolor.BLEKITNY));
                            aktualnaLiczbaGraczy += 3;
                            break;
                        case 4:
                            boty.add(new Bot(1, Kolor.ZOLTY));
                            boty.add(new Bot(2, Kolor.ROZOWY));
                            boty.add(new Bot(3, Kolor.BLEKITNY));
                            boty.add(new Bot(4, Kolor.ZIELONY));
                            aktualnaLiczbaGraczy += 4;
                            break;
                        case 5:
                            boty.add(new Bot(1, Kolor.ZOLTY));
                            boty.add(new Bot(2, Kolor.ROZOWY));
                            boty.add(new Bot(3, Kolor.ZIELONY));
                            boty.add(new Bot(4, Kolor.NIEBIESKI));
                            aktualnaLiczbaGraczy += 5;
                            break;
                    }
                }
            }
        }

        public synchronized void koniecTury(){
                plansza.nowaTura();
                System.out.println("Tura gracza :" +turaGracza);
                turaGracza = (turaGracza+1)%(aktualnaLiczbaGraczy);
                for(Bot bot : boty)
                if(turaGracza == bot.indexBota){
                    kolejkaBota(bot.kolor);
                }
        }

        public synchronized boolean czyPelnaGra(){ //false to nie pelna
            if(aktualnaLiczbaGraczy<lGraczy+lBotow){
                return false;
            } else {
                return true;
            }
        }

        public synchronized boolean wykonajRuch(int rzad1, int kol1, int rzad2, int kol2, int indexGracza){
            if(plansza.ruszPionek(rzad1, kol1, rzad2, kol2)){
                return true;
            } else{
                return false;
            }
        }

        public synchronized void opuscRozgrywke(KlientWatek klient){
            ktoWyszedl.add(klient.indexGracza);
            gracze.remove(klient);
            aktualnaLiczbaGraczy--;
            if (gracze.size() == 0) {
                gry.remove(this);
            }
        }

        public synchronized void dolacz(KlientWatek klient){
            gracze.add(klient);
            aktualnaLiczbaGraczy++;
        }

        //bot

        public synchronized void kolejkaBota(Kolor kolor){
            koniecTury();
        }

        public synchronized MyScene getScene() {
            return scene;
        }

        public synchronized int getAktualnaLiczbaGraczy() {
            return aktualnaLiczbaGraczy;
        }

        public synchronized int getTuraGracza() {
            return turaGracza;
        }

        public synchronized ArrayList<ArrayList<Integer>> getListaPionkow() {
            return listaPionkow;
        }
        public synchronized int getWszyscyGracze(){
            return lBotow + lGraczy;
        }

        public synchronized void setListaPionkow(ArrayList<ArrayList<Integer>> listaPionkow) {
            this.listaPionkow = listaPionkow;

            for (int i = 0; i < PlanszaGwiazda.pola.length; i++) {
                for (int j = 0; j < PlanszaGwiazda.pola[0].length; j++) {
                    PlanszaGwiazda.pola[i][j] = listaPionkow.get(i).get(j);
                }
            }
        }

        public synchronized void setKoniec(Kolor k){
            if (k == Kolor.CZERWONY) {
                czerwonyKoniec = true;
            } else if (k == Kolor.BLEKITNY) {
                blekitnyKoniec = true;
            } else if (k == Kolor.ROZOWY) {
                rozowyKoniec = true;
            } else if (k == Kolor.NIEBIESKI) {
                niebieskiKoniec = true;
            } else if (k == Kolor.ZOLTY) {
                zoltyKoniec = true;
            } else if (k == Kolor.ZIELONY) {
                zielonyKoniec = true;
            }
        }
    }

    public static void main(String[] args) throws IOException{
        //ServerSocket serverSocket = new ServerSocket(9091);
        Serwer serwer = new Serwer(new ServerSocket(9091));
        serwer.nasluchiwanie();
    }
}