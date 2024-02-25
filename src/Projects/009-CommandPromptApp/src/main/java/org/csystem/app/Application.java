package org.csystem.app;

import com.karandev.io.util.console.CommandPrompt;
import org.csystem.app.commandprompt.CommandsInfo;

class Application {
    public static void run(String[] args)
    {
        var ci = new CommandsInfo();
        var cp = CommandPrompt.createBuilder()
                .registerObject(ci)
                .setPrompt("csd")
                .setSuffix(">")
                .create();

        ci.setCommandPrompt(cp);
        cp.run();
    }
}
