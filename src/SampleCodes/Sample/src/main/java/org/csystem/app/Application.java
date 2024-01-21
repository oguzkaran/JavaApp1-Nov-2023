/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki demo örneği inceleyiniz
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

class Application {
    public static void run(String[] args)
    {
        var xml = new XML.Builder()
                .setTag("maven")
                .setValue("test")
                .build();

        //...
    }
}

class XML {
    private String m_tag;
    private String m_attribute;
    private String m_attrValue;
    private String m_value;
    //...

    public static class Builder {
        private final XML m_xml;

        public Builder()
        {
            m_xml = new XML("", "", "", "");
        }

        public Builder setTag(String tag)
        {
            m_xml.m_tag = tag;

            return this;
        }

        public Builder setAttribute(String attribute)
        {
            m_xml.m_attribute = attribute;

            return this;
        }

        public Builder setAttributeValue(String value)
        {
            m_xml.m_attrValue = value;

            return this;
        }

        public Builder setValue(String value)
        {
            m_xml.m_value = value;

            return this;
        }

        public XML build()
        {
            return m_xml;
        }
    }

    private XML(String tag, String attribute, String attValue, String value)
    {
        m_tag = tag;
        m_value = value;
        m_attribute = attribute;
        m_attrValue = attValue;
    }

    //...
}
