package org.csystem.app.camera.car;

import com.karandev.io.util.console.Console;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void run(String[] args)
    {
        checkLengthEquals(2, args.length, "Wrong number of arguments!...");

        try {
            var app = new CarCameraApp(MapType.values()[Integer.parseInt(args[1])]);

            app.run(Integer.parseInt(args[0]));
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid values!...");
        }
        catch (ArrayIndexOutOfBoundsException ignore) {
            Console.Error.writeLine("Order info value must be 1 (ordered) or zero (unordered)!...");
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred:%s", ex.getMessage());
        }
    }
}
