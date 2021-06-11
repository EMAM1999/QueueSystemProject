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
public abstract class ServersSystem {


          String s1;
          String s2;
          int serversNumber = 1;
          int capasity = (int) Double.POSITIVE_INFINITY;
          String workSystem = "FCFS";
          double arrivalRate = 0;
          double servesRate = 0;
          double arrivalTime = 0;
          double servesTime = 0;



          public ServersSystem() {
          }



          public ServersSystem(double arrivalRate , double servesRate) {
                    setArrivalRate(arrivalRate);
                    setServesRate(servesRate);
          }



          public ServersSystem(String str , double arrivalRate , double servesRate) {
                    setArrivalRate(arrivalRate);
                    setServesRate(servesRate);
                    int in1 = str.indexOf("/");
                    int in2 = str.indexOf("/" , in1 + 1);
                    int in3 = str.indexOf("/" , in2 + 1);
                    int in4 = str.indexOf("/" , in3 + 1);
                    if ( str.matches("[\\w]+/[\\w]+/[\\d]+/[\\d]+/[\\w]+") ) {
                              workSystem = str.substring(in4 + 1);
                              str = str.substring(0 , in4);
                    }
                    if ( str.matches("[\\w]+/[\\w]+/[\\d]+/[\\d]+") ) {
                              capasity = Integer.parseInt(str.substring(in3 + 1 , in4));
                              str = str.substring(0 , in3);
                    }
                    if ( str.matches("[\\w]+/[\\w]+/[\\d]+") ) {
                              serversNumber = Integer.parseInt(str.substring(in2 + 1 , in3));
                              str = str.substring(0 , in2);
                    }
                    if ( str.matches("[\\w]+/[\\w]+") ) {
                              s2 = str.substring(in1 + 1 , in2);
                              str = str.substring(0 , in1);
                    }
                    s1 = str.substring(in1);
          }



          public ServersSystem(String s1 , String s2 , int serversNumber , int capasity , double arrivalRate , double servesRate , int firstArrivals) {
                    this(s1 , s2 , serversNumber , capasity , "FCFS" , arrivalRate , servesRate);
          }



          public ServersSystem(String s1 , String s2 , int serversNumber , int capasity , String workSystem , double arrivalRate , double servesRate) {
                    setArrivalRate(arrivalRate);
                    setServesRate(servesRate);
                    setS1(s1);
                    setS2(s2);
                    setServersNumber(serversNumber);
                    setCapasity(capasity);
                    setWorkSystem(workSystem);
          }



          public double getArrivalRate() {
                    return arrivalRate;
          }



          private void setArrivalRate(double arrivalRate) {
                    this.arrivalRate = arrivalRate;
                    this.arrivalTime = 1 / arrivalRate;
          }



          public double getArrivalTime() {
                    return arrivalTime;
          }



          public int getCapasity() {
                    return capasity;
          }



          public void setCapasity(int capasity) {
                    this.capasity = capasity;
          }



          public String getS1() {
                    return s1;
          }



          public void setS1(String s1) {
                    this.s1 = s1;
          }



          public String getS2() {
                    return s2;
          }



          public void setS2(String s2) {
                    this.s2 = s2;
          }



          public int getServersNumber() {
                    return serversNumber;
          }



          public void setServersNumber(int serversNumber) {
                    this.serversNumber = serversNumber;
          }



          public double getServesRate() {
                    return servesRate;
          }



          private void setServesRate(double servesRate) {
                    this.servesRate = servesRate;
                    this.servesTime = 1 / servesRate;
          }



          public double getServesTime() {
                    return servesTime;
          }



          public String getWorkSystem() {
                    return workSystem;
          }



          public void setWorkSystem(String workSystem) {
                    this.workSystem = workSystem;
          }



          @Override
          public String toString() {
                    return s1 + "/" + s2 + "/" + serversNumber + "/" + capasity + "/" + workSystem + "\n"
                            + "λ = " +Maths.fraction(this.getArrivalRate()) + "\n"
                            + "μ = " + Maths.fraction(this.getServesRate());
          }

}
