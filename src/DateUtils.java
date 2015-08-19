import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Jekton on 8/16/2015.
 */
public class DateUtils {

    private static final int YEAR_START = 2015;
    private static final int DATE_MASK = 0x3FFF;

    private DateUtils() {}



    public static int getOrderDate() {
        Calendar calendar = new GregorianCalendar();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return assembleDate(year, month, day);
    }

    /**
     * 计算订单条形码中的 “日期”
     * @param year year
     * @param month month of year, start with 1
     * @param day day of month
     * @return 4-bit decimal date
     */
    private static int assembleDate(int year, int month, int day) {
        // uses DATE_MASK to ensure that the result will not exceed 14-bit
        return ((year - YEAR_START) << 9) | (month << 5) | day & DATE_MASK;
    }


    public static void main(String[] args) {
        System.out.println(getOrderDate());
    }
}
