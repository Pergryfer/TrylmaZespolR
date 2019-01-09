package klient;

import sample.Kolor;
import sample.Pionek;

import java.util.ArrayList;
import java.util.Random;

public class PlanszaKlient {
    public static int[][] pola = new int[17][25];
    public static ArrayList<Pionek> pionki = new ArrayList<>();
    int liczbaGraczy;
    boolean pierwszyRuch = true;

    public PlanszaKlient(int liczbaGraczy){
        this.liczbaGraczy = liczbaGraczy;
        stworzPionki();
        stworzPola();
        dodajPionki();
    }

    private void stworzPola(){
        for (int i = 0; i < 17; i++) {
            for (int j = 0; j < 25; j++) {
                if (i == 0 || i == 16) {
                    if (pola[i][j] < 1)
                        pola[i][j] = 2;
                }
                if (i == 1 || i == 15) {
                    if (pola[i][j] < 1)
                        pola[i][j] = 2;
                }
                if (i == 2 || i == 14) {
                    if (pola[i][j] < 1)
                        pola[i][j] = 2;
                }
                if (i == 3 || i == 13) {
                    if (pola[i][j] < 1)
                        pola[i][j] = 2;
                }
                if (i == 4 || i == 12) {
                    if (j % 2 == 0) {
                        pola[i][j] = 0;
                    } else {
                        pola[i][j] = 2;
                    }
                }
                if (i == 5 || i == 11) {
                    if (j % 2 == 1) {
                        pola[i][j] = 0;
                    } else {
                        pola[i][j] = 2;
                    }
                }
                if (i == 6 || i == 10) {
                    if (j % 2 == 0 && j > 1 && j < 23) {
                        pola[i][j] = 0;
                    } else {
                        pola[i][j] = 2;
                    }
                }
                if (i == 7 || i == 9) {
                    if (j % 2 == 1 && j > 2 && j < 22) {
                        pola[i][j] = 0;
                    } else {
                        pola[i][j] = 2;
                    }
                }
                if (i == 8) {
                    if (j % 2 == 0 && j > 3 && j < 21) {
                        pola[i][j] = 0;
                    } else {
                        pola[i][j] = 2;
                    }
                }
            }
        }



        /*
        for(int i = 0; i < 17; i++){
            String s = "";
            for(int j = 0; j < 25; j++){
                s+=pola[i][j];
                if(j%24==0 && j!=0) {
                    System.out.println(s);
                }
            }
        }*/

    }
    private void stworzPionki() {
        if(liczbaGraczy >= 2) {
            for (int i = 10; i < 20; i++) {
                pionki.add(new Pionek(i, 19, Kolor.CZERWONY));
            }
            for (int i = 20; i < 30; i++) {
                pionki.add(new Pionek(i, 19, Kolor.BLEKITNY));
            }
        }
        if(liczbaGraczy >= 4){
            for (int i = 30; i < 40; i++) {
                pionki.add(new Pionek(i, 19, Kolor.NIEBIESKI));
            }
            for (int i = 40; i < 50; i++) {
                pionki.add(new Pionek(i, 19, Kolor.ROZOWY));
            }
        }
        if(liczbaGraczy == 6){
            for (int i = 50; i < 60; i++) {
                pionki.add(new Pionek(i, 19, Kolor.ZOLTY));
            }
            for (int i = 60; i < 70; i++) {
                pionki.add(new Pionek(i, 19, Kolor.ZIELONY));
            }
        }
    }
    int id = 10;
    private void dodajPionki(){
        if(liczbaGraczy >= 2){
            pola[0][12] = pionki.get(0).id;
            pola[1][11] = pionki.get(1).id;
            pola[1][13] = pionki.get(2).id;
            pola[2][10] = pionki.get(3).id;
            pola[2][12] = pionki.get(4).id;
            pola[2][14] = pionki.get(5).id;
            pola[3][9] = pionki.get(6).id;
            pola[3][11] = pionki.get(7).id;
            pola[3][13] = pionki.get(8).id;
            pola[3][15] = pionki.get(9).id;

            pola[13][9] = pionki.get(10).id;
            pola[13][11] = pionki.get(11).id;
            pola[13][13] = pionki.get(12).id;
            pola[13][15] = pionki.get(13).id;
            pola[14][10] = pionki.get(14).id;
            pola[14][12] = pionki.get(15).id;
            pola[14][14] = pionki.get(16).id;
            pola[15][11] = pionki.get(17).id;
            pola[15][13] = pionki.get(18).id;
            pola[16][12] = pionki.get(19).id;
        }
        if(liczbaGraczy >= 4) {
            pola[4][0] = pionki.get(20).id;
            pola[4][2] = pionki.get(21).id;
            pola[4][4] = pionki.get(22).id;
            pola[4][6] = pionki.get(23).id;
            pola[5][1] = pionki.get(24).id;
            pola[5][3] = pionki.get(25).id;
            pola[5][5] = pionki.get(26).id;
            pola[6][2] = pionki.get(27).id;
            pola[6][4] = pionki.get(28).id;
            pola[7][3] = pionki.get(29).id;



            pola[9][21] = pionki.get(30).id;
            pola[10][20] = pionki.get(31).id;
            pola[10][22] = pionki.get(32).id;
            pola[11][19] = pionki.get(33).id;
            pola[11][21] = pionki.get(34).id;
            pola[11][23] = pionki.get(35).id;
            pola[12][18] = pionki.get(36).id;
            pola[12][20] = pionki.get(37).id;
            pola[12][22] = pionki.get(38).id;
            pola[12][24] = pionki.get(39).id;
        }
        if(liczbaGraczy == 6){
            pola[4][18] = pionki.get(40).id;
            pola[4][20] = pionki.get(41).id;
            pola[4][22] = pionki.get(42).id;
            pola[4][24] = pionki.get(43).id;
            pola[5][19] = pionki.get(44).id;
            pola[5][21] = pionki.get(45).id;
            pola[5][23] = pionki.get(46).id;
            pola[6][20] = pionki.get(47).id;
            pola[6][22] = pionki.get(48).id;
            pola[7][21] = pionki.get(49).id;



            pola[9][3] = pionki.get(50).id;
            pola[10][2] = pionki.get(51).id;
            pola[10][4] = pionki.get(52).id;
            pola[11][1] = pionki.get(53).id;
            pola[11][3] = pionki.get(54).id;
            pola[11][5] = pionki.get(55).id;
            pola[12][0] = pionki.get(56).id;
            pola[12][2] = pionki.get(57).id;
            pola[12][4] = pionki.get(58).id;
            pola[12][6] = pionki.get(59).id;

        }

        for (int i =0; i < 17; i++){
            for(int j=0; j < 25; j++){
                if(pola[i][j] == 2){
                    System.out.print("---");
                }else {
                    if (pola[i][j] == 0) {
                        System.out.print("[_]");
                    }else{
                        System.out.print("[" + pola[i][j] + "]");
                    }
                }
            }
            System.out.println();
        }


    }
}
