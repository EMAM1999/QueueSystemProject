/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package Systems;

/**

 @author EMAM
 */
public abstract class MMSystems extends ServersSystem {


          double roo = 0;
          double P0 = 0;

//          public WWSystem(String str , double arrivalRate , double servesRate , int firstArrivals) {
//                    super(str , arrivalRate , servesRate , firstArrivals);
//          }


          public MMSystems(int serversNumber , int capasity , double arrivalRate , double servesRate) {
                    this(serversNumber , capasity , "FCFS" , arrivalRate , servesRate);
          }



          public MMSystems(int serversNumber , double arrivalRate , double servesRate) {
                    this(serversNumber , (int) Double.POSITIVE_INFINITY , "FCFS" , arrivalRate , servesRate);
          }



          public MMSystems(int serversNumber , int capasity , String workSystem , double arrivalRate , double servesRate) {
                    super("M" , "M" , serversNumber , capasity , workSystem , arrivalRate , servesRate);
                    roo = this.arrivalRate / this.servesRate;
          }


//
//          public MMSystems(int serversNumber , int capasity) {
//                    super("M" , "M" , serversNumber , capasity , "FCFS" , 0 , 0 );
//          }

          public abstract double L();



          public abstract void configP0();



          public abstract double Lq();



          public abstract double W();



          public abstract double Wq();



          public double getP0() {
                    return P0;
          }



          public abstract double P(int n);



          public double getRoo() {
                    if ( roo != 0 ) {
                              return roo;
                    }
                    return arrivalRate / servesRate;
          }

//          public void setRoo(double roo) {
//                    this.roo = roo;
//                    this.P0 = 1 - roo;
//                    if ( arrivalRate != 0 ) {
//                              servesRate = arrivalRate / roo;
//                    } else if ( servesRate != 0 ) {
//                              arrivalRate = servesRate * roo;
//                    }
//          }
//          public double P(int n , int t) {
//                    return (Maths.power(arrivalRate * t , n) / Maths.factorial(n)) * (Maths.power(Maths.E , -arrivalRate * t));
//          }
}
