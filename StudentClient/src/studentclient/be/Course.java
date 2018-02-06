/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentclient.be;

/**
 *
 * @author Asbamz
 */
public class Course
{
    private String title;
    private String teacher;
    private SchoolClass schoolClass;
    private ClassRoom classRoom;
    private ScheduleDay scheduleDay;
    private int startTime;
    private int endTime;

    public Course(String title, String teacher, SchoolClass schoolClass, ClassRoom classRoom, ScheduleDay scheduleDay, int startTime, int endTime)
    {
        this.title = title;
        this.teacher = teacher;
        this.schoolClass = schoolClass;
        this.classRoom = classRoom;
        this.scheduleDay = scheduleDay;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getTitle()
    {
        return title;
    }

    public String getTeacher()
    {
        return teacher;
    }

    public SchoolClass getSchoolClass()
    {
        return schoolClass;
    }

    public ClassRoom getClassRoom()
    {
        return classRoom;
    }

    public ScheduleDay getScheduleDay()
    {
        return scheduleDay;
    }

    public int getStartTime()
    {
        return startTime;
    }

    public int getEndTime()
    {
        return endTime;
    }

}
