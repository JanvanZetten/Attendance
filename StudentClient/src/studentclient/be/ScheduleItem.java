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
public class ScheduleItem
{
    private Course course;
    private String teacher;
    private String note;
    private SchoolClass schoolClass;
    private ClassRoom classRoom;
    private ScheduleDay scheduleDay;
    private int startTime;
    private int endTime;

    public ScheduleItem(Course course, String teacher, String note, SchoolClass schoolClass, ClassRoom classRoom, ScheduleDay scheduleDay, int startTime, int endTime)
    {
        this.course = course;
        this.teacher = teacher;
        this.note = note;
        this.schoolClass = schoolClass;
        this.classRoom = classRoom;
        this.scheduleDay = scheduleDay;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Course getCourse()
    {
        return course;
    }

    public String getTeacher()
    {
        return teacher;
    }

    public String getNote()
    {
        return note;
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
