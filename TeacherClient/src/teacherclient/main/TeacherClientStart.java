/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherclient.main;

import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import teacherclient.gui.controller.LoginWindowController;

/**
 *
 * @author janvanzetten
 */
public class TeacherClientStart extends Application
{

    private Scene scene;

    @Override
    public void start(Stage stage) throws IOException
    {
        File file = new File("../SharedClasses/src/sharedclasses/gui/view/LoginWindow.fxml");
        FXMLLoader fxLoader = new FXMLLoader(file.getCanonicalFile().toURI().toURL());
        fxLoader.setController(new LoginWindowController());
        Parent root = fxLoader.load();
        stage.setResizable(false);

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("EASV-Teacher");
        file = new File("../SharedClasses/src/sharedclasses/Resources/EASVLogo.png");
        stage.getIcons().add(new Image(file.getCanonicalFile().toURI().toString()));
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

}
