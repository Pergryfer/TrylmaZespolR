package test;

import org.testng.annotations.Test;
import sample.PlanszaGwiazda;

public class PlanszaGwiazdaTest {
    @Test
    public void testPlanszy(){
        PlanszaGwiazda plansza1 = new PlanszaGwiazda();
        System.out.println("Plansza Startowa");
        pokazMape(plansza1);
        System.out.println("Ruch 1");
        plansza1.ruszPionek(1,1, 0,2);
        pokazMape(plansza1);
        System.out.println("Ruch 2");
 //       plansza1.ruszPionek(1,1, 2,2);
 //       plansza1.ruszPionek(1,1, 2,2);
        plansza1.ruszPionek(0,2,2,4);
        pokazMape(plansza1);
    }

    private void pokazMape(PlanszaGwiazda plansza1){
        for (int i =0; i < 3; i++){
            for(int j=0; j < 5; j++){
                if(plansza1.pola[i][j] == -1){
                    System.out.print("---");
                }else {
                    if (plansza1.pola[i][j] == 0) {
                        System.out.print("[_]");
                    }else{
                        System.out.print("[" + plansza1.pola[i][j] + "]");
                    }
                }
            }
            System.out.println();
        }
    }

}
