/*----------------------------------------------------------------------------------------------------------------------
    Aslında inner class içerisinde ait olduğu sınıf türünden bir referans veri elemanı bulunur ve nesne yaratılması
    sırasında bu referansa ilgili adres yani inner class'ın yaratılmasında kullanılan ait olduğu sınıf türünden nesnenin
    adresi geçilir. Bu durum tipik olarak inner class'ın her ctor'una +1 tane parametre ekleyerek yapılabilir. O
    parametrenin türü ait olduğu sınıf türünden referanstır. Aşağıda A sınıf bildiriminin ve nesne yaratılmasının
    yaklaşık karşılığı gösterilmiştir:
    Bildirimin aşağı seviyedeki yaklaşık karşılığı:

    class A {
        //...
        public void foo()
        {
            Console.writeLine("A.foo");
        }

        public static class B {
            private final A m_a;
            //...
            public B(A a) //Default ctor'un yaklaşık karşılığı
            {
                m_a = a;
            }

            public void foo()
            {
                Console.writeLine("B.foo");
                m_a.foo();
            }
        }
    }

    Nesne yaratılmasının aşağı seviyedeki yaklaşık karşılığı:
    var a = new A();
    var b = new A.B(a);
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import org.csystem.util.console.Console;

class Application {
    public static void run(String[] args)
    {
        while (true) {
            var a = new A();

            //...
            a.foo();
            //...
        }
    }
}

class A {
    private B m_b;
    //...
    public void foo()
    {
        Console.writeLine("A.foo");
        m_b = new B();
    }

    public class B {
        //...
        public void foo()
        {
            Console.writeLine("B.foo");
        }
    }
}