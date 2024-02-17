package org.csystem.app.commandprompt;

@FunctionalInterface
public interface ICommandConsumer {
    void accept(String [] cmdInfo);
}

