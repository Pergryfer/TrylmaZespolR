package sample;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Klient {
    public static void main(String[] args) throws IOException{
        String serverAdress = JOptionPane.showInputDialog("Enter IP Addres of a machine that is \n" +
        "running the date service on port 9090:");
        Socket s = new Socket(serverAdress, 9090);
        BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String answer = input.readLine();
        JOptionPane.showMessageDialog(null, answer);
        System.exit(0);
    }
}
