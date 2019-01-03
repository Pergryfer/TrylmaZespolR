package sample;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import klient.Klient;

import static javafx.scene.paint.Color.YELLOWGREEN;
import static klient.Klient.ruszPionek;

public class MyPane extends Pane {
    private String miejsce;
    public int x;
    public int y;

    public MyPane(String miejsce){
        //nie patrz na to XD, przypisuje im indexy w zaleznosci od numerku na planszy XD
        super();
        this.miejsce = miejsce;
        this.setStyle("-fx-background-color: #eee6ee");
        this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        this.setPrefSize(40, 40);
        switch (miejsce){
            case "0":
                this.x = 0;
                this.y = 12;
                break;
            case "1":
                this.x = 1;
                this.y = 11;
                break;
            case "2":
                this.x = 1;
                this.y = 13;
                break;
            case "3":
                this.x = 2;
                this.y = 10;
                break;
            case "4":
                this.x = 2;
                this.y = 12;
                break;
            case "5":
                this.x = 2;
                this.y = 14;
                break;
            case "6":
                this.x = 3;
                this.y = 9;
                break;
            case "7":
                this.x = 3;
                this.y = 11;
                break;
            case "8":
                this.x = 3;
                this.y = 13;
                break;
            case "9":
                this.x = 3;
                this.y = 15;
                break;
            case "10":
                this.x = 4;
                this.y = 0;
                break;
            case "11":
                this.x = 4;
                this.y = 2;
                break;
            case "12":
                this.x = 4;
                this.y = 4;
                break;
            case "13":
                this.x = 4;
                this.y = 6;
                break;
            case "14":
                this.x = 4;
                this.y = 8;
                break;
            case "15":
                this.x = 4;
                this.y = 10;
                break;
            case "16":
                this.x = 4;
                this.y = 12;
                break;
            case "17":
                this.x = 4;
                this.y = 14;
                break;
            case "18":
                this.x = 4;
                this.y = 16;
                break;
            case "19":
                this.x = 4;
                this.y = 18;
                break;
            case "20":
                this.x = 4;
                this.y = 20;
                break;
            case "21":
                this.x = 4;
                this.y = 22;
                break;
            case "22":
                this.x = 4;
                this.y = 24;
                break;
            case "23":
                this.x = 5;
                this.y = 1;
                break;
            case "24":
                this.x = 5;
                this.y = 3;
                break;
            case "25":
                this.x = 5;
                this.y = 5;
                break;
            case "26":
                this.x = 5;
                this.y = 7;
                break;
            case "27":
                this.x = 5;
                this.y = 9;
                break;
            case "28":
                this.x = 5;
                this.y = 11;
                break;
            case "29":
                this.x = 5;
                this.y = 13;
                break;
            case "30":
                this.x = 5;
                this.y = 15;
                break;
            case "31":
                this.x = 5;
                this.y = 17;
                break;
            case "32":
                this.x = 5;
                this.y = 19;
                break;
            case "33":
                this.x = 5;
                this.y = 21;
                break;
            case "34":
                this.x = 5;
                this.y = 23;
                break;
            case "35":
                this.x = 6;
                this.y = 2;
                break;
            case "36":
                this.x = 6;
                this.y = 4;
                break;
            case "37":
                this.x = 6;
                this.y = 6;
                break;
            case "38":
                this.x = 6;
                this.y = 8;
                break;
            case "39":
                this.x = 6;
                this.y = 10;
                break;
            case "40":
                this.x = 6;
                this.y = 12;
                break;
            case "41":
                this.x = 6;
                this.y = 14;
                break;
            case "42":
                this.x = 6;
                this.y = 16;
                break;
            case "43":
                this.x = 6;
                this.y = 18;
                break;
            case "44":
                this.x = 6;
                this.y = 20;
                break;
            case "45":
                this.x = 6;
                this.y = 22;
                break;
            case "46":
                this.x = 7;
                this.y = 3;
                break;
            case "47":
                this.x = 7;
                this.y = 5;
                break;
            case "48":
                this.x = 7;
                this.y = 7;
                break;
            case "49":
                this.x = 7;
                this.y = 9;
                break;
            case "50":
                this.x = 7;
                this.y = 11;
                break;
            case "51":
                this.x = 7;
                this.y = 13;
                break;
            case "52":
                this.x = 7;
                this.y = 15;
                break;
            case "53":
                this.x = 7;
                this.y = 17;
                break;
            case "54":
                this.x = 7;
                this.y = 19;
                break;
            case "55":
                this.x = 7;
                this.y = 21;
                break;
            case "56":
                this.x = 8;
                this.y = 4;
                break;
            case "57":
                this.x = 8;
                this.y = 6;
                break;
            case "58":
                this.x = 8;
                this.y = 8;
                break;
            case "59":
                this.x = 8;
                this.y = 10;
                break;
            case "60":
                this.x = 8;
                this.y = 12;
                break;
            case "61":
                this.x = 8;
                this.y = 14;
                break;
            case "62":
                this.x = 8;
                this.y = 16;
                break;
            case "63":
                this.x = 8;
                this.y = 18;
                break;
            case "64":
                this.x = 8;
                this.y = 20;
                break;
            case "65":
                this.x = 9;
                this.y = 3;
                break;
            case "66":
                this.x = 9;
                this.y = 5;
                break;
            case "67":
                this.x = 9;
                this.y = 7;
                break;
            case "68":
                this.x = 9;
                this.y = 9;
                break;
            case "69":
                this.x = 9;
                this.y = 11;
                break;
            case "70":
                this.x = 9;
                this.y = 13;
                break;
            case "71":
                this.x = 9;
                this.y = 15;
                break;
            case "72":
                this.x = 9;
                this.y = 17;
                break;
            case "73":
                this.x = 9;
                this.y = 19;
                break;
            case "74":
                this.x = 9;
                this.y = 21;
                break;
            case "75":
                this.x = 10;
                this.y = 2;
                break;
            case "76":
                this.x = 10;
                this.y = 4;
                break;
            case "77":
                this.x = 10;
                this.y = 6;
                break;
            case "78":
                this.x = 10;
                this.y = 8;
                break;
            case "79":
                this.x = 10;
                this.y = 10;
                break;
            case "80":
                this.x = 10;
                this.y = 12;
                break;
            case "81":
                this.x = 10;
                this.y = 14;
                break;
            case "82":
                this.x = 10;
                this.y = 16;
                break;
            case "83":
                this.x = 10;
                this.y = 18;
                break;
            case "84":
                this.x = 10;
                this.y = 20;
                break;
            case "85":
                this.x = 10;
                this.y = 22;
                break;
            case "86":
                this.x = 11;
                this.y = 1;
                break;
            case "87":
                this.x = 11;
                this.y = 3;
                break;
            case "88":
                this.x = 11;
                this.y = 5;
                break;
            case "89":
                this.x = 11;
                this.y = 7;
                break;
            case "90":
                this.x = 11;
                this.y = 9;
                break;
            case "91":
                this.x = 11;
                this.y = 11;
                break;
            case "92":
                this.x = 11;
                this.y = 13;
                break;
            case "93":
                this.x = 11;
                this.y = 15;
                break;
            case "94":
                this.x = 11;
                this.y = 17;
                break;
            case "95":
                this.x = 11;
                this.y = 19;
                break;
            case "96":
                this.x = 11;
                this.y = 21;
                break;
            case "97":
                this.x = 11;
                this.y = 23;
                break;
            case "98":
                this.x = 12;
                this.y = 0;
                break;
            case "99":
                this.x = 12;
                this.y = 2;
                break;
            case "100":
                this.x = 12;
                this.y = 4;
                break;
            case "101":
                this.x = 12;
                this.y = 6;
                break;
            case "102":
                this.x = 12;
                this.y = 8;
                break;
            case "103":
                this.x = 12;
                this.y = 10;
                break;
            case "104":
                this.x = 12;
                this.y = 12;
                break;
            case "105":
                this.x = 12;
                this.y = 14;
                break;
            case "106":
                this.x = 12;
                this.y = 16;
                break;
            case "107":
                this.x = 12;
                this.y = 18;
                break;
            case "108":
                this.x = 12;
                this.y = 20;
                break;
            case "109":
                this.x = 12;
                this.y = 22;
                break;
            case "110":
                this.x = 12;
                this.y = 24;
                break;
            case "111":
                this.x = 13;
                this.y = 9;
                break;
            case "112":
                this.x = 13;
                this.y = 11;
                break;
            case "113":
                this.x = 13;
                this.y = 13;
                break;
            case "114":
                this.x = 13;
                this.y = 15;
                break;
            case "115":
                this.x = 14;
                this.y = 10;
                break;
            case "116":
                this.x = 14;
                this.y = 12;
                break;
            case "117":
                this.x = 14;
                this.y = 14;
                break;
            case "118":
                this.x = 15;
                this.y = 11;
                break;
            case "119":
                this.x = 15;
                this.y = 13;
                break;
            case "120":
                this.x = 16;
                this.y = 12;
                break;
        }

        this.setOnMouseClicked((event) -> {
            Pionek pion = null;
            //System.out.println(this.getChildren().get(0).getId() + "\n" + this.getChildren().get(1).getId() + "\n" + this.getMiejsce());
            int klikniety = Pionek.kliknietyPionek;
            if(klikniety > 9 && this.getChildren().size() == 0) {
                pion = Main.szukajPionka(klikniety);
                MyPane pane = (MyPane) pion.getParent();
//                if(PlanszaGwiazda.czyDostepnePole(pane.getXX(), pane.getYY(), this.x, this.y)){
                    if(Klient.ruszPionek(pane.getXX(), pane.getYY(), this.x, this.y)) {
                        pane.getChildren().remove(pion);
                        this.getChildren().add(pion);
                        pion.setCenterX(this.getCenterX());
                        pion.setCenterY(this.getCenterY());
                    }
//                }
            }
        });
    }

    public String getMiejsce() {

        return miejsce;
    }

    public void setMiejsce(String miejsce) {
        this.miejsce = miejsce;
    }


    //Funkcje zwracajace srodek pola w okienku

    public double getCenterX(){
        return this.getTranslateX()+20;
    }

    public double getCenterY(){
        return this.getTranslateY()+20;
    }

    public int getXX() {
        return x;
    }

    public int getYY() {
        return y;
    }
}
