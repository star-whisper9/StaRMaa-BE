package space.sotis.starmaa.util;

/**
 * @author x1ngyu
 * @since 2024/10/11
 */

public class WeekdayAlgorithm {
    public static final int MONDAY = 1;
    public static final int TUESDAY = 1 << 1;
    public static final int WEDNESDAY = 1 << 2;
    public static final int THURSDAY = 1 << 3;
    public static final int FRIDAY = 1 << 4;
    public static final int SATURDAY = 1 << 5;
    public static final int SUNDAY = 1 << 6;

    public static int combineDays(int... days) {
        int result = 0;
        for (int day : days) {
            result |= day;
        }
        return result;
    }

    public static boolean isDayIncluded(int combinedDays, int day) {
        return (combinedDays & day) != 0;
    }
}
