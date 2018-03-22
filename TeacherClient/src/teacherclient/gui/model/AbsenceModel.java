/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherclient.gui.model;

import sharedclasses.be.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import sharedclasses.be.SchoolClass;
import sharedclasses.gui.model.AbsenceGraph;
import teacherclient.bll.BllManager;

/**
 *
 * @author alexl
 */
public class AbsenceModel
{

    private SchoolClass schoolClass;
    private BllManager bll;
    private AbsenceGraph ag;

    /**
     * Sets data class instances to be the same as other classes and sets items
     * from the view to be according to the mock data.
     * @param labelClass
     * @param listviewStudents
     * @param chartPane
     * @param cData
     * @param bll
     */
    public void setInformation(Label labelClass, ListView<Student> listviewStudents, AnchorPane chartPane, SchoolClass schoolClass, BllManager bll)
    {
        this.schoolClass = schoolClass;
        this.bll = bll;
        labelClass.setText("Absence in " + schoolClass.getName() + ":");
        setStudentList(listviewStudents);

        ag = new AbsenceGraph(chartPane, bll.getChartSeries());
    }

    /**
     * Sets the list view to be filled with relevant students that prticipate in
     * that course.
     * @param listviewStudents
     */
    private void setStudentList(ListView<Student> listviewStudents)
    {
        ObservableList<Student> ol = FXCollections.observableArrayList();
        for (Student student : bll.getListAllStudents())
        {
            for (sharedclasses.be.SchoolClass sClass : student.getClasses())
            {
                if (sClass.getName().equals(schoolClass.getName()))
                {
                    ol.add(student);
                }
            }
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
    }
}
