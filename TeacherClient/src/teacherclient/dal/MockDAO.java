/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherclient.dal;

import sharedclasses.be.Student;
import sharedclasses.be.SchoolClass;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.XYChart;
import sharedclasses.be.ClassRoom;
import sharedclasses.be.Course;
import sharedclasses.be.ScheduleDay;
import sharedclasses.be.ScheduleItem;
import sharedclasses.bll.BLLException;
import sharedclasses.bll.TimeUtils;

/**
 *
 * @author alexl
 */
public class MockDAO
{

    private ArrayList<SchoolClass> listAllClasses;
    private ArrayList<SchoolClass> listOne;
    private ArrayList<SchoolClass> listTwo;
    private ArrayList<SchoolClass> listThree;

    private SchoolClass CS2017A;
    private SchoolClass CS2017B;
    private SchoolClass CS2016A;
    private SchoolClass CS2016B;

    private ArrayList<Student> listAllStudents;

    private Student Alex;
    private Student Asbjørn;
    private Student Jan;

    TimeUtils tu = new TimeUtils();
    Student one = new Student(1, "Jan van Zetten", "janx");
    Student two = new Student(2, "Alex Tygesen", "applemelon");
    Student three = new Student(3, "Asbjørn Mansa Jensen", "asbamse");

    /**
     * Creates mock data for use to test the GUI.
     */
    public MockDAO()
    {
        CS2017A = new SchoolClass(1, "CS2017A");
        CS2017B = new SchoolClass(2, "CS2017B");
        CS2016A = new SchoolClass(3, "CS2016A");
        CS2016B = new SchoolClass(4, "CS2016B");

        listAllClasses = new ArrayList<SchoolClass>();
        listAllClasses.add(CS2017A);
        listAllClasses.add(CS2017B);
        listAllClasses.add(CS2016A);
        listAllClasses.add(CS2016B);

        listOne = new ArrayList<SchoolClass>();
        listOne.add(CS2017A);
        listOne.add(CS2016A);

        listTwo = new ArrayList<SchoolClass>();
        listTwo.add(CS2017A);
        listTwo.add(CS2017B);
        listTwo.add(CS2016A);

        listThree = new ArrayList<SchoolClass>();
        listThree.add(CS2017B);
        listThree.add(CS2016B);

        Alex = new Student(1, "Alex Tygesen", listOne);
        Asbjørn = new Student(2, "Asbjørn Mansa EtEllerAndet", listTwo);
        Jan = new Student(3, "JanvanZetten", listThree);

        listAllStudents = new ArrayList<Student>();
        listAllStudents.add(Alex);
        listAllStudents.add(Asbjørn);
        listAllStudents.add(Jan);
    }

    /**
     * Gets all classes that exist.
     * @return
     */
    public List<SchoolClass> getListAllClasses()
    {
        return listAllClasses;
    }

    /**
     * Gets all students that exist.
     * @return
     */
    public List<Student> getListAllStudents()
    {
        return listAllStudents;
    }

    /**
     * Gets and creates all schedule items a user can see in a schedule for all
     * teachers.
     * @return
     */
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
        schoolClasses.add(new SchoolClass(1, "CS2017A"));

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

    /**
     * Gets and creates all schedule items a user can see in a schedule for a
     * specific teacher.
     * @return
     */
    public List<ScheduleItem> getSchedueleItemsTeacher()
    {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("SDE"));
        courses.add(new Course("SCO"));
        courses.add(new Course("ITO"));
        courses.add(new Course("Teacher meeting"));

        List<String> teachers = new ArrayList<>();
        teachers.add("Mads");

        List<SchoolClass> schoolClasses = new ArrayList<>();
        schoolClasses.add(new SchoolClass(1, "CS2017A"));
        schoolClasses.add(new SchoolClass(2, "CS2017B"));
        schoolClasses.add(new SchoolClass(3, "CS2016A"));
        schoolClasses.add(new SchoolClass(4, ""));

        List<ClassRoom> classRooms = new ArrayList<>();
        classRooms.add(new ClassRoom("C3"));
        classRooms.add(new ClassRoom("Teacher room"));
        classRooms.add(new ClassRoom("C1"));

        List<ScheduleItem> scheduleItems = new ArrayList<>();

        try
        {
            ScheduleItem mon2 = new ScheduleItem(courses.get(0), teachers.get(0), "Read chapters 1-6", schoolClasses.get(0), classRooms.get(0), tu.dateFromString("2018-02-19 10:45"), tu.dateFromString("2018-02-19 13:30"));
            ScheduleItem tue = new ScheduleItem(courses.get(0), teachers.get(0), "", schoolClasses.get(0), classRooms.get(0), tu.dateFromString("2018-02-20 09:00"), tu.dateFromString("2018-02-20 11:30"));

            mon2.addParticipant(one);
            tue.addParticipant(one);

            mon2.addParticipant(two);
            tue.addParticipant(two);

            mon2.addParticipant(three);
            tue.addParticipant(three);

            scheduleItems.add(mon2);
            scheduleItems.add(tue);
        }
        catch (BLLException ex)
        {
            Logger.getLogger(MockDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return scheduleItems;
    }

    /**
     * Gets and creates the absence bar chart items.
     * @return
     */
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
}
