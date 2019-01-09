package sample;

import klient.OknoPlanszy;

import java.util.ArrayList;

public class Gracz {


    Kolor kolor, przeciwnik;
    int id;
    ArrayList<Pionek> pionki = new ArrayList<Pionek>(10);

    public Gracz(Kolor kolor){
        this.kolor = kolor;
        przeciwnik = kolor.kolorPrzeciwnika();
        for (int i = 0; i < pionki.size(); i++){
            if(OknoPlanszy.pionki.get(i).kolor == kolor){
                pionki.add(OknoPlanszy.pionki.get(i));
            }
        }
    }

}
