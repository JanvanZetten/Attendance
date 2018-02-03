package studentclient.gui.model;

import java.util.List;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import studentclient.be.Course;
import studentclient.be.CourseComparator;
import studentclient.be.ScheduleDay;

/**
 *
 * @author Asbamz
 */
public class Schedule extends GridPane
{
    private final int HEADER_HEIGHT = 20;
    private final int MINUTS_IN_AN_HOUR = 60;
    private final int START_TIME;
    private final int END_TIME;
    private final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;
    private final Color DOTTED_BORDER_LINE_COLOR = Color.LIGHTGREY;
    private final Color DEFAULT_COURSE_COLOR = Color.DEEPSKYBLUE;

    /**
     * Creates schedule between given times.
     * @param startTime from 0-24
     * @param endTime from 0-24
     */
    public Schedule(int startTime, int endTime) throws RuntimeException
    {
        // construct GridPane.
        super();

        // Set restraints.
        if (startTime > endTime)
        {
            throw new RuntimeException("Start time cannot be larger than end time!");
        }
        else if (startTime < 0 || startTime > 23 || endTime < 1 || endTime > 24 || startTime == endTime)
        {
            throw new RuntimeException("Start time and end time should be within 0-24 and they cannot be the same!");
        }

        // Set final variables.
        START_TIME = startTime;
        END_TIME = endTime;

        // Run setup.
        setupSchedule();
    }

    /**
     * Setup GridPane and adds times and days.
     */
    private void setupSchedule()
    {
        // Set gap between elements.
        this.setHgap(1.0);
        this.setVgap(1.0);

        // Set column contraint to center content and use percent of width.
        this.getColumnConstraints().clear();
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setHalignment(HPos.CENTER);
        column1.setPercentWidth(10);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setHalignment(HPos.CENTER);
        column2.setPercentWidth(18);
        this.getColumnConstraints().add(column1);
        this.getColumnConstraints().add(column2);
        this.getColumnConstraints().add(column2);
        this.getColumnConstraints().add(column2);
        this.getColumnConstraints().add(column2);
        this.getColumnConstraints().add(column2);

        // Add empty upper left corner.
        this.add(schemeElement(""), 0, 1);

        // Add all days from ScheduleDay Enum.
        int col = 1;
        for (ScheduleDay scheduleDay : ScheduleDay.values())
        {
            this.add(schemeElement(scheduleDay.getDay()), col, 1);
            col++;
        }

        // Add a VBox with all the whole hours.
        VBox time = new VBox();
        for (int i = START_TIME; i < END_TIME; i++)
        {
            StackPane element = schemeElement(i + ":00", MINUTS_IN_AN_HOUR * 1.0);
            element.setAlignment(Pos.TOP_CENTER);
            element.setStyle(element.getStyle() + "; -fx-border-color: " + DOTTED_BORDER_LINE_COLOR.toString().split("x")[1].substring(0, 6) + ";" + "-fx-border-style: dotted hidden hidden hidden;");
            time.getChildren().add(element);
        }
        this.add(time, 0, 2);
    }

