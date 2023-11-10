package com.alidenizesen.parser;

import org.csystem.parser.Parser;
import org.csystem.parser.source.ISource;

public class WhitespaceCounterParser extends Parser {
    private int m_count;
    public WhitespaceCounterParser(ISource source)
    {
        super(source);
    }

    public int getCount()
    {
        return m_count;
    }

    public void parse()
    {
        int ch;

        while ((ch = source.nextChar()) != -1)
            if (Character.isWhitespace(ch))
                ++m_count;
    }
}
