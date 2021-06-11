/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package Systems;

import static EMAM.calculator.Maths.*;

/**

 @author EMAM
 */
public class MMCSystem extends MMSystems {


          private final double r;



          public MMCSystem(int serversNumber , double arrivalRate , double servesRate) {
                    super(serversNumber , arrivalRate , servesRate);
                    r = arrivalRate / servesRate;
                    roo = r / capasity;
                    configP0();
          }



          public MMCSystem(int serversNumber , int capasity , double arrivalRate , double servesRate) {
                    this(serversNumber , arrivalRate , servesRate);
                    setCapasity(capasity);
          }



          public double getR() {
                    return r;
          }



          public double mue(int n) {
                    if ( n >= 0 && n < serversNumber ) {
                              return n * servesRate;
                    } else if ( n >= serversNumber ) {
                              return serversNumber * servesRate;
                    } else {
                              throw new Error("Invalid negative number " + n);
                    }
          }



          @Override
          public void configP0() {
                    int c = serversNumber;
                    double res;
                    if ( roo < 1 ) {
                              res = (c * power(r , c)) / (factorial(c) * (c - r));
                    } else {
                              res = (power(r , c) / factorial(c)) * ((c * servesRate) / (c * servesRate - arrivalRate));
                    }
                    for ( int n = 0 ; n < c ; n++ ) {
                              res += (power(r , n) / factorial(n));
                    }
                    P0 = 1 / res;
          }



          @Override
          public double P(int n) {
                    int c = serversNumber;
                    if ( n >= 0 && n < c ) {
                              return P0 * (power(r , n) / factorial(n));
                    } else if ( n >= c ) {
                              return P0 * (power(r , n) / (factorial(c) * power(c , n - c)));
                    } else {
                              throw new Error("Invalid negative number " + n);
                    }
          }



          @Override
          public double L() {
                    return Lq() + r;
          }



          @Override
          public double Lq() {
                    int c = serversNumber;
                    return P0
                            * (power(r , c + 1) / c)
                            / (factorial(c) * power(1 - r / c , 2));
          }



          @Override
          public double W() {
                    return Wq() + servesTime;
          }



          public double idleServerAverageNum() {
                    return serversNumber - r;
          }



          @Override
          public double Wq() {
                    return Lq() / arrivalRate;
          }



          @Override
          public String toString() {
                    return super.toString() + "\n"
                            + "L = " + L() + "\n"
                            + "Lq = " + Lq() + "\n"
                            + "W = " + W() + "\n"
                            + "Wq = " + Wq();
          }

}
