package serwer;

import klient.Controller;
import sample.PlanszaGwiazda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Serwer {
    private ServerSocket serverSocket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private int lGraczy;
    private int lBotow;
    private PlanszaGwiazda plansza = null;

    public Serwer( ServerSocket ss){
        this.serverSocket = ss;
        System.out.println("Serwer Uruchomiony");
        System.out.println("Port: " + ss.getLocalPort());
        System.out.println("bind address: " + ss.getInetAddress());

        try {
            nasluchiwanie();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void nasluchiwanie() throws IOException {
        boolean serwerDziala = true;

        try {
            while(serwerDziala) {
                Socket socket = serverSocket.accept();
                try {
                   obslugaKlienta(socket);
                }catch (IOException e){
                    e.printStackTrace();
                }finally {
                    socket.close();
                }
            }
        }catch (IOException e){
          e.printStackTrace();
        } finally {
            try {
                if(serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e1){
                e1.printStackTrace();
            }
        }
/*        try {
            while (serwerDziala) {
                Socket socket = serverSocket.accept();
                try {
                    System.out.println("Klient sie poloczyl");
                    obslugaKlienta(socket);
                } finally {
                    socket.close();
                }

            }
        } finally {
            serverSocket.close();
        }*/
    }

    private void obslugaKlienta(Socket socket) throws IOException{
        try {
            in = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));
            out = new PrintWriter(
                    socket.getOutputStream(), true);
            for (String linia; (linia = in.readLine()) != null; ) {
                String odpowiedz = obslugaWiadomosci(linia);
                out.println(odpowiedz);
            }
        } catch (IOException e){
            e.printStackTrace();
        }finally {
            in.close();
            out.close();
            socket.close();
        }

    }

    private String obslugaWiadomosci(String wiadomosc){

        String[] rozdzielonaWiadomosc = wiadomosc.trim().split("\\s+");

        switch (rozdzielonaWiadomosc[0]){
            case "iloscGraczy":
                lGraczy = Integer.parseInt(rozdzielonaWiadomosc[1]);
                lBotow = Integer.parseInt(rozdzielonaWiadomosc[2]);
                System.out.println("Komenda: " + rozdzielonaWiadomosc[0]);
                //utworzenie planszy zaleznie od ilosci graczy
                plansza = new PlanszaGwiazda();
                return "Wiadomosc dostarczona i wykonana";
            case "ruch": // kolejnosc rzad1 kol1 rzad2 kol2
                int rzad1 = Integer.parseInt(rozdzielonaWiadomosc[1]);
                int kol1 = Integer.parseInt(rozdzielonaWiadomosc[2]);
                int rzad2 = Integer.parseInt(rozdzielonaWiadomosc[3]);
                int kol2 = Integer.parseInt(rozdzielonaWiadomosc[4]);

                if(plansza.ruszPionek(rzad1, kol1, rzad2, kol2)){
                    return "poprawny";
                } else {
                    return "niePoprawny";
                }
            case "dolacz":
                break;

            case "wyjdz": break;

            case "NastepnaTura": break;
                

            default:
                return "blad";
        }
        return "blad";
    }

    public static void main(String[] args) throws IOException{
   //     ServerSocket serverSocket = new ServerSocket(9091);
        Serwer serwer = new Serwer(new ServerSocket(9091));

    }
}
