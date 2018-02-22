/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentclient.be;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private Date startTime;
    private Date endTime;
    private List<Student> attended;

    public ScheduleItem(Course course, String teacher, String note, SchoolClass schoolClass, ClassRoom classRoom, Date startTime, Date endTime)
    {
        this.course = course;
        this.teacher = teacher;
        this.note = note;
        this.schoolClass = schoolClass;
        this.classRoom = classRoom;
        this.startTime = startTime;
        this.endTime = endTime;
        this.attended = new ArrayList();
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

    public Date getStartTime()
    {
        return startTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }

    public List<Student> getAttended()
    {
        return attended;
    }

    public void addParticipant(Student student)
    {
        attended.add(student);
    }
}
