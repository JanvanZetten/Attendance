/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentclient.gui.model;

import sharedclasses.gui.model.AbsenceGraph;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Asbamz
 */
public class AbsenceWindowModel
{
    private AbsenceGraph ag;

    /**
     * Creates graph with given values and adds to pane.
     * @param chartPane
     * @param series
     */
    public void createGraph(AnchorPane chartPane, XYChart.Series<String, Number> series)
    {
        ag = new AbsenceGraph(chartPane, series);
    }
}
