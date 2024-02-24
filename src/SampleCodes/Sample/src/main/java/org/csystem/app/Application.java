/*----------------------------------------------------------------------------------------------------------------------

----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import com.karandev.io.util.console.CommandPrompt;

class Application {
    public static void run(String[] args)
    {
        CommandPrompt.createBuilder()
                .registerObject(new CommandsInfo())
                .setPrompt("csd")
                .setWrongNumberOfArgumentsErrorMessage("Invalid usage")
                .create().run();
    }
}

