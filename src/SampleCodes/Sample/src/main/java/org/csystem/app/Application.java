/*----------------------------------------------------------------------------------------------------------------------
    Bir tamsayının belirli bir bitinin reset/clear edilmesi:
    Bir tamsayının belirli bir bitinin reset/clear edilmesi için sayı, ilgili biti sıfır olan diğer tüm bitleri 1 olan
    bir sayı (bitmask) ile & işlemine sokulur. Çünkü & işleminde sıfır yutan, 1 ise etkisiz elemandır. Aşağıdki örnekte
    sayının 4. biti sıfır yapılmıştır
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.bitwise.BitwiseUtil;

class Application {
    public static void run(String[] args)
    {
        int a = 0x00_00_00_51;  //0101_0001
        int mask = ~0x00_00_00_10; //1110_1111

        Console.writeLine(BitwiseUtil.toBinaryStr(a));
        Console.writeLine(BitwiseUtil.toBinaryStr(mask));

        a &= mask;
        Console.writeLine(BitwiseUtil.toBinaryStr(a));
    }
}

