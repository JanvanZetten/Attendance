/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherclient.bll;

/**
 *
 * @author Asbamz
 */
public class TimeUtils
{
    private final int MINUTES_IN_AN_HOUR = 60;

    /**
     * Converts Integer to hour:minutes format (00:00).
     * @param minutes Integer.
     * @return
     */
    public String min2MinHourFormat(int minutes)
    {
        int min;
        int hour;
        if (minutes < MINUTES_IN_AN_HOUR)
        {
            min = minutes;
            hour = 0;
        }
        else
        {
            min = minutes % MINUTES_IN_AN_HOUR;
            hour = (minutes - min) / MINUTES_IN_AN_HOUR;
        }

        return (hour < 10 ? "0" + hour : hour) + ":" + (min < 10 ? "0" + min : min);
    }
}
