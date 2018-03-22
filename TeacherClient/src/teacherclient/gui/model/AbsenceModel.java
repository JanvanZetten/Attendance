/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherclient.gui.model;

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

/**
 *
 * @author alexl
 */
public class AbsenceModel
{
    private final String PRETEXT = "Absence in ";
    private final String POSTTEXT = ":";

    private SchoolClass schoolClass;
    private BllManager bll;
    private AbsenceGraph ag;
    private AnchorPane chartPane;

    /**
     * Sets data class instances to be the same as other classes and sets items
     * from the view to be according to the mock data.
     * @param labelClass
     * @param listviewStudents
     * @param chartPane
     * @param schoolClass
     * @param bll
     */
    public void setInformation(Label labelClass, ListView<Student> listviewStudents, AnchorPane chartPane, SchoolClass schoolClass, BllManager bll)
    {
        this.schoolClass = schoolClass;
        this.chartPane = chartPane;
        this.bll = bll;
        labelClass.setText(PRETEXT + schoolClass.getName() + POSTTEXT);
        try
        {
            setStudentList(listviewStudents);
        }
        catch (BLLException ex)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Getting students: " + ex.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    /**
     * Sets the list view to be filled with relevant students that prticipate in
     * that course.
     * @param listviewStudents
     */
    private void setStudentList(ListView<Student> listviewStudents) throws BLLException
    {
        ObservableList<Student> ol = FXCollections.observableArrayList();
        List<Student> studentsInClass = bll.getStudentsInClass(schoolClass);
        if (studentsInClass != null)
        {
            for (Student student : studentsInClass)
            {
                ol.add(student);
            }
            listviewStudents.setItems(ol);
            listviewStudents.setCellFactory(param -> new ListCell<Student>()
            {
                @Override
                protected void updateItem(Student item, boolean empty)
                {
                    super.updateItem(item, empty);

                    if (empty || item == null || item.getName() == null)
                    {
                        setText(null);
                    }
                    else
                    {
                        setText(item.getName());
                    }
                }
            });
            if (ol.size() > 0)
            {
                selectStudent(ol.get(0));
            }
        }
        else
        {
            System.out.println("StudentInClass is null");
        }
    }

    public void selectStudent(Student student)
    {
        chartPane.getChildren().clear();
        ag = new AbsenceGraph(chartPane, AbsenceGraph.getChartSeriesFromStudentAbsenceInWeekDays(student));
    }
}
