/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentclient.gui.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *
 * @author Asbamz
 */
public class AbsenceWindowModel
{
    private final double ANIMATION_TIME = 1500;
    private final double ANIMATION_PARTING = 100;
    private final double ANIMATION_PAUSE = ANIMATION_TIME / ANIMATION_PARTING;

    /**
     * Creates graph with given values and adds to pane.
     * @param chartPane
     * @param series
     */
    public void createGraph(AnchorPane chartPane, XYChart.Series<String, Number> series)
    {
        double max = 0;
        double[] values = new double[series.getData().size()];
        double[] valueIncrs = new double[series.getData().size()];

        // Go through all given data.
        for (int i = 0; i < series.getData().size(); i++)
        {
            // Add original Y value.
            values[i] = series.getData().get(i).getYValue().doubleValue();
            // Add value increament after parting.
            valueIncrs[i] = values[i] / ANIMATION_PARTING;
            // Set max value.
            max = values[i] > max ? values[i] : max;

            int j = i;
            // Add listener to node.
            series.getData().get(i).nodeProperty().addListener(new ChangeListener<Node>()
            {
                @Override
                public void changed(ObservableValue<? extends Node> ov, Node oldNode, final Node node)
                {
                    // On node change run displayLabelForData.
                    if (node != null)
                    {
                        displayLabelForData(series.getData().get(j));
                    }
                }
            });

            // Set Y to 0 for animation start.
            series.getData().get(i).setYValue(0);
        }

        // XAxis styling.
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setTickMarkVisible(false);

        // YAxis styling.
        double maxPos = max + (10 - max % 10);
        NumberAxis yAxis = new NumberAxis(0, max % 10 > 6 ? maxPos + 10 : maxPos, 10);
        yAxis.setTickLabelFormatter(
                new NumberAxis.DefaultFormatter(yAxis, null, "%")
        );

        // Creating BarChart.
        BarChart<String, Number> absenceChart = new BarChart<>(xAxis, yAxis);
        absenceChart.setTitle("Absence Chart");
        absenceChart.getXAxis().setLabel("Class");
        absenceChart.getYAxis().setLabel("Absence in percentage");
        absenceChart.getData().add(series);
        absenceChart.setLegendVisible(false);
        absenceChart.setAnimated(false);

        // Add to AnchorPane.
        chartPane.getChildren().add(absenceChart);
        AnchorPane.setTopAnchor(absenceChart, 5.0);
        AnchorPane.setRightAnchor(absenceChart, 5.0);
        AnchorPane.setBottomAnchor(absenceChart, 5.0);
        AnchorPane.setLeftAnchor(absenceChart, 5.0);

        // Animation Timeline.
        Timeline timeline = new Timeline();
        // Add keyframe with a duration of the ANIMATION_PAUSE.
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(ANIMATION_PAUSE), new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                // For all series in chart.
                for (XYChart.Series<String, Number> forSeries : absenceChart.getData())
                {
                    int i = 0;
                    // For all data in series.
                    for (XYChart.Data<String, Number> data : forSeries.getData())
                    {
                        // Add the increament value.
                        Number yValue = data.getYValue();
                        Number randomValue = yValue.doubleValue() + valueIncrs[i];
                        data.setYValue(randomValue);
                        i++;
                    }
                }
            }
        }));
        // Run ANIMATION_PARTING times and play.
        timeline.setCycleCount((int) ANIMATION_PARTING);
        timeline.play();
        // Once the animation is finished.
        timeline.setOnFinished((event) ->
        {
            for (XYChart.Series<String, Number> forSeries : absenceChart.getData())
            {
                int i = 0;
                for (XYChart.Data<String, Number> data : forSeries.getData())
                {
                    // Set value to original value.
                    data.setYValue(values[i]);
                    i++;
                }
            }
        });
    }

    /**
     * Creates text in bars.
     * @param data
     */
    private void displayLabelForData(XYChart.Data<String, Number> data)
    {
        final Node node = data.getNode();
        final Text dataText = new Text(data.getYValue() + "");
        // When parent is set the dataText is set to parent.
        node.parentProperty().addListener(new ChangeListener<Parent>()
        {
            @Override
            public void changed(ObservableValue<? extends Parent> ov, Parent oldParent, Parent parent)
            {
                Group parentGroup = (Group) parent;
                parentGroup.getChildren().add(dataText);
            }
        });

        // When bounds is changed in parent, change position.
        node.boundsInParentProperty().addListener(new ChangeListener<Bounds>()
        {
            @Override
            public void changed(ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds bounds)
            {
                dataText.setLayoutX(
                        Math.round(
                                bounds.getMinX() + bounds.getWidth() / 2 - dataText.prefWidth(-1) / 2
                        )
                );

                if (node.getBoundsInLocal().getHeight() < dataText.getBoundsInLocal().getHeight() * 1.5)
                {
                    dataText.setFill(Color.BLACK);
                    dataText.setLayoutY(
                            Math.round(
                                    bounds.getMinY() - dataText.prefHeight(-1) * 0.5
                            )
                    );
                }
                else
                {
                    dataText.setFill(Color.WHITE);
                    dataText.setLayoutY(
                            Math.round(
                                    bounds.getMinY() - dataText.prefHeight(-1) * -1.0
                            )
                    );
                }
            }
        });

        // On change of Y value. Change text.
        data.YValueProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number oldNumber, Number Number)
            {
                NumberFormat formatter = new DecimalFormat("#0.0");
                dataText.setText(formatter.format(Number.doubleValue()) + "%");
            }
        });
    }
}
