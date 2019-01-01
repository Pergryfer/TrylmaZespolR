package serwer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Serwer {

    private static int liczbaGraczy = 0;

    public static void main(String[] args) throws IOException{
        System.out.println("Serwer wlaczony!");
        ServerSocket serverSocket = new ServerSocket(9090);
        try {
            while(true) {
                Socket socket = serverSocket.accept();
                try {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println("Udalo się zalogowac!");
                    liczbaGraczy += 1;
                    System.out.println(liczbaGraczy);
                } finally {
                    socket.close();
                }
            }
        } finally {
            serverSocket.close();
        }
    }
}
