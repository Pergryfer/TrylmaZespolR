package sample;

import klient.OknoPlanszy;

import java.util.ArrayList;

public class Gracz extends Uczestnik{


    Kolor kolor, przeciwnik;
    int id;
    public ArrayList<Pionek> pionki = new ArrayList<Pionek>(10);

    public Gracz(Kolor kolor){
        this.kolor = kolor;
        przeciwnik = kolor.kolorPrzeciwnika();
        dodajPionki();
    }

    private void dodajPionki(){
        for (int i = 0; i < PlanszaGwiazda.pionki.size(); i++){
            if(PlanszaGwiazda.pionki.get(i).kolor.equals(kolor)){
                pionki.add(PlanszaGwiazda.pionki.get(i));
            }
        }
    }

}
