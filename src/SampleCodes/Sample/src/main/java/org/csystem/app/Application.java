package org.csystem.app;

import com.karandev.io.util.console.Console;

import java.io.Closeable;
import java.io.Serializable;

class Application {
    public static void run(String[] args)
    {
        try (var dev1 = new Device("Rain sensor", "192.168.124", 3030);
             var dev2 = new Device("Rain sensor", "192.168.124");
             var dev3 = new Device("Rain sensor")) {

            Console.writeLine(dev1);
            Console.writeLine(dev2);
            Console.writeLine(dev3);
        }
    }
}

record Device(String name, String host, int port) implements Closeable, Serializable {
    Device {
        if (port <= 0 || port > 65535)
            throw new IllegalArgumentException("port number must in (0, 65536)");
    }

    public Device(String name, String host)
    {
        this(name, host, 6767);
    }

    public Device(String name)
    {
        this(name, "localhost");
    }

    @Override
    public String toString()
    {
        return "%s, %s, %d".formatted(name, host, port);
    }

    @Override
    public void close()
    {
        System.out.println("Close");
    }
}
