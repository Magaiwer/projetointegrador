package projetointegrador.Util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Formatter {

    public static final String FORMAT_PT_BR_WITH_TIME = "dd/MM/yyyy HH:mm:ss";

    public static String getDateTextFormated(LocalDateTime dateTime, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return dateTime.format(formatter);
    }

}
