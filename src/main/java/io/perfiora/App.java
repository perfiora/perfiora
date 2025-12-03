package io.perfiora;

import io.perfiora.base.PerfioraCommand;
import io.perfiora.command.InfoCommand;
import io.perfiora.util.Version;

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
        
        // Handle version flag
        if (args.length > 0 && (args[0].equals("--version") || args[0].equals("-v"))) {
            System.out.println("Perfiora " + Version.getVersion());
            return;
        }
        
        log.info("Perfiora " + Version.getVersion());
        if (args.length == 0) {
            log.info("Usage: perfiora <command> <args>");
            log.info("       perfiora --version");
        } else {
            log.info("Command: " + args[0]);

            PerfioraCommand command = null;
            if (args[0].equals("info")) {
                if (args.length < 2) {
                    log.warning("Error: info command requires a connection string");
                    log.info("Usage: perfiora info <jdbc-connection-string>");
                    return;
                }
                command = new InfoCommand(args[1]);
                command.execute();
            } else {
                log.warning("Unknown command: " + args[0]);
                log.info("Usage: perfiora <command> <args>");
                log.info("Available commands: info");
            }
        }
    }
}
