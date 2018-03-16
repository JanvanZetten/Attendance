package sharedclasses.be;

import java.util.Comparator;

/**
 *
 * @author Asbamz
 */
public class CourseComparator implements Comparator<ScheduleItem>
{
    /**
     * Comparator method. Sort ScheduleItem by day and start time.
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(ScheduleItem o1, ScheduleItem o2)
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

        // Compare on start time.
        if (o1.getStartTime() == o2.getStartTime())
        {
            return 0;
        }
        if (o1.getStartTime().before(o2.getStartTime()))
        {
            return -1;
        }
        if (o1.getStartTime().after(o2.getStartTime()))
        {
            return 1;
        }

        // Return as equals in unknown case.
        return 0;
    }
}
