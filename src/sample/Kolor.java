package sample;

import java.io.Serializable;

public enum Kolor implements Serializable{
    CZERWONY, ZIELONY, BLEKITNY, NIEBIESKI, ZOLTY, ROZOWY, SZARY;

    public Kolor kolorPrzeciwnika(){
        switch (this) {
            case CZERWONY:
                return BLEKITNY;
            case BLEKITNY:
                return CZERWONY;
            case NIEBIESKI:
                return ROZOWY;
            case ROZOWY:
                return NIEBIESKI;
            case ZOLTY:
                return ZIELONY;
            case ZIELONY:
                return ZOLTY;
            default:
                return null;
        }
    }
}
