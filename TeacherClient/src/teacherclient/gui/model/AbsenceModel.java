/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherclient.gui.model;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sharedclasses.be.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import sharedclasses.be.SchoolClass;
import sharedclasses.bll.BLLException;
import sharedclasses.bll.TimeUtils;
import sharedclasses.gui.model.AbsenceGraph;
import teacherclient.bll.BllManager;
import teacherclient.dal.HBoxCell;
import teacherclient.gui.model.HBoxCellComparator.AbsenceComparator;
import teacherclient.gui.model.HBoxCellComparator.HBoxCellComparator;
import teacherclient.gui.model.HBoxCellComparator.NameComparator;

/**
 *
 * @author alexl
 */
public class AbsenceModel
{

    private final String PRETEXT = "Absence in ";
    private final String POSTTEXT = ":";
    private final String TITLE = "Absence Chart";
    private final String X_AXIS_DESCRIPTION = "Absence in percentage";
    private final String Y_AXIS_DESCRIPTION = "Day of the week";
    private final String VALUE_POSTSYMBOL = "%";

    private SchoolClass schoolClass;
    private BllManager bll = new BllManager();
    private AbsenceGraph ag;
    private AnchorPane chartPane;
    private ObservableList<HBoxCell> ol;
    private ObservableList<HBoxCellComparator> comparatorList;
    private LocalDate startDate;
    private LocalDate endDate;

    public AbsenceModel()
    {
        comparatorList = FXCollections.observableArrayList();
    }

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
     * Sets the list view to be filled with relevant students that participate in
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
            selectStudent(ol.get(0).getStudent());
        }
        else
        {
            System.out.println("StudentInClass is null");
        }
    }

    public void selectStudent(Student student)
    {
        chartPane.getChildren().clear();
        this.startDate = getStartDate();
        this.endDate = getEndDate();
        try
        {
            TimeUtils tu = new TimeUtils();
            Calendar startDate = Calendar.getInstance();
            startDate.set(this.startDate.getYear(), this.startDate.getMonthValue() - 1, this.startDate.getDayOfMonth());
            Calendar endDate = Calendar.getInstance();
            endDate.set(this.endDate.getYear(), this.endDate.getMonthValue() - 1, this.endDate.getDayOfMonth());
            // Months are 0-based indexed.
            ag = new AbsenceGraph(chartPane, tu.getChartSeriesFromStudentAbsenceInWeekDays(startDate.getTime(), endDate.getTime(), bll.getPresentDays(student)), TITLE, X_AXIS_DESCRIPTION, Y_AXIS_DESCRIPTION, VALUE_POSTSYMBOL);
        }
        catch (BLLException ex)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Selecting student: " + ex.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    public LocalDate getStartDate()
    {
        return bll.getIntevalStartDate();
    }

    public void setupCmbSort(ComboBox<HBoxCellComparator> cmbSortListView)
    {
        comparatorList.add(new NameComparator());
        comparatorList.add(new AbsenceComparator());

        cmbSortListView.setItems(comparatorList);
        cmbSortListView.getSelectionModel().selectFirst();

        cmbSortListView.setConverter(new StringConverter<HBoxCellComparator>()
        {
            @Override
            public String toString(HBoxCellComparator object)
            {
                return object.getName();
            }

            @Override
            public HBoxCellComparator fromString(String string)
            {
                for (HBoxCellComparator hBoxCellComparator : comparatorList)
                {
                    if (hBoxCellComparator.getName().equals(string))
                    {
                        return hBoxCellComparator;
                    }
                }
                return null;
            }
        });

        cmbSortListView.valueProperty().addListener((observable, oldValue, newValue) ->
        {
            if (newValue != null)
            {
                if (newValue.getComparator() != null)
                {
                    ol.sort(newValue.getComparator());
                }
            }
        });
    }

    public LocalDate getEndDate()
    {
        return bll.getIntevalEndDate();
    }

    /**
     * sets and saves the interval values
     * @param startValue the start date
     * @param endValue the end date
     * @param currentStudent
     * @throws NumberFormatException
     */

    public void setInterval(LocalDate startValue, LocalDate endValue, Student currentStudent, ListView<HBoxCell> listviewStudents) throws NumberFormatException
    {
        try {
        if (startValue.toEpochDay() > endValue.toEpochDay())
        {
            throw new NumberFormatException("the start date has to be before the end date");
        }

        bll.saveInterval(startValue, endValue);

        selectStudent(currentStudent);
            setStudentList(listviewStudents);
        } catch (BLLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Selecting time interval: " + ex.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

}
