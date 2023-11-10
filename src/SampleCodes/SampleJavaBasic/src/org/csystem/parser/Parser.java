package org.csystem.parser;

import org.csystem.parser.source.ISource;

public abstract class Parser implements IParser {
    protected ISource source;

    protected Parser(ISource source)
    {
        this.source = source;
    }
}
