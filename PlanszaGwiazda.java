package sample;

import java.util.ArrayList;
import java.util.Random;

public class PlanszaGwiazda {
    public int[][] pola = new int[17][25];
    public ArrayList<Pionek> pionki = new ArrayList<Pionek>();
    int liczbaGraczy;

    public PlanszaGwiazda(){
        stworzPola();
        stworzPionki();
        dodajPionki();
    }

    private void stworzPola(){
        //wypelnianie jedynkami
        pola[0][12] = 1;
        pola[1][11] = 1; pola[1][13] = 1;
        pola[2][10] = 1; pola[2][12] = 1; pola[2][14] = 1;
        pola[3][9] = 1; pola[3][11] = 1; pola[3][13] = 1; pola[3][15] = 1;

        pola[13][9] = 1; pola[13][11] = 1; pola[13][13] = 1; pola[13][15] = 1;
        pola[14][10] = 1; pola[14][12] = 1; pola[14][14] = 1;
        pola[15][11] = 1; pola[15][13] = 1;
        pola[16][12] = 1;

        //wypelnianie reszty pol 2 i 0
        for (int i = 0; i < 17; i++){
            for(int j = 0; j < 25; j++) {
                if(i == 0 || i == 16) {
                    if (pola[i][j]!=1)
                        pola[i][j] = 2;
                }
                if(i == 1 || i == 15) {
                    if (pola[i][j]!=1)
                        pola[i][j] = 2;
                }
                if(i == 2 || i == 14) {
                    if (pola[i][j]!=1)
                        pola[i][j] = 2;
                }
                if(i == 3 || i == 13) {
                    if (pola[i][j]!=1)
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

        for(int i = 0; i < 17; i++){
            String s = "";
            for(int j = 0; j < 25; j++){
                s+=pola[i][j];
                if(j%24==0 && j!=0) {
                    System.out.println(s);
                }
            }
        }

    }
    private void stworzPionki(){
        pionki.add(new Pionek(1));
        pionki.add(new Pionek(2));
    }
    private void dodajPionki(){
        if(pola[0][2] != -1){
            pola[1][1] = pionki.get(0).id;
            pola[1][3] = pionki.get(1).id;
        }
    }

    private boolean czyDostepnePole(int rzad1, int kol1, int rzad2, int kol2 ){
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
            if(pola[rzad2-1][kol2-1] > 0) {
                System.out.println("wykonywanie przeskoku");
                return true;
            }
        }
        if(((kol2 == kol1 - 2) && (rzad2 == rzad1 - 2))){
            if(pola[rzad2+1][kol2+1] > 0) {
                System.out.println("wykonywanie przeskoku");
                return true;
            }
        }
        if(((kol2 == kol1 + 2) && (rzad2 == rzad1 - 2))){
            if(pola[rzad2+1][kol2-1] > 0) {
                System.out.println("wykonywanie przeskoku");
                return true;
            }
        }
        if(((kol2 == kol1 - 2) && (rzad2 == rzad1 + 2))){
            if(pola[rzad2-1][kol2+1] > 0) {
                System.out.println("wykonywanie przeskoku");
                return true;
            }
        }
        //czy  nie wybrano odpowiednie pole do ruchu
        if(!((kol2 == kol1 + 1) && (rzad2 == rzad1 + 1)) && !((kol2 == kol1 - 1) && (rzad2 == rzad1 - 1)) && !((kol2 == kol1 + 1) && (rzad2 == rzad1 - 1)) && !((kol2 == kol1 - 1) && (rzad2 == rzad1 + 1))){
            return false;
        }


        return true;
    }
    public void ruszPionek(int rzad1, int kol1, int rzad2, int kol2){
        if( czyDostepnePole(rzad1, kol1, rzad2, kol2)) {

            pola[rzad2][kol2] = pola[rzad1][kol1];
            pola[rzad1][kol1] = 0;
        }else{
            System.out.println("Nie można wykonać takiego ruchu!");
        }
    }

    private int losujGracza(int liczbaGraczy){
        Random generator = new Random();
        return generator.nextInt(liczbaGraczy)+1;
    }

}
