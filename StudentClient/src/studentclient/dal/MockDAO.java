/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentclient.dal;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.XYChart;
import studentclient.be.ClassRoom;
import studentclient.be.Course;
import studentclient.be.ScheduleItem;
import studentclient.be.SchoolClass;
import studentclient.be.Student;
import studentclient.bll.BLLException;
import studentclient.bll.TimeUtils;

/**
 *
 * @author janvanzetten
 */
public class MockDAO
{
    TimeUtils tu;
    Student one = new Student(1, "Jan van Zetten", "janx", "abc");
    Student two = new Student(2, "Alex Tygesen", "applemelon", "casper");
    Student three = new Student(3, "Asbjørn Mansa Jensen", "asbamse", "123");

    public MockDAO()
    {
        tu = new TimeUtils();
    }

    public List<ScheduleItem> getSchedueleItems()
    {
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

        try
        {
            ScheduleItem mon = new ScheduleItem(courses.get(1), teachers.get(1), "", schoolClasses.get(0), classRooms.get(0), tu.dateFromString("2018-02-19 09:00"), tu.dateFromString("2018-02-19 10:45"));
            ScheduleItem mon2 = new ScheduleItem(courses.get(0), teachers.get(0), "Read chapters 1-6", schoolClasses.get(0), classRooms.get(0), tu.dateFromString("2018-02-19 10:45"), tu.dateFromString("2018-02-19 13:30"));
            ScheduleItem tue = new ScheduleItem(courses.get(0), teachers.get(0), "", schoolClasses.get(0), classRooms.get(0), tu.dateFromString("2018-02-20 09:00"), tu.dateFromString("2018-02-20 11:30"));
            ScheduleItem tue2 = new ScheduleItem(courses.get(2), teachers.get(2), "", schoolClasses.get(0), classRooms.get(0), tu.dateFromString("2018-02-20 12:00"), tu.dateFromString("2018-02-20 15:15"));
            ScheduleItem wed = new ScheduleItem(courses.get(3), teachers.get(3), "", schoolClasses.get(0), classRooms.get(0), tu.dateFromString("2018-02-21 09:00"), tu.dateFromString("2018-02-21 12:45"));
            ScheduleItem thu = new ScheduleItem(courses.get(1), teachers.get(1), "", schoolClasses.get(0), classRooms.get(0), tu.dateFromString("2018-02-22 09:00"), tu.dateFromString("2018-02-22 12:45"));
            ScheduleItem fri = new ScheduleItem(courses.get(1), teachers.get(1), null, schoolClasses.get(0), classRooms.get(0), tu.dateFromString("2018-02-23 09:00"), tu.dateFromString("2018-02-23 12:45"));

            mon.addParticipant(one);
            mon2.addParticipant(one);
            tue.addParticipant(one);
            tue2.addParticipant(one);
            wed.addParticipant(one);
            thu.addParticipant(one);

            mon.addParticipant(two);
            mon2.addParticipant(two);
            tue.addParticipant(two);
            wed.addParticipant(two);
            thu.addParticipant(two);
            fri.addParticipant(two);

            mon2.addParticipant(three);
            tue.addParticipant(three);
            tue2.addParticipant(three);
            wed.addParticipant(three);
            fri.addParticipant(three);

            scheduleItems.add(mon);
            scheduleItems.add(mon2);
            scheduleItems.add(tue);
            scheduleItems.add(tue2);
            scheduleItems.add(wed);
            scheduleItems.add(thu);
            scheduleItems.add(fri);
        }
        catch (BLLException ex)
        {
            Logger.getLogger(MockDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return scheduleItems;
    }

    public XYChart.Series<String, Number> getChartSeries()
    {
        // Some test value:
        XYChart.Series<String, Number> series = new XYChart.Series();
        series.getData().add(new XYChart.Data("Total", 25.1));
        series.getData().add(new XYChart.Data("SCO", 5.0));
        series.getData().add(new XYChart.Data("SDE", 46.1));
        series.getData().add(new XYChart.Data("ITO", 35.5));
        series.getData().add(new XYChart.Data("DBOS", 0.2));
        return series;
    }

    public List<Student> getStudents()
    {
        List<Student> students = new ArrayList<>();

        students.add(one);
        students.add(two);
        students.add(three);

        return students;
    }
}
