package studentclient.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import studentclient.gui.model.AbsenceWindowModel;

/**
 *
 * @author janvanzetten
 */
public class AbsenceWindowController implements Initializable
{
    @FXML
    private AnchorPane chartPane;

    private AbsenceWindowModel awm = new AbsenceWindowModel();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
    }

    public void passData(XYChart.Series<String, Number> series)
    {
        awm.createGraph(chartPane, series);
    }
}
