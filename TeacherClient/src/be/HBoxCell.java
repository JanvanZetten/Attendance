package be;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

/**
 * En Gruppe
 * @author Alex, Asbjørn & Jan
 */
public class HBoxCell extends HBox
{
    private Label label = new Label();
    private Button button1 = new Button();
    private Label filler = new Label();
    private Button button2 = new Button();

    /**
     * Creates HBox from super class. Sets JavaFX Nodes.
     * @param labelText
     * @param buttonText1
     * @param fillerText
     * @param buttonText2
     * @param movie
     * @param mwm
     */
    public HBoxCell(String labelText, String buttonText1, String fillerText, String buttonText2)
    {
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
        label.setTextFill(Paint.valueOf("White"));
        label.setAlignment(Pos.CENTER_LEFT);
        label.setBackground(purplebackground);
                
                
        
        
        button1.setText(buttonText1);
        button1.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                

            }
        });

        filler.setText(fillerText);

        button2.setText(buttonText2);
        button2.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                
            }
        });

        this.getChildren().addAll(label, button1, filler, button2);
    }

}
