/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sharedclasses.gui.model;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.util.Duration;

/**
 *
 * @author Asbamz
 */
public class PopUpBubble
{
    private final double TRANSITION_TIME = 500;
    private final double TIME_SHOWN = 2000;
    private final Color BORDER_COLOR;
    private final Color TEXT_COLOR = Color.web("#FFFFFF");

    private Popup popup = new Popup();
    private VBox vBox = new VBox();
    private Node ownerNode;
    private Bounds boundsInScene;

    private ChangeListener changeWidth;
    private ChangeListener changeHeight;
    private ChangeListener changeX;
    private ChangeListener changeY;
    private ChangeListener changeFocus;

    /**
     * Makes a popup text message above given node.
     * @param ownerNode node to bind popup to.
     * @param text text to write in label.
     * @param background color of background.
     */
    public PopUpBubble(Node ownerNode, String text, Color background)
    {
        BORDER_COLOR = background;
        this.ownerNode = ownerNode;

        vBox.setSpacing(-1.0);
        vBox.setAlignment(Pos.CENTER);

        StackPane sp = new StackPane();
        sp.setStyle("-fx-padding: 5; -fx-background-color: #" + background.toString().split("x")[1].substring(0, 6) + "; -fx-background-radius: 2; -fx-border-color: #" + BORDER_COLOR.toString().split("x")[1].substring(0, 6) + "; -fx-border-radius: 10;");
        Label lbl = new Label(text);
        lbl.setTextFill(TEXT_COLOR);
        lbl.setFont(new Font(18.0));
        sp.getChildren().add(lbl);
        vBox.getChildren().add(sp);

        // Triangle making border
        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(new Double[]
        {
            0.0, 0.0, 12.0, 0.0, 6.0, 6.0
        });
        triangle.setFill(BORDER_COLOR);

        // Trangle merging to background.
        Polygon triangleTop = new Polygon();
        triangleTop.getPoints().addAll(new Double[]
        {
            0.0, 0.0, 8.0, 0.0, 4.0, 4.0
        });
        triangleTop.setFill(background);

        sp = new StackPane();
        sp.getChildren().add(triangle);
        sp.getChildren().add(triangleTop);
        sp.setAlignment(Pos.TOP_CENTER);
        vBox.getChildren().add(sp);

        popup.getContent().add(vBox);
        popup.setAutoHide(false);

        show();
        addListeners();

        // Animations
        FadeTransition ft = new FadeTransition(Duration.millis(TRANSITION_TIME), vBox);
        ft.setFromValue(0.1);
        ft.setToValue(1.0);
        ft.play();

        // Time showing
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(TIME_SHOWN), (event) ->
        {
            remove();
        }));
        timeline.play();
    }

    /**
     * If called, hide popup and removes listeners.
     */
    public void remove()
    {
        // Animations
        FadeTransition ft = new FadeTransition(Duration.millis(TRANSITION_TIME), vBox);
        ft.setFromValue(1.0);
        ft.setToValue(0.1);
        ft.play();
        ft.setOnFinished((event) ->
        {
            // Remove after animation
            popup.hide();
            popup = null;

            removeListeners();
        });
    }

    /**
     * Add listeners to control popup positioning.
     */
    private void addListeners()
    {
        changeWidth = new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue observable, Number oldValue, Number newValue)
            {
                Bounds boundsInChange = ownerNode.localToScreen(ownerNode.getBoundsInLocal());
                popup.setAnchorX(boundsInChange.getMinX() + (ownerNode.getBoundsInParent().getWidth() / 2) - (popup.getWidth() / 2) - (oldValue.doubleValue() - newValue.doubleValue()));
            }
        };

        changeHeight = new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue observable, Number oldValue, Number newValue)
            {
                Bounds boundsInChange = ownerNode.localToScreen(ownerNode.getBoundsInLocal());
                popup.setAnchorY(boundsInChange.getMinY() - popup.getHeight() - (oldValue.doubleValue() - newValue.doubleValue()));
            }
        };

        changeX = new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue observable, Number oldValue, Number newValue)
            {
                Bounds boundsInChange = ownerNode.localToScreen(ownerNode.getBoundsInLocal());
                popup.setAnchorX(boundsInChange.getMinX() + (ownerNode.getBoundsInParent().getWidth() / 2) - (popup.getWidth() / 2));
            }
        };

        changeY = new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue observable, Number oldValue, Number newValue)
            {
                Bounds boundsInChange = ownerNode.localToScreen(ownerNode.getBoundsInLocal());
                popup.setAnchorY(boundsInChange.getMinY() - popup.getHeight());
            }
        };

        changeFocus = new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue observable, Boolean oldValue, Boolean newValue)
            {
                if (newValue)
                {
                    show();
                }
                else
                {
                    popup.hide();
                }
            }
        };

        ownerNode.getScene().getWindow().widthProperty().addListener(changeWidth);
        ownerNode.getScene().getWindow().heightProperty().addListener(changeHeight);

        ownerNode.getScene().getWindow().xProperty().addListener(changeX);
        ownerNode.getScene().getWindow().yProperty().addListener(changeY);

        ownerNode.getScene().getWindow().focusedProperty().addListener(changeFocus);
    }

    /**
     * Removes listeners.
     */
    private void removeListeners()
    {
        ownerNode.getScene().getWindow().widthProperty().removeListener(changeWidth);
        ownerNode.getScene().getWindow().heightProperty().removeListener(changeHeight);

        ownerNode.getScene().getWindow().xProperty().removeListener(changeX);
        ownerNode.getScene().getWindow().yProperty().removeListener(changeY);

        ownerNode.getScene().getWindow().focusedProperty().removeListener(changeFocus);
    }

    /**
     * Shows popup.
     */
    private void show()
    {
        boundsInScene = ownerNode.localToScreen(ownerNode.getBoundsInLocal());
        popup.show(ownerNode, boundsInScene.getMinX() + (ownerNode.getBoundsInParent().getWidth() / 2), boundsInScene.getMinY() - 20);
        popup.setAnchorX(boundsInScene.getMinX() + (ownerNode.getBoundsInParent().getWidth() / 2) - (popup.getWidth() / 2));
        popup.setAnchorY(boundsInScene.getMinY() - popup.getHeight());
    }
}
