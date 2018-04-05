/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sharedclasses.bll;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javafx.scene.chart.XYChart;
import sharedclasses.be.ScheduleDay;
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

    /**
     * Calculates average absence and creates a XYChart series.
     * @param presence
     * @return
     */
    public XYChart.Series<String, Number> getChartSeriesFromStudentAbsenceInWeekDays(Date startDate, Date endDate, List<Date> presence)
    {
        XYChart.Series<String, Number> series = new XYChart.Series();

        if (startDate != null || endDate != null)
        {
            Calendar sc = Calendar.getInstance();
            Calendar ec = Calendar.getInstance();
            sc.setTime(startDate);
            sc.set(sc.get(Calendar.YEAR), sc.get(Calendar.MONTH), sc.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
            ec.setTime(endDate);
            ec.set(ec.get(Calendar.YEAR), ec.get(Calendar.MONTH), ec.get(Calendar.DAY_OF_MONTH), 23, 59, 59);

            // Make array for schoolDay count. A index for each day.
            List<Double> schoolDays = new ArrayList<>();
            for (int i = 0; i < ScheduleDay.values().length; i++)
            {
                schoolDays.add(0.0);
            }

            // Count schedule days within given dates.
            while (sc.getTime().before(ec.getTime()))
            {
                for (int i = 0; i < ScheduleDay.values().length; i++)
                {
                    if (dayFromDate(sc.getTime()).equalsIgnoreCase(ScheduleDay.values()[i].getDay()))
                    {
                        schoolDays.set(i, schoolDays.get(i) + 1.0);
                    }
                }
                sc.add(Calendar.DATE, 1);
            }

            sc.setTime(startDate);
            sc.set(sc.get(Calendar.YEAR), sc.get(Calendar.MONTH), sc.get(Calendar.DAY_OF_MONTH), 0, 0, 0);

            Calendar pc = Calendar.getInstance();

            // Create array for count of days present.
            List<Double> presentDays = new ArrayList<>();
            for (int i = 0; i < ScheduleDay.values().length; i++)
            {
                presentDays.add(0.0);
            }
            // Add present days.
            for (Date date : distinctDateListByDay(presence))
            {
                pc.setTime(date);
                pc.set(pc.get(Calendar.YEAR), pc.get(Calendar.MONTH), pc.get(Calendar.DAY_OF_MONTH), 12, 0, 0);
                if (pc.after(sc) && pc.before(ec))
                {
                    for (int i = 0; i < ScheduleDay.values().length; i++)
                    {
                        if (dayFromDate(date).equalsIgnoreCase(ScheduleDay.values()[i].getDay()))
                        {
                            presentDays.set(i, presentDays.get(i) + 1.0);
                        }
                    }
                }
            }

            // Create chart series with persentage of absence.
            for (int i = 0; i < ScheduleDay.values().length; i++)
            {
                if (schoolDays.get(i) == 0.0)
                {
                    series.getData().add(new XYChart.Data(ScheduleDay.values()[i].getDay(), 0.0));
                }
                else
                {
                    double result = 100 - ((presentDays.get(i) / schoolDays.get(i)) * 100);
                    result = result < 0.0 ? 0.0 : result;
                    result = result > 100.0 ? 100.0 : result;
                    series.getData().add(new XYChart.Data(ScheduleDay.values()[i].getDay(), result));
                }
            }
        }
        else
        {
            System.out.println("Start date or end date is null!");

            // Create chart series with NaN values.
            for (int i = 0; i < ScheduleDay.values().length; i++)
            {
                series.getData().add(new XYChart.Data(ScheduleDay.values()[i].getDay(), Double.NaN));
            }
        }
        return series;
    }

    private List<Date> distinctDateListByDay(List<Date> dates)
    {
        List<Date> distinctDates = new ArrayList<>();

        Calendar sc = Calendar.getInstance();
        for (Date date : dates)
        {
            sc.setTime(date);
            sc.set(sc.get(Calendar.YEAR), sc.get(Calendar.MONTH), sc.get(Calendar.DAY_OF_MONTH), 12, 0, 0);
            if (!distinctDates.contains(sc.getTime()))
            {
                distinctDates.add(sc.getTime());
            }
        }

        return distinctDates;
    }
}
