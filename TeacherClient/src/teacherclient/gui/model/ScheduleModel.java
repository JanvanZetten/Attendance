/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherclient.gui.model;

import java.util.List;
import teacherclient.be.ScheduleItem;

/**
 *
 * @author Asbamz
 */
public class ScheduleModel
{
    private Schedule schedule;

    public ScheduleModel()
    {
        schedule = new Schedule(8, 16);
    }

    public Schedule getSchedule()
    {
        return schedule;
    }

    /**
     * Update courses in schedule.
     * @param courses in schedule.
     */
    public void updateSchedule(List<ScheduleItem> courses)
    {
        schedule.setupCourses(courses);
    }
}