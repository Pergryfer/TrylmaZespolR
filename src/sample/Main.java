package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;

import java.util.ArrayList;


public class Main extends Application {

    static ArrayList<MyPane> pola = new ArrayList<MyPane>();
    static ArrayList<HBox> lista = new ArrayList<HBox>();

    @Override
    public void start(Stage primaryStage) throws Exception{
        /*Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();*/

        Pane root = stworzOkno();
        Scene scene = new Scene(root, 1000, 680);
        primaryStage.setScene(scene);
        primaryStage.show();


    }





    public static void main(String[] args) {
        launch(args);
        PlanszaGwiazda planszaGwiazda = new PlanszaGwiazda();
    }

    private Pane stworzOkno(){
        Pane pane = new Pane();
        pane.setPrefSize(1000,680);

        VBox vBox = new VBox();
        vBox.setPrefSize(1000,680);
        int k = 0;
        int l = 0;
        for(int i = 0; i < 121; i++){
            pola.add(new MyPane(Integer.toString(i)));
            if(i == 0){
                lista.add(new HBox());
                lista.get(k).setAlignment(Pos.TOP_CENTER);
                lista.get(k).setPrefSize(1000,40);
                lista.get(k).getChildren().add(pola.get(0));
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
                lista.get(k).getChildren().addAll(pola.get(3), new MyVBox(), pola.get(4), new MyVBox(), pola.get(5));
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
        Circle c = new Circle(20);
        pola.get(0).getChildren().add(c);
        c.setCenterX(pola.get(0).getCenterX());
        c.setCenterY(pola.get(0).getCenterY());

        vBox.getChildren().addAll(lista);
        pane.getChildren().add(vBox);



        return pane;
    }
}
