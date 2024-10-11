package space.sotis.starmaa.util;

/**
 * @author x1ngyu
 * @see WeekdayAlgorithm#combineDays(byte...)
 * @see WeekdayAlgorithm#isDayIncluded(byte, byte)
 * @since 2024/10/11
 * <p>
 * 重复日期组合算法，按位表示周一到周日的组合。
 * <p>
 * 用七个位，<strong>从右到左</strong> 表示周一到周日，1表示包含，0表示不包含。<br>
 * 例如：
 * <code>0001010 表示周二和周四</code><br>
 */
public class WeekdayAlgorithm {
    public static final byte MONDAY = 1;
    public static final byte TUESDAY = 1 << 1;
    public static final byte WEDNESDAY = 1 << 2;
    public static final byte THURSDAY = 1 << 3;
    public static final byte FRIDAY = 1 << 4;
    public static final byte SATURDAY = 1 << 5;
    public static final byte SUNDAY = 1 << 6;

    /**
     * 将多个日期组合成一个整数
     *
     * @param days 日期数组，使用时请使用{@link WeekdayAlgorithm#MONDAY}等常量
     * @return <code>byte型</code> 组合的日期
     */
    public static byte combineDays(byte... days) {
        byte result = 0;
        for (byte day : days) {
            result |= day;
        }
        return result;
    }

    /**
     * 判断组合日期是否包含某一天
     *
     * @param combinedDays 组合的日期
     * @param day          要判断的日期
     * @return <code>true</code> 包含，<code>false</code> 不包含
     */
    public static boolean isDayIncluded(byte combinedDays, byte day) {
        return (combinedDays & day) != 0;
    }
}
