package sample;

public enum Kolor {
    CZERWONY, ZIELONY, BLEKITNY, NIEBIESKI, ZOLTY, ROZOWY;

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
            default:
                return null;
        }
    }
}
