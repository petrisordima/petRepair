package util;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    /**
     * sysdate logger, logs the date in the specified format. In this case
     * dd-MM_HH-mm-ss
     */
    public static String logTime() {

        Format formatter;
        Date date = new Date();
        formatter = new SimpleDateFormat("dd-MM_HH-mm-ss");
        return formatter.format(date);
    }
}
