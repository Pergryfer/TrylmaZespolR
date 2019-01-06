package test;

import org.testng.annotations.Test;
import sample.PlanszaGwiazda;

public class PlanszaGwiazdaTest {
    @Test
    public void testPlanszy(){
        PlanszaGwiazda plansza1 = new PlanszaGwiazda(2);
        System.out.println("Plansza Startowa");
        pokazMape(plansza1);
        System.out.println("Ruch 1");
        plansza1.ruszPionek(3,9,4,8);
        System.out.println("Ruch 2");
        plansza1.ruszPionek(1,11,3,9);
        System.out.println("Ruch 3");
        plansza1.ruszPionek(3,9,5,7);
        System.out.println("Ruch 4");
        plansza1.ruszPionek(2,10,4,12);
        System.out.println("Ruch 5");
        plansza1.ruszPionek(3,11,3,9);
        System.out.println("Ruch 6");
        plansza1.ruszPionek(13,9,12,10);
        System.out.println("Ruch 7");
        plansza1.ruszPionek(13,13,13,9);
        pokazMape(plansza1);
    }

    @Test
    public void CzyDobrzeDodanoPionka(){

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
