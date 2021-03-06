/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package queue.v1.pkg2;

import EMAM.calculator.Maths;
import Systems.MM1KSystem;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import static queue.v1.pkg2.FXMLDocumentController.Systems;
import static queue.v1.pkg2.FXMLDocumentController.systemNumber;

/**
 FXML Controller class

 @author EMAM
 */
public class FXML_MM1KController implements Initializable {


          private MM1KSystem d;

          @FXML
          private Label backArrow;

          @FXML
          private Label errorMessage;

          @FXML
          private JFXTextField lamda;

          @FXML
          private JFXTextField mue;
          @FXML
          private JFXTextField capasity;
          @FXML
          private Group outputGroup;

          @FXML
          private Label output;

          @FXML
          private JFXTextField nOfP;

          @FXML
          private Text pn;



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
          void backPressed(MouseEvent event) {
                    backArrow.setScaleX(0.9);
                    backArrow.setScaleY(0.9);
                    backArrow.setTextFill(Color.RED);
          }



          @FXML
          void goLast(MouseEvent event) throws IOException {
                    systemNumber = (systemNumber - 1 + 5) % 5;
                    Parent root = FXMLLoader.load(getClass().getResource(Systems[systemNumber]));
                    QueueV12.stage.setScene(new Scene(root));
          }



          @FXML
          void goNext(MouseEvent event) throws IOException {
                    systemNumber = (systemNumber + 1) % 5;
                    Parent root = FXMLLoader.load(getClass().getResource(Systems[systemNumber]));
                    QueueV12.stage.setScene(new Scene(root));
          }



          @FXML
          void reset(MouseEvent event) {
                    lamda.setText("");
                    mue.setText("");
                    nOfP.setText("");
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
                                                  int k = Integer.parseInt(capasity.getText());
                                                  solve(arrivalRate , servesRate , k);
                                        } else {
                                                  throw new Exception();
                                        }
                              } catch ( Exception e ) {
                                        errorMessage.setVisible(true);
                              }
                    }
          }



          private void solve(double arrivalRate , double servesRate , int k) {
                    d = new MM1KSystem(arrivalRate , servesRate , k);
                    output.setText(d.toString().replace((int) Double.POSITIVE_INFINITY + "" , "???"));
                    outputGroup.setVisible(true);
          }



          @FXML
          void solvePn(KeyEvent event) {
                    try {
                              errorMessage.setVisible(false);
                              pn.setText("" + d.P(Integer.parseInt(nOfP.getText())));
                    } catch ( NumberFormatException e ) {
                              errorMessage.setVisible(true);
                              pn.setText("?");
                    }
          }



          private boolean checkInput() {
                    return !(errorMessage.isVisible()
                            || lamda.getText().replaceAll(" " , "").isEmpty()
                            || lamda.getText().replaceAll(" " , "").isEmpty()
                            || lamda.getText().replaceAll(" " , "").isEmpty());
          }



          /**
           Initializes the controller class.
           */
          @Override
          public void initialize(URL url , ResourceBundle rb) {
                    // TODO
          }

}
