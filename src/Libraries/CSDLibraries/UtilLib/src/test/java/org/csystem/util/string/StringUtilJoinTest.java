package stringUtil;

import org.csystem.util.string.StringUtil;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;
import java.util.List;

@Ignore("Fatih Karabulut")
@RunWith(Parameterized.class)
public class StringUtilJoinTest {
    public Data dataInfo;
    public static class Data{
        String  expected;
        String str;
        String[] actual;


        public Data(String expected, String [] actual,String str) {
            this.expected = expected;
            this.actual = actual;
            this.str = str;
        }

    }

    public StringUtilJoinTest(Data dataList) {
        dataInfo = dataList;
    }

    @Parameterized.Parameters
    public static Collection<Data> dataList()
    {
        return List.of(new Data("ankara-izmir-istanbul",new String[]{"ankara","izmir","istanbul"},"-"),
                new Data("zonguldak*giresun*trabzon",new String[]{"zonguldak","giresun","trabzon"},"*"),
                new Data("batman/diyarbakır/siirt",new String[]{"batman","diyarbakır","siirt"},"/"));
    }

    @Test
    public void test()
    {
        Assert.assertEquals(dataInfo.expected,StringUtil.join(dataInfo.actual,dataInfo.str));
    }
}
