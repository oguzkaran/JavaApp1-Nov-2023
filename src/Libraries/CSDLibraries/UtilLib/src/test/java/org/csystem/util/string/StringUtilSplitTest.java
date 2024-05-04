package org.csystem.util.string;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

@Ignore
@RunWith(Parameterized.class)
public class StringUtilSplitTest {
    public DataInfo m_dataInfo;

    public StringUtilSplitTest(DataInfo dataInfo) {
        m_dataInfo = dataInfo;
    }

    static class DataInfo {
        public String s;
        public String separators;
        public boolean removeEmpties;
        public String[] expected;


        public DataInfo(String s, String separators, boolean removeEmpties, String[] expected) {
            this.s = s;
            this.separators = separators;
            this.expected = expected;
            this.removeEmpties = removeEmpties;
        }
    }

    @Parameterized.Parameters
    public static List<DataInfo> createData()
    {
        return List.of(
                new DataInfo("Hello, world! How are you?", ",!? ",true , new String[]{"Hello", "world", "How", "are", "you"}),
                new DataInfo("Are you kidding me ? "," ",true , new String[]{"Are", "you", "kidding", "me", "?"})

        );
    }
    @Test
    public void givenString_returnStringArray(){
        Assert.assertEquals(StringUtil.split(m_dataInfo.s,m_dataInfo.separators,m_dataInfo.removeEmpties) ,m_dataInfo.expected);
    }

}

