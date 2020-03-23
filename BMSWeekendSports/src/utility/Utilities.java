package utility;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

public class Utilities {

    public <T> T deSerialize(InputStream input, Class<T>  contentClass) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(input, contentClass);
    }

    public boolean isWeekend(int date, String month){
        // create a calendar
        Calendar cal = Calendar.getInstance();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        int monthInInt = getMonth(month);
        if(monthInInt < currentMonth){
            year++;
        }

        // set the year,month and day.
        cal.set(year, monthInInt, date);

        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            return true;
        }
        return false;
    }

    public int getMonth(String month){
        int monthInInt = Calendar.getInstance().get(Calendar.MONTH);
        switch (month) {
            case "Jan":
                monthInInt = 0;
                break;
            case "Feb":
                monthInInt = 1;
                break;
            case "Mar":
                monthInInt = 2;
                break;
            case "Apr":
                monthInInt = 3;
                break;
            case "May":
                monthInInt = 4;
                break;
            case "Jun":
                monthInInt = 5;
                break;
            case "Jul":
                monthInInt = 6;
                break;
            case "Aug":
                monthInInt = 7;
                break;
            case "Sep":
                monthInInt = 8;
                break;
            case "Oct":
                monthInInt = 9;
                break;
            case "Nov":
                monthInInt = 10;
                break;
            case "Dec":
                monthInInt = 11;
                break;
            default:
                break;
        }
        return monthInInt;
    }
}
