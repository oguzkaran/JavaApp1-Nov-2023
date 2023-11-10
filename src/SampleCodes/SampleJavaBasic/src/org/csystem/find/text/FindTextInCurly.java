package org.csystem.find.text;

public class FindTextInCurly {
    private String m_text;
    private String m_result;
    private boolean m_valid;

    public FindTextInCurly(String str)
    {
        m_text = str;
        m_result = m_text;
        m_valid = true;
    }

    public String getText()
    {
        return m_text;
    }

    public void setText(String text)
    {
        m_text = text;
    }

    public String getResult()
    {
        return m_result;
    }

    public boolean isValid()
    {
        return m_valid;
    }

    public void parse()
    {
        while (true) {
            int openIndex = m_result.indexOf('{');
            int closeIndex = m_result.lastIndexOf('}');

            if (openIndex == -1 && closeIndex != -1 || openIndex > closeIndex) {
                m_valid = false;
                break;
            }

            if (closeIndex == -1)
                break;

            m_result = m_result.substring(openIndex + 1, closeIndex);
        }
    }
}
