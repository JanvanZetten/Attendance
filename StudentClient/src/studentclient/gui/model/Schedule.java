package studentclient.gui.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import studentclient.be.ScheduleItem;
import studentclient.be.CourseComparator;
import studentclient.be.ScheduleDay;
import studentclient.be.Student;
import studentclient.bll.TimeUtils;

/**
 *
 * @author Asbamz
 */
public class Schedule extends GridPane
{
    private final int HEADER_HEIGHT = 20;
    private final int MINUTES_IN_AN_HOUR = 60;
    private final int START_TIME;
    private final int END_TIME;
    private final Color DEFAULT_BACKGROUND_COLOR = Color.web("#FFFFFF");
    private final Color DOTTED_BORDER_LINE_COLOR = Color.web("#D9D9D9");
    private final Color DEFAULT_COURSE_COLOR = Color.web("#3176b5");
    private final Color DEFAULT_COURSE_ABSENCE_COLOR = Color.web("#DB3B26");
    private final Color DEFUALT_COURSE_TEXT_COLOR = Color.web("#FFFFFF");
    private final int[] DEFAULT_DISPLAYED_CONTENT_HIGHTS =
    {
        60, 80, 100, 120
    };
    private final String ID_TIME_COLUMN = "timeCol";
    private final String ID_DAY_ROW = "dayRow";
    private final String ID_COURSE = "course";
    private final String ID_COURSE_FIELD = "courseField";

    private AnchorPane[] apLst;
    private TimeUtils tu;

    /**
     * Creates schedule between given times.
     *
     * @param startTime from 0-24
     * @param endTime from 0-24
     */
    public Schedule(int startTime, int endTime) throws RuntimeException
    {
        // construct GridPane.
        super();

        apLst = new AnchorPane[5];
        for (int i = 0; i < apLst.length; i++)
        {
            apLst[i] = new AnchorPane();
        }
        tu = new TimeUtils();

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
        this.setHgap(0.0);
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
        StackPane sp = schemeElement("");
        sp.setId(ID_DAY_ROW);
        this.add(sp, 0, 1);

        // Add all days from ScheduleDay Enum.
        int col = 1;
        for (ScheduleDay scheduleDay : ScheduleDay.values())
        {
            // Add day to GridPane/this.
            sp = schemeElement(scheduleDay.getDay());
            sp.setId(ID_DAY_ROW);
            this.add(sp, col, 1);

            // Set cursor.
            int cursor = START_TIME * MINUTES_IN_AN_HOUR;
            VBox courseBox = new VBox();
            courseBox.setId(ID_COURSE_FIELD);
            StackPane element;

            // Insert empty panes for the rest of the day.
            while (cursor < END_TIME * MINUTES_IN_AN_HOUR)
            {
                int nextPivot = MINUTES_IN_AN_HOUR - (cursor % MINUTES_IN_AN_HOUR);
                element = schemeElement("", nextPivot * 1.0);
                if (cursor % MINUTES_IN_AN_HOUR == 0)
                {
                    element.setStyle(element.getStyle() + "; -fx-border-color: " + DOTTED_BORDER_LINE_COLOR.toString().split("x")[1].substring(0, 6) + ";" + "-fx-border-style: dotted hidden hidden hidden;");
                }
                courseBox.getChildren().add(element);
                cursor += nextPivot;
            }

            // Add course to GridPane/this.
            sp = new StackPane();
            sp.getChildren().add(courseBox);
            apLst[col - 1].getChildren().add(sp);
            apLst[col - 1].setTopAnchor(sp, 0.0);
            apLst[col - 1].setLeftAnchor(sp, 0.0);
            apLst[col - 1].setRightAnchor(sp, 0.0);

            this.add(apLst[col - 1], col, 2);

            // Addition to column counter.
            col++;
        }

        // Add a VBox with all the whole hours.
        VBox time = new VBox();
        time.setId(ID_TIME_COLUMN);
        for (int i = START_TIME; i < END_TIME; i++)
        {
            StackPane element = schemeElement(i + ":00", MINUTES_IN_AN_HOUR * 1.0);
            element.setAlignment(Pos.TOP_CENTER);
            element.setStyle(element.getStyle() + "; -fx-border-color: " + DOTTED_BORDER_LINE_COLOR.toString().split("x")[1].substring(0, 6) + ";" + "-fx-border-style: dotted hidden hidden hidden;");
            time.getChildren().add(element);
        }
        this.add(time, 0, 2);
    }

