package klient;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Kolor;
import sample.MyPane;
import sample.MyVBox;
import sample.Pionek;

import java.util.ArrayList;

public class OknoPlanszy {

    ArrayList<MyPane> pola = new ArrayList<MyPane>();
    static ArrayList<HBox> lista = new ArrayList<HBox>();
    public static ArrayList<Pionek> pionki = new ArrayList<Pionek>();
    static PlanszaKlient planszaKlient;
    public Pane pane;
    private ArrayList<ArrayList> listaPionkow;


    public OknoPlanszy(){
        planszaKlient = Klient.getPlanszaKlient();
        pionki = planszaKlient.pionki;
        pane = stworzOkno();
    }


    private Pane stworzOkno() {
        Button przycisk = new Button("Koniec tury");
        przycisk.setOnAction(event -> {
            Klient.koniecTury();
        });

        //wypelnianie okna polami
        Pane pane = new Pane();
        pane.setPrefSize(1000, 680);

        VBox vBox = new VBox();
        vBox.setPrefSize(1000, 680);

        int k = 0;

        for (int i = 0; i < 121; i++) {
            pola.add(new MyPane(Integer.toString(i)));
            if (i == 0) {
                lista.add(new HBox());
                lista.get(k).setAlignment(Pos.TOP_CENTER);
                lista.get(k).setPrefSize(1000, 40);
                lista.get(k).getChildren().addAll(pola.get(0));
                k++;
            } else if (i == 2) {
                lista.add(new HBox());
                lista.get(k).setAlignment(Pos.TOP_CENTER);
                lista.get(k).setPrefSize(1000, 40);
                lista.get(k).getChildren().addAll(pola.get(1), new MyVBox(), pola.get(2));
                k++;
            } else if (i == 5) {
                lista.add(new HBox());
                lista.get(k).setAlignment(Pos.TOP_CENTER);
                lista.get(k).setPrefSize(1000, 40);
                double px = przycisk.getWidth();
                lista.get(k).getChildren().addAll(przycisk, new MyVBox(200), pola.get(3), new MyVBox(), pola.get(4), new MyVBox(), pola.get(5), new MyVBox(275+px));
                k++;
            } else if (i == 9) {
                lista.add(new HBox());
                lista.get(k).setAlignment(Pos.TOP_CENTER);
                lista.get(k).setPrefSize(1000, 40);
                lista.get(k).getChildren().addAll(pola.get(6), new MyVBox(), pola.get(7), new MyVBox(), pola.get(8), new MyVBox(), pola.get(9));
                k++;
            } else if (i == 22) {
                lista.add(new HBox());
                lista.get(k).setAlignment(Pos.TOP_CENTER);
                lista.get(k).setPrefSize(1000, 40);
                lista.get(k).getChildren().addAll(pola.get(10), new MyVBox(), pola.get(11), new MyVBox(), pola.get(12), new MyVBox(), pola.get(13), new MyVBox(), pola.get(14),
                        new MyVBox(), pola.get(15), new MyVBox(), pola.get(16), new MyVBox(), pola.get(17), new MyVBox(), pola.get(18), new MyVBox(), pola.get(19), new MyVBox(),
                        pola.get(20), new MyVBox(), pola.get(21), new MyVBox(), pola.get(22));
                k++;
            } else if (i == 34) {
                lista.add(new HBox());
                lista.get(k).setAlignment(Pos.TOP_CENTER);
                lista.get(k).setPrefSize(1000, 40);
                lista.get(k).getChildren().addAll(pola.get(23), new MyVBox(), pola.get(24), new MyVBox(), pola.get(25), new MyVBox(), pola.get(26), new MyVBox(), pola.get(27),
                        new MyVBox(), pola.get(28), new MyVBox(), pola.get(29), new MyVBox(), pola.get(30), new MyVBox(), pola.get(31), new MyVBox(), pola.get(32), new MyVBox(),
                        pola.get(33), new MyVBox(), pola.get(34));
                k++;
            } else if (i == 45) {
                lista.add(new HBox());
                lista.get(k).setAlignment(Pos.TOP_CENTER);
                lista.get(k).setPrefSize(1000, 40);
                lista.get(k).getChildren().addAll(pola.get(35), new MyVBox(), pola.get(36), new MyVBox(), pola.get(37), new MyVBox(), pola.get(38), new MyVBox(), pola.get(39),
                        new MyVBox(), pola.get(40), new MyVBox(), pola.get(41), new MyVBox(), pola.get(42), new MyVBox(), pola.get(43), new MyVBox(), pola.get(44), new MyVBox(),
                        pola.get(45));
                k++;
            } else if (i == 55) {
                lista.add(new HBox());
                lista.get(k).setAlignment(Pos.TOP_CENTER);
                lista.get(k).setPrefSize(1000, 40);
                lista.get(k).getChildren().addAll(pola.get(46), new MyVBox(), pola.get(47), new MyVBox(), pola.get(48), new MyVBox(), pola.get(49), new MyVBox(), pola.get(50),
                        new MyVBox(), pola.get(51), new MyVBox(), pola.get(52), new MyVBox(), pola.get(53), new MyVBox(), pola.get(54), new MyVBox(), pola.get(55));
                k++;
            } else if (i == 64) {
                lista.add(new HBox());
                lista.get(k).setAlignment(Pos.TOP_CENTER);
                lista.get(k).setPrefSize(1000, 40);
                lista.get(k).getChildren().addAll(pola.get(56), new MyVBox(), pola.get(57), new MyVBox(), pola.get(58), new MyVBox(), pola.get(59), new MyVBox(), pola.get(60),
                        new MyVBox(), pola.get(61), new MyVBox(), pola.get(62), new MyVBox(), pola.get(63), new MyVBox(), pola.get(64));
                k++;
            } else if (i == 74) {
                lista.add(new HBox());
                lista.get(k).setAlignment(Pos.TOP_CENTER);
                lista.get(k).setPrefSize(1000, 40);
                lista.get(k).getChildren().addAll(pola.get(65), new MyVBox(), pola.get(66), new MyVBox(), pola.get(67), new MyVBox(), pola.get(68), new MyVBox(), pola.get(69),
                        new MyVBox(), pola.get(70), new MyVBox(), pola.get(71), new MyVBox(), pola.get(72), new MyVBox(), pola.get(73), new MyVBox(), pola.get(74));
                k++;
            } else if (i == 85) {
                lista.add(new HBox());
                lista.get(k).setAlignment(Pos.TOP_CENTER);
                lista.get(k).setPrefSize(1000, 40);
                lista.get(k).getChildren().addAll(pola.get(75), new MyVBox(), pola.get(76), new MyVBox(), pola.get(77), new MyVBox(), pola.get(78), new MyVBox(), pola.get(79),
                        new MyVBox(), pola.get(80), new MyVBox(), pola.get(81), new MyVBox(), pola.get(82), new MyVBox(), pola.get(83), new MyVBox(), pola.get(84), new MyVBox(),
                        pola.get(85));
                k++;
            } else if (i == 97) {
                lista.add(new HBox());
                lista.get(k).setAlignment(Pos.TOP_CENTER);
                lista.get(k).setPrefSize(1000, 40);
                lista.get(k).getChildren().addAll(pola.get(86), new MyVBox(), pola.get(87), new MyVBox(), pola.get(88), new MyVBox(), pola.get(89), new MyVBox(), pola.get(90),
                        new MyVBox(), pola.get(91), new MyVBox(), pola.get(92), new MyVBox(), pola.get(93), new MyVBox(), pola.get(94), new MyVBox(), pola.get(95), new MyVBox(),
                        pola.get(96), new MyVBox(), pola.get(97));
                k++;
            } else if (i == 110) {
                lista.add(new HBox());
                lista.get(k).setAlignment(Pos.TOP_CENTER);
                lista.get(k).setPrefSize(1000, 40);
                lista.get(k).getChildren().addAll(pola.get(98), new MyVBox(), pola.get(99), new MyVBox(), pola.get(100), new MyVBox(), pola.get(101), new MyVBox(), pola.get(102),
                        new MyVBox(), pola.get(103), new MyVBox(), pola.get(104), new MyVBox(), pola.get(105), new MyVBox(), pola.get(106), new MyVBox(), pola.get(107), new MyVBox(),
                        pola.get(108), new MyVBox(), pola.get(109), new MyVBox(), pola.get(110));
                k++;
            } else if (i == 114) {
                lista.add(new HBox());
                lista.get(k).setAlignment(Pos.TOP_CENTER);
                lista.get(k).setPrefSize(1000, 40);
                lista.get(k).getChildren().addAll(pola.get(111), new MyVBox(), pola.get(112), new MyVBox(), pola.get(113), new MyVBox(), pola.get(114));
                k++;
            } else if (i == 117) {
                lista.add(new HBox());
                lista.get(k).setAlignment(Pos.TOP_CENTER);
                lista.get(k).setPrefSize(1000, 40);
                lista.get(k).getChildren().addAll(pola.get(115), new MyVBox(), pola.get(116), new MyVBox(), pola.get(117));
                k++;
            } else if (i == 119) {
                lista.add(new HBox());
                lista.get(k).setAlignment(Pos.TOP_CENTER);
                lista.get(k).setPrefSize(1000, 40);
                lista.get(k).getChildren().addAll(pola.get(118), new MyVBox(), pola.get(119));
                k++;
            } else if (i == 120) {
                lista.add(new HBox());
                lista.get(k).setAlignment(Pos.TOP_CENTER);
                lista.get(k).setPrefSize(1000, 40);
                lista.get(k).getChildren().addAll(pola.get(120));
                k++;
            }
        }

        kolorujPola();


        for(int i = 0; i < 17; i++){
            for(int j = 0; j < 25; j++){
                if(PlanszaKlient.pola[i][j] != 0 && PlanszaKlient.pola[i][j] != 2){
                    Pionek p = szukajPionka(PlanszaKlient.pola[i][j]);
                    int po = szukajPola(i, j);
                    pola.get(po).getChildren().add(p);
                    p.setCenterX(pola.get(po).getCenterX());
                    p.setCenterY(pola.get(po).getCenterY());
                }
            }
        }


        kolorujPionki();


        vBox.getChildren().addAll(lista);
        pane.getChildren().add(vBox);

        return pane;

    }

