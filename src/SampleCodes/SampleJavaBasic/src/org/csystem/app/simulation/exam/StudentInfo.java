package org.csystem.app.simulation.exam;

public class StudentInfo {
    private String m_name;
    private String m_lectureName;
    private int m_midtermGrade;
    private int m_finalGrade;

    //...
    public String getName()
    {
        return m_name;
    }

    public void setName(String name)
    {
        m_name = name;
    }

    public String getLectureName()
    {
        return m_lectureName;
    }

    public void setLectureName(String lectureName)
    {
        m_lectureName = lectureName;
    }

    public int getMidtermGrade()
    {
        return m_midtermGrade;
    }

    public void setMidtermGrade(int midtermGrade)
    {
        m_midtermGrade = midtermGrade;
    }

    public int getFinalGrade()
    {
        return m_finalGrade;
    }

    public void setFinalGrade(int finalGrade)
    {
        m_finalGrade = finalGrade;
    }

    public double getGrade()
    {
        return m_midtermGrade * 0.4 + m_finalGrade * 0.6;
    }

    public boolean success()
    {
        return getGrade() >= 50;
    }

    public String toString()
    {
        return String.format("%s, %d, %d, %.2f, %s", m_name, m_midtermGrade, m_finalGrade, getGrade(), success() ? "Success" : "Fail");
    }
}
