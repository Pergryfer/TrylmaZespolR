package sample;

import java.util.ArrayList;

public class PlanszaGwiazda {
    public int[][] pola = new int[3][5];
    public ArrayList<Pionek> pionki = new ArrayList<Pionek>();

    public PlanszaGwiazda(){
        stworzPola();
        stworzPionki();
        dodajPionki();
    }

    private void stworzPola(){
        for (int i =0; i < 3; i++){
          for(int j=0; j < 5; j++){
              pola[i][j] = -1;
          }
        }
        //tu trzeba algorytm do uzupelniania planszy
        pola[0][2] = 0;
        pola[1][1] = 0;
        pola[1][3] = 0;
        pola[2][0] = 0;
        pola[2][2] = 0;
        pola[2][4] = 0;
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

}
