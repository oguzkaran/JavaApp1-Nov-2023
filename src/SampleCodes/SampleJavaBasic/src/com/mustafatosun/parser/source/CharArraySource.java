package com.mustafatosun.parser.source;

import org.csystem.parser.source.ISource;

public class CharArraySource implements ISource {
    private final char[] m_chars;
    private int m_index;

    public CharArraySource(String str)
    {
        m_chars = str.toCharArray();
    }

    public int nextChar()
    {
        return m_chars.length == m_index ? -1 : m_chars[m_index++];
    }

}
