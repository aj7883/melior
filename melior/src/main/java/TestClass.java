import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;

public class TestClass {
    public static void main(String[] args) {

        String timeSlot = "9:30 Available";
        String substring = timeSlot.substring(0, timeSlot.lastIndexOf(" "));

        System.out.println(substring);
    }
}
