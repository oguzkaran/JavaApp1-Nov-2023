package org.csystem.app.commandprompt;

import com.karandev.io.util.console.CommandPrompt;
import com.karandev.io.util.console.Console;
import com.karandev.io.util.console.annotation.Command;
import com.karandev.io.util.console.annotation.Commands;
import com.karandev.io.util.console.annotation.ErrorCommand;

public class CommandsInfo {
    private CommandPrompt m_commandPrompt;

    @Command("len")
    @Command
    private void length(String str)
    {
        Console.writeLine(str.length());
    }
    @Command("upp")
    @Command
    private void upper(String str)
    {
        Console.writeLine(str.toUpperCase());
    }

    @Command("low")
    @Command
    private void lower(String str)
    {
        Console.writeLine(str.toLowerCase());
    }

    @Command("rev")
    @Command
    private void reverse(String str)
    {
        Console.writeLine(new StringBuilder(str).reverse());
    }

    @Command("chpr")
    private void changePrompt(String prompt)
    {
        m_commandPrompt.setPrompt(prompt);
    }

    @Commands({@Command("quit"), @Command, @Command("leave")})
    private void quit()
    {
        Console.writeLine("C and System Programmers Association");
        Console.writeLine("Thanks");
        System.exit(0);
    }

    @ErrorCommand
    private void errorCommand()
    {
        Console.Error.writeLine("Command not found!...");
    }

    public void setCommandPrompt(CommandPrompt commandPrompt)
    {
        m_commandPrompt = commandPrompt;
    }
}
