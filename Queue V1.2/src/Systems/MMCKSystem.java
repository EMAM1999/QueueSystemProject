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
public class MMCKSystem extends MMCSystem {


          public MMCKSystem(int serversNumber , int capasity , double arrivalRate , double servesRate) {
                    super(serversNumber , capasity , arrivalRate , servesRate);
          }



          @Override
          public double L() {
                    int c = serversNumber;
                    double res = 0;
                    for ( int n = 0 ; n < c ; n++ ) {
                              res += (c - n) * (power(getR() , n) / factorial(n));
                    }
                    res *= getP0();
                    return Lq() + c - res;
          }



          @Override
          public double Lq() {
                    int c = serversNumber;
                    return ((roo * power(getR() , c) * getP0()) / (factorial(c) * power(1 - roo , 2)))
                            * (1 - power(roo , capasity - c + 1) - ((1 - roo) * (capasity - c + 1) * power(roo , capasity - c)));
          }



          @Override
          public double P(int n) {
                    if ( n < capasity ) {
                              return super.P(n);
                    } else {
                              throw new Error("Invalid number " + n);
                    }
          }



          @Override
          public double W() {
                    return L() / lamdaDash();
          }



          @Override
          public double Wq() {
                    return Lq() / lamdaDash();
          }



          @Override
          public void configP0() {
                    int c = serversNumber;
                    double res = power(getR() , c) / factorial(c);
                    if ( roo != 1 ) {
                              res *= ((1 - power(roo , capasity - c + 1)) / (1 - roo));
                    } else {
                              res *= (capasity - c + 1);
                    }
                    for ( int n = 0 ; n < c ; n++ ) {
                              res += (power(getR() , n) / factorial(n));
                    }
                    P0 = 1 / res;
          }



          @Override
          public double mue(int n) {
                    if ( n < capasity ) {
                              return super.mue(n);
                    } else {
                              throw new Error("Invalid number " + n);
                    }
          }



          public double lamdaDash() {
                    return arrivalRate * (1 - P(capasity));
          }

}
