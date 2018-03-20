/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentclient;

import java.io.File;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sharedclasses.be.UserOptions;
import studentclient.gui.controller.LoginWindowController;

/**
 *
 * @author janvanzetten
 */
public class StudentClientStart extends Application
{

    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root;
        if (LoginIsRemember())
        {
            root = FXMLLoader.load(getClass().getResource("gui/view/MainWindowView.fxml"));
            stage.setResizable(true);
        }
        else
        {
            File file = new File("../SharedClasses/src/sharedclasses/gui/view/LoginWindow.fxml");
            System.out.println(file.getCanonicalFile().toURI().toString());
            FXMLLoader fxLoader = new FXMLLoader(file.getCanonicalFile().toURI().toURL());
            fxLoader.setController(new LoginWindowController());
            root = fxLoader.load();
            stage.setResizable(false);
        }

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("EASV - Student");
        File file = new File("../SharedClasses/src/sharedclasses/Resources/EASVLogo.png");
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

    private boolean LoginIsRemember()
    {
        return new UserOptions().getRememberMe();
    }

}
