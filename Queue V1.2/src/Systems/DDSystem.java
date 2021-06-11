/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package Systems;

import EMAM.calculator.Maths;

/**

 @author EMAM
 */
public class DDSystem extends ServersSystem {


          double Ti;
          int M = 0;


//          public DDSystem(String str , double arrivalRate , double servesRate , int firstArrivals) {
//                    super(str , arrivalRate , servesRate , firstArrivals);
//          }

          public DDSystem(double arrivalRate , double servesRate) {
                    super(arrivalRate , servesRate);
                    setS1("D");
                    setS2("D");
          }



          public DDSystem(double arrivalRate , double servesRate , int capasity) {
                    this(arrivalRate , servesRate);
                    setCapasity(capasity);
          }



          public DDSystem(double arrivalRate , double servesRate , int capasity , int firstArrivals) {
                    super("D" , "D" , 1 , capasity , "FCFS" , arrivalRate , servesRate);
                    setS1("D");
                    setS2("D");
                    this.M = firstArrivals;
          }



          public int getM() {
                    return M;
          }



          public double getTi() {
                    configTi();
                    return Math.round(Ti);
          }



          public void setM(int M) {
                    this.M = M;
          }



          public double[] n(int t) {
                    double[] array = new double[2];
                    if ( arrivalRate > servesRate ) {
                              if ( t <= arrivalTime ) {
                                        return new double[] { 0 , 0 };
                              }
                              if ( t >= Ti ) {
                                        array[0] = capasity;
                                        array[1] = (servesTime % arrivalTime == 0) ? capasity : capasity - 1;
                              } else {
                                        array[0] = array[1] = (Math.floor(arrivalRate * t) - Math.floor(servesRate * t - (arrivalTime * servesRate)));
                                        System.out.println(array[0]);
                              }
                              return array;
                    }
                    if ( arrivalRate < servesRate ) {
                              if ( t < Ti ) {
                                        array[0] = array[1] = M + (Math.floor(arrivalRate * t) - Math.floor(servesRate * t));
                                        return array;
                              } else {
                                        double d = servesRate / arrivalRate;
                                        if ( Maths.ceil(d) - d <= 0.00001 ) {
                                                  array[0] = 0;
                                                  array[1] = 1;
                                                  return array;
                                        } else {
                                                  array[0] = array[1] = 0;
                                                  return array;
                                        }
                              }
                    }
                    return new double[] { M , M };
          }



          public double[] Wq(int n) {
                    double[] array = new double[] { 0 , 0 };
                    if ( arrivalRate > servesRate ) {
                              double sub = servesTime - arrivalTime;
                              if ( n == 0 ) {
                                        array[0] = array[1] = 0;
                              } else if ( n >= arrivalRate * Ti ) {
                                        array[0] = sub * (arrivalRate * Ti - 2);
                                        array[1] = (servesTime % arrivalTime == 0) ? sub * (arrivalRate * Ti - 2) : sub * (arrivalRate * Ti - 3);
                              } else {
                                        array[0] = array[1] = sub * (n - 1);
                              }
                              return array;
                    } else if ( arrivalRate == servesRate ) {
                              array[0] = array[1] = (M - 1) * (servesTime);
                              return array;
                    } else {
                              if ( n == 0 ) {
                                        array[0] = array[1] = (M - 1) / (2 * servesRate);
                                        return array;
                              } else if ( n < Math.floor(this.Ti * arrivalRate) ) {
                                        array[0] = array[1] = (M - 1 + n) * (servesTime) - (n * arrivalTime);
                                        return array;
                              } else {
                                        return array;
                              }
                    }
          }



          private void configTi() {
                    if ( arrivalRate > servesRate ) {
//                              double t = (((getCapasity() + 1) * getArrivalRate()) - getServesRate()) / ((getArrivalRate() * getArrivalRate()) - (getArrivalRate() * getServesRate()));
                              double t = (((getCapasity() + 1) - (getServesRate() / getArrivalRate())) / (getArrivalRate() - getServesRate()));
                              t = Math.round(t);
                              while ( Math.round(Math.floor(this.arrivalRate * t) - Math.floor(this.servesRate * (t -  this.arrivalTime))) == Math.round(this.capasity + 1) ) {
                                        t = t - arrivalTime;
                              }
                              this.Ti = t + arrivalTime;
                    } else if ( arrivalRate < servesRate ) {
                              double t = M / (servesRate - arrivalRate);
                              t = Math.round(t);
                              while ( Math.round(Math.floor(servesRate * t) - Math.floor(arrivalRate * t)) == Math.round(M) ) {
                                        t -= arrivalTime;
                              }
                              this.Ti = t + arrivalTime;
                    } else {
                              this.Ti = 0;
                    }
          }



          @Override
          public String toString() {
                    return super.toString()
                            + "\nTi = " + getTi()
                            + (M != 0 ? "\nM = " + M : "");
          }

}
