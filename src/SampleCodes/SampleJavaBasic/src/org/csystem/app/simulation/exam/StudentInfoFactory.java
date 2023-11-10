package org.csystem.app.simulation.exam;

import java.util.Random;

import static org.csystem.util.string.StringUtil.generateRandomTextEN;

public class StudentInfoFactory {
    private Random m_random;

    public StudentInfoFactory()
    {
        m_random = new Random();
    }

    public StudentInfoFactory(Random random)
    {
        m_random = random;
    }

    public StudentInfo createStudentInfo(String lectureName)
    {
        StudentInfo studentInfo = new StudentInfo();

        studentInfo.setName(String.format("%s %s", generateRandomTextEN(m_random, m_random.nextInt(5, 11)),
                generateRandomTextEN(m_random, m_random.nextInt(5, 11))));
        studentInfo.setLectureName(lectureName);
        studentInfo.setMidtermGrade(m_random.nextInt(0, 101));
        studentInfo.setFinalGrade(m_random.nextInt(0, 101));

        return studentInfo;
    }
}
