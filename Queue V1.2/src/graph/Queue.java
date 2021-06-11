/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package graph;

import EMAM.calculator.Maths;
import Systems.DDSystem;
import Systems.MM1KSystem;
import Systems.MM1System;
import Systems.MMCKSystem;
import Systems.MMCSystem;
import java.io.FileNotFoundException;

import javafx.animation.*;
import javafx.application.*;

import static javafx.application.Application.launch;
import javafx.collections.*;
import javafx.geometry.*;

import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.*;

/**
 @author E.M.A.M
 */
public class Queue extends Application {
          
          
          private final double height = 700;
          private final double width = 1000;
          private Timeline t;
//          private Image image = new Image(getClass().getResourceAsStream("111.jpg"));

// YAGNI

          
          private Scene mainScene() {
//                    elements
                    ComboBox<String> c = new ComboBox(FXCollections.observableArrayList("D/D/1/K-1" , "M/M/1" , "M/M/1/K" , "M/M/C" , "M/M/C/K"));
                    TextField lamda = new TextField();
                    TextField mue = new TextField();
                    TextField capacity = new TextField();
                    TextField C = new TextField();
                    Button ok = new Button("Ok");
                    Label res = new Label();

//                    sittings
                    c.setValue("D/D/1/K-1");
                    lamda.setPromptText("arrival rate");
                    mue.setPromptText("serves rate");
                    capacity.setPromptText("capacity");
                    C.setPromptText("serves number");

//                    events
                    ok.setOnAction((e) -> {
                              String v = c.getValue();
                              switch ( v ) {
                                        case "D/D/1/K-1":
                                                  res.setText(new DDSystem(Maths.calculate(lamda.getText()) , Maths.calculate(mue.getText()) , Integer.parseInt(capacity.getText())).toString());
                                                  break;
                                        case "M/M/1":
                                                  res.setText(new MM1System(Maths.calculate(lamda.getText()) , Maths.calculate(mue.getText()) , Integer.parseInt(capacity.getText())).toString());
                                                  break;
                                        case "M/M/1/K":
                                                  res.setText(new MM1KSystem(Maths.calculate(lamda.getText()) , Maths.calculate(mue.getText()) , Integer.parseInt(capacity.getText())).toString());
                                                  break;
                                        case "M/M/C":
                                                  res.setText(new MMCSystem(Integer.parseInt(capacity.getText()) , Maths.calculate(lamda.getText()) , Maths.calculate(mue.getText())).toString());
                                                  break;
                                        case "M/M/C/K":
                                                  res.setText(new MMCKSystem(Integer.parseInt(C.getText()) , Integer.parseInt(capacity.getText()) , Maths.calculate(lamda.getText()) , Maths.calculate(mue.getText())).toString());
                                                  break;
                                        default:
                                                  break;
                              }
                    });
                    
                    VBox root = new VBox(20 , c , lamda , mue , capacity , C , ok , res);
                    root.setAlignment(Pos.CENTER);
                    return new Scene(root , width , height);
          }



//          private Scene welcome() throws FileNotFoundException {
////                    BufferedImage image = ImageIO.read(fileOrInputStreamOrURL);
//                    Button b = new Button("X");
//                    Group root = new Group(new ImageView(image));
//
//                    root.setScaleX(0);
//                    root.setScaleY(0);
//                    Scene scene = new Scene(root , image.getWidth() , image.getHeight() , Color.TRANSPARENT);
//                    t = new Timeline(
//                            new KeyFrame(Duration.seconds(.5) ,
//                                    new KeyValue(root.scaleXProperty() , 1) ,
//                                    new KeyValue(root.scaleYProperty() , 1) ,
//                                    new KeyValue(root.rotateProperty() , 1800)
//                            ));
//                    t.setAutoReverse(true);
//                    t.play();
//                    scene.setOnMousePressed(e -> hold(e));
//                    scene.setOnMouseDragged(e -> drag(e));
//                    return scene;
//          }
          private void hold(MouseEvent e) {
                    x = e.getSceneX();
                    y = e.getSceneY();
          }
          
          double x = 0, y = 0;
          
          
          
          private void drag(MouseEvent e) {
                    double difX = e.getScreenX() - x;
                    double difY = e.getScreenY() - y;
                    s.setX(difX);
                    s.setY(difY);
          }
          
          
          
          @Override
          public void start(Stage primaryStage) throws FileNotFoundException {
                    primaryStage.setTitle("Queue Project");
                    primaryStage.setScene(mainScene());
                    primaryStage.setAlwaysOnTop(true);
                    primaryStage.show();
                    s = primaryStage;
          }
          
          Stage s;



          /**
           @param args the command line arguments
           */
          public static void main(String[] args) {
                    launch(args);
          }
          
          
          
          public static final String fraction(double value) {
                    if ( value == (int) value ) {
                              return (int) value + "/1";
                    }
                    double x;
                    int i = 1;
                    while ( true ) {
                              x = value * i++;
                              if ( x == (int) x ) {
                                        break;
                              }
                    }
                    return (int) x + "/" + i;
          }
          
}
