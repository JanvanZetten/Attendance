package studentclient;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import studentclient.be.Course;
import studentclient.be.ScheduleDay;
import studentclient.be.SchoolClass;
import studentclient.gui.model.Schedule;

/**
 *
 * @author Asbamz
 */
public class ScheduleTest extends Application
{
    private Schedule schedule;
    private StackPane root;

    @Override
    public void start(Stage stage) throws Exception
    {
        stage.setTitle("Test Schedule");

        schedule = new Schedule(8, 16);

        List<Course> test = new ArrayList<>();
        test.add(new Course("SDE", "Peter", new SchoolClass("CS2017A"), ScheduleDay.MONDAY, 360, 450));
        test.add(new Course("SCO", "Mads", new SchoolClass("CS2017A"), ScheduleDay.MONDAY, 540, 580));
        test.add(new Course("SCO", "Mads", new SchoolClass("CS2017B"), ScheduleDay.WEDNESDAY, 600, 780));
        test.add(new Course("SCO", "Mads", new SchoolClass("CS2017A"), ScheduleDay.TUESDAY, 540, 710));
        test.add(new Course("SDE", "Peter", new SchoolClass("CS2017A"), ScheduleDay.WEDNESDAY, 360, 540));
        test.add(new Course("SDE", "Peter", new SchoolClass("CS2017B"), ScheduleDay.MONDAY, 600, 780));
        test.add(new Course("SDE", "Peter", new SchoolClass("CS2017A"), ScheduleDay.FRIDAY, 900, 1020));
        schedule.setupCourses(test);

        root = new StackPane();
        schedule.prefWidthProperty().bind(root.widthProperty());
        schedule.prefHeightProperty().bind(root.heightProperty());
        root.getChildren().add(schedule);
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

}
