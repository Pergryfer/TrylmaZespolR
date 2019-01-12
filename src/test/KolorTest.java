package test;

import org.testng.annotations.Test;
import sample.Kolor;

import static org.testng.AssertJUnit.assertEquals;

public class KolorTest {
    @Test
    public void testKoloruPrzeciwnika(){
        Kolor kolor1 = Kolor.CZERWONY;
        Kolor kolor3 = Kolor.NIEBIESKI;
        Kolor kolor2 = Kolor.ZIELONY;

        assertEquals(Kolor.BLEKITNY, kolor1.kolorPrzeciwnika());
        assertEquals(Kolor.ZOLTY, kolor2.kolorPrzeciwnika());
        assertEquals(Kolor.ROZOWY, kolor3.kolorPrzeciwnika());
    }
}
