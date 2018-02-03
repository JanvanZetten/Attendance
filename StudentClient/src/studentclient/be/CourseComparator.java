package studentclient.be;

import java.util.Comparator;

/**
 *
 * @author Asbamz
 */
public class CourseComparator implements Comparator<Course>
{
    /**
     * Comparator method. Sort Course by day and start time.
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(Course o1, Course o2)
    {
        // Check for null values.
        if (o1 == null && o2 == null)
        {
            return 0;
        }
        if (o1 == null)
        {
            return -1;
        }
        if (o2 == null)
        {
            return 1;
        }

        // Compare on day.
        if (o1.getScheduleDay().getValue() == o2.getScheduleDay().getValue())
        {
            // Compare on start time.
            if (o1.getStartTime() == o2.getStartTime())
            {
                return 0;
            }
            if (o1.getStartTime() < o2.getStartTime())
            {
                return -1;
            }
            if (o1.getStartTime() > o2.getStartTime())
            {
                return 1;
            }
        }
        if (o1.getScheduleDay().getValue() < o2.getScheduleDay().getValue())
        {
            return -1;
        }
        if (o1.getScheduleDay().getValue() > o2.getScheduleDay().getValue())
        {
            return 1;
        }

        // Return as equals in unknown case.
        return 0;
    }
}
