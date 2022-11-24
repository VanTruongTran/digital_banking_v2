/**
 * purpose: xử lý nghiệp vụ ngày tháng
 * date created: 29/9/2022
 * author: Van Truong
 * version: 1.0.0
 */

package digitalbanking.io;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeCreator {
    private static SimpleDateFormat sdf;

    //phương thức trả về ngày giờ hiện tại
    public static String getDateTime() {
        sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Date today = Calendar.getInstance().getTime();
        return sdf.format(today);
    }
}
