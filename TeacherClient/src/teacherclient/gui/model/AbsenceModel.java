/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherclient.gui.model;

import teacherclient.be.Student;
import teacherclient.data.CurrentData;
import teacherclient.data.MockData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author alexl
 */
public class AbsenceModel
{

    private CurrentData cData;
    private MockData mData;
    private AbsenceGraph ag;

    /**
     * Sets data class instances to be the same as other classes and sets items
     * from the view to be according to the mock data.
     * @param labelClass
     * @param listviewStudents
     * @param chartPane
     * @param cData
     * @param mData 
     */
    public void setInformation(Label labelClass, ListView<Student> listviewStudents, AnchorPane chartPane, CurrentData cData, MockData mData)
    {
        this.cData = cData;
        this.mData = mData;
        labelClass.setText("Absence in " + cData.getCurrentClass().getName() + ":");
        setStudentList(listviewStudents);

        ag = new AbsenceGraph(chartPane, mData.getChartSeries());
    }

    /**
     * Sets the list view to be filled with relevant students that prticipate
     * in that course.
     * @param listviewStudents 
     */
    private void setStudentList(ListView<Student> listviewStudents)
    {
        ObservableList<Student> ol = FXCollections.observableArrayList();
        for (Student student : mData.getListAllStudents())
        {
            for (teacherclient.be.SchoolClass sClass : student.getClasses())
            {
                if (sClass.getName().equals(cData.getCurrentClass().getName()))
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
