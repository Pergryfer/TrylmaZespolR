package sample;

import java.util.ArrayList;
import java.util.Random;

public class PlanszaGwiazda {
    public static int[][] pola = new int[17][25];
    public static ArrayList<Pionek> pionki = new ArrayList<>();
    int liczbaGraczy;
    boolean pierwszyRuch = true;

    public PlanszaGwiazda(int liczbaGraczy){
        this.liczbaGraczy = liczbaGraczy;
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

    }

    int id = 10;

    private void dodajPionki(){
            if(liczbaGraczy >= 2){
                pola[0][12] = id;
                id++;
                pola[1][11] = id;
                id++;
                pola[1][13] = id;
                id++;
                pola[2][10] = id;
                id++;
                pola[2][12] = id;
                id++;
                pola[2][14] = id;
                id++;
                pola[3][9] = id;
                id++;
                pola[3][11] = id;
                id++;
                pola[3][13] = id;
                id++;
                pola[3][15] = id;
                id++;

                pola[13][9] = id;
                id++;
                pola[13][11] = id;
                id++;
                pola[13][13] = id;
                id++;
                pola[13][15] = id;
                id++;
                pola[14][10] = id;
                id++;
                pola[14][12] = id;
                id++;
                pola[14][14] = id;
                id++;
                pola[15][11] = id;
                id++;
                pola[15][13] = id;
                id++;
                pola[16][12] = id;
                id++;


        }
        if(liczbaGraczy >= 4) {

            pola[4][0] = id;
            id++;
            pola[4][2] = id;
            id++;
            pola[4][4] = id;
            id++;
            pola[4][6] = id;
            id++;
            pola[5][1] = id;
            id++;
            pola[5][3] = id;
            id++;
            pola[5][5] = id;
            id++;
            pola[6][2] = id;
            id++;
            pola[6][4] = id;
            id++;
            pola[7][3] = id;
            id++;

            pola[9][21] = id;
            id++;
            pola[10][20] = id;
            id++;
            pola[10][22] = id;
            id++;
            pola[11][19] = id;
            id++;
            pola[11][21] = id;
            id++;
            pola[11][23] = id;
            id++;
            pola[12][18] = id;
            id++;
            pola[12][20] = id;
            id++;
            pola[12][22] = id;
            id++;
            pola[12][24] = id;
            id++;
        }
        if(liczbaGraczy == 6){

            pola[4][18] = id;
            id++;
            pola[4][20] = id;
            id++;
            pola[4][22] = id;
            id++;
            pola[4][24] = id;
            id++;
            pola[5][19] = id;
            id++;
            pola[5][21] = id;
            id++;
            pola[5][23] = id;
            id++;
            pola[6][20] = id;
            id++;
            pola[6][22] = id;
            id++;
            pola[7][21] = id;
            id++;

            pola[9][3] = id;
            id++;
            pola[10][2] = id;
            id++;
            pola[10][4] = id;
            id++;
            pola[11][1] = id;
            id++;
            pola[11][3] = id;
            id++;
            pola[11][5] = id;
            id++;
            pola[12][0] = id;
            id++;
            pola[12][2] = id;
            id++;
            pola[12][4] = id;
            id++;
            pola[12][6] = id;
            id++;

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

    public boolean czyDostepnePole(int rzad1, int kol1, int rzad2, int kol2){
        //czy nie wychodzi poza tablice
        if(kol1 >=  pola[1].length || kol2 >= pola[1].length || rzad1 >=  pola[0].length || rzad2 >= pola[0].length ){
            return false;
        }
        //c.d.
        if(rzad1 < 0 && rzad2 < 0 && kol1 < 0 && kol2 < 0){
            return false;
        }
        //czy to nie pole do gry i nie posiada pionka
        if(pola[rzad2][kol2] == -1 || pola[rzad1][kol1] <= 0 ){
            return false;
        }
        //czy jest wykonywany ruch z przeskokiem
        if(((kol2 == kol1 + 2) && (rzad2 == rzad1 + 2))){
            if(pola[rzad2-1][kol2-1] > 2) {
                System.out.println("wykonywanie przeskoku");
                return true;
            }
        }
        if(((kol2 == kol1 - 2) && (rzad2 == rzad1 - 2))){
            if(pola[rzad2+1][kol2+1] > 2) {
                System.out.println("wykonywanie przeskoku");
                return true;
            }
        }
        if(((kol2 == kol1 + 2) && (rzad2 == rzad1 - 2))){
            if(pola[rzad2+1][kol2-1] > 2) {
                System.out.println("wykonywanie przeskoku");
                return true;
            }
        }
        if(((kol2 == kol1 - 2) && (rzad2 == rzad1 + 2))){
            if(pola[rzad2-1][kol2+1] > 2) {
                System.out.println("wykonywanie przeskoku");
                return true;
            }
        }
        if(((kol2 == kol1 - 4) && (rzad2 == rzad1))){
            if(pola[rzad2][kol2+2] > 2) {
                System.out.println("wykonywanie przeskoku");
                return true;
            }
        }
        if(((kol2 == kol1 + 4) && (rzad2 == rzad1))){
            if(pola[rzad2][kol2-2] > 2) {
                System.out.println("wykonywanie przeskoku");
                return true;
            }
        }
        //czy  nie wybrano odpowiednie pole do ruchu
        if(!((kol2 == kol1 + 1) && (rzad2 == rzad1 + 1)) && !((kol2 == kol1 - 1) && (rzad2 == rzad1 - 1)) && !((kol2 == kol1 + 1)
                && (rzad2 == rzad1 - 1)) && !((kol2 == kol1 - 1) && (rzad2 == rzad1 + 1)) && !((rzad1 == rzad2)
                && (kol2 == kol1 - 2))  && !((rzad1 == rzad2) && (kol2 == kol1 + 2)) && !pierwszyRuch){

            return false;
        }

        return true;
    }
    public boolean ruszPionek(int rzad1, int kol1, int rzad2, int kol2){
        if( czyDostepnePole(rzad1, kol1, rzad2, kol2)) {
            pola[rzad2][kol2] = pola[rzad1][kol1];
            pola[rzad1][kol1] = 0;

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

            return true;
        }else{
            System.out.println("Nie można wykonać takiego ruchu!");
            return false;
        }

    }

    public void nowaTura(){
        pierwszyRuch = true;
    }

    private int losujGracza(int liczbaGraczy){
        Random generator = new Random();
        return generator.nextInt(liczbaGraczy)+1;
    }

}
