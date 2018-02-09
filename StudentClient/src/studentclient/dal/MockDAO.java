/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentclient.dal;

import java.util.ArrayList;
import java.util.List;
import studentclient.be.ClassRoom;
import studentclient.be.Course;
import studentclient.be.ScheduleDay;
import studentclient.be.ScheduleItem;
import studentclient.be.SchoolClass;

/**
 *
 * @author janvanzetten
 */
public class MockDAO {
    
    

    public List<ScheduleItem> getSchedueleItems() {
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

        scheduleItems.add(new ScheduleItem(courses.get(1), teachers.get(1), "", schoolClasses.get(0), classRooms.get(0), ScheduleDay.MONDAY, 540, 645));
        scheduleItems.add(new ScheduleItem(courses.get(0), teachers.get(0), "Read chapters 1-6", schoolClasses.get(0), classRooms.get(0), ScheduleDay.MONDAY, 645, 810));
        scheduleItems.add(new ScheduleItem(courses.get(0), teachers.get(0), "", schoolClasses.get(0), classRooms.get(0), ScheduleDay.TUESDAY, 540, 690));
        scheduleItems.add(new ScheduleItem(courses.get(2), teachers.get(2), "", schoolClasses.get(0), classRooms.get(0), ScheduleDay.TUESDAY, 720, 915));
        scheduleItems.add(new ScheduleItem(courses.get(3), teachers.get(3), "", schoolClasses.get(0), classRooms.get(0), ScheduleDay.WEDNESDAY, 540, 765));
        scheduleItems.add(new ScheduleItem(courses.get(1), teachers.get(1), "", schoolClasses.get(0), classRooms.get(0), ScheduleDay.THURSDAY, 540, 765));
        scheduleItems.add(new ScheduleItem(courses.get(1), teachers.get(1), null, schoolClasses.get(0), classRooms.get(0), ScheduleDay.FRIDAY, 540, 765));
        
        return scheduleItems;
    }
}
