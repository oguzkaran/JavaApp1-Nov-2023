package org.csystem.util.string;

import esen.alideniz.string.StringUtil;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

@Ignore
@RunWith(Parameterized.class)
public class StringUtilReverseTest {

    public DataInfo m_dataInfo;
    public StringUtilReverseTest(DataInfo dataInfo){
        m_dataInfo = dataInfo;
    }

    static class DataInfo{
        public String str;
        public String expected;
        DataInfo(String str, String expected) {
            this.str = str;
            this.expected = expected;

        }
    }
    @Parameterized.Parameters
    public static Collection<DataInfo> createData()
    {
        return List.of(
                new DataInfo("zonguldak" , "kadlugnoz"),
                new DataInfo("galatasaray" , "yarasatalag"),
                new DataInfo("Turkey" , "yekruT")

        );
    }

    @Test
    public void givenString_returnReverseString(){
        Assert.assertEquals( m_dataInfo.expected , StringUtil.reverse(m_dataInfo.str));
    }
}
