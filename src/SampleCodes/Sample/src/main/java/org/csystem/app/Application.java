/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki örnekte, int -> int.. dönüşümü int -> long... dönüşümünden daha kaliteli olduğundan long parametreli foo
    çağrılır
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import org.csystem.util.console.Console;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class Application {
    public static void run(String[] args)
    {
        try (var br = Files.newBufferedReader(Path.of("countries.csv"))) {
            br.lines().skip(1).forEach(Console::writeLine);
        }
        catch (IOException ex) {
            Console.writeLine(ex.getMessage());
        }
    }
}
