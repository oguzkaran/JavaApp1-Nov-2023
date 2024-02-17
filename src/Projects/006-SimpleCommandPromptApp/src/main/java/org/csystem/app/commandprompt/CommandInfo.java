package org.csystem.app.commandprompt;

public class CommandInfo {
    public String cmdText;
    public ICommandConsumer commandConsumer;

    public CommandInfo(String cmdText)
    {
        this.cmdText = cmdText;
    }

    public CommandInfo(String cmdText, ICommandConsumer commandConsumer)
    {
        this.cmdText = cmdText;
        this.commandConsumer = commandConsumer;
    }

    //...

    @Override
    public boolean equals(Object other)
    {
        return other instanceof CommandInfo ci && ci.cmdText.equals(cmdText);
    }
}
