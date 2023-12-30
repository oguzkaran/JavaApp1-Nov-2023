/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki demo örneği inceleyiniz
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import org.csystem.util.array.ArrayUtil;

class Application {
    public static void run(String[] args)
    {
        int [] a = {1, 2, 3};
        int [] b = {4, 5, 6};
        ArrayUtil.print(Util.merge(a, b));
        ArrayUtil.print(Util.merge(a, 10, 30, 40));
    }
}

class Util {
    public static int [] merge(int[]a, int...b)
    {
        var result = new int[a.length + b.length];

        for (var i = 0; i < a.length; ++i)
            result[i] = a[i];

        for (var i = 0; i < b.length; ++i)
            result[a.length + i] = b[i];

        return result;
    }
}
