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
public enum ScheduleDay
{
    MONDAY(1, "Monday"), TUESDAY(2, "Tuesday"), WEDNESDAY(3, "Wednesday"), THURSDAY(4, "Thursday"), FRIDAY(5, "Friday");

    public int value;
    public String day;

    // Enum constructor to contain order and name.
    ScheduleDay(int value, String day)
    {
        this.value = value;
        this.day = day;
    }

    public Integer getValue()
    {
        return this.value;
    }

    public String getDay()
    {
        return this.day;
    }
}
