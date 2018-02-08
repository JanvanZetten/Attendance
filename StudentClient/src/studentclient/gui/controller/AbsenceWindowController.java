/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentclient.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author janvanzetten
 */
public class AbsenceWindowController implements Initializable {

    //some test value:
    final static String total = "Total";
    final static String sco = "SCO";
    final static String sde= "SDE";
    final static String ito = "ITO";
    final static String dbos = "DBOS";

    @FXML
    private BarChart<String,Number> absenceChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        XYChart.Series series1 = new XYChart.Series();
        series1.getData().add(new XYChart.Data(total, 25.1));
        series1.getData().add(new XYChart.Data(sco, 20));
        series1.getData().add(new XYChart.Data(sde, 5));
        series1.getData().add(new XYChart.Data(ito, 35.5));
        series1.getData().add(new XYChart.Data(dbos, 10.2)); 
        
        absenceChart.getData().add(series1);
        absenceChart.getYAxis().setLabel("Absence in percentage");
    }    
    
}
