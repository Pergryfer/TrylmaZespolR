package serwer;

import klient.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class Serwer {

    private static int liczbaPolaczen = 0;

    private static int lGraczy, lBotow;

    public static void main(String[] args) throws IOException{


        System.out.println("Serwer wlaczony!");
        ServerSocket serverSocket = new ServerSocket(9090);



        try {
            while(true) {
                Socket socket = serverSocket.accept();
                Scanner sc = new Scanner(socket.getInputStream());
                try {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println("Udalo się zalogowac!");
                    liczbaPolaczen += 1;
                    System.out.println(liczbaPolaczen);
                } finally {
                    socket.close();
                }
            }
        } finally {
            serverSocket.close();
        }
    }
}
