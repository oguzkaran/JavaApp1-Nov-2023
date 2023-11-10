package org.csystem.app.commandprompt;

import org.csystem.util.string.StringUtil;

import java.util.Scanner;

public class CommandPrompt {
    private static final String [] COMMANDS = {"length", "reverse", "upper", "lower", "change", "quit"};
    private String m_prompt;

    private static void doForLength(String [] commandInfo)
    {
        if (commandInfo.length != 2) {
            System.out.printf("%s command must have 1 argument%n", commandInfo[0]);
            return;
        }

        System.out.println(commandInfo[1].length());
    }

    private static void doForReverse(String [] commandInfo)
    {
        if (commandInfo.length != 2) {
            System.out.printf("%s command must have 1 argument%n", commandInfo[0]);
            return;
        }

        System.out.println(StringUtil.reverse(commandInfo[1]));
    }

    private static void doForUpper(String [] commandInfo)
    {
        if (commandInfo.length != 2) {
            System.out.printf("%s command must have 1 argument%n", commandInfo[0]);
            return;
        }

        System.out.println(commandInfo[1].toUpperCase());
    }

    private static void doForLower(String [] commandInfo)
    {
        if (commandInfo.length != 2) {
            System.out.printf("%s command must have 1 argument%n", commandInfo[0]);
            return;
        }

        System.out.println(commandInfo[1].toLowerCase());
    }

    private static void doForQuit(String [] commandInfo)
    {
        System.out.println("C and System Programmers Association");
        System.out.println("Thank you!...");
        System.exit(0);
    }

    private void doForChange(String [] commandInfo)
    {
        if (commandInfo.length != 2) {
            System.out.printf("%s command must have 1 argument%n", commandInfo[0]);
            return;
        }

        m_prompt = commandInfo[1];
    }

    private static String getCommand(String cmdText)
    {
        for (String cmd : COMMANDS)
            if (cmd.startsWith(cmdText))
                return cmd;

        return "";
    }

    private void doForCommand(String [] commandInfo)
    {
        switch (commandInfo[0]) {
            case "length" -> doForLength(commandInfo);
            case "reverse" -> doForReverse(commandInfo);
            case "upper" -> doForUpper(commandInfo);
            case "lower" -> doForLower(commandInfo);
            case "change" -> doForChange(commandInfo);
            case "quit" -> doForQuit(commandInfo);
        }
    }

    private void doCommand(String [] commandInfo)
    {
        String command = getCommand(commandInfo[0]);

        if (!command.isEmpty()) {
            commandInfo[0] = command;
            doForCommand(commandInfo);
        }
        else
            System.out.println("Invalid command!...");
    }

    private void parseCommand(String [] commandInfo)
    {
        if (commandInfo[0].length() > 2)
            doCommand(commandInfo);
        else
            System.out.println("Command must have at least 3(three) characters!...");
    }

    public CommandPrompt(String p)
    {
        m_prompt = p;
    }

    public String getPrompt()
    {
        return m_prompt;
    }

    public void setPrompt(String prompt)
    {
        this.m_prompt = prompt;
    }

    public void run()
    {
        Scanner kb = new Scanner(System.in);

        while (true) {
            System.out.print(m_prompt + ">");
            String command = kb.nextLine().strip();
            String [] commandInfo = command.split("[ \t]+");
            parseCommand(commandInfo);
        }
    }
}
