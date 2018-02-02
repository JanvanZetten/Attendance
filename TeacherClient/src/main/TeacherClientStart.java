/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author janvanzetten
 */
public class TeacherClientStart extends Application {

    private Scene scene;
    
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/gui/view/LoginWindow.fxml"));
        Parent root = fxLoader.load();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("EASV-Teacher");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
