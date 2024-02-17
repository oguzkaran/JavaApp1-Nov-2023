/*----------------------------------------------------------------------------------------------------------------------
    Deprectaed annotation'ı ile deprecated olan bir sentaktik eleman işaretlenebilir. Bu annotation bir RUNTIME
    annotation'ı olmasına karşın build aşamasında derleyiciler ve kod analizi araçları gibi araçlar tarafından da bakılır.
    Genel olarak derleyiciler ve static kod analizi araçları Deprecated işaretlenmiş sentaktik elemanlar için uyarı
    mesajları verirler. Programcı bir deprecated eleman için bu annotation'ı kesinlikle kullanmalı ve dökumante etmelidir.
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

class Application {
    public static void run(String[] args)
    {

    }
}

@YourAnnotation()
class Sample {
    @TheirAnnotation
    private int m_value;

    @YourAnnotation
    @MyAnnotation(value = 10, message = "This is a test")
    @OurAnnotation({"xxxx", "yyy"})
    public void foo(@TheirAnnotation int a)
    {
        //...
    }

    @YourAnnotation(45)
    @MyAnnotation(20)
    @OurAnnotation("this is another test")

    public void bar()
    {
        //...
    }
}

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.LOCAL_VARIABLE, ElementType.PARAMETER, ElementType.FIELD})
@interface TheirAnnotation {

}

@Retention(RetentionPolicy.CLASS)
@interface OurAnnotation {
    String [] value();
}

@Retention(RetentionPolicy.SOURCE)
@interface YourAnnotation {
    int value() default 0;
}

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    String message() default  "";
    int value();
}