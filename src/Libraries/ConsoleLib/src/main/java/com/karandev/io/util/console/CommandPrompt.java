/*----------------------------------------------------------------------
	FILE        : CommandPrompt.java
	AUTHOR      : Oğuz Karan
	LAST UPDATE : 24.02.2024

	General CommandPrompt class of REPL Framework

	Copyleft (c) 1993 by C and System Programmers Association (CSD)
	All Rights Free
-----------------------------------------------------------------------*/
package com.karandev.io.util.console;

import com.karandev.io.util.console.annotation.Command;
import com.karandev.io.util.console.annotation.ErrorCommand;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;

public final class CommandPrompt {
    private Object m_regObject;
    private final ArrayList<CommandInfo> m_commandInfo = new ArrayList<>();
    private Method m_errorCommandMethod;
    private String m_prompt = "krn";
    private String m_suffix = "$";
    private String m_paramStringErrorMessage = "Message parameters must be String!...";
    private String m_wrongNumberOfArgumentsMessage = "Wrong number of arguments!...";
    private String m_invalidCommand = "Invalid command!...";

    private static class CommandInfo {
        String commandText;
        Method method;
        int argCount;

        CommandInfo(String commandText, Method method, int argCount)
        {
            this.commandText = commandText;
            this.method = method;
            this.argCount = argCount;
        }
    }

    public static class Builder {
        private final CommandPrompt m_commandPrompt = new CommandPrompt();

        private Builder()
        {
        }

        public Builder registerObject(Object regObject)
        {
            m_commandPrompt.registerObject(regObject);

            return this;
        }

        public Builder setPrompt(String prompt)
        {
            m_commandPrompt.m_prompt = prompt;
            return this;
        }

        public Builder setSuffix(String suffix)
        {
            m_commandPrompt.m_suffix = suffix;
            return this;
        }

        public Builder setParamStringErrorMessage(String message)
        {
            m_commandPrompt.m_paramStringErrorMessage = message;
            return this;
        }

        public Builder setWrongNumberOfArgumentsErrorMessage(String message)
        {
            m_commandPrompt.m_wrongNumberOfArgumentsMessage = message;
            return this;
        }

        public Builder setInvalidCommandErrorMessage(String message)
        {
            m_commandPrompt.m_invalidCommand = message;
            return this;
        }

        public CommandPrompt create()
        {
            return m_commandPrompt;
        }
    }

    private boolean areAllString(Parameter [] parameters)
    {
        for (var param : parameters)
            if (param.getParameterizedType() != String.class)
                return false;

        return true;
    }

    private void runCommands(String [] cmdInfo)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void register(Command [] commands, Method method)
    {
        for (var command : commands) {
            var value = command.value();
            var commandText = value.isBlank() ? method.getName() : value;
            var parameters = method.getParameters();

            if (!areAllString(parameters))
                throw new IllegalArgumentException(m_paramStringErrorMessage);

            m_commandInfo.add(new CommandInfo(commandText, method, parameters.length));
        }
    }

    private void registerObject(Object regObject)
    {
        m_regObject = regObject;
        var clsRegObject = m_regObject.getClass();

        var methods = clsRegObject.getDeclaredMethods();

        for (var method : methods) {
            var commands = method.getDeclaredAnnotationsByType(Command.class);

            if (commands.length == 0) {
                if (m_errorCommandMethod == null && method.getDeclaredAnnotation(ErrorCommand.class) != null
                        && method.getTypeParameters().length == 0)
                    m_errorCommandMethod = method;
                continue;
            }
            register(commands, method);
        }
    }

    private CommandPrompt()
    {
    }

    public static Builder createBuilder()
    {
        return new Builder();
    }

    public void run()
    {
        try {
            while (true) {
                var cmd = Console.read(m_prompt + m_suffix);

                if (cmd.isBlank())
                    continue;

                runCommands(cmd.split("[ \t]+"));
            }
        }
        catch (Throwable ex) {
            throw new IllegalArgumentException(ex.getMessage(), ex);
        }
    }
}
