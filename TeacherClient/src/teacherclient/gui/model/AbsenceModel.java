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
import javafx.scene.chart.BarChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

/**
 *
 * @author alexl
 */
public class AbsenceModel {

    private CurrentData cData;
    private MockData mData;

    public void setInformation(Label labelClass, ListView<Student> listviewStudents, BarChart<?, ?> barchartAbsence, CurrentData cData, MockData mData) {
        this.cData = cData;
        this.mData = mData;
        labelClass.setText("Absence in " + cData.getCurrentClass().getName() + ":");
        setStudentList(listviewStudents);
        setBarChart(barchartAbsence);
    }

    private void setStudentList(ListView<Student> listviewStudents) {
        ObservableList<Student> ol = FXCollections.observableArrayList();
        for (Student student : mData.getListAllStudents()) {
            for (teacherclient.be.Class sClass : student.getClasses()) {
                if (sClass.getName().equals(cData.getCurrentClass().getName())) {
                    ol.add(student);
                }
            }
        }
        listviewStudents.setItems(ol);
        listviewStudents.setCellFactory(param -> new ListCell<Student>() {
            @Override
            protected void updateItem(Student item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getName()== null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });
    }

    private void setBarChart(BarChart<?, ?> barchartAbsence) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
