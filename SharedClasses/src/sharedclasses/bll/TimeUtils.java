/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sharedclasses.bll;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import sharedclasses.bll.BLLException;

/**
 * To convert time.
 * @author Asbamz
 */
public class TimeUtils
{
    private final int MINUTES_IN_AN_HOUR = 60;
    private final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
    private final String DAY_FORMAT = "EEEE";
    private final String HOUR_MINUTE_FORMAT = "HH:mm";
    private final Calendar cal;

    /**
     * Creates calendar on construction.
     */
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

    /**
     * Converts Date to hour:minutes format (00:00).
     * @param date
     * @return
     */
    public String minuteHourFormatFromDate(Date date)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(HOUR_MINUTE_FORMAT, Locale.ENGLISH);
        return simpleDateFormat.format(date);
    }

    /**
     * Converts string to date. Given the String is formated as DATE_FORMAT.
     * @param stringDate
     * @return
     * @throws BLLException
     */
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

    /**
     * Converts Date to the day as String.
     * @param date
     * @return
     */
    public String dayFromDate(Date date)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DAY_FORMAT, Locale.ENGLISH);
        return simpleDateFormat.format(date);
    }

    /**
     * Converts Date to minutes from 00:00 of that day.
     * @param date
     * @return
     */
    public int minutesFromDate(Date date)
    {
        cal.setTime(date);
        int hours = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        return (hours * MINUTES_IN_AN_HOUR) + min;
    }
}
