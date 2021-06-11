/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package queue.v1.pkg2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.*;

/**

 @author EMAM
 */
public class FXMLDocumentController implements Initializable {


          public static int systemNumber;

          public static String[] Systems = { "FXML_DD.fxml" , "FXML_MM1.fxml" , "FXML_MM1K.fxml" , "FXML_MMC.fxml" , "FXML_MMCK.fxml" };

          @FXML
          Group helpGroup;
          @FXML
          Label helpLabel;


//          private double x = 0, y = 0;
//
//          @FXML
//          private void hold(MouseEvent e) {
//                    x = e.getSceneX();
//                    y = e.getSceneY();
//          }
//
//
//
//          @FXML
//          private void drag(MouseEvent e) {
//                    double difX = e.getScreenX() - x;
//                    double difY = e.getScreenY() - y;
//                    QueueV12.scene.setX(difX);
//                    QueueV12.scene.setY(difY);
//          }
//

          @FXML
          public void help(MouseEvent e) {
                    Node l = ((Node) e.getSource());
                    String id = l.getId();
                    switch ( id ) {
                              case "dd":
                                        helpLabel.setText(documents[0]);
                                        break;
                              case "mm1":
                                        helpLabel.setText(documents[1]);
                                        break;
                              case "mm1k":
                                        helpLabel.setText(documents[2]);
                                        break;
                              case "mmc":
                                        helpLabel.setText(documents[3]);
                                        break;
                              case "mmck":
                                        helpLabel.setText(documents[4]);
                                        break;
                    }
                    openHelp();
          }



          private void closeHelp() {
                    helpGroup.setVisible(false);
          }



          private void openHelp() {
                    helpGroup.setVisible(true);
          }



          @FXML
          public void closeHelp(MouseEvent e) {
                    closeHelp();
          }



          @FXML
          public void moodEntered(MouseEvent e) {
                    Group l = ((Group) e.getSource());
                    l.setOpacity(0.8);
                    l.setScaleX(1.1);
                    l.setScaleY(1.1);
          }



          @FXML
          public void moodExited(MouseEvent e) {
                    Group l = ((Group) e.getSource());
                    l.setOpacity(1);
                    l.setScaleX(1);
                    l.setScaleY(1);
          }



          @FXML
          public void moodChoosed(MouseEvent e) throws IOException {
                    int id = Integer.parseInt(((Node) e.getSource()).getId());
                    systemNumber = id;
                    Parent root = FXMLLoader.load(getClass().getResource(Systems[id]));
                    QueueV12.stage.setScene(new Scene(root));
          }

          private String[] documents = { "" , "" , "" , "" , "" };



          @Override
          public void initialize(URL url , ResourceBundle rb) {
                    try {
                              new FileWriter("SystemsInfo.txt" , true);
                              BufferedReader file = new BufferedReader(new FileReader("SystemsInfo.txt"));
                              String info = "";
                              Object[] o = file.lines().toArray();
                              for ( Object ele : o ) {
                                        info += "\n" + ele.toString();
                              }
                              Matcher matcher = Pattern.compile(
                                      "(DD1K\\{)([\\s||\\S]*?)(\\})"
                              ).matcher(info);
                              while ( matcher.find() ) {
                                        documents[0] += matcher.group(2);
                              }
                              matcher = Pattern.compile(
                                      "(MM\\{)([\\s||\\S]*?)(\\})"
                              ).matcher(info);
                              while ( matcher.find() ) {
                                        documents[1] += matcher.group(2);
                              }
                              matcher = Pattern.compile(
                                      "(MM1K\\{)([\\s||\\S]*?)(\\})"
                              ).matcher(info);
                              while ( matcher.find() ) {
                                        documents[2] += matcher.group(2);
                              }
                              matcher = Pattern.compile(
                                      "(MMC\\{)([\\s||\\S]*?)(\\})"
                              ).matcher(info);
                              while ( matcher.find() ) {
                                        documents[3] += matcher.group(2);
                              }
                              matcher = Pattern.compile(
                                      "(MMCK\\{)([\\s||\\S]*?)(\\})"
                              ).matcher(info);
                              while ( matcher.find() ) {
                                        documents[4] += matcher.group(2);
                              }
                    } catch ( FileNotFoundException ex ) {
                              Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE , null , ex);
                    } catch ( IOException ex ) {
                    }

          }

}
