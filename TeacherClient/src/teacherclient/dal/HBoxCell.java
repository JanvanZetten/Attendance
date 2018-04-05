package teacherclient.dal;

import sharedclasses.be.SchoolClass;
import teacherclient.gui.controller.AbsenceController;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static java.util.concurrent.TimeUnit.DAYS;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sharedclasses.be.Student;
import sharedclasses.dal.DALException;
import teacherclient.bll.BllManager;
import teacherclient.gui.model.AbsenceModel;

/**
 * @author Alex, Asbjørn & Jan
 */
public class HBoxCell extends HBox {

    private Label label;
    private Button button1;
    private Button button2;
    private Label middleString;
    private Label lblAbsence;
    private Student student;
    private Double absence;
    private List<Date> presentDays;

    /**
     * Creates HBox from super class. Sets JavaFX Nodes.
     *
     * @param labelText
     */
    public HBoxCell(String labelText, SchoolClass schoolClass, BllManager bll) {
        super();

        label = new Label();
        button1 = new Button();
        button2 = new Button();
        middleString = new Label();

        label.setText(labelText);
        label.setMaxWidth(200);
        button1.setMaxWidth(Double.MAX_VALUE);
        button2.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(label, Priority.ALWAYS);
        HBox.setHgrow(button1, Priority.ALWAYS);
        HBox.setHgrow(button2, Priority.ALWAYS);

        //styling
        label.setFont(Font.font("Helvetica Neue"));
        label.setFont(Font.font(29));
        label.setTextFill(Paint.valueOf("gray"));
        label.setAlignment(Pos.CENTER_LEFT);

        middleString.setText(" ");

        button1.setText("Absence");
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Stage newStage = new Stage();
                    newStage.initModality(Modality.APPLICATION_MODAL);
                    FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/teacherclient/gui/view/AbsenceView.fxml"));
                    Parent root = fxLoader.load();
                    Scene scene = new Scene(root);
                    newStage.setTitle("Absence in " + schoolClass.getName());
                    newStage.setScene(scene);

                    AbsenceController cont = fxLoader.getController();
                    cont.setData(schoolClass, bll);

                    newStage.showAndWait();

                } catch (IOException ex) {
                    Logger.getLogger(HBoxCell.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        button2.setText("Statistics");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Stage newStage = new Stage();
                    newStage.initModality(Modality.APPLICATION_MODAL);
                    FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/teacherclient/gui/view/AbsenceView.fxml"));
                    Parent root = fxLoader.load();
                    Scene scene = new Scene(root);
                    newStage.setTitle("Statistics in " + schoolClass.getName());
                    newStage.setScene(scene);

                    AbsenceController cont = fxLoader.getController();
                    cont.setData(schoolClass, bll);

                    newStage.showAndWait();

                } catch (IOException ex) {
                    Logger.getLogger(HBoxCell.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        this.getChildren().addAll(label, button1, middleString, button2);
    }

    /**
     * Sets the HBoxes in the Statistics view (Name + Absence%).
     * 
     * @param student
     * @param model 
     */
    public HBoxCell(Student student, AbsenceModel model) {
        super();
        try {
            label = new Label();
            lblAbsence = new Label();
            this.student = student;

            label.setText(student.getName());
            label.setMaxWidth(200);
            label.setStyle("-fx-text-fill: white;" + "-fx-font-size: 16;");
            label.setMaxWidth(200);
            HBox.setHgrow(label, Priority.ALWAYS);
            
            lblAbsence.setAlignment(Pos.CENTER_RIGHT);
            lblAbsence.setStyle("-fx-text-fill: white;" + "-fx-font-size: 16;");

            calculateAbsence(model, student);
            
            NumberFormat format = new DecimalFormat("#0.00");
            lblAbsence.setText(format.format(absence) + "%");

            this.getChildren().addAll(label, lblAbsence);

        } catch (DALException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Could not get present days: " + ex.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    public Student getStudent() {
        return student;
    }

    public Double getAbsence() {
        return absence;
    }

    /**
     * Calculates absence percentage based on week-days from two given dates.
     * 
     * @param model
     * @param student
     * @throws DALException 
     */
    private void calculateAbsence(AbsenceModel model, Student student) throws DALException {

        DalFacade dal = new DalManager();
        List<Date> presentDays = new ArrayList<>();
        List<Date> dbPresentDays = dal.getPresentDays(student);

        Date startDate = Date.from(model.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(model.getEndDate().atStartOfDay(ZoneId.systemDefault()).toInstant());

        Calendar cal = Calendar.getInstance();

        for (int i = 0; i < dbPresentDays.size(); i++) {
            if (dbPresentDays.get(i).compareTo(startDate) != -1
                    && dbPresentDays.get(i).compareTo(endDate) != 1) {

                cal.setTime(dbPresentDays.get(i));

                if (cal.get(Calendar.DAY_OF_WEEK) != 6
                        && cal.get(Calendar.DAY_OF_WEEK) != 7) {
                    
                    presentDays.add(dbPresentDays.get(i));
                }
            }
        }

        absence = presentDays.size() * 1.0 / daysBetweenDatesWithoutWeekends(startDate, endDate) * 100;
        absence = absence + 100 - (2 * absence);
        presentDays.clear();
    }

    /**
     * Calculates the amount of days between two dates and removes weekend days.
     *
     * @param start
     * @param end
     * @return
     */
    private double daysBetweenDatesWithoutWeekends(Date start, Date end) {
        //Ignore argument check

        Calendar c1 = Calendar.getInstance();
        c1.setTime(start);
        int w1 = c1.get(Calendar.DAY_OF_WEEK);
        c1.add(Calendar.DAY_OF_WEEK, -w1);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(end);
        int w2 = c2.get(Calendar.DAY_OF_WEEK);
        c2.add(Calendar.DAY_OF_WEEK, -w2);

        //end Saturday to start Saturday
        long days = (c2.getTimeInMillis() - c1.getTimeInMillis()) / (1000 * 60 * 60 * 24);
        long daysWithoutWeekendDays = days - (days * 2 / 7);

        // Adjust days to add on (w2) and days to subtract (w1) so that Saturday
        // and Sunday are not included
        if (w1 == Calendar.SUNDAY && w2 != Calendar.SATURDAY) {
            w1 = Calendar.MONDAY;
        } else if (w1 == Calendar.SATURDAY && w2 != Calendar.SUNDAY) {
            w1 = Calendar.FRIDAY;
        }

        if (w2 == Calendar.SUNDAY) {
            w2 = Calendar.MONDAY;
        } else if (w2 == Calendar.SATURDAY) {
            w2 = Calendar.FRIDAY;
        }
        return daysWithoutWeekendDays - w1 + w2;
    }
}
