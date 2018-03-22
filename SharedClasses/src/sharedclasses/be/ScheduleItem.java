/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sharedclasses.be;

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

    /**
     * Schedule Item countains all the information for one schedule.
     * @param course Course for the schedule.
     * @param teacher Teacher who will be educating for the schedule.
     * @param note Note with for example homework before class.
     * @param schoolClass Which school class the schedule is for.
     * @param classRoom Which class room used.
     * @param startTime Start of schedule.
     * @param endTime End of schedule.
     */
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

    /**
     * Get Course.
     * @return Course.
     */
    public Course getCourse()
    {
        return course;
    }

    /**
     * Get Teacher.
     * @return Teacher.
     */
    public String getTeacher()
    {
        return teacher;
    }

    /**
     * Get Note.
     * @return Note.
     */
    public String getNote()
    {
        return note;
    }

    /**
     * Get School Class.
     * @return School Class.
     */
    public SchoolClass getSchoolClass()
    {
        return schoolClass;
    }

    /**
     * Get Class Room.
     * @return Class Room.
     */
    public ClassRoom getClassRoom()
    {
        return classRoom;
    }

    /**
     * Get Start Time.
     * @return Start Time.
     */
    public Date getStartTime()
    {
        return startTime;
    }

    /**
     * Get End Time.
     * @return End Time.
     */
    public Date getEndTime()
    {
        return endTime;
    }

    /**
     * Get Attended.
     * @return list of Student who Attended.
     */
    public List<Student> getAttended()
    {
        return attended;
    }

    /**
     * Add a participant.
     * @param student student who participates.
     */
    public void addParticipant(Student student)
    {
        attended.add(student);
    }
}
