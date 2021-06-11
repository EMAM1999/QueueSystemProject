/*
 To change this license header, choose License Headers in Project Properties.
 To change this template file, choose Tools | Templates
 and open the template in the editor.
 */
package queue.v1.pkg2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**

 @author EMAM
 */
public class QueueV12 extends Application {


          public static Scene scene;
          public static Stage stage;



          @Override
          public void start(Stage stage) throws Exception {
                    Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

                    Scene scene = new Scene(root);
                    QueueV12.scene = scene;
                    this.stage = stage;
                    stage.setScene(scene);
                    stage.show();
          }



          /**
           @param args the command line arguments
           */
          public static void main(String[] args) {
                    launch(args);
          }

}
