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
public class MM1System extends MMSystems {


//          public WWSystem(String str , double arrivalRate , double servesRate , int firstArrivals) {
//                    super(str , arrivalRate , servesRate , firstArrivals);
//          }
          public MM1System(double arrivalRate , double servesRate , int capasity) {
                    this(arrivalRate , servesRate);
                    setCapasity(capasity);
          }



          public MM1System(double arrivalRate , double servesRate) {
                    super(1 , arrivalRate , servesRate);
                    configP0();
          }


//
//          public MM1System(int capasity) {
//                    super(1 , capasity);
//          }

          @Override
          public double L() {
                    return arrivalRate / (servesRate - arrivalRate);
          }



          @Override
          public double Lq() {
                    return this.arrivalRate * Wq();
          }



          @Override
          public double W() {
                    return L() / arrivalRate;
          }



          @Override
          public double Wq() {
                    return W() * this.roo;
          }

//
//          public void setP0(double P0) {
//                    this.P0 = P0;
//                    setRoo(1 - P0);
//          }


          @Override
          public double P(int n) {
                    return Math.pow(this.roo , n) * getP0();
          }



//          public double getRoo() {
//                    if ( roo != 0 ) {
//                              return roo;
//                    }
//                    return arrivalRate / servesRate;
//          }
//
//
//
//
//
//
//          public void setRoo(double roo) {
//                    this.roo = roo;
//                    if ( arrivalRate != 0 ) {
//                              servesRate = arrivalRate / roo;
//                    } else if ( servesRate != 0 ) {
//                              arrivalRate = servesRate * roo;
//                    }
//                    if ( this.capasity == (int) Double.POSITIVE_INFINITY ) {
//                              this.P0 = 1 - roo;
//                    } else if ( roo != 1 ) {
//                              this.P0 = (1 - roo) / (1 - Maths.power(roo , capasity + 1));
//                    } else {
//                              throw new Error("roo must be != 1");
//                    }
//          }
          public double P(int n , int t) {
                    return (Maths.power(arrivalRate * t , n) / Maths.factorial(n)) * (Maths.power(Maths.E , -arrivalRate * t));
          }



          @Override
          public void configP0() {
                    this.P0 = 1 - roo;
          }



          @Override
          public String toString() {
                    return super.toString() + "\n"
                            + "L = " + String.format("%.05f",L()) + "\n"
                            + "Lq = " +String.format("%.05f",Lq()) + "\n"
                            + "W = " + String.format("%.05f",W()) + "\n"
                            + "Wq = " + String.format("%.05f",Wq());
          }

}
