package org.csystem.app;

import com.karandev.io.util.console.annotation.Command;
import com.karandev.io.util.console.annotation.Commands;
import com.karandev.io.util.console.annotation.ErrorCommand;

public class CommandsInfo {
    //...
    @Command("upp")
    @Command
    private void upper(String str)
    {
        //...
    }

    @Command("low")
    @Command
    private void lower(String str)
    {
        //...
    }

    @Commands({@Command("quit"), @Command, @Command("leave")})
    private void exit(String str)
    {
        //...
    }

    @ErrorCommand
    public void error()
    {
        //...
    }
}
