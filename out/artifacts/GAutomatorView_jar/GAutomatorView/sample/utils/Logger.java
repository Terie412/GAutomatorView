package sample.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class Logger {
    private static java.util.logging.Logger log;

    public static java.util.logging.Logger getLogger() throws IOException {
        if (log == null) {
            log = java.util.logging.Logger.getLogger("SimpleJava");
            log.setLevel(Level.ALL);
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new SimpleFormatter(){
                @Override
                public String format(LogRecord record) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    String date = simpleDateFormat.format(new Date());
                    return "[" + date + "] " + "[" + record.getLevel() + "] " + "[" + record.getClass().getName() + "]" + "\t:" + record.getMessage() + "\n";
                }
            });
            log.addHandler(consoleHandler);
            log.setUseParentHandlers(false);
        }
        return log;
    }
}