    /**
     * Adds courses to schedule.
     *
     * @param courses
     */
    public void setupCourses(List<ScheduleItem> courses, Student student)
    {
        List<Node> toRemove = new ArrayList<>();
        for (int i = 0; i < apLst.length; i++)
        {
            toRemove.clear();
            for (int j = 0; j < apLst[i].getChildren().size(); j++)
            {
                if (apLst[i].getChildren().get(j).getId() == null)
                {
                    continue;
                }
                if (apLst[i].getChildren().get(j).getId().equalsIgnoreCase(ID_COURSE))
                {
                    toRemove.add(apLst[i].getChildren().get(j));
                }
            }
            apLst[i].getChildren().removeAll(toRemove);
        }

        // Sort courses by day and start time.
        courses.sort(new CourseComparator());

        int col = 1;
        // Run through all days.
        for (ScheduleDay day : ScheduleDay.values())
        {
            // Set cursor.
            StackPane element;

            // Check through all courses.
            for (ScheduleItem course : courses)
            {
                int startTimeInMin = tu.minutesFromDate(course.getStartTime());
                int endTimeInMin = tu.minutesFromDate(course.getEndTime());
                // If the course is on current day.
                if (tu.dayFromDate(course.getStartTime()).equalsIgnoreCase(day.getDay()))
                {
                    // Do nothing in case the course is before given start time.
                    if (START_TIME * MINUTES_IN_AN_HOUR > endTimeInMin)
                    {
                        // DO-NOTHING.
                    }
                    else
                    {
                        int start;
                        int end;
                        // If the start time is before the given start time. Print rest of course.
                        if (tu.minutesFromDate(course.getStartTime()) < START_TIME * MINUTES_IN_AN_HOUR)
                        {
                            start = START_TIME * MINUTES_IN_AN_HOUR < startTimeInMin ? startTimeInMin : START_TIME * MINUTES_IN_AN_HOUR;
                            end = END_TIME * MINUTES_IN_AN_HOUR > endTimeInMin ? endTimeInMin : END_TIME * MINUTES_IN_AN_HOUR;
                        } // If the course is within given time.
                        else if (endTimeInMin < END_TIME * MINUTES_IN_AN_HOUR)
                        {
                            start = START_TIME * MINUTES_IN_AN_HOUR < startTimeInMin ? startTimeInMin : START_TIME * MINUTES_IN_AN_HOUR;
                            end = END_TIME * MINUTES_IN_AN_HOUR > endTimeInMin ? endTimeInMin : END_TIME * MINUTES_IN_AN_HOUR;
                        }
                        else
                        {
                            continue;
                        }

                        // Insert course.
                        String courseTxt = course.getCourse().getName();
                        int height = end - start;
                        if (height >= DEFAULT_DISPLAYED_CONTENT_HIGHTS[0])
                        {
                            courseTxt += "\n" + course.getClassRoom().getName();
                        }
                        if (height >= DEFAULT_DISPLAYED_CONTENT_HIGHTS[1])
                        {
                            courseTxt += "\n" + course.getTeacher();
                        }
                        if (height >= DEFAULT_DISPLAYED_CONTENT_HIGHTS[2])
                        {
                            courseTxt = tu.minuteHourFormatFromDate(course.getStartTime()) + " - " + tu.minuteHourFormatFromDate(course.getEndTime()) + "\n" + courseTxt;
                        }
                        if (height >= DEFAULT_DISPLAYED_CONTENT_HIGHTS[3])
                        {
                            courseTxt += "\n" + (course.getNote() != null ? (course.getNote().length() > 0 ? course.getNote() : "") : "");
                        }

                        if (course.getAttended().contains(student) || course.getStartTime().after(Calendar.getInstance(Locale.ENGLISH).getTime()))
                        {
                            element = schemeElement(courseTxt, height * 1.0, DEFAULT_COURSE_COLOR, true);
                        }
                        else
                        {
                            element = schemeElement(courseTxt, height * 1.0, DEFAULT_COURSE_ABSENCE_COLOR, true);
                        }
                        element.setId(ID_COURSE);

                        if ((start - (START_TIME * MINUTES_IN_AN_HOUR)) % MINUTES_IN_AN_HOUR == 0)
                        {
                            element.setStyle(element.getStyle()
                                    + "; -fx-border-color: "
                                    + DEFAULT_BACKGROUND_COLOR.toString().split("x")[1].substring(0, 6)
                                    + ", "
                                    + DOTTED_BORDER_LINE_COLOR.toString().split("x")[1].substring(0, 6)
                                    + ";"
                                    + "-fx-border-style: solid, dotted hidden hidden hidden;");
                        }
                        else
                        {
                            element.setStyle(element.getStyle()
                                    + "; -fx-border-color: "
                                    + DEFAULT_BACKGROUND_COLOR.toString().split("x")[1].substring(0, 6)
                                    + ";");
                        }

                        apLst[day.value - 1].getChildren().add(element);
                        apLst[day.value - 1].setTopAnchor(element, start - (START_TIME * MINUTES_IN_AN_HOUR) * 1.0);
                        apLst[day.value - 1].setLeftAnchor(element, 4.0);
                        apLst[day.value - 1].setRightAnchor(element, 0.0);
                    }
                }
            }

            // Addition to column counter.
            col++;
        }
    }

    /**
     * Creates a StackPane with a white background. Adds a label to the
     * StackPane with given text.
     *
     * @param text
     * @return
     */
    private StackPane schemeElement(String text)
    {
        return schemeElement(text, null, DEFAULT_BACKGROUND_COLOR, false);
    }

    /**
     * Creates a StackPane with given color. Adds a label to the StackPane with
     * given text.
     *
     * @param text
     * @param color
     * @return
     */
    private StackPane schemeElement(String text, Color color)
    {
        return schemeElement(text, null, color, false);
    }

    /**
     * Creates a StackPane with given height and a white background. Adds a
     * label to the StackPane with given text.
     *
     * @param text
     * @param height
     * @return
     */
    private StackPane schemeElement(String text, Double height)
    {
        return schemeElement(text, height, DEFAULT_BACKGROUND_COLOR, false);
    }

    /**
     * Creates a StackPane with given height and color. Adds a label to the
     * StackPane with given text.
     *
     * @param text
     * @param height
     * @param color
     * @return
     */
    private StackPane schemeElement(String text, Double height, Color color, boolean WhiteText)
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
        lbl.setFont(new Font("Helvatica Neue", 14));
        lbl.setWrapText(true);
        if (WhiteText)
        {
            lbl.setTextFill(DEFUALT_COURSE_TEXT_COLOR);
        }
        lbl.setTextAlignment(TextAlignment.CENTER);
        sp.getChildren().add(lbl);
        return sp;
    }
}
