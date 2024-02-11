/*----------------------------------------------------------------------------------------------------------------------
    Java'da test işlemlerinde kullanılabilen "assert statement" bulunmaktadır. assert deyiminin genel biçimi şu şekildedir:
        assert <boolean türden ifade> [: <void olmayan bir ifade>]
    Akış assert deyimine geldiğinde boolean türden ifade hesaplanır, eğer true ise akış devam eder, false ise AssertinError
    türünden exception fırlatılır ve tipik olarak akış sonlanır. Eğer optional olan void olmayan ifade varsa o ifadenin
    değeri de stderr'ye basılır

    assert kullanımı için JVM'e -ea veya -enableAssertions seçeneği verilmelidir. Eğer bu seçeneklerden biri verilmezse
    assert deyimleri gözmezden gelinir. assert deyimleri derleme sırasında var olduğundan seçenekler verilmemiş olsa bile
    byte code içerisinde bulunurlar. Öyleyse programcının, ürün aşamasında assert deyimlerinin byte kodda olmayacağı
    biçimde yazması gerekir. Şüphesiz assert deyimlerini yorum satırlarına almak ya da koddan kaldırmak profesyonel bir
    çözüm değildir. Bu durumda programcı sabit görevi göre bir flag veri elemanı ile bu kodları devreye sokabilir ya da
    derleme aşamasında arakoda eklenmemesini sağlayabilir. UtilLib içerisindeki numberToText3DigitsTR private metodunda
    bu yöntem kullanılmıştır. Anımsanacağı gibi derleyici akışın bir noktaya kesin gelmeyeceğini anlarsa ya error verir
    ya da ilgili kodu byte code'a eklemez. Ayrıca sabit ifadesi (constant expression) ile değer verilmiş final bir değişken
    arakodda sabit görevi görür. Yani örneğin onun için bellekte (heap ya da stack) bir ayrılması gerekmez. Örnekte
    DEBUG sabitine false değeri verildiğinde akış hiç bir şekilde assert deyimlerine gelemeyeceğinden derleyici assert
    deyimlerini byte code'a eklemez. Bu durumda test işleminden sonra DEBUG false değerine çekilerek nihayi ürün elde
    edilir. assert deyimleri özellikle private metotların testlerinde tercih edilebilir.
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

class Application {
    public static void run(String[] args)
    {

    }
}