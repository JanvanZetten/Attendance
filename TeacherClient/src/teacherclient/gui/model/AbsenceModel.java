/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherclient.gui.model;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import sharedclasses.be.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import sharedclasses.be.SchoolClass;
import sharedclasses.bll.BLLException;
import sharedclasses.gui.model.AbsenceGraph;
import teacherclient.bll.BllManager;
import teacherclient.dal.HBoxCell;

/**
 *
 * @author alexl
 */
public class AbsenceModel {

    private final String PRETEXT = "Absence in ";
    private final String POSTTEXT = ":";

    private SchoolClass schoolClass;
    private BllManager bll = new BllManager();
    private AbsenceGraph ag;
    private AnchorPane chartPane;
    private ObservableList<HBoxCell> ol;

    /**
     * Sets data class instances to be the same as other classes and sets items
     * from the view to be according to the mock data.
     *
     * @param labelClass
     * @param listviewStudents
     * @param chartPane
     * @param schoolClass
     * @param bll
     */
    public void setInformation(Label labelClass, ListView<HBoxCell> listviewStudents, AnchorPane chartPane, SchoolClass schoolClass, BllManager bll)
    {
        this.schoolClass = schoolClass;
        this.chartPane = chartPane;
        this.bll = bll;
        labelClass.setText(PRETEXT + schoolClass.getName() + POSTTEXT);
        try {
            setStudentList(listviewStudents);
        } catch (BLLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Getting students: " + ex.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    /**
     * Sets the list view to be filled with relevant students that prticipate in
     * that course.
     *
     * @param listviewStudents
     */
    private void setStudentList(ListView<HBoxCell> listviewStudents) throws BLLException
    {
        ol = FXCollections.observableArrayList();
        List<Student> studentsInClass = bll.getStudentsInClass(schoolClass);
        if (studentsInClass != null)
        {
            for (Student student : studentsInClass)
            {
                ol.add(new HBoxCell(student, this));
            }
            listviewStudents.setItems(ol);
        }
        else
        {
            System.out.println("StudentInClass is null");
        }
    }

    public void selectStudent(Student student) {
        chartPane.getChildren().clear();
        try
        {
            Calendar startDate = Calendar.getInstance();
            Calendar endDate = Calendar.getInstance();
            // Months are 0-based indexed.
            startDate.set(2018, 2, 22);
            ag = new AbsenceGraph(chartPane, AbsenceGraph.getChartSeriesFromStudentAbsenceInWeekDays(startDate.getTime(), endDate.getTime(), bll.getPresentDays(student)));
        }
        catch (BLLException ex)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Selecting student: " + ex.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    public ObservableList<HBoxCell> getOl() {
        return ol;
    }
    
    

    public LocalDate getStartDate() {

        return bll.getIntevalStartDate();

    }
}
