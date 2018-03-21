package teacherclient.gui.controller;

import teacherclient.dal.HBoxCell;
import teacherclient.gui.model.MainModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import sharedclasses.be.Teacher;

/**
 * FXML Controller class
 *
 * @author alexl
 */
public class MainController implements Initializable
{
    private final String LOGIN_PRETEXT = "Logged in as ";

    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu loginLbl;
    @FXML
    private ListView<HBoxCell> listviewClasses;

    private MainModel model;

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

    public void setUser(Teacher teacher)
    {
        model.setActiveUser(teacher);
        loginLbl.setText(LOGIN_PRETEXT + teacher.getName());
        model.changeMenubarForMac(menuBar, mainAnchorPane);
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

    @FXML
    private void handleLogOut(ActionEvent event)
    {
        model.logOut(mainAnchorPane);
    }

}
