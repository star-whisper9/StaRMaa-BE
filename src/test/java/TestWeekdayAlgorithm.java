import space.sotis.starmaa.util.WeekdayAlgorithm;

/**
 * @author x1ngyu
 * @since 2024/10/11
 */

public class TestWeekdayAlgorithm {
    public static void main(String[] args) {
        int days = WeekdayAlgorithm.combineDays(1, 1);
        System.out.println(WeekdayAlgorithm.isDayIncluded(days, WeekdayAlgorithm.MONDAY));
        System.out.println(WeekdayAlgorithm.isDayIncluded(days, WeekdayAlgorithm.TUESDAY));
        System.out.println(WeekdayAlgorithm.isDayIncluded(days, WeekdayAlgorithm.WEDNESDAY));
        System.out.println(WeekdayAlgorithm.isDayIncluded(days, WeekdayAlgorithm.THURSDAY));
        System.out.println(WeekdayAlgorithm.isDayIncluded(days, WeekdayAlgorithm.FRIDAY));
        System.out.println(WeekdayAlgorithm.isDayIncluded(days, WeekdayAlgorithm.SATURDAY));
        System.out.println(WeekdayAlgorithm.isDayIncluded(days, WeekdayAlgorithm.SUNDAY));
    }
}
