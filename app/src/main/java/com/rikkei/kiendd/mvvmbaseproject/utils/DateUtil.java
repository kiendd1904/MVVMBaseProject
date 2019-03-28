package com.rikkei.kiendd.mvvmbaseproject.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;

public class DateUtil {

    public static final String DATE_FORMAT_1 = "yyyy/MM/dd";
    public static final String DATE_FORMAT_2 = "MM/dd";
    public static final String DATE_FORMAT_3 = "HHmmssSSS";

    // return month start at 1
    public static int getCurrentTime(int type) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Japan"));
        switch (type) {
            case Calendar.DAY_OF_MONTH:
                return calendar.get(Calendar.DAY_OF_MONTH);
            case Calendar.MONTH:
                return calendar.get(Calendar.MONTH) + 1;
            case Calendar.YEAR:
                return calendar.get(Calendar.YEAR);
        }
        return 0;
    }

    public static String getCalendarByPattern(Calendar calendar, String pattern) {
        calendar.setTimeZone(TimeZone.getTimeZone("Japan"));
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.JAPANESE);
        dateFormat.setTimeZone(TimeZone.getTimeZone("Japan"));
        String strDate = dateFormat.format(calendar.getTime());
        return strDate;
    }

    public static String getCurrentDayByPattern(String pattern) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Japan"));
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.JAPANESE);
        dateFormat.setTimeZone(TimeZone.getTimeZone("Japan"));
        return dateFormat.format(calendar.getTime());
    }

    public static Calendar getCalendarByDateAndPattern(@NonNull String date, @NonNull String pattern) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.JAPANESE);
            dateFormat.setTimeZone(TimeZone.getTimeZone("Japan"));
            Calendar c = Calendar.getInstance(TimeZone.getTimeZone("Japan"));
            c.setTime(dateFormat.parse(date));
            return c;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Calendar.getInstance();
    }

    // convert time (yyyy/MM/dd) to Milliseconds
    public static long convertTimeToMilliseconds(String birthday) {
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(new SimpleDateFormat(DATE_FORMAT_1, Locale.getDefault()).parse(birthday));
            return c.getTimeInMillis();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long getCurrentTimeMillis() {
        Calendar c = Calendar.getInstance();
        return c.getTimeInMillis();
    }

    //convert time yyyy/MM/dd to M/dd
    public static String convertTimeToDayAndMonth(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_1, Locale.getDefault());
        Date testDate = null;
        try {
            testDate = sdf.parse(time);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("M/d", Locale.getDefault());
        String newFormat = formatter.format(testDate);
        return newFormat;
    }

    //convert time yyyy/MM/dd to yyyy/M/d
    public static String convertTimeNoZero(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_1, Locale.getDefault());
        Date testDate = null;
        try {
            testDate = sdf.parse(time);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/M/d", Locale.getDefault());
        String newFormat = formatter.format(testDate);
        return newFormat;
    }

    public static Date convertStringToDate(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_1, Locale.getDefault());
        Date testDate = null;
        try {
            testDate = sdf.parse(time);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return testDate;
    }

    /**
     * Get a day diff between two dates
     *
     * @param date1 the oldest date
     * @param date2 the newest date
     * @return the diff value, in the provided unit
     */
    public static long getDayDiff(Date date1, Date date2) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1);
        c1.set(Calendar.HOUR_OF_DAY, 0);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(date2);
        c2.set(Calendar.HOUR_OF_DAY, 0);
        c2.set(Calendar.MINUTE, 0);
        c2.set(Calendar.SECOND, 1);

        long diffInMillies = c2.getTime().getTime() - c1.getTime().getTime();
        return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    /**
     * Compare two Calendar by Years, Months and Days without time in day
     *
     * @param c1
     * @param c2
     * @return -1 if c1 before c2, 0 if c1 and c2 are same day, 1 if c1 after c2
     */
    public static int compareTwoCalendarOnlyDay(@NonNull Calendar c1, @NonNull Calendar c2) {
        if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)) {
            return Integer.compare(c1.get(Calendar.DAY_OF_YEAR), c2.get(Calendar.DAY_OF_YEAR));
        } else if (c1.get(Calendar.YEAR) > c2.get(Calendar.YEAR)) {
            return 1;
        } else {
            return -1;
        }
    }

    public static Calendar getJapanCalendar() {
        return Calendar.getInstance(TimeZone.getTimeZone("Japan"));
    }
}
