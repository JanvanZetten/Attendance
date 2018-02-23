/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherclient.be;

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

    /**
     * Constructor of Schedule Item.
     * @param course
     * @param teacher
     * @param note
     * @param schoolClass
     * @param classRoom
     * @param scheduleDay
     * @param startTime
     * @param endTime 
     */
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

    /**
     * Getter of course.
     * @return 
     */
    public Course getCourse()
    {
        return course;
    }

    /**
     * Getter of teacher.
     * @return 
     */
    public String getTeacher()
    {
        return teacher;
    }

    /**
     * Getter of note.
     * @return 
     */
    public String getNote()
    {
        return note;
    }

    /**
     * Getter of class.
     * @return 
     */
    public SchoolClass getSchoolClass()
    {
        return schoolClass;
    }

    /**
     * Getter of class room.
     * @return 
     */
    public ClassRoom getClassRoom()
    {
        return classRoom;
    }

    /**
     * Getter of schedule day.
     * @return 
     */
    public ScheduleDay getScheduleDay()
    {
        return scheduleDay;
    }

    /**
     * Getter of starting time.
     * @return 
     */
    public int getStartTime()
    {
        return startTime;
    }

    /**
     * Getter of ending time.
     * @return 
     */
    public int getEndTime()
    {
        return endTime;
    }

}
