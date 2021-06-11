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
import javafx.scene.text.*;

/**

 @author EMAM
 */
public class PulseGraph extends Group {


          private final double block;
          private final double height;
          private final double width;
          private int xNumbers;
          private int yNumbers;
          private double at;
          private double st;
          private DDSystem s;

          private Group xAxisGroup = new Group();
          private Group yAxisGroup = new Group();
          private Group pulseAxisGroup = new Group();
          private double xStepValue;
          private double xStep;
          private double yStepValue;
//          private Group axisesGroup;




          public PulseGraph(double height , double width , int yNumbers , int xNumbers , DDSystem s) {
                    this.height = height;
                    this.width = width;
                    this.xNumbers = xNumbers;
//                            + ((int) (width % xNumbers) / (int) (width / xNumbers));
                    this.yNumbers = Math.max(yNumbers , s.getM());
                    this.at = s.getArrivalTime();
                    this.st = s.getServesTime();
//                    this.capacity = s.getCapasity();
                    this.block = s.getTi();
                    this.s = s;

                    this.xStepValue = this.width / this.xNumbers;
                    this.xStep = HCF((int) at , (int) st);
                    this.yStepValue = (int) height / (this.yNumbers + 1);

                    configAxises();
                    configPulseGroup();
          }



          private void configPulseGroup() {
                    Polyline line = new Polyline();
//                    double at = this.at * (xStepValue);
//                    double st = this.st * (xStepValue);
                    int r = -1, d = -1;
                    double y = s.getM(), yTem = y;
                    boolean started = s.getM() > 0;
                    for ( double i = 0 ; i <= width ; i += xStepValue ) {
                              r++;
                              if ( r % this.at == 0 && r > 0 ) {
                                        r = 0;
                                        y++;
                              }
                              if ( y != 0 ) {
                                        started = true;
                              }
                              if ( started ) {
                                        d++;
                                        if ( d % this.st == 0 && d > 0 ) {
                                                  d = 0;
                                                  y--;
                                        }
                              }
                              while ( y > Math.max(this.s.getCapasity() , this.s.getM()) ) {
                                        y--;
                              }
                              while ( y < 0 ) {
                                        y++;
                              }
                              if ( y == 0 ) {
                                        started = false;
                                        d = -1;
                              }
                              if ( y != yTem ) {
                                        line.getPoints().add((double) i);
                                        line.getPoints().add(-yTem * this.yStepValue);
                              }
                              line.getPoints().add(i);
                              line.getPoints().add(-y * this.yStepValue);

                              if ( d % this.st == 0 && d > 0 ) {
                                        r = 0;
                              }
                              yTem = y;

////////////////// 
//                              yTem = y;
//                              rTem = r;
//                              dTem = d;
                              int thisSeecond = (int) (i / xStepValue);
//                              if ( Math.abs(thisSeecond - i / xStepValue) <= Graph.errorValue ) {
//                                        thisSeecond++;
//                              }
                              if ( thisSeecond % this.xStep == 0 ) {
                                        Text t = new Text("" + (int) y);
                                        int fontSize = 16;
                                        t.setFont(Font.font(t.getFont().getFamily() , FontWeight.BOLD , fontSize));
                                        t.setTranslateX(i - fontSize / 4);
                                        t.setTranslateY(-1.5 * this.yStepValue);
                                        xAxisGroup.getChildren().add(t);
                              }
                    }
                    line.setStroke(Color.BROWN);
                    line.setStrokeWidth(2);
                    pulseAxisGroup.getChildren().add(line);
                    pulseAxisGroup.setTranslateY(this.height);
                    this.getChildren().add(pulseAxisGroup);
          }

//          private void hold(MouseEvent e) {
//                    root.setCursor(Cursor.CLOSED_HAND);
//                    x = axisesGroup.getTranslateX() - e.getSceneX();
//                    y = axisesGroup.getTranslateY() - e.getSceneY();
//          }
//
//
//
//          private void drag(MouseEvent e) {
//                    double difX = e.getSceneX() + x;
//                    double difY = e.getSceneY() + y;
//                    if ( difX >= 100000 - width / 2 || difX <= -100000 - width / 2 ) {
//                              return;
//                    }
//                    axisesGroup.setTranslateX(difX);
//                    axisesGroup.setTranslateY(difY);
//          }


          private void configXAxisGroup() {
                    Line xAxis = new Line(0 , height , width , height);
                    xAxisGroup.getChildren().add(xAxis);
                    for ( double i = 0 ; i <= width ; i += xStepValue * xStep ) {
                              xAxisGroup.getChildren().add(new Line(i , height - 2 , i , height + 2));
                              Line l = new Line(i , -yStepValue , i , height - 2);
                              l.setStrokeWidth(.5);
                              l.setStroke(Color.LIGHTGRAY);
                              if ( Math.abs(xStepValue * this.block - i) <= Graph.errorValue ) {
                                        l.setStrokeWidth(1);
                                        l.setStroke(Color.DARKGOLDENROD);
                              }
                              xAxisGroup.getChildren().add(l);
                              int temp = (int) (i / xStepValue);
                              if ( Math.abs(temp + 1 - (i / xStepValue)) <= Graph.errorValue ) {
                                        temp++;
                              }
                              if ( temp != 0 ) {
                                        Text t = new Text("" + temp);
                                        t.setFont(Font.font(10));
                                        t.setTranslateX(i - 4);
                                        t.setTranslateY(height + 15);
                                        xAxisGroup.getChildren().add(t);
                              }
                    }
                    this.getChildren().add(xAxisGroup);
          }



          private void configAxises() {
                    configXAxisGroup();
                    configYAxisGroup();
          }



          private void configYAxisGroup() {
                    Line yAxis = new Line(0 , height , 0 , -yStepValue);
//                    Text y = new Text("Y Axis");
//                    y.setTranslateX((width / 2) + 10);
//                    y.setTranslateY(15);
                    yAxisGroup.getChildren().add(yAxis);
                    for ( int i = 0 ; i <= height ; i += yStepValue ) {
                              yAxisGroup.getChildren().add(new Line(-2 , height - i , 2 , height - i));
                              Line l = new Line(2 , height - i , width , height - i);
                              l.setStrokeWidth(1);
                              l.setStroke(Color.LIGHTGRAY);
                              yAxisGroup.getChildren().add(l);

                              int temp = (int) (i / yStepValue);
//                              if (  temp != 0. ) {
                              Text t = new Text(String.format("%2s" , temp));
                              t.setFont(Font.font(10));
                              t.setTranslateX(-15);
                              t.setTranslateY(height - i);
                              yAxisGroup.getChildren().add(t);
//                              }
                    }
                    Text t = new Text("n(t)");
                    t.setFont(Font.font(t.getFont().getFamily() , FontWeight.BOLD , 15));
                    t.setTranslateX(5);
                    t.setTranslateY(-yStepValue / 2);
                    yAxisGroup.getChildren().add(t);
                    this.getChildren().add(yAxisGroup);
          }

}
