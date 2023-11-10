package org.csystem.app.commandprompt;

public class CommandPromptApp {
    public static void run()
    {
        CommandPrompt commandPrompt = new CommandPrompt("CSD");

        commandPrompt.run();
    }
    public static void main(String[] args)
    {
        run();
    }
}
