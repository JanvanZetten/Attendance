package gui.controller;

import data.HBoxCell;
import gui.model.MainModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author alexl
 */
public class MainController implements Initializable {
    
    private MainModel model;
    @FXML
    private ListView<HBoxCell> listviewClasses;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = new MainModel();
        model.createMockData();
        model.setClassList(listviewClasses);
    }    
    
}
