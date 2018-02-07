package studentclient;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import studentclient.be.ClassRoom;
import studentclient.be.Course;
import studentclient.be.ScheduleItem;
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
        courses.add(new Course("SDE"));
        courses.add(new Course("SCO"));
        courses.add(new Course("ITO"));
        courses.add(new Course("DBOS"));

        List<String> teachers = new ArrayList<>();
        teachers.add("Peter");
        teachers.add("Mads");
        teachers.add("Lars");
        teachers.add("Bent");

        List<SchoolClass> schoolClasses = new ArrayList<>();
        schoolClasses.add(new SchoolClass("CS2017A"));

        List<ClassRoom> classRooms = new ArrayList<>();
        classRooms.add(new ClassRoom("C3"));

        List<ScheduleItem> scheduleItems = new ArrayList<>();
        scheduleItems.add(new ScheduleItem(courses.get(0), teachers.get(0), "", schoolClasses.get(0), classRooms.get(0), ScheduleDay.MONDAY, 360, 450));
        scheduleItems.add(new ScheduleItem(courses.get(1), teachers.get(1), "", schoolClasses.get(0), classRooms.get(0), ScheduleDay.MONDAY, 540, 580));
        scheduleItems.add(new ScheduleItem(courses.get(1), teachers.get(1), "", schoolClasses.get(0), classRooms.get(0), ScheduleDay.WEDNESDAY, 600, 780));
        scheduleItems.add(new ScheduleItem(courses.get(1), teachers.get(1), "", schoolClasses.get(0), classRooms.get(0), ScheduleDay.TUESDAY, 540, 710));
        scheduleItems.add(new ScheduleItem(courses.get(0), teachers.get(0), "", schoolClasses.get(0), classRooms.get(0), ScheduleDay.WEDNESDAY, 360, 540));
        scheduleItems.add(new ScheduleItem(courses.get(0), teachers.get(0), "", schoolClasses.get(0), classRooms.get(0), ScheduleDay.MONDAY, 600, 780));
        scheduleItems.add(new ScheduleItem(courses.get(0), teachers.get(0), "", schoolClasses.get(0), classRooms.get(0), ScheduleDay.FRIDAY, 900, 1020));
        schedule.setupCourses(scheduleItems);
        scheduleItems.clear();
        scheduleItems.add(new ScheduleItem(courses.get(1), teachers.get(1), "", schoolClasses.get(0), classRooms.get(0), ScheduleDay.MONDAY, 540, 645));
        scheduleItems.add(new ScheduleItem(courses.get(0), teachers.get(0), "Read chapters 1-6.", schoolClasses.get(0), classRooms.get(0), ScheduleDay.MONDAY, 645, 810));
        scheduleItems.add(new ScheduleItem(courses.get(0), teachers.get(0), "", schoolClasses.get(0), classRooms.get(0), ScheduleDay.TUESDAY, 540, 690));
        scheduleItems.add(new ScheduleItem(courses.get(2), teachers.get(2), "", schoolClasses.get(0), classRooms.get(0), ScheduleDay.TUESDAY, 720, 915));
        scheduleItems.add(new ScheduleItem(courses.get(3), teachers.get(3), "", schoolClasses.get(0), classRooms.get(0), ScheduleDay.WEDNESDAY, 540, 765));
        scheduleItems.add(new ScheduleItem(courses.get(1), teachers.get(1), "", schoolClasses.get(0), classRooms.get(0), ScheduleDay.THURSDAY, 540, 765));
        scheduleItems.add(new ScheduleItem(courses.get(1), teachers.get(1), "", schoolClasses.get(0), classRooms.get(0), ScheduleDay.FRIDAY, 540, 765));
        schedule.setupCourses(scheduleItems);

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