    /**
     * Adds courses to schedule.
     * @param courses
     */
    public void setupCourses(List<Course> courses)
    {
        // Sort courses by day and start time.
        courses.sort(new CourseComparator());

        int col = 1;
        // Run through all days.
        for (ScheduleDay day : ScheduleDay.values())
        {
            // Set cursor.
            int cursor = START_TIME * MINUTS_IN_AN_HOUR;
            VBox courseBox = new VBox();
            StackPane element;

            // Check through all courses.
            for (Course course : courses)
            {
                // If the course is on current day.
                if (course.getScheduleDay() == day)
                {
                    // Do nothing in case the course is before given start time.
                    if (START_TIME * MINUTS_IN_AN_HOUR > course.getEndTime())
                    {
                    }
                    // If the start time is before the given start time. Print rest of course.
                    else if (course.getStartTime() < START_TIME * MINUTS_IN_AN_HOUR)
                    {
                        int start = START_TIME * MINUTS_IN_AN_HOUR < course.getStartTime() ? course.getStartTime() : START_TIME * MINUTS_IN_AN_HOUR;
                        int end = END_TIME * MINUTS_IN_AN_HOUR > course.getEndTime() ? course.getEndTime() : END_TIME * MINUTS_IN_AN_HOUR;
                        element = schemeElement(course.getTitle() + "\n" + course.getTeacher(), end - start * 1.0, DEFAULT_COURSE_COLOR);
                        cursor += end - start;
                        courseBox.getChildren().add(element);
                    }
                    // If the course is within given time.
                    else if (course.getEndTime() < END_TIME * MINUTS_IN_AN_HOUR)
                    {
                        // If at beginning of day or before a course.
                        if (cursor == START_TIME * MINUTS_IN_AN_HOUR || cursor < course.getStartTime())
                        {
                            // While distance to next course is larger than distance to whole hour.
                            while (course.getStartTime() - cursor > MINUTS_IN_AN_HOUR - (cursor % MINUTS_IN_AN_HOUR))
                            {
                                // Print empty pane down to next whole hour.
                                int nextPivot = MINUTS_IN_AN_HOUR - (cursor % MINUTS_IN_AN_HOUR);
                                element = schemeElement("", nextPivot * 1.0);
                                // If cursor is at whole hour insert a pane with seperator top.
                                if (cursor % MINUTS_IN_AN_HOUR == 0)
                                {
                                    element.setStyle(element.getStyle() + "; -fx-border-color: " + DOTTED_BORDER_LINE_COLOR.toString().split("x")[1].substring(0, 6) + ";" + "-fx-border-style: dotted hidden hidden hidden;");
                                }
                                courseBox.getChildren().add(element);
                                cursor += nextPivot;
                            }

                            // Insert empty pane down to next course.
                            int nextPivot = course.getStartTime() - cursor;
                            element = schemeElement("", nextPivot * 1.0);
                            if (cursor % MINUTS_IN_AN_HOUR == 0)
                            {
                                element.setStyle(element.getStyle() + "; -fx-border-color: " + DOTTED_BORDER_LINE_COLOR.toString().split("x")[1].substring(0, 6) + ";" + "-fx-border-style: dotted hidden hidden hidden;");
                            }
                            courseBox.getChildren().add(element);
                            cursor += nextPivot;
                        }

                        // Insert course.
                        int start = START_TIME * MINUTS_IN_AN_HOUR < course.getStartTime() ? course.getStartTime() : START_TIME * MINUTS_IN_AN_HOUR;
                        int end = END_TIME * MINUTS_IN_AN_HOUR > course.getEndTime() ? course.getEndTime() : END_TIME * MINUTS_IN_AN_HOUR;
                        element = schemeElement(course.getTitle() + "\n" + course.getTeacher(), end - start * 1.0, DEFAULT_COURSE_COLOR);
                        cursor += end - start;
                        courseBox.getChildren().add(element);
                    }
                }
            }

            // Insert empty panes for the rest of the day.
            while (cursor < END_TIME * MINUTS_IN_AN_HOUR)
            {
                int nextPivot = MINUTS_IN_AN_HOUR - (cursor % MINUTS_IN_AN_HOUR);
                element = schemeElement("", nextPivot * 1.0);
                if (cursor % MINUTS_IN_AN_HOUR == 0)
                {
                    element.setStyle(element.getStyle() + "; -fx-border-color: " + DOTTED_BORDER_LINE_COLOR.toString().split("x")[1].substring(0, 6) + ";" + "-fx-border-style: dotted hidden hidden hidden;");
                }
                courseBox.getChildren().add(element);
                cursor += nextPivot;
            }

            // Add course to GridPane/this.
            this.add(courseBox, col, 2);

            // Addition to column counter.
            col++;
        }
    }

    /**
     * Creates a StackPane with a white background. Adds a label to the
     * StackPane with given text.
     * @param text
     * @return
     */
    private StackPane schemeElement(String text)
    {
        return schemeElement(text, null, DEFAULT_BACKGROUND_COLOR);
    }

    /**
     * Creates a StackPane with given color. Adds a label to the StackPane with
     * given text.
     * @param text
     * @param color
     * @return
     */
    private StackPane schemeElement(String text, Color color)
    {
        return schemeElement(text, null, color);
    }

    /**
     * Creates a StackPane with given height and a white background. Adds a
     * label to the StackPane with given text.
     * @param text
     * @param height
     * @return
     */
    private StackPane schemeElement(String text, Double height)
    {
        return schemeElement(text, height, DEFAULT_BACKGROUND_COLOR);
    }

    /**
     * Creates a StackPane with given height and color. Adds a label to the
     * StackPane with given text.
     * @param text
     * @param height
     * @param color
     * @return
     */
    private StackPane schemeElement(String text, Double height, Color color)
    {
        StackPane sp = new StackPane();
        sp.setStyle("-fx-background-color: #" + color.toString().split("x")[1].substring(0, 6));

        if (height != null)
        {
            sp.setMinHeight(height);
            sp.setPrefHeight(height);
            sp.setMaxHeight(height);
        }

        Label lbl = new Label(text);
        lbl.setTextAlignment(TextAlignment.CENTER);
        sp.getChildren().add(lbl);
        return sp;
    }
}
