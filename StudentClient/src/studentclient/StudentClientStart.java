/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author janvanzetten
 */
public class StudentClientStart extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root;
        if (LoginIsRemember()) {
            root = FXMLLoader.load(getClass().getResource("gui/view/MainWindowView.fxml"));
        } else {
            root = FXMLLoader.load(getClass().getResource("gui/view/LoginWindow.fxml"));
        }
        

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("ESAV - Student");
        stage.getIcons().add(new Image("studentclient/Resources/EASVLogo.png"));
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    
    private boolean LoginIsRemember() {
        return false;
    }

}
