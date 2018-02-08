/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.controller;

import be.Student;
import data.CurrentData;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author alexl
 */
public class AbsenceViewController implements Initializable {

    @FXML
    private ListView<Student> listviewStudents;
    @FXML
    private BarChart<?, ?> barchartAbsence;
    @FXML
    private Label labelClass;
    
    private CurrentData cData;
    private ArrayList studentList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Initialize");
    }    

    public void setCData(CurrentData cData) {
        this.cData = cData;
        System.out.println("SetData");
        
    }
    
}
