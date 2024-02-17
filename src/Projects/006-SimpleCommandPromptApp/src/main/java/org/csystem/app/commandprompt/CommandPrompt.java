package org.csystem.app.commandprompt;


import com.karandev.io.util.console.Console;

import java.util.ArrayList;

public class CommandPrompt {
    private static final ArrayList<CommandInfo> COMMANDS = new ArrayList<>();

    private static void fillCommands(CommandPrompt commandPrompt)
    {
        COMMANDS.add(new CommandInfo("length", CommandPrompt::lengthCallback));
        COMMANDS.add(new CommandInfo("reverse", CommandPrompt::reverseCallback));
        COMMANDS.add(new CommandInfo("upper", CommandPrompt::upperCallback));
        COMMANDS.add(new CommandInfo("lower", CommandPrompt::lowerCallback));
        COMMANDS.add(new CommandInfo("chpr", commandPrompt::changePromptCallback));
        COMMANDS.add(new CommandInfo("quit", CommandPrompt::quitCallback));
    }

    private String m_prompt;

    private static String [] parseCommandStr(String cmdStr)
    {
        return cmdStr.split("[ \t]+");
    }

    private void parseCommand(String [] cmdInfo)
    {
        var index = COMMANDS.indexOf(new CommandInfo(cmdInfo[0]));

        if (index != -1)
            COMMANDS.get(index).commandConsumer.accept(cmdInfo);
        else
            Console.Error.writeLine("Invalid command!...");
    }

    private static void lengthCallback(String [] cmdInfo)
    {
        if (cmdInfo.length <= 2)
            Console.writeLine(cmdInfo[1].length());
        else
            Console.writeLine("Invalid arguments for length command!...");
    }

    private static void reverseCallback(String [] cmdInfo)
    {
        if (cmdInfo.length <= 2)
            Console.writeLine(new StringBuilder(cmdInfo[1]).reverse());
        else
            Console.writeLine("Invalid arguments for reverse command!...");
    }

    private static void upperCallback(String [] cmdInfo)
    {
        if (cmdInfo.length <= 2)
            Console.writeLine(cmdInfo[1].toUpperCase());
        else
            Console.writeLine("Invalid arguments for upper command!...");
    }

    private static void lowerCallback(String [] cmdInfo)
    {
        if (cmdInfo.length <= 2)
            Console.writeLine(cmdInfo[1].toLowerCase());
        else
            Console.writeLine("Invalid arguments for lower command!...");
    }

    private static void quitCallback(String [] cmdInfo)
    {
        Console.writeLine("C and System Programmers Association");
        Console.writeLine("Thanks");
        System.exit(0);
    }

    private void changePromptCallback(String [] cmdInfo)
    {
        if (cmdInfo.length <= 2)
            m_prompt = cmdInfo[1];
        else
            Console.writeLine("Invalid arguments for chpr command!...");
    }

    public CommandPrompt(String prompt)
    {
        m_prompt = prompt;
    }

    public void run()
    {
        fillCommands(this);
        while (true) {
            var cmdStr = Console.read(m_prompt + ">").strip();

            parseCommand(parseCommandStr(cmdStr));
        }
    }
}
