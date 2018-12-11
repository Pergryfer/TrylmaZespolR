package test;

import org.testng.annotations.Test;
import sample.PlanszaGwiazda;

public class PlanszaGwiazdaTest {
    @Test
    public void testPlanszy(){
        PlanszaGwiazda plansza1 = new PlanszaGwiazda();
        System.out.println("Plansza Startowa");
        pokazMape(plansza1);
        plansza1.ruszPionek(3,9,4,8);
        plansza1.ruszPionek(1,11,3,9);
        plansza1.ruszPionek(2,11,1,9);
        System.out.println("Ruch 1");
        pokazMape(plansza1);
    }

    @Test
    public void CzyDobrzeDodanoPionka(){

    }



    private void pokazMape(PlanszaGwiazda plansza1){
        for (int i =0; i < 17; i++){
            for(int j=0; j < 25; j++){
                if(plansza1.pola[i][j] == 2){
                    System.out.print("    ");
                }else {
                    if (plansza1.pola[i][j] == 0) {
                        System.out.print("[__]");
                    }else{
                        System.out.print("[" + plansza1.pola[i][j] + "]");
                    }
                }
            }
            System.out.println();
        }
    }

}
