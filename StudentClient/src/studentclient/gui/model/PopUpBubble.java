/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentclient.gui.model;

import javafx.geometry.Bounds;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Popup;

/**
 *
 * @author Asbamz
 */
public class PopUpBubble
{
    public PopUpBubble(Node ownerNode, String text, Color background)
    {
        Popup popup = new Popup();

        VBox vBox = new VBox();
        vBox.setSpacing(-1.0);
        vBox.setAlignment(Pos.CENTER);

        StackPane sp = new StackPane();
        sp.setStyle("-fx-padding: 5; -fx-background-color: #" + background.toString().split("x")[1].substring(0, 6) + "; -fx-background-radius: 10; -fx-border-color: #000000; -fx-border-radius: 10;");
        Label lbl = new Label(text);
        sp.getChildren().add(lbl);
        vBox.getChildren().add(sp);

        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(new Double[]
        {
            0.0, 0.0, 12.0, 0.0, 6.0, 6.0
        });
        triangle.setFill(new Color(0.0 / 255.0, 0.0 / 255.0, 0.0 / 255.0, 255.0 / 255.0));

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
        popup.setAutoHide(true);

        Bounds boundsInScene = ownerNode.localToScreen(ownerNode.getBoundsInLocal());
        popup.show(ownerNode, boundsInScene.getMinX() + (ownerNode.getBoundsInParent().getWidth() / 2), boundsInScene.getMinY() - 20);
        popup.setAnchorX(boundsInScene.getMinX() + (ownerNode.getBoundsInParent().getWidth() / 2) - (popup.getWidth() / 2));
        popup.setAnchorY(boundsInScene.getMinY() - popup.getHeight());
    }
}
