package test;

import klient.PlanszaKlient;
import org.testng.annotations.Test;
import sample.PlanszaGwiazda;

import static org.testng.AssertJUnit.assertEquals;

public class PlanszaKlientTest {
    @Test
    public void testPlanszy(){
        PlanszaKlient planszaKlient = new PlanszaKlient(4);

        int licznik = 0;
        for(int i = 0; i < PlanszaKlient.pola.length; i++) {
            for (int j = 0; j < PlanszaKlient.pola[0].length; j++) {
                if (PlanszaKlient.pola[i][j] > 9) {
                    licznik++;
                }
            }
        }

        assertEquals(40, PlanszaKlient.pionki.size());
        assertEquals(40, licznik);
    }
}
