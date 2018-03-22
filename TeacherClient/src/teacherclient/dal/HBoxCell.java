package teacherclient.dal;

import sharedclasses.be.SchoolClass;
import teacherclient.gui.controller.AbsenceController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import teacherclient.bll.BllManager;
import teacherclient.gui.controller.ScheduleViewController;

/**
 * @author Alex, Asbjørn & Jan
 */
public class HBoxCell extends HBox
{

    private Label label = new Label();
    private Button button1 = new Button();
    private Label filler = new Label();
    private Button button2 = new Button();
    private Label middleString = new Label();

    /**
     * Creates HBox from super class. Sets JavaFX Nodes.
     *
     * @param labelText
     */
    public HBoxCell(String labelText, SchoolClass schoolClass, BllManager bll)
    {
        super();

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

        button2.setText("Schedule");
        button2.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                try
                {
                    Stage newStage = new Stage();
                    newStage.initModality(Modality.APPLICATION_MODAL);
                    FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/teacherclient/gui/view/ScheduleView.fxml"));
                    Parent root = fxLoader.load();

                    ScheduleViewController cont = fxLoader.getController();

                    BllManager bll = new BllManager();
                    cont.updateSchedule(bll.getScheduleItems());

                    Scene scene = new Scene(root);
                    newStage.setTitle("Schedule for " + schoolClass.getName());
                    newStage.setScene(scene);
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

}
