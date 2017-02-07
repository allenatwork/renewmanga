package allen.testmg.utils;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by igorkhomenko on 1/13/15.
 */
public class TimeUtils {
    public final static long ONE_SECOND = 1000;
    public final static long SECONDS = 60;

    public final static long ONE_MINUTE = ONE_SECOND * 60;
    public final static long MINUTES = 60;

    public final static long ONE_HOUR = ONE_MINUTE * 60;
    public final static long HOURS = 24;

    public final static long ONE_DAY = ONE_HOUR * 24;

    private TimeUtils() {
    }

    /**
     * converts time (in milliseconds) to human-readable format
     * "<w> days, <x> hours, <y> minutes and (z) seconds"
     */
    public static String millisToLongDHMS(long duration) {
        if (duration > 0) {
            duration = new Date().getTime() - duration;
        }
        if (duration < 0) {
            duration = 0;
        }

        StringBuffer res = new StringBuffer();
        long temp = 0;
        if (duration >= ONE_SECOND) {
            temp = duration / ONE_DAY;
            if (temp > 0) {
                duration -= temp * ONE_DAY;
                res.append(temp).append(" day").append(temp > 1 ? "s" : "")
                        .append(duration >= ONE_MINUTE ? ", " : "");
            }

            temp = duration / ONE_HOUR;
            if (temp > 0) {
                duration -= temp * ONE_HOUR;
                res.append(temp).append(" hour").append(temp > 1 ? "s" : "")
                        .append(duration >= ONE_MINUTE ? ", " : "");
            }

            temp = duration / ONE_MINUTE;
            if (temp > 0) {
                duration -= temp * ONE_MINUTE;
                res.append(temp).append(" minute").append(temp > 1 ? "s" : "");
            }

            if (!res.toString().equals("") && duration >= ONE_SECOND) {
                res.append(" and ");
            }

            temp = duration / ONE_SECOND;
            if (temp > 0) {
                res.append(temp).append(" second").append(temp > 1 ? "s" : "");
            }
            res.append(" ago");
            return res.toString();
        } else {
            return "0 second ago";
        }
    }

    public static Date convertTimeMillisToDate(long timeMillis) {
        return new Date(timeMillis);
    }

    /**
     * @return number of day count from today. Ex : 1 if DestDate is tomorrow.
     */
    public static int getGapDayFromToday(Date destDate) {

        return getGapDay(Calendar.getInstance().getTime(), destDate);
    }

    /**
     * @return null if sourceDate == null or destDate == null
     * else return the gap day.
     */
    public static Integer getGapDay(Date sourceDate, Date destDate) {
        if (sourceDate == null || destDate == null) return null;

        Date startTimeOfSourceDate = makeStartDate(sourceDate);
        Date startTimeOfDestDate = makeStartDate(destDate);

        long startTimeOfSourceDateMillisecond = startTimeOfSourceDate.getTime();
        long startTimeOfDestDateMillisecond = startTimeOfDestDate.getTime();
        double gapDays = (startTimeOfDestDateMillisecond - startTimeOfSourceDateMillisecond) / 24.0 / 60.0 / 60.0 / 1000.0;

        return roundDouble(gapDays);
    }

    /**
     * Set time as 0:0:0 of this day
     */
    public static Date makeStartDate(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        return calendar.getTime();
    }

    public static int roundDouble(double value) {
        BigDecimal decimal = new BigDecimal(String.valueOf(value));
        decimal = decimal.setScale(0, BigDecimal.ROUND_HALF_EVEN);
        return decimal.intValue();
    }


    public static void plusAmountOfDay(Calendar calendar, int numberOfDay) {
        if (calendar == null) return;

        calendar.add(Calendar.DATE, numberOfDay);
    }


}
