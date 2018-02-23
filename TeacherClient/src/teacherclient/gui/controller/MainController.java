package teacherclient.gui.controller;

import teacherclient.data.HBoxCell;
import teacherclient.gui.model.MainModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author alexl
 */
public class MainController implements Initializable
{

    private MainModel model;
    @FXML
    private ListView<HBoxCell> listviewClasses;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        model = new MainModel();
        model.createMockData();
        model.setClassList(listviewClasses);
    }

    /**
     * Sends the My Schedule button over to the model.
     * @param event 
     */
    @FXML
    private void btnMyScheduele(ActionEvent event)
    {
        model.openSchedule();
    }

}
