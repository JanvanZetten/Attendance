package teacherclient.dal;

import sharedclasses.be.SchoolClass;
import teacherclient.gui.controller.AbsenceController;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
public class HBoxCell extends HBox
{

    private Label label;
    private Button button1;
    private Button button2;
    private Label middleString;
    private Label lblAbsence;
    private Long absence;
    private Student student;

    /**
     * Creates HBox from super class. Sets JavaFX Nodes.
     *
     * @param labelText
     */
    public HBoxCell(String labelText, SchoolClass schoolClass, BllManager bll)
    {
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
        button1.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                try
                {
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

                }
                catch (IOException ex)
                {
                    Logger.getLogger(HBoxCell.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        button2.setText("Statistics");
        button2.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                try
                {
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

                }
                catch (IOException ex)
                {
                    Logger.getLogger(HBoxCell.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        this.getChildren().addAll(label, button1, middleString, button2);
    }

    public HBoxCell(Student student, AbsenceModel model)
    {
        super();
        DalFacade dal = new DalManager();

        label = new Label();
        lblAbsence = new Label();
        this.student = student;

        label.setText(student.getName());
        label.setMaxWidth(200);
        lblAbsence.setText(student.getId() + "%");

        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        LocalDate selectedDate = LocalDate.now();

        try
        {
            List<Date> presentDays = dal.getPresentDays(student);

            System.out.println("Present days: " + presentDays.size());
            System.out.println("Days between dates: " + ChronoUnit.DAYS.between(dal.getIntevalStartDate(), LocalDate.now()));

            absence = presentDays.size() / ChronoUnit.DAYS.between(dal.getIntevalStartDate(), LocalDate.now()) * 100;
            lblAbsence.setText(absence + "%");

            label.setStyle("-fx-text-fill: white;" + "-fx-font-size: 16;");
            label.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label, Priority.ALWAYS);

            lblAbsence.setAlignment(Pos.CENTER_RIGHT);
            lblAbsence.setStyle("-fx-text-fill: white;" + "-fx-font-size: 16;");

            this.getChildren().addAll(label, lblAbsence);
        }
        catch (DALException ex)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Could not get present days: " + ex.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    public Student getStudent()
    {
        return student;
    }

    public Long getAbsence()
    {
        return absence;
    }
}
