package io.perfiora;

import io.perfiora.base.PerfioraCommand;
import io.perfiora.command.InfoCommand;

/**
 * Perfiora main class.
 */
public class App {

    public static void main(String[] args) {
        System.out.println("Perfiora");
        if (args.length == 0) {
            System.out.println("Usage: perfiora <command> <args>");
        } else {
            System.out.println("Command: " + args[0]);

            PerfioraCommand command = null;
            if (args[0].equals("info")) {
                command = new InfoCommand(args[1]);
                command.execute();
            }
        }
    }
}
