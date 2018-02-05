package studentclient;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import studentclient.be.ClassRoom;
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

        List<Course> courses = new ArrayList<>();
        courses.add(new Course("SDE", "Peter", new SchoolClass("CS2017A"), new ClassRoom("C3"), ScheduleDay.MONDAY, 360, 450));
        courses.add(new Course("SCO", "Mads", new SchoolClass("CS2017A"), new ClassRoom("C3"), ScheduleDay.MONDAY, 540, 580));
        courses.add(new Course("SCO", "Mads", new SchoolClass("CS2017B"), new ClassRoom("C3"), ScheduleDay.WEDNESDAY, 600, 780));
        courses.add(new Course("SCO", "Mads", new SchoolClass("CS2017A"), new ClassRoom("C3"), ScheduleDay.TUESDAY, 540, 710));
        courses.add(new Course("SDE", "Peter", new SchoolClass("CS2017A"), new ClassRoom("C3"), ScheduleDay.WEDNESDAY, 360, 540));
        courses.add(new Course("SDE", "Peter", new SchoolClass("CS2017B"), new ClassRoom("C3"), ScheduleDay.MONDAY, 600, 780));
        courses.add(new Course("SDE", "Peter", new SchoolClass("CS2017A"), new ClassRoom("C3"), ScheduleDay.FRIDAY, 900, 1020));
        schedule.setupCourses(courses);
        courses.clear();
        courses.add(new Course("SCO", "Mads", new SchoolClass("CS2017A"), new ClassRoom("C3"), ScheduleDay.MONDAY, 540, 645));
        courses.add(new Course("SDE", "Peter", new SchoolClass("CS2017A"), new ClassRoom("C3"), ScheduleDay.MONDAY, 645, 810));
        courses.add(new Course("SDE", "Peter", new SchoolClass("CS2017A"), new ClassRoom("C3"), ScheduleDay.TUESDAY, 540, 690));
        courses.add(new Course("ITO", "Lars", new SchoolClass("CS2017A"), new ClassRoom("C3"), ScheduleDay.TUESDAY, 720, 915));
        courses.add(new Course("DBOS", "Bent", new SchoolClass("CS2017A"), new ClassRoom("C3"), ScheduleDay.WEDNESDAY, 540, 765));
        courses.add(new Course("SCO", "Mads", new SchoolClass("CS2017A"), new ClassRoom("C3"), ScheduleDay.THURSDAY, 540, 765));
        courses.add(new Course("SCO", "Mads", new SchoolClass("CS2017A"), new ClassRoom("C3"), ScheduleDay.FRIDAY, 540, 765));
        schedule.setupCourses(courses);

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
