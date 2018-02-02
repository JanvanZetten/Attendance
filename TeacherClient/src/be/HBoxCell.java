package be;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

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
