package serwer;

import sample.Kolor;

import java.util.ArrayList;

public class Bot {
    int indexBota;
    Kolor kolor;
    ArrayList<Integer> indeksyPionkow;


    public Bot(int indexBota, Kolor kolor){
        this.indexBota = indexBota;
        this.kolor = kolor;
        indeksyPionkow = new ArrayList<>();
        if (kolor == Kolor.BLEKITNY) {
            for (int i = 20; i < 30; i++) {
                indeksyPionkow.add(i);
            }
        }
    }
}
