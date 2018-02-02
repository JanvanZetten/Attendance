/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentclient.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import studentclient.gui.model.MainModel;

/**
 * FXML Controller class
 *
 * @author janvanzetten
 */
public class MainWindowViewController implements Initializable {
    
    MainModel mainModel = new MainModel();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleAbsenceGraph(ActionEvent event) {
        mainModel.showAbsenceGraph();
    }

    @FXML
    private void handlePresent(ActionEvent event) {
        mainModel.handlePresent(event);
    }

    @FXML
    private void handleNextWeek(MouseEvent event) {
    }

    @FXML
    private void handlePreviousWeek(MouseEvent event) {
    }
    
}
