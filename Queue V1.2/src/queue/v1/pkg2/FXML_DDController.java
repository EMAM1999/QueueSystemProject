/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package queue.v1.pkg2;

import EMAM.calculator.Maths;
import Systems.DDSystem;
import com.jfoenix.controls.JFXTextField;
import graph.Graph;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.*;
import static queue.v1.pkg2.FXMLDocumentController.*;

/**
 FXML Controller class

 @author EMAM
 */
public class FXML_DDController implements Initializable {


          private DDSystem d;

          @FXML
          private Label output;
          @FXML
          private Group outputGroup;
          @FXML
          private JFXTextField lamda;
          @FXML
          private JFXTextField mue;
          @FXML
          private JFXTextField capasity;
          @FXML
          private Group mGroup;
          @FXML
          private JFXTextField m;
          @FXML
          private Label errorMessage;
          @FXML
          private Label backArrow;

          @FXML
          private JFXTextField nOfWq;
          @FXML
          private JFXTextField tOfN;

          @FXML
          private Text nt;

          @FXML
          private Text wqn;

          @FXML
          private Group graphGroup;



          @FXML
          void backPressed(MouseEvent event) {
                    backArrow.setScaleX(0.9);
                    backArrow.setScaleY(0.9);
                    backArrow.setTextFill(Color.RED);
          }



          void backReleased() {
                    backArrow.setScaleX(1);
                    backArrow.setScaleY(1);
                    backArrow.setTextFill(Color.BLACK);
          }



          @FXML
          void back(MouseEvent event) throws IOException {
                    backReleased();
                    Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                    QueueV12.stage.setScene(new Scene(root));
          }



          @FXML
          void goNext(MouseEvent event) throws IOException {
                    systemNumber = (systemNumber + 1) % 5;
                    Parent root = FXMLLoader.load(getClass().getResource(Systems[systemNumber]));
                    QueueV12.stage.setScene(new Scene(root));
          }



          @FXML
          void goLast(MouseEvent event) throws IOException {
                    systemNumber = (systemNumber - 1 + 5) % 5;
                    Parent root = FXMLLoader.load(getClass().getResource(Systems[systemNumber]));
                    QueueV12.stage.setScene(new Scene(root));
          }



          @FXML
          void reset(MouseEvent event) {
                    lamda.setText("");
                    mue.setText("");
                    capasity.setText("");
                    m.setText("");
                    tOfN.setText("");
                    nOfWq.setText("");
                    mGroup.setVisible(false);
                    outputGroup.setVisible(false);
                    errorMessage.setVisible(false);
          }




          @FXML
          void confirm(KeyEvent event) {
                    errorMessage.setVisible(false);
                    if ( event.getCode() == KeyCode.ENTER ) {
                              try {
                                        if ( checkInput() ) {
                                                  double arrivalRate = Maths.calculate(lamda.getText());
                                                  double servesRate = Maths.calculate(mue.getText());
                                                  int k = (int) Maths.calculate(capasity.getText());
                                                  capasity.setText("" + k);
                                                  if ( arrivalRate > servesRate ) {
                                                            solve(arrivalRate , servesRate , k , 0);
                                                  } else {
                                                            if ( mGroup.isVisible() ) {
                                                                      solve(arrivalRate , servesRate , k , Integer.parseInt(m.getText()));
                                                            } else {
                                                                      mGroup.setVisible(true);
                                                            }
                                                  }
                                        } else {
                                                  throw new Exception();
                                        }
                              } catch ( Exception e ) {
                                        errorMessage.setVisible(true);
                              }
                    }
          }



          private void solve(double arrivalRate , double servesRate , int K , int M) {
                    d = new DDSystem(arrivalRate , servesRate , K , M);
                    output.setText(d.toString().replace((int) Double.POSITIVE_INFINITY + "" , "∞"));
                    outputGroup.setVisible(true);
                    Graph g = new Graph(60 , 90 , d.getCapasity() , 10 , d);
                    graphGroup.getChildren().add(g);
          }



          private boolean checkInput() {
                    return !(errorMessage.isVisible()
                            || lamda.getText().replaceAll(" " , "").isEmpty()
                            || lamda.getText().replaceAll(" " , "").isEmpty()
                            || lamda.getText().replaceAll(" " , "").isEmpty());
          }



          @FXML
          void solveNt(Event event) {
//                    if ( event.getCode() == KeyCode.ENTER ) {
                    try {
                              errorMessage.setVisible(false);
                              double[] res = d.n(Integer.parseInt(tOfN.getText()));
                              if ( res[0] == res[1] ) {
                                        nt.setText(res[0] + "");
                              } else {
                                        nt.setText(res[0] + "  OR  " + res[1]);
                              }

                    } catch ( NumberFormatException e ) {
                              errorMessage.setVisible(true);
                              nt.setText("?");
                    }
//                    }

          }



          @FXML
          void solveWqn(Event event) {
//                    if ( event.getCode() == KeyCode.ENTER ) {
                    try {
                              errorMessage.setVisible(false);
                              double[] res = d.Wq(Integer.parseInt(nOfWq.getText()));
                              if ( res[0] == res[1] ) {
                                        wqn.setText(res[0] + "");
                              } else {
                                        wqn.setText(res[0] + "  OR  " + res[1]);
                              }
                    } catch ( NumberFormatException e ) {
                              errorMessage.setVisible(true);
                              wqn.setText("?");
                    }
//                    }
          }



          @FXML
          void showGraph(Event event) {
                    Graph g = new Graph(600 , 900 , d.getCapasity() + 1 , (int) (d.getTi() * 1.2) , d);
                    g.setTranslateX(20);

                    Stage stage = new Stage();
                    stage.setScene(new Scene(g));
                    stage.setResizable(false);
                    stage.show();
          }



          /**
           Initializes the controller class.
           */
          @Override
          public void initialize(URL url , ResourceBundle rb) {
                    // TODO
          }

}
