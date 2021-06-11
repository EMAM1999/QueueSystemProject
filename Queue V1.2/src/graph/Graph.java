/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package graph;

import Systems.DDSystem;
import javafx.scene.*;

/**

 @author EMAM
 */
public class Graph extends Group {


          public static double errorValue = 0.000001;



          public Graph(double height , double width , int yNumbers , int xNumbers , DDSystem s) {
                    double h = height / 3;
                    double ch = height - h;
                    this.getChildren().add(new ArrivedServedGraph(ch , width , xNumbers , s));
                    
                    Group p = new PulseGraph(h , width , s.getCapasity() , xNumbers , s);
                    p.setTranslateY(ch + .4 * h);
                    this.getChildren().add(p);
          }



          public static final int HCF(int num1 , int num2) {
                    if ( num1 <= 0 ) {
                              throw new RuntimeException("number must be positive :: Unexpected : " + num1);
                    }
                    if ( num2 <= 0 ) {
                              throw new RuntimeException("number must be positive :: Unexpected : " + num2);
                    }
                    int res = 1;
                    int lcf = 0;
                    while ( lcf != 1 ) {
                              lcf = LCF(num1 , num2);
                              res *= lcf;
                              num1 /= lcf;
                              num2 /= lcf;
                    }
                    return res;
          }



          private static final int LCF(int num1 , int num2) {
                    if ( num1 <= 0 ) {
                              throw new RuntimeException("number must be positive :: Unexpected : " + num1);
                    }
                    if ( num2 <= 0 ) {
                              throw new RuntimeException("number must be positive :: Unexpected : " + num2);
                    }
                    String f = "";
                    for ( int i = 1 ; i <= num1 ; i++ ) {
                              if ( num1 % i == 0 ) {
                                        f += i + " ";
                              }
                    }
                    for ( int i = 2 ; i <= num2 ; i++ ) {
                              if ( num2 % i == 0 ) {
                                        if ( f.contains(i + "") ) {
                                                  return i;
                                        }
                              }
                    }
                    return 1;
          }

}
