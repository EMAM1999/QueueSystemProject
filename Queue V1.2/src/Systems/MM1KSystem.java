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
public class MM1KSystem extends MM1System {


          public MM1KSystem(double arrivalRate , double servesRate , int capasity) {
                    super( arrivalRate , servesRate,capasity);
                    configP0();
          }



          @Override
          public void configP0() {
                    if ( roo != 1 ) {
                              P0 = (1 - roo) / (1 - Maths.power(roo , capasity + 1));
                    } else {
                              P0 = 1 / (capasity + 1);
                    }
          }



          public double lamdaDash() {
                    return arrivalRate * (1 - P(capasity));
          }



          @Override
          public double L() {
                    if ( roo == 1 ) {
                              return capasity / 2;
                    } else {
                              return roo
                                      * ((1 - (capasity + 1) * Math.pow(roo , capasity) + capasity * Math.pow(roo , capasity + 1))
                                      / ((1 - roo) * (1 - Math.pow(roo , capasity + 1))));
                    }
          }



          @Override
          public double Lq() {
                    return lamdaDash() * Wq();
          }



          @Override
          public double W() {
                    return L() / lamdaDash();
          }



          @Override
          public double Wq() {
                    return W() - servesTime;
          }



          @Override
          public String toString() {
                    return super.toString(); //To change body of generated methods, choose Tools | Templates.
          }

}
