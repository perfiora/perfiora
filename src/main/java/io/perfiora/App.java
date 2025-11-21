package io.perfiora;

import io.perfiora.base.PerfioraCommand;
import io.perfiora.command.InfoCommand;

import java.util.logging.*;

/**
 * Perfiora main class.
 */
public class App {

    private static final Logger log = Logger.getLogger(App.class.getName());

    public static void configureLogging() {
        Logger rootLogger = Logger.getLogger("");

        for (Handler handler : rootLogger.getHandlers()) {
            rootLogger.removeHandler(handler);
        }

        ConsoleHandler consoleHandler = new ConsoleHandler();

        rootLogger.setLevel(Level.INFO);
        consoleHandler.setLevel(Level.INFO);

        consoleHandler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                return String.format(
                        "%1$tF %1$tT [%2$-4s] %3$s - %4$s%n",
                        record.getMillis(),
                        record.getLevel().getName(),
                        record.getLoggerName(),
                        formatMessage(record)
                );
            }
        });

        rootLogger.addHandler(consoleHandler);
    }

    public static void main(String[] args) {
        configureLogging();
        log.info("Perfiora");
        if (args.length == 0) {
            log.info("Usage: perfiora <command> <args>");
        } else {
            log.info("Command: " + args[0]);

            PerfioraCommand command = null;
            if (args[0].equals("info")) {
                command = new InfoCommand(args[1]);
                command.execute();
            }
        }
    }
}
