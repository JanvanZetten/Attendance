package be;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Alex, Asbjørn & Jan
 */
public class HBoxCell extends HBox {

    private Label label = new Label();
    private Button button1 = new Button();
    private Label filler = new Label();
    private Button button2 = new Button();

    /**
     * Creates HBox from super class. Sets JavaFX Nodes.
     *
     * @param labelText
     * @param buttonText1
     * @param fillerText
     * @param buttonText2
     * @param movie
     * @param mwm
     */
    public HBoxCell(String labelText, String buttonText1, String buttonText2, Class sentClass) {
        super();

        label.setText(labelText);
        label.setMaxWidth(100);
        button1.setMaxWidth(Double.MAX_VALUE);
        button2.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(label, Priority.ALWAYS);
        HBox.setHgrow(button1, Priority.ALWAYS);
        HBox.setHgrow(button2, Priority.ALWAYS);

        //styling
        Background bluebackground = new Background(new BackgroundFill(Paint.valueOf("#46a2f8"), new CornerRadii(5), Insets.EMPTY));
        Background purplebackground = new Background(new BackgroundFill(Paint.valueOf("#0d264d"), CornerRadii.EMPTY, Insets.EMPTY));

        button1.setFont(Font.font("Helvetica Neue"));
        button1.setFont(Font.font(18));
        button1.setTextFill(Paint.valueOf("White"));
        button1.setBackground(bluebackground);

        button2.setFont(Font.font("Helvetica Neue"));
        button2.setFont(Font.font(18));
        button2.setTextFill(Paint.valueOf("White"));
        button2.setBackground(bluebackground);

        label.setFont(Font.font("Helvetica Neue"));
        label.setFont(Font.font(29));
        label.setTextFill(Paint.valueOf("gray"));
        label.setAlignment(Pos.CENTER_LEFT);
        //label.setBackground(purplebackground);

        button1.setText(buttonText1);
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Stage newStage = new Stage();
                    newStage.initModality(Modality.APPLICATION_MODAL);
                    FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/gui/view/AbsenceView.fxml"));
                    Parent root = fxLoader.load();
                    Scene scene = new Scene(root);
                    newStage.setTitle("Absence in " + sentClass.getName());
                    newStage.setScene(scene);
                    newStage.showAndWait();
                } catch (IOException ex) {
                    Logger.getLogger(HBoxCell.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });


        button2.setText(buttonText2);
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Stage newStage = new Stage();
                    newStage.initModality(Modality.APPLICATION_MODAL);
                    FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/gui/view/ScheduleView.fxml"));
                    Parent root = fxLoader.load();
                    Scene scene = new Scene(root);
                    newStage.setTitle("Schedule for " + sentClass.getName());
                    newStage.setScene(scene);
                    newStage.showAndWait();
                } catch (IOException ex) {
                    Logger.getLogger(HBoxCell.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        this.getChildren().addAll(label, button1, button2);
    }

}
