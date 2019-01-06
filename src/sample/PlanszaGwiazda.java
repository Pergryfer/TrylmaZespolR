package sample;

import java.util.ArrayList;
import java.util.Random;

public class PlanszaGwiazda {
    public static int[][] pola = new int[17][25];
    public ArrayList<Pionek> pionki = new ArrayList<>();
    int liczbaGraczy;
    boolean pierwszyRuch = true;

    public PlanszaGwiazda(){
        stworzPionki();
        dodajPionki();
        stworzPola();
    }

    private void stworzPola(){
        //wypelnianie reszty pol 2 i 0
        for (int i = 0; i < 17; i++){
            for(int j = 0; j < 25; j++) {
                if(i == 0 || i == 16) {
                    if (pola[i][j]<1)
                        pola[i][j] = 2;
                }
                if(i == 1 || i == 15) {
                    if (pola[i][j]<1)
                        pola[i][j] = 2;
                }
                if(i == 2 || i == 14) {
                    if (pola[i][j]<1)
                        pola[i][j] = 2;
                }
                if(i == 3 || i == 13) {
                    if (pola[i][j]<1)
                        pola[i][j] = 2;
                }
                if(i == 4 || i == 12) {
                    if (j % 2 == 0) {
                        pola[i][j] = 0;
                    } else {
                        pola[i][j] = 2;
                    }
                }
                if(i == 5 || i == 11){
                    if(j%2==1) {
                        pola[i][j] = 0;
                    } else {
                        pola[i][j] = 2;
                    }
                }
                if(i == 6 || i == 10){
                    if(j%2==0 && j > 1 && j < 23) {
                        pola[i][j] = 0;
                    } else {
                        pola[i][j] = 2;
                    }
                }
                if(i == 7 || i == 9){
                    if(j%2==1 && j > 2 && j < 22) {
                        pola[i][j] = 0;
                    } else {
                        pola[i][j] = 2;
                    }
                }
                if(i == 8) {
                    if(j%2==0 && j > 3 && j < 21) {
                        pola[i][j] = 0;
                    } else {
                        pola[i][j] = 2;
                    }
                }
            }
        }
        /*
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
        } */

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
        for (int i = 10; i < 20; i++) {
            pionki.add(new Pionek(i));
        }
        for (int i = 20; i < 30; i++) {
            pionki.add(new Pionek(i));
        }
    }

    private void dodajPionki(){
            pola[0][12] = pionki.get(0).id;
            pola[1][11] = pionki.get(1).id; pola[1][13] = pionki.get(2).id;
            pola[2][10] = pionki.get(3).id; pola[2][12] = pionki.get(4).id; pola[2][14] = pionki.get(5).id;
            pola[3][9] = pionki.get(6).id; pola[3][11] = pionki.get(7).id; pola[3][13] = pionki.get(8).id; pola[3][15] = pionki.get(9).id;

            pola[13][9] = pionki.get(10).id; pola[13][11] = pionki.get(11).id; pola[13][13] = pionki.get(12).id; pola[13][15] = pionki.get(13).id;
            pola[14][10] = pionki.get(14).id; pola[14][12] = pionki.get(15).id; pola[14][14] = pionki.get(16).id;
            pola[15][11] = pionki.get(17).id; pola[15][13] = pionki.get(18).id;
            pola[16][12] = pionki.get(19).id;
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
