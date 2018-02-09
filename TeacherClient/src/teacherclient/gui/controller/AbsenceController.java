/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherclient.gui.controller;

import teacherclient.be.Student;
import teacherclient.data.CurrentData;
import teacherclient.data.MockData;
import teacherclient.gui.model.AbsenceModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author alexl
 */
public class AbsenceController implements Initializable {

    @FXML
    private ListView<Student> listviewStudents;
    @FXML
    private BarChart<?, ?> barchartAbsence;
    @FXML
    private Label labelClass;
    
    private AbsenceModel model;
    private Student selectedStudent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = new AbsenceModel();
    }    

    public void setData(CurrentData cData, MockData mData) {
        model.setInformation(labelClass, listviewStudents, barchartAbsence, cData, mData);
        
        
    }

    private void studentSelected(MouseEvent event) {
        if (listviewStudents.getSelectionModel().getSelectedItem() != null && selectedStudent != listviewStudents.getSelectionModel().getSelectedItem()) {
            selectedStudent = listviewStudents.getSelectionModel().getSelectedItem();
            System.out.println(listviewStudents.getSelectionModel().getSelectedItem());
            
        }
    }
    
}
