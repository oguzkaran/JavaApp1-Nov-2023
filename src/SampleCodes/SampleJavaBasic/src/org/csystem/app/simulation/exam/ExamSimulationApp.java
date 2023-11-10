/*----------------------------------------------------------------------------------------------------------------------
	Sınıf Çalışması: Bir okulda merkezi olarak bir Matematik ara sınavı ve final sınavı yapılacak olsun. Şube sayısı ve
	her bir şubede kaç tane öğrenci olduğu bilgisini klavyeden alınız. Öğrencilerin notlarını [0, 100] aralığında rasgele
	belirleyiniz. Bu işlemlerden sonra her bir şubenin geçme notu ortalaması, geçenlerin sayısı, kalanların sayısı ile
	bu sınava katılan toplam öğrencilerin geçme notu ortalaması, toplam geçenlerin sayısı, toplam kalanların sayısı
	bilgilerini hesaplayan ve rapor olarak döken programı yazınız
	Açıklamalar:
		- Dizinin türünün bir sınıf yazarak belirleyiniz
		- Geçme notu arasınav * 0.4 + final sınavı * 0.6 olarak hesaplanacaktır. Geçme notu >= 50 olan olan öğrenciler
		dersi geçmiş olacaktır
		- Arasınav ve final notları int olarak tutulacaktır, geçme notu double olarak hesaplanacaktır
		- Klavyeden giriş için verilecek mesajları ve rapor çıktısını dilediğiniz gibi belirleyebilirsiniz
		- TODO: Raporda her şube için en başarılı öğrenci, en başarısız öğrenci ile okulun bu sınavda en başarılı öğrencisi, en başarısız öğrencisi de gösterilecektir

	Not: İleride daha iyisi yazılacaktır
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app.simulation.exam;

public class ExamSimulationApp {
    public static void run()
    {
        ExamSimulation examSimulation = new ExamSimulation("Matematik");

        examSimulation.run();
        examSimulation.printStudents();
        examSimulation.printReport();
    }

    public static void main(String[] args)
    {
        run();
    }
}
