/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentclient.gui.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author janvanzetten
 */
public class AbsenceWindowController implements Initializable
{
    private final double TRANSITION_TIME = 500;

    //some test value:
    final static String total = "Total";
    final static String sco = "SCO";
    final static String sde = "SDE";
    final static String ito = "ITO";
    final static String dbos = "DBOS";

    @FXML
    private AnchorPane chartPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        XYChart.Series<String, Number> series1 = new XYChart.Series();
        series1.getData().add(new XYChart.Data(total, 25.1));
        series1.getData().add(new XYChart.Data(sco, 20));
        series1.getData().add(new XYChart.Data(sde, 5));
        series1.getData().add(new XYChart.Data(ito, 35.5));
        series1.getData().add(new XYChart.Data(dbos, 10.2));

        List<Timeline> tl = new ArrayList<>();
        double max = 0;
        for (XYChart.Data<String, Number> data : series1.getData())
        {
            double d = data.getYValue().doubleValue();
            max = d > max ? d : max;
            data.setYValue(0);
            Timeline ts = new Timeline();
            ts.getKeyFrames().add(new KeyFrame(Duration.millis(TRANSITION_TIME),
                    new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent actionEvent)
                {
                    data.setYValue(d);
                }
            }));
            tl.add(ts);
        }

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setTickMarkVisible(false);

        NumberAxis yAxis = new NumberAxis(0, max + (10 - max % 10), 10);
        yAxis.setTickLabelFormatter(
                new NumberAxis.DefaultFormatter(yAxis, null, "%")
        );

        BarChart<String, Number> absenceChart = new BarChart<>(xAxis, yAxis);
        absenceChart.setTitle("Absence Chart");
        absenceChart.getXAxis().setLabel("Class");
        absenceChart.getYAxis().setLabel("Absence in percentage");
        absenceChart.getData().add(series1);
        absenceChart.setLegendVisible(false);

        for (Timeline timeline : tl)
        {
            timeline.play();
        }

        chartPane.getChildren().add(absenceChart);
        AnchorPane.setTopAnchor(absenceChart, 5.0);
        AnchorPane.setRightAnchor(absenceChart, 5.0);
        AnchorPane.setBottomAnchor(absenceChart, 5.0);
        AnchorPane.setLeftAnchor(absenceChart, 5.0);
    }
}
