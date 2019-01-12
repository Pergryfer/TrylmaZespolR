package test;

import klient.PlanszaKlient;
import org.testng.annotations.Test;
import sample.PlanszaGwiazda;

import static org.testng.AssertJUnit.assertEquals;

public class PlanszaGwiazdaTest {
    @Test
    public void testRuchu(){
        PlanszaGwiazda plansza1 = new PlanszaGwiazda(2);
        System.out.println("Plansza Startowa");
        pokazMape(plansza1);

        System.out.println("Ruch 1");
        plansza1.ruszPionek(3,9,4,8);
        plansza1.nowaTura();

        System.out.println("Ruch 2");
        plansza1.ruszPionek(1,11,3,9);
        System.out.println("Ruch 3");
        plansza1.ruszPionek(3,9,5,7);
        plansza1.nowaTura();

        System.out.println("Ruch 4");
        plansza1.ruszPionek(2,10,4,12);
        plansza1.nowaTura();

        System.out.println("Ruch 5");
        plansza1.ruszPionek(3,11,3,9);
        plansza1.nowaTura();

        System.out.println("Ruch 6");
        plansza1.ruszPionek(13,9,12,10);
        plansza1.nowaTura();

        System.out.println("Ruch 7");
        plansza1.ruszPionek(13,13,13,9);
        plansza1.nowaTura();

        System.out.println("Ruch 8");
        plansza1.ruszPionek(0,12,0,14);
        plansza1.nowaTura();

        System.out.println("Ruch 9");
        plansza1.ruszPionek(3,13,3,15);
        plansza1.nowaTura();


        System.out.println("Plansza po ruchach");
        pokazMape(plansza1);

        assertEquals(0, plansza1.pola[1][11]);
        assertEquals(0, plansza1.pola[2][10]);
        assertEquals(0, plansza1.pola[3][11]);
        assertEquals(0, plansza1.pola[13][13]);
        assertEquals(11, plansza1.pola[5][7]);
        assertEquals(13, plansza1.pola[4][12]);
        assertEquals(16, plansza1.pola[4][8]);
        assertEquals(17, plansza1.pola[3][9]);
        assertEquals(10, plansza1.pola[0][12]);
        assertEquals(18, plansza1.pola[3][13]);
    }

    @Test
    public void czyDobraIloscPionkow(){
        PlanszaGwiazda plansza1 = new PlanszaGwiazda(4);
        int licznik = 0;
        for (int i = 0; i < plansza1.pola.length; i++) {
            for (int j = 0; j < plansza1.pola[0].length; j++) {
                if (plansza1.pola[i][j] > 9) {
                    licznik++;
                }
            }
        }

        assertEquals(40, licznik);
    }

    @Test
    public void czyDobryZakresLosowania(){
        PlanszaGwiazda planszaGwiazda = new PlanszaGwiazda(6);

        assertEquals(1,planszaGwiazda.zaczyna, 5);

    }



    private void pokazMape(PlanszaGwiazda plansza1){
        for (int i = -1; i < 17; i++){
            for(int j= 0; j < 25; j++){
                if(i == -1 ){
                    if(j < 10) {
                        System.out.print("  " + j + " ");
                    }else {
                        System.out.print(" " + j + " ");
                    }
                } else {
                    if (plansza1.pola[i][j] == 2) {
                        System.out.print("    ");
                    } else {
                        if (plansza1.pola[i][j] == 0) {
                            System.out.print("[__]");
                        } else {
                            System.out.print("[" + plansza1.pola[i][j] + "]");
                        }
                    }
                }
            }
            System.out.println(i);
        }
    }

}
