/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherclient.gui.controller;

import sharedclasses.be.Student;
import teacherclient.gui.model.AbsenceModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import sharedclasses.be.SchoolClass;
import teacherclient.bll.BllManager;

/**
 * FXML Controller class
 *
 * @author alexl
 */
public class AbsenceController implements Initializable
{

    @FXML
    private ListView<Student> listviewStudents;
    @FXML
    private Label labelClass;
    @FXML
    private AnchorPane chartPane;

    private AbsenceModel model;
    private Student selectedStudent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        model = new AbsenceModel();
    }

    /**
     * Sets the model to have the same data class instances as other classes and
     * sends FXML items to be set in the model.
     * @param cData
     * @param mData
     */
    public void setData(SchoolClass schoolClass, BllManager bll)
    {
        model.setInformation(labelClass, listviewStudents, chartPane, schoolClass, bll);
    }

    /**
     * Activates when a student is selected in the list view.
     * @param event
     */
    @FXML
    private void studentSelected(MouseEvent event)
    {
        {
            if (listviewStudents.getSelectionModel().getSelectedItem() != null && selectedStudent != listviewStudents.getSelectionModel().getSelectedItem())
            {
                selectedStudent = listviewStudents.getSelectionModel().getSelectedItem();
                System.out.println(listviewStudents.getSelectionModel().getSelectedItem());

            }
        }
    }
}
