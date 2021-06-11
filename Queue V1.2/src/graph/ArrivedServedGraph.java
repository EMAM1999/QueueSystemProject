/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package graph;

import Systems.DDSystem;
import static graph.Graph.HCF;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;

/**

 @author EMAM
 */
public class ArrivedServedGraph extends Group {


          private final Color[] colors = {
                    Color.GOLD ,
                    Color.CORNFLOWERBLUE ,
                    Color.CORAL ,
                    Color.CADETBLUE ,
                    Color.DARKGOLDENROD ,
                    Color.BROWN ,
                    Color.CHOCOLATE ,
                    Color.FORESTGREEN ,
                    Color.DARKSLATEGRAY ,
                    Color.DARKORANGE

          };

          private final double block;
          private final int capacity;
          private final double height;
          private final double width;
          private int xNumbers;
          private double at;
          private double st;
          private DDSystem s;

          private Group xAxisGroup = new Group();
          private Group yAxisGroup = new Group();
          private Group pulseAxisGroup = new Group();
          private double xStepValue;
          private double xStep;



          ArrivedServedGraph(double height , double width , int xNumbers , DDSystem s) {
                    this.height = height;
                    this.width = width;
                    this.xNumbers = xNumbers;
                    this.capacity = s.getCapasity();
                    this.block = s.getTi();
                    this.at = s.getArrivalTime();
                    this.st = s.getServesTime();
                    this.s = s;

                    this.xStepValue = this.width / this.xNumbers;
                    this.xStep = HCF((int) at , (int) st);

                    configXAxisGroup();
                    configVectors();
          }



          private void configVectors() {
                    Group g = configArrivesVectors();
                    g.setTranslateY(this.height / 4);
                    this.getChildren().add(g);
                    if ( s.getM() == 0 ) {
                              g = configInServedVectors();
                              g.setTranslateY(this.height * .75);
                              this.getChildren().add(g);

                              g = configOutServedVectors();
                              g.setTranslateY(this.height);
                              this.getChildren().add(g);
                    }
          }



          private Group configArrivesVectors() {
                    Group g = new Group();
                    if ( s.getM() > 0 ) {
                              Group arrow = getArrow(this.xStepValue * this.xStep , this.height / 4 , Color.BLACK);
                              arrow.setTranslateX(this.xStepValue * this.at * (- 1));
                              g.getChildren().add(arrow);
                    }

                    for ( int i = 1 ; i < this.xNumbers / this.at ; i++ ) {
                              Group arrow = getArrow(this.xStepValue * this.xStep / 3 , this.height / 4 , colors[i % colors.length]);
                              arrow.setTranslateX(this.xStepValue * this.at * (i - 1));
                              g.getChildren().add(arrow);
                    }
                    g.setTranslateX(this.xStepValue * this.at);
                    return g;
          }



          private Group configInServedVectors() {
                    Group g = new Group();
                    for ( int i = 1 ; i < this.xNumbers / this.st ; i++ ) {
                              Group arrow = getArrow(this.xStepValue * this.xStep / 3 , this.height / 4 , colors[i % colors.length]);
                              arrow.setTranslateX(this.xStepValue * this.st * (i - 1));
                              g.getChildren().add(arrow);
                    }
                    g.setTranslateX(this.xStepValue * this.at);
                    return g;
          }



          private Group configOutServedVectors() {
                    Group g = new Group();
                    for ( int i = 1 ; i < (this.xNumbers / this.st) - 1 ; i++ ) {
                              Group arrow = getArrow(this.xStepValue * this.xStep / 3 , this.height / 4 , colors[i % colors.length]);
                              arrow.setTranslateX(this.xStepValue * this.st * (i - 1));
                              g.getChildren().add(arrow);
                    }
                    g.setTranslateX(this.xStepValue * (this.at + this.st));
                    return g;
          }



          private Group getArrow(double w , double h , Color color) {
                    Polygon polygon = new Polygon(
                            0 , 0 ,
                            .5 * w , -.25 * h ,
                            .25 * w , -.25 * h ,
                            .25 * w , -h ,
                            -.25 * w , -h ,
                            -.25 * w , -.25 * h ,
                            -.5 * w , -.25 * h
                    );
                    polygon.setFill(color);
                    return new Group(polygon);
          }



          private void configXAxisGroup() {
                    Line xAxis = new Line(0 , height * (1 / 4.) , width , height * (1 / 4.));
                    xAxisGroup.getChildren().add(xAxis);
                    xAxis = new Line(0 , height * (3 / 4.) , width , height * (3 / 4.));
                    xAxisGroup.getChildren().add(xAxis);

                    double y = 0;
                    int rTem = 0;
                    int dTem = 0;
                    for ( double i = 0 ; i <= width ; i += this.xStep * this.xStepValue ) {
                              Line l = new Line(i , 0 , i , height - 2);
                              l.setStrokeWidth(.5);
                              l.setStroke(Color.LIGHTGRAY);
                              if ( xStepValue * this.block == i ) {
                                        l.setStrokeWidth(1);
                                        l.setStroke(Color.DARKGOLDENROD);
                              }
                              xAxisGroup.getChildren().add(l);

//                              int d = (int) (((i - at) / this.xStep) / st);
//                              int r = (int) ((i / this.xStep) / at);
//                              if ( (r - d) > this.capasity ) {
//                                        r = rTem;
//                              }
//                              y = y + r - rTem - (d - dTem);
//                              Text t = new Text("" + y);
//                              t.setFont(Font.font(10));
//                              t.setTranslateX(i - 4);
//                              t.setTranslateY(height + 15);
//                              xAxisGroup.getChildren().add(t);
//                              rTem = r;
//                              dTem = d;
                    }
                    this.getChildren().add(xAxisGroup);
          }

}