    public int szukajPola(int x, int y) {
        for (int i = 0; i < pola.size(); i++) {
            if (pola.get(i).getXX() == x && pola.get(i).getYY() == y) {
                return i;
            }
        }
        return 0;
    }

    public static Pionek szukajPionka(int ind){
        for(int i = 0; i < pionki.size(); i++){
            if(pionki.get(i).getIdd() == ind){
                return pionki.get(i);
            }
        }
        return null;
    }

    private void kolorujPola(){
        for(int i = 0; i < 121; i++){
            if(i < 10){
                pola.get(i).setStyle("-fx-background-color: rgba(255, 0, 0, 0.33); -fx-background-radius: 5; -fx-stroke: #000000");
                pola.get(i).setKolor(Kolor.CZERWONY);
            } else if(i < 14 || i > 22 && i < 26 || i > 34 && i < 37 || i == 46){
                pola.get(i).setStyle("-fx-background-color: rgba(61, 125, 186, 0.33); -fx-background-radius: 5; -fx-stroke: #000000");
                pola.get(i).setKolor(Kolor.NIEBIESKI);
            } else if( i > 18 && i < 23 || i > 31 && i < 35 || i > 43 && i < 46 || i == 55) {
                pola.get(i).setStyle("-fx-background-color: rgba(247, 255, 17, 0.33); -fx-background-radius: 5; -fx-stroke: #000000");
                pola.get(i).setKolor(Kolor.ZOLTY);
            } else if( i == 65 || i > 74 && i < 77 || i > 85 && i < 89 || i > 97 && i < 102) {
                pola.get(i).setStyle("-fx-background-color: rgba(68, 184, 76, 0.33); -fx-background-radius: 5; -fx-stroke: #000000");
                pola.get(i).setKolor(Kolor.ZIELONY);
            } else if( i == 74 || i > 83 && i < 86 || i > 94 && i < 98 || i > 106 && i < 111) {
                pola.get(i).setStyle("-fx-background-color: rgba(255, 50, 183, 0.33); -fx-background-radius: 5; -fx-stroke: #000000");
                pola.get(i).setKolor(Kolor.ROZOWY);
            } else if(i > 110){
                pola.get(i).setStyle("-fx-background-color: rgba(0, 233, 255, 0.33); -fx-background-radius: 5; -fx-stroke: #000000");
                pola.get(i).setKolor(Kolor.BLEKITNY);
            }
        }
    }

