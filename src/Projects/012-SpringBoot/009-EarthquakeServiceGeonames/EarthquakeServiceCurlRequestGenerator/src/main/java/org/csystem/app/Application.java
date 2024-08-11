package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    private static final String BASE_URL = "http://%s:%d";
    private static final String URL = "curl \"%s/api/earthquake/json?west=%f&south=%f&east=%f6&north=%f\"";

    private static void lineCallback(String template, String line, BufferedWriter bw)
    {
        String [] info = line.split("[,]");

        if (info.length != 5)
            throw new IllegalArgumentException("Invalid file format");

        try {
            bw.write(URL.formatted(template, Double.parseDouble(info[1]), Double.parseDouble(info[2]),
                    Double.parseDouble(info[3]), Double.parseDouble(info[4])));
            bw.write("\r\n");
        }
        catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    private static void generateRequests(String host, int port, Path csvPath, Path destPath) throws IOException
    {
        try (var br = Files.newBufferedReader(csvPath, StandardCharsets.UTF_8);
             var bw = Files.newBufferedWriter(destPath, StandardCharsets.UTF_8)) {

            if (br.readLine() == null)
                throw new IOException("Invalid file format");

            br.lines().forEach(s -> lineCallback(BASE_URL.formatted(host, port), s, bw));
        }
    }

    public static void run(String[] args)
    {
        checkLengthEquals(4, args.length, "usage: java -jar CurlRequestGenerator-1.0.0.jar <host> <port> <csv file path> <dest file path>");
        try {
            var host = args[0];
            var port = Integer.parseInt(args[1]);
            var csvPath = Path.of(args[2]);
            var destPath = Path.of(args[3]);

            generateRequests(host, port, csvPath, destPath);
        }
        catch (NumberFormatException ex) {
            Console.Error.writeLine("Invalid value:%s", ex.getMessage());
        }
        catch (FileNotFoundException ex) {
            Console.Error.writeLine("File not found:%s", ex.getMessage());
        }
        catch (InvalidPathException ex) {
            Console.Error.writeLine("Invalid path:%s", ex.getInput());
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO Problem occurred:%s", ex.getMessage());
        }
    }
}
