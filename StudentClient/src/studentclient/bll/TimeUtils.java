/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentclient.bll;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Asbamz
 */
public class TimeUtils
{
    private final int MINUTES_IN_AN_HOUR = 60;
    private final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
    private final String DAY_FORMAT = "EEEE";
    private final String HOUR_MINUTE_FORMAT = "HH:mm";
    private final Calendar cal;

    public TimeUtils()
    {
        cal = Calendar.getInstance(Locale.ENGLISH);
    }

    /**
     * Converts Integer to hour:minutes format (00:00).
     * @param minutes Integer.
     * @return
     */
    public String minuteHourFormatFromMinutes(int minutes)
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

    public String minuteHourFormatFromDate(Date date)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(HOUR_MINUTE_FORMAT, Locale.ENGLISH);
        return simpleDateFormat.format(date);
    }

    public Date dateFromString(String stringDate) throws BLLException
    {
        Date date;
        SimpleDateFormat ft = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);

        try
        {
            date = ft.parse(stringDate);
        }
        catch (ParseException ex)
        {
            throw new BLLException(ex.getMessage(), ex.getCause());
        }

        return date;
    }

    public String dayFromDate(Date date)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DAY_FORMAT, Locale.ENGLISH);
        return simpleDateFormat.format(date);
    }

    public int minutesFromDate(Date date)
    {
        cal.setTime(date);
        int hours = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        return (hours * MINUTES_IN_AN_HOUR) + min;
    }
}
