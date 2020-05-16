package com.coderusk.speedcode.app;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Caldy {
    ///////////////////////////////////////
    String input = "";
    Calendar inputCalendar = null;
    String reference = "";
    Calendar referenceCalendar = null;
    String inputFormat = "";
    String output_format = "";
    String reference_format = "";
    boolean readable = false;
    String left = "";
    String right = "";
    String leftFormat = "";
    String rightFormat = "";
    Calendar left_calendar, right_calendar;
    Calendar result = null;
    boolean csv = false;
    private long resultLong = 0L;

    private void reset()
    {
        input = "";
        inputCalendar = null;
        reference = "";
        referenceCalendar = null;
        inputFormat = "";
        output_format = "";
        reference_format = "";
        readable = false;
        left = "";
        right = "";
        leftFormat = "";
        rightFormat = "";
        left_calendar = null;
        right_calendar = null;
        result = null;
        csv = false;
        resultLong = 0L;
    }

    public static String dateTime(String format) {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat(format);
        String val = dateFormat.format(date);
        return val;
    }

    public static String convert(String input_format, String input, String output_format) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(input_format, Locale.ENGLISH);
        try {
            cal.setTime(sdf.parse(input));
            Date date = cal.getTime();
            DateFormat dateFormat = new SimpleDateFormat(output_format);
            String val = dateFormat.format(date);
            return val;
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getTimeStamp() {
        String format = "yyyy_MM_dd_HH_mm_ss_SSS";
        return dateTime(format);
    }

    public static String getDateTime() {
        String format = "yyyy-MM-dd HH:mm:ss";
        return dateTime(format);
    }

    public static String nextDate(String input_format, String input, String output_format) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(input_format, Locale.ENGLISH);
        try {
            cal.setTime(sdf.parse(input));
            cal.add(Calendar.DATE, 1);
            ;
            Date date = cal.getTime();
            DateFormat dateFormat = new SimpleDateFormat(output_format);
            String val = dateFormat.format(date);
            return val;
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String addedDate(String recharged_on, String input_format, int days, String output_format) {
        Calendar input_date = parseDate(recharged_on, input_format);
        input_date.add(Calendar.DATE, days);
        String output_date = parseFromDate(input_date, output_format);
        return output_date;
    }

    public static String parseFromDate(Calendar input_date, String output_format) {
        Date date = input_date.getTime();
        DateFormat dateFormat = new SimpleDateFormat(output_format);
        String val = dateFormat.format(date);
        return val;
    }

    public static Calendar parseDate(String input, String input_format) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(input_format, Locale.ENGLISH);
        try {
            cal.setTime(sdf.parse(input));
            return cal;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Caldy create() {
        return new Caldy();
    }

    private Calendar stringToCalendar(String input, String inputFormat) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(inputFormat, Locale.ENGLISH);
        try {
            Date date = sdf.parse(input);
            if (date == null) {
                return null;
            }
            cal.setTime(date);
            return cal;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String calendarToString(Calendar calendar, String output_format) {
        return new SimpleDateFormat(output_format).format(calendar.getTime());
    }

    private Calendar now() {
        return Calendar.getInstance();
    }

    private long difDays(Calendar left, Calendar right) {
        /*long diff = left.getTimeInMillis() - right.getTimeInMillis();
        long days = diff / (24 * 60 * 60 * 1000);
        return days;*/

        int y1 = left.get(Calendar.YEAR);
        int m1 = left.get(Calendar.MONTH) + 1;
        int d1 = left.get(Calendar.DATE);

        int y2 = right.get(Calendar.YEAR);
        int m2 = right.get(Calendar.MONTH) + 1;
        int d2 = right.get(Calendar.DATE);

        String date1 = String.format("%d-%02d-%02d", y1, m1, d1);
        String date2 = String.format("%d-%02d-%02d", y2, m2, d2);

        long diff = stringToCalendar(date1, "yyyy-MM-dd").getTimeInMillis() - stringToCalendar(date2, "yyyy-MM-dd").getTimeInMillis();
        long days = diff / (24 * 60 * 60 * 1000);
        return days;
    }

    private long absDifDays(Calendar left, Calendar right) {
        return Math.abs(difDays(left, right));
    }

    private String readableDayString(int val) {
        switch (val) {
            case -1:
                return "Yesterday";
            case 0:
                return "Today";
            case 1:
                return "Tomorrow";
            default:
                return "";
        }
    }

    private String makeCsv(String input) {
        return input.replaceAll("[-_:\\s]", ",").replaceAll(",,", ",");
    }
    //////////////////////////////////////////////////////////////////////
    /**inputs**/
    public Caldy csv() {
        this.csv = true;
        return this;
    }

    public Caldy input(Calendar input) {
        this.inputCalendar = input;
        return this;
    }

    public Caldy input(String input) {
        if (csv) {
            input = makeCsv(input);
            csv = false;
        }
        this.input = input;
        return this;
    }

    public Caldy inputFormat(String format) {
        if (csv) {
            format = makeCsv(format);
            csv = false;
        }
        this.inputFormat = format;
        return this;
    }

    public Caldy outputFormat(String format) {
        if (csv) {
            format = makeCsv(format);
            csv = false;
        }
        this.output_format = format;
        return this;
    }

    public Caldy reference(String reference) {
        if (csv) {
            reference = makeCsv(reference);
            csv = false;
        }
        this.reference = reference;
        return this;
    }

    public Caldy referenceFormat(String format) {
        if (csv) {
            format = makeCsv(format);
            csv = false;
        }
        this.reference_format = format;
        return this;
    }

    public Caldy left(String left) {
        if (csv) {
            left = makeCsv(left);
            csv = false;
        }
        this.left = left;
        return this;
    }

    public Caldy left(Calendar left) {
        this.left_calendar = left;
        return this;
    }

    public Caldy right(String right) {
        if (csv) {
            right = makeCsv(right);
            csv = false;
        }
        this.right = right;
        return this;
    }

    public Caldy readable(boolean readable) {
        this.readable = readable;
        return this;
    }

    public Caldy right(Calendar right) {
        this.right_calendar = right;
        return this;
    }
    //////////////////////////////////////////////
    private void composeLeft() {
        if (left_calendar != null) {
            return;
        }
        if (!left.isEmpty() && !leftFormat.isEmpty()) {
            left_calendar = stringToCalendar(left, leftFormat);
            return;
        }
    }

    private void composeRight() {
        if (right_calendar != null) {
            return;
        }
        if (!right.isEmpty() && !rightFormat.isEmpty()) {
            right_calendar = stringToCalendar(right, rightFormat);
            return;
        }
        right_calendar = now();
    }

    private void composeInput()    {
        if (inputCalendar == null) {
            inputCalendar = stringToCalendar(input, inputFormat);
            inputFormat = "";
            input = "";
        }
    }

    private void composeReference()    {
        if (referenceCalendar == null) {
            if (!reference.isEmpty()) {
                if (!reference_format.isEmpty()) {
                    referenceCalendar = stringToCalendar(reference, reference_format);
                } else {
                    referenceCalendar = now();
                }
            } else {
                referenceCalendar = now();
            }
        }
    }
    //////////////////////////////////////////////////////////
    public Caldy nextDate() {/**expect input**/
        composeInput();
        inputCalendar.add(Calendar.DATE, 1);
        result = inputCalendar;
        inputCalendar = null;
        return this;
    }

    public Caldy convert() {/**expect input**/
        composeInput();
        result = inputCalendar;
        inputCalendar = null;
        return this;
    }

    public Caldy done() {/**expect input**/
        composeInput();
        result = inputCalendar;
        inputCalendar = null;
        return this;
    }

    public Caldy compareMinute() {/**expect left and right(default is 'now')**/
        composeLeft();
        composeRight();

        long diff = left_calendar.getTimeInMillis() - right_calendar.getTimeInMillis();
        resultLong = diff / (60 * 1000);
        return this;
    }
    ///////////////////////////////////////////////////////////
    public long getLong() {
        long ret = resultLong;
        reset();
        return ret;
    }

    public String getString() {
        if (result == null) {
            return "";
        }
        if (!readable) {
            return calendarToString(result, output_format);
        }
        composeReference();
        int dif = (int) difDays(result, referenceCalendar);
        int abs_dif = Math.abs(dif);
        if (abs_dif < 2) {
            return readableDayString(dif);
        } else {
            return calendarToString(result, output_format);
        }
    }

    public Calendar get()    {
        Calendar ret = result;
        reset();
        return ret;
    }
    ////////////////

}