    private void kolorujPionki(){
        for(int i = 0; i < pionki.size(); i++){
            if(pionki.get(i).getIdd() > 9 && pionki.get(i).getIdd() < 20) {
                pionki.get(i).setStyle("-fx-fill: #c21400; -fx-stroke: #000000");
            } else if(pionki.get(i).getIdd() > 19 && pionki.get(i).getIdd() < 30) {
                pionki.get(i).setStyle("-fx-fill: #00e9ff; -fx-stroke: #000000");
            } else if(pionki.get(i).getIdd() > 29 && pionki.get(i).getIdd() < 40) {
                pionki.get(i).setStyle("-fx-fill: #3d7cba; -fx-stroke: #000000");
            } else if(pionki.get(i).getIdd() > 39 && pionki.get(i).getIdd() < 50) {
                pionki.get(i).setStyle("-fx-fill: #ff32b7; -fx-stroke: #000000");
            } else if(pionki.get(i).getIdd() > 49 && pionki.get(i).getIdd() < 60) {
                pionki.get(i).setStyle("-fx-fill: #f7ff11; -fx-stroke: #000000");
            } else if(pionki.get(i).getIdd() > 59 && pionki.get(i).getIdd() < 70) {
                pionki.get(i).setStyle("-fx-fill: #4eb84c; -fx-stroke: #000000");
            }
        }
    }

    public void setListaPionkow(ArrayList<ArrayList> listaPionkow){
        this.listaPionkow = listaPionkow;

        for (int i = 0; i < listaPionkow.size(); i++){
            for (int j = 0; j < listaPionkow.get(i).size(); j++){
                PlanszaKlient.pola[i][j] = (int) listaPionkow.get(i).get(j);
            }
        }

        for (int i = 0; i < pola.size(); i++){
            pola.get(i).getChildren().clear();
        }

        for(int i = 0; i < 17; i++){
            for(int j = 0; j < 25; j++){
                System.out.println(i+ " "+ j);
                if(PlanszaKlient.pola[i][j] != 0 && PlanszaKlient.pola[i][j] != 2){
                    Pionek p = szukajPionka(PlanszaKlient.pola[i][j]);
                    int po = szukajPola(i, j);
                    pola.get(po).getChildren().add(p);
                    p.setCenterX(pola.get(po).getCenterX());
                    p.setCenterY(pola.get(po).getCenterY());
                }
            }
        }
    }
}
